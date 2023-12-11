package kr.bomiza.universe.meeting.application.service

import kr.bomiza.universe.common.annotation.UseCase
import kr.bomiza.universe.meeting.application.port.`in`.AttendanceUseCase
import kr.bomiza.universe.meeting.application.port.`in`.FindAllAttendanceUseCase
import kr.bomiza.universe.meeting.application.port.`in`.FindLastAttendanceUseCase
import kr.bomiza.universe.meeting.application.port.out.LoadAttendancePort
import kr.bomiza.universe.meeting.application.port.out.LoadUserPort
import kr.bomiza.universe.meeting.application.port.out.SaveAttendancePort
import kr.bomiza.universe.meeting.domain.exception.AttendanceCheckOutException
import kr.bomiza.universe.meeting.domain.exception.ExistAttendanceCheckInException
import kr.bomiza.universe.meeting.domain.exception.NotFoundAttendanceException
import kr.bomiza.universe.meeting.domain.model.Attendance
import org.springframework.transaction.annotation.Transactional
import java.util.*

@UseCase
@Transactional(readOnly = true)
class AttendanceService(
    val loadUserPort: LoadUserPort,
    val saveAttendancePort: SaveAttendancePort,
    val loadAttendancePort: LoadAttendancePort,
) : AttendanceUseCase,
    FindAllAttendanceUseCase,
    FindLastAttendanceUseCase {

    @Transactional
    override fun attendance(userId: UUID, checkIn: Boolean) {
        if (checkIn) this.attendanceCheckIn(userId) else this.attendanceCheckOut(userId)
    }

    private fun attendanceCheckIn(userId: UUID) {
        loadAttendancePort.findByUserIdAndCheckIn(userId)?.also {
            throw ExistAttendanceCheckInException(it.id.toString())
        }
        val user = loadUserPort.loadUser(userId)
        saveAttendancePort.saveAttendance(Attendance.checkIn(user))
    }

    private fun attendanceCheckOut(userId: UUID) {
        loadAttendancePort.findByUserIdAndCheckIn(userId)?.apply {
            this.checkOut()
            saveAttendancePort.saveAttendance(this)
        } ?: throw AttendanceCheckOutException()
    }

    override fun findLastAttendance(userId: UUID): Attendance {
        return loadAttendancePort.findFirstByUserIdOrderByCreatedDateDesc(userId)
            ?: throw NotFoundAttendanceException(userId.toString())
    }

    override fun findAllAttendance(userId: UUID): List<Attendance> {
        return loadAttendancePort.findALLByUserIdOrderByCreatedDateDesc(userId)
    }
}