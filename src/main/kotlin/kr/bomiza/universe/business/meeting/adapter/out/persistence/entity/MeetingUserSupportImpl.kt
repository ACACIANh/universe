package kr.bomiza.universe.business.meeting.adapter.out.persistence.entity

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
class MeetingUserSupportImpl(
    val jpaQueryFactory: JPAQueryFactory
) : MeetingUserSupport {

    override fun findByUserIdAndDate(userId: UUID, date: LocalDate): List<MeetingUserJpaEntity> {
        val meetingJpaEntity = QMeetingJpaEntity.meetingJpaEntity
        val meetingUserJpaEntity = QMeetingUserJpaEntity.meetingUserJpaEntity
        return jpaQueryFactory.select(meetingUserJpaEntity)
            .from(meetingUserJpaEntity)
            .join(meetingJpaEntity)
            .on(meetingUserJpaEntity.meetingId.eq(meetingJpaEntity.id))
            .where(meetingUserJpaEntity.user.id.eq(userId).and(meetingJpaEntity.date.eq(date)))
            .fetch()
    }
}