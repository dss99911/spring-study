package kim.jeonghyeon.study.spring.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/request")
class RequestExampleController {
    @RequestMapping("/user", method = [RequestMethod.GET])
    fun get(): String {
        //value, method
        return "user" //return ui page name
    }

    @GetMapping
    fun getParam(@RequestParam(value = "data", required = false) data: String): String {
        //get
        return "test"
    }

    @GetMapping("/data/{id}")
    fun getPathVariable(@PathVariable id: String): String {
        //get
        return "test"
    }
}