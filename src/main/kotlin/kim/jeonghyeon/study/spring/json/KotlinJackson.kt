package kim.jeonghyeon.study.spring.json

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

/**
 * you have to add dependency as the below on your project.
 * it is not working to add it on library only. whatever configuration to use(api, compile, implementation)
 * implementation "com.fasterxml.jackson.module:jackson-module-kotlin:2.9.7"
 */
class KotlinJackson {
    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilder
            = Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule())
}