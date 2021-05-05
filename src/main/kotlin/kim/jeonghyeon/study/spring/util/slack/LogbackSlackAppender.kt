package kim.jeonghyeon.study.spring.util.slack

import ch.qos.logback.classic.pattern.ExtendedThrowableProxyConverter
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.UnsynchronizedAppenderBase
import kim.jeonghyeon.study.spring.util.hostname

class LogbackSlackAppender : UnsynchronizedAppenderBase<ILoggingEvent>() {
    private var exceptionConverter: ExtendedThrowableProxyConverter = ExtendedThrowableProxyConverter()
    lateinit var slackMessageSender: SlackMessageSender

    private var componentName: String = ""
    private var phase: String = ""
    private var hostname: String = hostname()

    private var webhookUri: String = ""
    private var channel: String = ""

    override fun start() {
        super.start()

        exceptionConverter.start()
        slackMessageSender = SlackMessageSender()
    }

    override fun append(evt: ILoggingEvent) {
        try {
//            sendMessageWithWebhookUri(evt)
        } catch (ex: Exception) {
            addError("Error posting log to slack.com : $evt", ex)
        }
    }

    private fun sendMessageWithWebhookUri(evt: ILoggingEvent) {
        val stacktrace = exceptionConverter.convert(evt)
        val slackAttachment = SlackAttachment(
            text = stacktrace,
            color = Color.RED,
            fields = listOf(
                Field("Level", evt.level.toString(), true),
                Field("Component", componentName, true),
                Field("Phase", phase, true),
                Field("Host", hostname, true),
            ),
            ts = System.currentTimeMillis() / 1000
        )

        slackMessageSender.sendBase(webhookUri, SlackPostData(channel, "Notification", "", evt.formattedMessage, listOf(slackAttachment)))
    }

    fun setComponentName(componentName: String) {
        this.componentName = componentName
    }

    fun setPhase(phase: String) {
        this.phase = phase
    }

    fun setChannel(channel: String) {
        this.channel = channel
    }

    fun setWebhookUri(webhookUri: String) {
        this.webhookUri = webhookUri
    }
}