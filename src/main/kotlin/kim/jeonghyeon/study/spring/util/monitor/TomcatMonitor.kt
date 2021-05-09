package kim.jeonghyeon.study.spring.util.monitor

import org.springframework.util.CollectionUtils
import java.lang.management.ManagementFactory
import javax.management.ObjectName
import javax.management.Query

class TomcatMonitor {
    fun sample() {
        /**
         * attributes
         * - maxThreads
         * - currentThreadsBusy
         * - currentThreadCount
         * - maxConnections
         * - connectionCount
         */
        val mbs = ManagementFactory.getPlatformMBeanServer()
        var objectNames: Set<ObjectName> = mbs.queryNames(ObjectName("Tomcat:type=ThreadPool,*"), Query.geq(Query.attr("maxThreads"), Query.value(0)))
        objectNames = if (!CollectionUtils.isEmpty(objectNames)) objectNames else mbs.queryNames(ObjectName("Catalina:type=ThreadPool,*"), Query.geq(Query.attr("maxThreads"), Query.value(0)))
        mbs.getAttribute(objectNames.first(), "attribute-name")

        /**
         * Attributes
         * - requestCount
         * - errorCount
         * - processingTime
         */
        objectNames = mbs.queryNames(ObjectName("Tomcat:type=GlobalRequestProcessor,*"), null)
        objectNames = if (!CollectionUtils.isEmpty(objectNames)) objectNames else mbs.queryNames(ObjectName("Catalina:type=GlobalRequestProcessor,*"), null)

        val garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans()
        garbageCollectorMXBeans.first().collectionCount
        garbageCollectorMXBeans.first().collectionTime
        val memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans()
        memoryPoolMXBeans.first().usage

    }
}