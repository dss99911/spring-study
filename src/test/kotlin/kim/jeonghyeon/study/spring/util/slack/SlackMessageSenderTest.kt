package kim.jeonghyeon.study.spring.util.slack

import org.junit.jupiter.api.Test

internal class SlackMessageSenderTest {

    fun log() {

    }
    @Test
    fun sendText() {
        SlackMessageSender().sendText("https://hooks.slack.com/services/your-token", "hyun", "dd", "", "text")
    }

    @Test
    fun sendSimpleNotification() {
        SlackMessageSender().sendSimpleNotification("https://hooks.slack.com/services/your-token", "hyun", "Daily Stat", ":point_right:", "good", "test title", "test message")
    }

    @Test
    fun sendBase() {
        SlackMessageSender().sendBase("https://hooks.slack.com/services/your-token",
            SlackPostData("hyun", "hy", "", "", listOf(
                SlackAttachment("title", "http://google.com", "pretext", "tex", Color.BLUE, listOf(
                    Field("field title", "value", true),
                    Field("field title2", "value2", true),
                    Field("field title2", "value2value2value2value2value2va\nlue2value2value2value2value2value2value2value2value2value2value2value2va\n" +
                            "lue2value2value2value2value2value2value2value2value2value2value2value2va\n" +
                            "lue2value2value2value2value2value2value2", true),
                    Field("field title2", "value2", true),
                ))
            ))
        )
    }
}