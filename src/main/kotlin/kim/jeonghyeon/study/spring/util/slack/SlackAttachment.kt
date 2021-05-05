package kim.jeonghyeon.study.spring.util.slack

data class SlackAttachment(
    val title: String? = null,
    val title_link: String? = null,
    val pretext: String? = null,
    val text: String? = null,
    val color: String? = null,
    val fields: List<Field> = listOf(),
    val ts: Long = 0
)