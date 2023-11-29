package kr.bomiza.universe.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import kr.bomiza.universe.common.jackson.CustomObjectMapperModule
import kr.bomiza.universe.common.web.LoggingInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration(
    val objectMapper: ObjectMapper,
    val loggingInterceptor: LoggingInterceptor,
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(loggingInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns(
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/api-docs/**",
                "/actuator/health"
            )
            .order(0);
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val customObjectMapperModule = CustomObjectMapperModule()
        objectMapper.registerModule(customObjectMapperModule)
    }
}