package kim.jeonghyeon.study.spring.util.monitor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.util.CollectionUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.lang.management.ManagementFactory
import javax.management.ObjectName
import javax.management.Query

@Controller
@RequestMapping("/metric")
class MetricController(
    @Autowired
    val metric: MetricsSample
) {
    companion object {
        val METRIC_NAME_REQUESTS = "requests"
        val METRIC_NAME_THREADS = "threads"
        val METRIC_NAME_RUNNING = "running"
        val METRIC_NAME_RANDOM = "random"
        val METRIC_NAME_RANDOM_DELAY = "randomdelay"
        val METRIC_NAME_DB_CHECK = "dbcheck"
    }

    init {
        metric.startReporter()
        metric.setupGauges(METRIC_NAME_THREADS) {
            //to detect client request thread pool, check which webserver to use
            //then check how to detect client request thread pool size
            Thread.activeCount()
        }
        metric.registerHealthCheck(METRIC_NAME_DB_CHECK, "not working") {
            //ping
            Math.random() > 0.5
        }

    }


    @GetMapping
    fun get(): String {
        metric.markMeters(METRIC_NAME_REQUESTS)
        return "index"
    }

    @GetMapping("/thread")
    @ResponseBody
    fun increaseThreadCount(): String {
        val count = Thread.activeCount()
        println("count:  $count, this $this")
        Thread.sleep(10000)
        return "Thread Count : $count"
    }

    @GetMapping("/running")
    @ResponseBody
    fun longRunningApi() {
        metric.increaseCounter(METRIC_NAME_RUNNING)
        Thread.sleep(10000)
        metric.decreaseCounter(METRIC_NAME_RUNNING)
    }

    @GetMapping("/random")
    @ResponseBody
    fun getRandom(): String {
        val randomValue = (Math.random() * 1000).toInt()
        metric.updateHistogram(METRIC_NAME_RANDOM, randomValue)
        return randomValue.toString()
    }


    @GetMapping("/random-delay")
    @ResponseBody
    fun getRandomDelay(): String {
        metric.runTimer(METRIC_NAME_RANDOM_DELAY) {
            val randomValue = (Math.random() * 10000).toLong()
            Thread.sleep(randomValue)
        }

        return "OK"
    }

    @GetMapping("/health-check")
    @ResponseBody
    fun healthCheck(): String {
        return metric.startHealthCheck().toString()
    }
}