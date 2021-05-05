package kim.jeonghyeon.study.spring.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*


class ControllerExample {
        /**
     * api return value is response not ui page.
     */
    @RestController
    class RestControllerExample {
        @RequestMapping("/user", method = [RequestMethod.GET])
        fun getUser(): String {
            return "user" //return response
        }
    }
}