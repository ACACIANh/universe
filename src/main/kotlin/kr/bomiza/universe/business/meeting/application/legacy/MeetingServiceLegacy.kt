package kr.bomiza.universe.business.meeting.application.legacy

import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.MeetingRepository
import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.UserRepository
import kr.bomiza.universe.domain.meeting.enums.UserRole
import kr.bomiza.universe.domain.meeting.exception.NotFoundAdminUserException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.DayOfWeek
import java.time.LocalDate

// todo: 개선방법 찾아보기
const val CAPACITY_MEMBER = 16

@Service
@Transactional(readOnly = true)
class MeetingServiceLegacy(
    val meetingRepository: MeetingRepository,
    val userRepository: UserRepository,
) {

    fun createWeekendMeetingOnMonday() {

        var intervalDays: Int = (DayOfWeek.FRIDAY.value - DayOfWeek.MONDAY.value)

        val friday = LocalDate.now().plusDays(intervalDays++.toLong())
        val saturday = LocalDate.now().plusDays(intervalDays++.toLong())
        val sunday = LocalDate.now().plusDays(intervalDays.toLong())

        this.createMeetingOnAdmin(friday)
        this.createMeetingOnAdmin(saturday)
        this.createMeetingOnAdmin(sunday)
    }

    fun createMeetingOnAdmin(createDate: LocalDate) {

        val adminUser = userRepository.findByRole(UserRole.ADMIN)
            .orElseThrow { NotFoundAdminUserException() }
//        todo: 없애고 추가하기
//        meetingRepository.save(MeetingJpaEntity(adminUser, createDate, CAPACITY_MEMBER))
        log.info("createMeetingOnAdmin success createdDate: $createDate")
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(MeetingServiceLegacy::class.java)
    }
}