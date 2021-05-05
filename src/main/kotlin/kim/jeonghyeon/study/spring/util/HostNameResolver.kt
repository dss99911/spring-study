package kim.jeonghyeon.study.spring.util


import java.net.InetAddress
import java.net.UnknownHostException

fun hostname(): String = try {
    InetAddress.getLocalHost().hostName.split("\\.").toTypedArray()[0]
} catch (e: UnknownHostException) {
    "unknown"
}