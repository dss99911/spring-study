package kim.jeonghyeon.study.spring.util.slack

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.spi.ThrowableProxy
import ch.qos.logback.core.filter.Filter
import ch.qos.logback.core.spi.FilterReply
import kim.jeonghyeon.study.spring.exception.CustomException

class DenyExceptionLogFilter : Filter<ILoggingEvent>() {
    override fun decide(event: ILoggingEvent): FilterReply {
        val logging = (event.throwableProxy as? ThrowableProxy)
            ?.throwable
            ?.let { it as? CustomException }
            ?.logging
            ?: return FilterReply.NEUTRAL
        return if (!logging) {
            FilterReply.DENY
        } else FilterReply.NEUTRAL
    }
}