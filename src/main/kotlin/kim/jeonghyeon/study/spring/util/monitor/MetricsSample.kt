package kim.jeonghyeon.study.spring.util.monitor

import com.codahale.metrics.ConsoleReporter
import com.codahale.metrics.Gauge
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.MetricRegistry.name
import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheckRegistry
import com.codahale.metrics.health.jvm.ThreadDeadlockHealthCheck
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit


/**
 * 이벤트의 Metric을 구하는 라이브러리
 * - Reporter가 Metric을 전송함.
 * - 모니터링시에, 모든 서비스에서 로그를 전송하고, 모니터링 서버에서 metric을 구하게되면, 로그 서버에 부하가 심해질 수 있기에,
 * - 모니터링 데이터를 전송하는 서비스에서 metric을 구해서, 모니터링 서버로 전송
 */
@Service
class MetricsSample {
    private val metrics = MetricRegistry()
    private val healthChecks = HealthCheckRegistry()

    /**
     * 이벤트 수 구하기
     * 평균, 1분,5분 15분 윈도우
     */
    fun markMeters(name: String) {
        metrics.meter(sampleName(name)).mark()
    }

    /**
     * 현재 값 구하기.
     * 예)현재 동시 처리되고 있는 thread 갯수등 구하기
     */
    fun setupGauges(gaugeName: String, value: () -> Int) {
        metrics.gauge(sampleName(gaugeName, "size")) { Gauge { value() } }
    }


    /**
     * Gauge와 비슷하나, 값을 증가, 감소 시키는 것을 라이브러리가 관리함.
     * 예) 특정, 혹은 각각의 api가 동시에 몇개가 처리되고 있는지 확인 가능.
     *      gauge의 경우, 현재 값을 바로 알 수 있는 경우이고, counter는 현재 값을 직접 구해야 하는 경우에 사용.
     *
     * consider how to handle exception(별도의 counter를 등록해서, 별도로 계산)
     */
    fun increaseCounter(counterName: String) {
        metrics.counter(sampleName(counterName)).inc()
    }

    fun decreaseCounter(counterName: String) {
        metrics.counter(sampleName(counterName)).dec()
    }

    /**
     *
     * 빈도를 나타내는 도수분포도 데이터를 구함.
     * mean, stddev, min, max, median, 75, 95, 98,99th 데이터
     * 언제까지의 데이터를 가지고 체크할 지, Reservoir로 결정. ExponentiallyDecayingReservoir를 default임.
     *  - SlidingTimeWindowArrayReservoir로 최근 고정된 기간으로 설정할 수 있음(최근 10초등)
     *
     *
     */
    fun updateHistogram(histogramName: String, value: Int) {
        metrics.histogram(sampleName(histogramName)).update(value)
    }

    /**
     * 처리시간의 도수분포도를 보여줌
     * 추가로, meter와 동일한 이벤트 횟수 정보를 보여줌.
     *
     * 언제까지의 데이터를 가지고 체크할 지, Reservoir로 결정. ExponentiallyDecayingReservoir를 default임.
     *  - SlidingTimeWindowArrayReservoir로 최근 고정된 기간으로 설정할 수 있음(최근 10초등)
     *
     * consider how to handle exception(별도의 timer를 등록해서, 별도로 계산)
     */
    fun runTimer(timerName: String, perform: () -> Unit) {
        metrics.timer(sampleName(timerName))
            .time().use {
                perform()
            }
    }


    fun registerHealthCheck(checkName: String, unhealthMessage: String, isHealthy: () -> Boolean) {
        healthChecks.register(sampleName(checkName), SampleHealthChecker(isHealthy, unhealthMessage))
//        healthChecks.register("threadDeadLock", ThreadDeadlockHealthCheck())
    }

    /**
     * TODO when is it checked?
     */
    fun startHealthCheck(): Boolean {
        val results: Map<String, HealthCheck.Result> = healthChecks.runHealthChecks()
        for ((key, value) in results) {
            if (value.isHealthy) {
                println("$key is healthy")
            } else {
                System.err.println(key + " is UNHEALTHY: " + value.message)
                val e = value.error
                e?.printStackTrace()
                return false
            }
        }
        return true
    }

    fun startReporter() {
        val reporter: ConsoleReporter = ConsoleReporter.forRegistry(metrics)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build()
        reporter.start(1, TimeUnit.SECONDS)
    }

    private fun sampleName(vararg names: String) = name(MetricsSample::class.java, *names)!!
}

class SampleHealthChecker(val isHealthy: () -> Boolean, private val unhealthMessage: String) : HealthCheck() {
    override fun check(): Result {
        return if (isHealthy()) {
            Result.healthy()
        } else Result.unhealthy(unhealthMessage)
    }
}