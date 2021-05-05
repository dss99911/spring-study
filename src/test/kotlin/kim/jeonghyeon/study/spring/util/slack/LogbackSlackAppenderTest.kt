package kim.jeonghyeon.study.spring.util.slack

import kim.jeonghyeon.study.spring.exception.CustomException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

internal class LogbackSlackAppenderTest {
    val logger: org.slf4j.Logger = LoggerFactory.getLogger(LogbackSlackAppenderTest::class.java)
    @Test
    fun log() {
        logger.warn("test error")
        Thread.sleep(2000)//for async to work
    }

    @Test
    fun exception() {
        logger.error("test error", CustomException("test exception"))

        Thread.sleep(2000)//for async to work
    }
}