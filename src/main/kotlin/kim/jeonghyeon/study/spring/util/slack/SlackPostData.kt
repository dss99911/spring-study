package kim.jeonghyeon.study.spring.util.slack

data class SlackPostData(
    val channel: String,
    val username: String,
    val icon_emoji: String,
    val text: String,
    val attachments: List<SlackAttachment> = listOf()
)