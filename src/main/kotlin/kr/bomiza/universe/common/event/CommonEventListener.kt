package kr.bomiza.universe.common.event

import kr.bomiza.universe.common.model.Event
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone

@Component
@Transactional(readOnly = true)
class CommonEventListener(
    val eventRepository: EventRepository
) {

    @Async
    @TransactionalEventListener
    @Transactional
    fun logging(event: Event) {
        val eventEntity = EventEntity(
            event.javaClass.simpleName,
            LocalDateTime.ofInstant(Instant.ofEpochMilli(event.timestamp), TimeZone.getDefault().toZoneId())
        )
        eventRepository.save(eventEntity)
    }
}
