package kr.bomiza.universe.business.attendance.application.service

import kr.bomiza.universe.business.attendance.application.port.`in`.AttendanceUseCase
import kr.bomiza.universe.business.attendance.application.port.`in`.FindAttendanceUseCase
import kr.bomiza.universe.business.attendance.application.port.out.LoadAttendancePort
import kr.bomiza.universe.business.attendance.application.port.out.LoadUserPort
import kr.bomiza.universe.business.attendance.application.port.out.SaveAttendancePort
import kr.bomiza.universe.common.annotation.UseCase
import kr.bomiza.universe.common.util.TimeUtils
import kr.bomiza.universe.domain.attendance.exception.AttendanceCheckOutException
import kr.bomiza.universe.domain.attendance.exception.ExistAttendanceCheckInException
import kr.bomiza.universe.domain.attendance.exception.NotFoundAttendanceException
import kr.bomiza.universe.domain.attendance.model.Attendance
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.util.*

@UseCase
@Transactional(readOnly = true)
class AttendanceService(
    val loadUserPort: LoadUserPort,
    val saveAttendancePort: SaveAttendancePort,
    val loadAttendancePort: LoadAttendancePort,
) : AttendanceUseCase,
    FindAttendanceUseCase {

    @Transactional
    override fun attendance(userId: UUID, checkIn: Boolean) {
        if (checkIn) this.attendanceCheckIn(userId) else this.attendanceCheckOut(userId)
    }

    private fun attendanceCheckIn(userId: UUID) {
        loadAttendancePort.findByUserIdAndCheckIn(userId)?.let {
            throw ExistAttendanceCheckInException(it.id.toString())
        }
        val user = loadUserPort.loadUser(userId)
        saveAttendancePort.saveAttendance(Attendance.checkIn(user, TimeUtils.requestTime()))
    }

    private fun attendanceCheckOut(userId: UUID) {
        loadAttendancePort.findByUserIdAndCheckIn(userId)?.also {
            it.checkOut(TimeUtils.requestTime())
            saveAttendancePort.saveAttendance(it)
        } ?: throw AttendanceCheckOutException()
        // 참여 완료에 대한 처리 필요
    }

    override fun findLastAttendance(userId: UUID): Attendance {
        return loadAttendancePort.findFirstByUserIdOrderByCreatedDateDesc(userId)
            ?: throw NotFoundAttendanceException(userId.toString())
    }

    override fun findAllAttendance(userId: UUID, page: Pageable): List<Attendance> {
        return loadAttendancePort.findAllByUserIdOrderByCreatedDateDesc(userId, page)
    }
}