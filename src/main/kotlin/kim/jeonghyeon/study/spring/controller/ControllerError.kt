/**
 * 404 error : if there is no package, it is not recognized,
 */
package kim.jeonghyeon.springlibrary.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 404 error : if set uri on @Controller. it is not working,
 * it should be put on @RequestMapping("/test")
 */
@Controller("/test")
class ThisIsWrongWay {

}