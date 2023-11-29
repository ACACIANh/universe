package kr.bomiza.universe.web.controller

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
@Hidden
class IndexController() {

    @GetMapping("/")
    fun index(model: Model): String {

        return "index"
    }
}