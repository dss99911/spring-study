package kim.jeonghyeon.study.spring.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * api return value is response not ui page.
 */
@RestController
@RequestMapping("/rest")
class RestExampleController {
    @RequestMapping("/user", method = [RequestMethod.GET])
    fun getUser(): String {
        return "user" //return response
    }
}

@RequestMapping("/rest2")
class RestExampleController2 {
    @RequestMapping("/user", method = [RequestMethod.GET])
    @ResponseBody
    fun getUser(): String {
        return "user" //return response
    }
}