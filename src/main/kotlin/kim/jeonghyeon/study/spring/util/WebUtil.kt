package kim.jeonghyeon.study.spring.util

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

object WebUtil {
    fun request() = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request

    fun cookies() = request().cookies
}