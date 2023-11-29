package kr.bomiza.universe.configuration

import kr.bomiza.universe.domain.Persistence
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
    basePackageClasses = [Persistence::class]
)
class JpaConfiguration