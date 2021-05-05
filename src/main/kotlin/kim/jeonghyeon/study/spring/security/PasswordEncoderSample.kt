package kim.jeonghyeon.study.spring.security

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder

/**
 * todo this is deprecated. check updated way
 */
class PasswordEncoderSample {

    fun encodePassword(password: String): String {
        val encoder = MessageDigestPasswordEncoder("SHA-256")
        return encoder.encode(password)
    }

    fun matchesPassword(rawPassword: String, encodedPassword: String): Boolean {
        val encoder = MessageDigestPasswordEncoder("SHA-256")
        return encoder.matches(rawPassword, encodedPassword)
    }
}