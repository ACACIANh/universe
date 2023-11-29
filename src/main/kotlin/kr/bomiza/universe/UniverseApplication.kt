package kr.bomiza.universe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UniverseApplication {
//    @PostConstruct
//    fun started() {
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
//    }
}

fun main(args: Array<String>) {

    runApplication<UniverseApplication>(*args)
}
