package kim.jeonghyeon.study.spring.dao

import kim.jeonghyeon.study.spring.client.Foo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service


/**
 * https://docs.spring.io/spring-framework/docs/3.0.x/spring-framework-reference/html/jdbc.html
 */
@Service
class JdbcTemplateSample(
    @Autowired
    val jdbcTemplate: JdbcTemplate
) {
    fun useTemplate() {
        val foos = jdbcTemplate.query("select * from foo") { rs, rowNum -> Foo(rs.getLong("name"), rs.getString("name")) }
    }
}