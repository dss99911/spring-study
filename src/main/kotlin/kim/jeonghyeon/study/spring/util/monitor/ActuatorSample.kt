package kim.jeonghyeon.study.spring.util.monitor

import io.micrometer.core.instrument.MeterRegistry
import kim.jeonghyeon.study.spring.util.hostname
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Actuator
 *  - https://docs.spring.io/spring-boot/docs/2.0.0.M4/reference/html/production-ready-metrics.html
 *
 * Actuator with Influx
 *  - https://micrometer.io/docs/registry/influx
 *  - Direct to Influx
 *  - Through Telegraf
 *
 * Available Metrics
 *  - https://programming.vip/docs/detailed-usage-of-spring-boot-actuator-monitoring.html
 *  - https://levelup.gitconnected.com/application-monitoring-using-spring-boot-actuators-part-1-dab8576f4db6
 *  - able to check metric on 127.0.0.1:8080/actuator 127.0.0.1:8080/actuator/metrics
 *  - process.cpu.usage : 0~1 percent (https://docs.oracle.com/javase/8/docs/jre/api/management/extension/com/sun/management/OperatingSystemMXBean.html#getProcessCpuLoad--)
 */
@Configuration
class ActuatorSample {
    @Bean
    fun metricsCommonTags(@Value("\${phase}") phase: String?): MeterRegistryCustomizer<MeterRegistry>? {
        return MeterRegistryCustomizer { registry: MeterRegistry ->
            registry.config().commonTags(
                "component", "sample",
                "phase", phase,
                "host", hostname()
            )
        }
    }
}
