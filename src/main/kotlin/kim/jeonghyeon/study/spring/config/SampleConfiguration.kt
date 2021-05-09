package kim.jeonghyeon.study.spring.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ThreadPoolExecutor

@Configuration
class SampleConfiguration {

    @Bean(destroyMethod = "shutdown")
    fun scheduler(): ScheduledExecutorService {
        return Executors.newScheduledThreadPool(1)
    }

    @Bean
    fun executor(@Value("\${sample.threadPool.size}") threadPoolSize: Int): ThreadPoolExecutor? {
        return Executors.newFixedThreadPool(threadPoolSize) as ThreadPoolExecutor
    }

    @Bean
    fun executorPostProcessor(): BeanPostProcessor {
        return object : BeanPostProcessor {

            @Throws(BeansException::class)
            override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
                return bean
            }

            @Throws(BeansException::class)
            override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
                //Find bean and process something
                return bean
            }
        }
    }
}

