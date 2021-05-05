package kim.jeonghyeon.study.spring.scheduler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * add @EnableScheduling on Application
 */
@Component
class ScheduleSample {
    @Scheduled(cron = "\${cron.mon-to-fri}")
    fun schedule() {

    }
}