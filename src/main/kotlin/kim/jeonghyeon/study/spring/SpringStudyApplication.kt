package kim.jeonghyeon.study.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SpringStudyApplication

fun main(args: Array<String>) {
    runApplication<SpringStudyApplication>(*args)
}
