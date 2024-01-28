



@Service
class RetryService {

    @Retryable(value = RecoverableMessagingException.class, maxAttempts = 3, backoff = @Backoff(delay = 100, maxDelay = 500))
    fun retryOnFailure() {

    }
}