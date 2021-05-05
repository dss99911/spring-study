package kim.jeonghyeon.study.spring.util.slack

import kim.jeonghyeon.study.spring.client.Foo
import org.springframework.http.HttpEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject

class SlackMessageSender {
    fun sendText(webhookUrl: String, channel: String, userName: String, emoji: String, text: String) {
        val slackData = SlackPostData(channel, userName, emoji, text)
        sendBase(webhookUrl, slackData)
    }

    fun sendSimpleNotification(webhookUrl: String, channel: String, userName: String, emoji: String, color: String, title: String, message: String) {
        val slackData = SlackPostData(channel, userName, emoji, "", listOf(SlackAttachment(color = color, title = title, text= message)))
        sendBase(webhookUrl, slackData)
    }

    fun sendBase(webhookUri: String, data: SlackPostData): String {
        val restTemplate = RestTemplate()
        val request: HttpEntity<SlackPostData> = HttpEntity(data)
        return restTemplate.postForObject<String>(webhookUri, request, SlackPostData::class.java)
    }
}