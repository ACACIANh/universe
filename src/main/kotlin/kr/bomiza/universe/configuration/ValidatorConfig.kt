package kr.bomiza.universe.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*

@Configuration
class ValidatorConfig {

    @Bean
    fun localeResolver(): LocaleResolver {
        val resolver = AcceptHeaderLocaleResolver()
        resolver.setDefaultLocale(Locale.KOREAN)
        resolver.setSupportedLocales(listOf(Locale.ENGLISH, Locale.KOREAN))
        return resolver
    }
}