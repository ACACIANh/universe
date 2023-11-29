package kr.bomiza.universe.configuration

import kr.bomiza.universe.UniverseApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
    basePackageClasses = [UniverseApplication::class]
)
class JpaConfiguration