package org.hotswap.agent.example.springbootkotlin

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/example"])
class ExampleApi {

    @GetMapping("/{test}")
    @ResponseBody
    fun test(@PathVariable test: String) : String = "hello " + test
}
