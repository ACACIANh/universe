package kr.bomiza.universe.service

import kr.bomiza.universe.common.model.enums.MDCKeys
import kr.bomiza.universe.common.model.dto.attendance.AttendanceResponseDto
import kr.bomiza.universe.common.model.exception.AttendanceCheckOutException
import kr.bomiza.universe.common.model.exception.ExistAttendanceCheckInException
import kr.bomiza.universe.common.model.exception.NotFoundAttendanceException
import kr.bomiza.universe.common.model.exception.NotFoundUserException
import kr.bomiza.universe.domain.attendance.Attendance
import kr.bomiza.universe.domain.attendance.AttendanceRepository
import kr.bomiza.universe.domain.user.User
import kr.bomiza.universe.domain.user.UserRepository
import org.slf4j.MDC
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.ObjectUtils
import java.time.LocalDateTime
import java.time.ZonedDateTime


@Service
@Transactional(readOnly = true)
class AttendanceService(
    val attendanceRepository: AttendanceRepository,
    val userRepository: UserRepository
) {

    @Transactional
    fun attendance(userId: Long, checkIn: Boolean) {
        val user = userRepository.findById(userId)
            .orElseThrow {
                NotFoundUserException(userId.toString())
            }

        val existingCheckInAttendance = attendanceRepository.findByUserIdAndCheckIn(userId)

        if (checkIn) {
            if (ObjectUtils.isEmpty(existingCheckInAttendance)) {
                val currentTime = LocalDateTime.parse(MDC.get(MDCKeys.REQUEST_TIME.name))
                attendanceRepository.save(Attendance(user, currentTime, null))
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

    fun findLastAttendance(userId: Long): AttendanceResponseDto {
        val attendance = attendanceRepository.findFirstByUserIdOrderByCreatedDateDesc(userId)
            ?: throw NotFoundAttendanceException(userId.toString())
        return AttendanceResponseDto(attendance)
    }

    fun findAllAttendance(userId: Long): List<AttendanceResponseDto> {
        val attendances = attendanceRepository.findALLByUserIdOrderByCreatedDateDesc(userId)
        return attendances.map { e -> AttendanceResponseDto(e) }
    }
}