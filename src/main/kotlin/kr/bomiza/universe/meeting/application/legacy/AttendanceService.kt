package kr.bomiza.universe.meeting.application.legacy

import kr.bomiza.universe.meeting.adapter.`in`.web.model.response.AttendanceResponseDto
import kr.bomiza.universe.common.enums.MDCKeys
import kr.bomiza.universe.meeting.domain.exception.AttendanceCheckOutException
import kr.bomiza.universe.meeting.domain.exception.ExistAttendanceCheckInException
import kr.bomiza.universe.meeting.domain.exception.NotFoundAttendanceException
import kr.bomiza.universe.meeting.domain.exception.NotFoundUserException
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.AttendanceJpaEntity
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.AttendanceRepository
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.UserRepository
import org.slf4j.MDC
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.ObjectUtils
import java.time.LocalDateTime
import java.util.*


@Service
@Transactional(readOnly = true)
class AttendanceService(
    val attendanceRepository: AttendanceRepository,
    val userRepository: UserRepository
) {

    @Transactional
    fun attendance(userId: UUID, checkIn: Boolean) {
        val user = userRepository.findById(userId)
            .orElseThrow {
                NotFoundUserException(userId.toString())
            }

        val existingCheckInAttendance = attendanceRepository.findByUserIdAndCheckIn(userId)

        if (checkIn) {
            if (ObjectUtils.isEmpty(existingCheckInAttendance)) {
                val currentTime = LocalDateTime.parse(MDC.get(MDCKeys.REQUEST_TIME.name))
                attendanceRepository.save(AttendanceJpaEntity(user, currentTime, null))
                return
            }
            throw ExistAttendanceCheckInException(existingCheckInAttendance!!.id.toString())
        }
        if (ObjectUtils.isEmpty(existingCheckInAttendance)) {
            throw AttendanceCheckOutException()
        }
        val currentTime = LocalDateTime.parse(MDC.get(MDCKeys.REQUEST_TIME.name))
        existingCheckInAttendance!!.checkOut = currentTime
        attendanceRepository.save(existingCheckInAttendance)
    }

    fun findLastAttendance(userId: UUID): AttendanceResponseDto {
        val attendance = attendanceRepository.findFirstByUserIdOrderByCreatedDateDesc(userId)
            ?: throw NotFoundAttendanceException(userId.toString())
        return AttendanceResponseDto(attendance)
    }

    fun findAllAttendance(userId: UUID): List<AttendanceResponseDto> {
        val attendances = attendanceRepository.findALLByUserIdOrderByCreatedDateDesc(userId)
        return attendances.map { e -> AttendanceResponseDto(e) }
    }
}