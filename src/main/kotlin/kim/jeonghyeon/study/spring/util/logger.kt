package kim.jeonghyeon.study.spring.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = LoggerFactory.getLogger("Logger")


fun log(message: String?) {
    logger.debug(message)
}