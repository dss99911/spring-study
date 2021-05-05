package kim.jeonghyeon.study.spring.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/response")
class ResponseExampleController {
    @PostMapping
    @ResponseBody
    fun postTest(): ModelMap {
        //return response
        return ModelMap("result", 1000)
    }

    @GetMapping
    fun getTest(modelMap: ModelMap): String {
        //deliver data
        modelMap["name"] = "name"
        //return ui page name
        return "test"
    }
}