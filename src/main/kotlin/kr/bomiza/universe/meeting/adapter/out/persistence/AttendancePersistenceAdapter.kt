package kr.bomiza.universe.meeting.adapter.out.persistence

import kr.bomiza.universe.common.annotation.PersistenceAdapter
import kr.bomiza.universe.meeting.adapter.out.persistence.entity.AttendanceRepository
import kr.bomiza.universe.meeting.application.port.out.LoadAttendancePort
import kr.bomiza.universe.meeting.application.port.out.SaveAttendancePort
import kr.bomiza.universe.meeting.domain.model.Attendance
import org.springframework.data.domain.Pageable
import java.util.*


@PersistenceAdapter
class AttendancePersistenceAdapter(
    val attendanceRepository: AttendanceRepository,
    val meetingPersistenceMapper: MeetingPersistenceMapper
) : SaveAttendancePort,
    LoadAttendancePort {

    override fun saveAttendance(attendance: Attendance) {
        attendanceRepository.save(meetingPersistenceMapper.mapToEntity(attendance))
    }

    override fun findByUserIdAndCheckIn(userId: UUID): Attendance? {
        return attendanceRepository.findByUserIdAndCheckIn(userId)?.let {
            meetingPersistenceMapper.mapToDomain(it)
        }
    }

    override fun findFirstByUserIdOrderByCreatedDateDesc(userId: UUID): Attendance? {
        return attendanceRepository.findFirstByUserIdOrderByCreatedDateDesc(userId)?.let {
            meetingPersistenceMapper.mapToDomain(it)
        }
    }

    override fun findAllByUserIdOrderByCreatedDateDesc(userId: UUID, page: Pageable): List<Attendance> {
        return attendanceRepository.findAllByUserIdOrderByCreatedDateDesc(userId, page)
            .map { meetingPersistenceMapper.mapToDomain(it) }
    }
}