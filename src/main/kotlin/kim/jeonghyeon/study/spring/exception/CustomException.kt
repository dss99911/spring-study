package kim.jeonghyeon.study.spring.exception

class CustomException(val description: String, val logging: Boolean = true) : RuntimeException() {
}