package kr.bomiza.universe.business.attendance.adapter.out.persistence

import kr.bomiza.universe.business.attendance.adapter.out.persistence.entity.AttendanceRepository
import kr.bomiza.universe.business.attendance.application.port.out.LoadAttendancePort
import kr.bomiza.universe.business.attendance.application.port.out.SaveAttendancePort
import kr.bomiza.universe.common.annotation.PersistenceAdapter
import kr.bomiza.universe.domain.attendance.model.Attendance
import org.springframework.data.domain.Pageable
import java.util.*


@PersistenceAdapter
class AttendancePersistenceAdapter(
    val attendanceRepository: AttendanceRepository,
    val attendancePersistenceMapper: AttendancePersistenceMapper
) : SaveAttendancePort,
    LoadAttendancePort {

    override fun saveAttendance(attendance: Attendance) {
        attendanceRepository.save(attendancePersistenceMapper.mapToEntity(attendance))
    }

    override fun findByUserIdAndCheckIn(userId: UUID): Attendance? {
        return attendanceRepository.findByUserIdAndCheckIn(userId)?.let {
            attendancePersistenceMapper.mapToDomain(it)
        }
    }

    override fun findFirstByUserIdOrderByCreatedDateDesc(userId: UUID): Attendance? {
        return attendanceRepository.findFirstByUserIdOrderByCreatedDateDesc(userId)?.let {
            attendancePersistenceMapper.mapToDomain(it)
        }
    }

    override fun findAllByUserIdOrderByCreatedDateDesc(userId: UUID, page: Pageable): List<Attendance> {
        return attendanceRepository.findAllByUserIdOrderByCreatedDateDesc(userId, page)
            .map { attendancePersistenceMapper.mapToDomain(it) }
    }
}