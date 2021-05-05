package kim.jeonghyeon.study.spring.client

import org.springframework.http.*
import org.springframework.web.client.RestTemplate
import java.net.URI


class RestTemplateSample {
    val restTemplate = RestTemplate()
    val fooResourceUrl = "http://localhost:8080/spring-rest/foos"

    fun get() {

        val response = restTemplate.getForEntity("$fooResourceUrl/1", String::class.java)
        response.statusCode == HttpStatus.OK
    }

    fun getWithObject() {
        val foo: Foo? = restTemplate
            .getForObject(fooResourceUrl.toString() + "/1", Foo::class.java)

    }

    /**
     * 어떤 헤더를 쓸수 있는지 알려주는 듯.
     */
    fun retrievHeaders() {
        val restTemplate = RestTemplate()
        val fooResourceUrl = "http://localhost:8080/spring-rest/foos"
        val httpHeaders: HttpHeaders = restTemplate.headForHeaders(fooResourceUrl)
        httpHeaders.getContentType()?.includes(MediaType.APPLICATION_JSON)
    }

    fun postObject() {
        val restTemplate = RestTemplate()

        val request: HttpEntity<Foo> = HttpEntity<Foo>(Foo(name = "bar"))
        val foo: Foo? = restTemplate.postForObject(fooResourceUrl, request, Foo::class.java)
    }

    /**
     * response로 결과 값을 받지 않고, location만 받는다.
     */
    fun postLocation() {
        val request: HttpEntity<Foo> = HttpEntity(Foo(name = "bar"))
        val location: URI? = restTemplate
            .postForLocation(fooResourceUrl, request)
    }

    fun exhange() {
        val restTemplate = RestTemplate()
        val request: HttpEntity<Foo> = HttpEntity(Foo(name = "bar"))
        val response: ResponseEntity<Foo> = restTemplate
            .exchange(fooResourceUrl, HttpMethod.POST, request, Foo::class.java)
        response.statusCode == HttpStatus.CREATED

        val foo = response.body

    }
}

data class Foo(val id: Long = 0, val name: String? = null )