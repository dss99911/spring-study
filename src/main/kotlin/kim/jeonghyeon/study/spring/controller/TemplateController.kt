package kim.jeonghyeon.study.spring.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/template")
class TemplateController {

    @GetMapping
    fun get(): String {
        return "index"
    }
}