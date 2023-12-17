package kr.bomiza.universe.business.meeting.adapter.out.persistence

import kr.bomiza.universe.common.enums.MDCKeys
import kr.bomiza.universe.common.enums.UserState
import kr.bomiza.universe.domain.meeting.enums.MeetingUserState
import kr.bomiza.universe.common.enums.UserRole
import kr.bomiza.universe.domain.meeting.model.Meeting
import kr.bomiza.universe.domain.meeting.model.MeetingUser
import kr.bomiza.universe.domain.meeting.model.User
//import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.AttendanceJpaEntity
import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.MeetingJpaEntity
import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.MeetingUserJpaEntity
import kr.bomiza.universe.business.meeting.adapter.out.persistence.entity.UserJpaEntity
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.MDC
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class MeetingPersistenceMapperTest {

    private lateinit var meetingPersistenceMapper: MeetingPersistenceMapper

    @BeforeEach
    fun 매퍼준비() {
        meetingPersistenceMapper = MeetingPersistenceMapper()
        MDC.put(MDCKeys.REQUEST_TIME.name, LocalDateTime.now().toString())
    }

    @Test
    fun 유저매핑toDomain() {
        //given
        val userJpaEntity = UserJpaEntity(UUID.randomUUID(), "하동구", UserState.ACTIVATE, UserRole.MEMBER)

        //when
        val user = meetingPersistenceMapper.mapToDomain(userJpaEntity)

        //then
        Assertions.assertThat(userJpaEntity.id).isEqualTo(user.id)
        Assertions.assertThat(userJpaEntity.name).isEqualTo(user.name)
        Assertions.assertThat(userJpaEntity.state).isEqualTo(user.state)
        Assertions.assertThat(userJpaEntity.role).isEqualTo(user.role)
    }

    @Test
    fun 유저매핑toEntity() {
        //given
        val user = User(UUID.randomUUID(), "하동구", UserState.ACTIVATE, UserRole.MEMBER)

        //when
        val entity = meetingPersistenceMapper.mapToEntity(user)

        //then
        Assertions.assertThat(user.id).isEqualTo(entity.id)
        Assertions.assertThat(user.name).isEqualTo(entity.name)
        Assertions.assertThat(user.state).isEqualTo(entity.state)
        Assertions.assertThat(user.role).isEqualTo(entity.role)
    }

//    @Test
//    fun 출석매핑toDomain() {
//        //given
//        val userJpaEntity = UserJpaEntity(UUID.randomUUID(), "하동구", UserState.ACTIVATE, UserRole.MEMBER)
//        val attendanceJpaEntity = AttendanceJpaEntity(UUID.randomUUID(), userJpaEntity, LocalDateTime.now(), null)
//
//        //when
//        val attendance = meetingPersistenceMapper.mapToDomain(attendanceJpaEntity)
//
//        //then
//        Assertions.assertThat(attendanceJpaEntity.id).isEqualTo(attendance.id)
//        Assertions.assertThat(attendanceJpaEntity.user.id).isEqualTo(attendance.user.id)
//        Assertions.assertThat(attendanceJpaEntity.checkIn).isEqualTo(attendance.checkIn)
//        Assertions.assertThat(attendanceJpaEntity.checkOut).isEqualTo(attendance.checkOut)
//    }
//
//    @Test
//    fun 출석매핑toEntity() {
//        //given
//        val user = User(UUID.randomUUID(), "하동구", UserState.ACTIVATE, UserRole.MEMBER)
//        val attendance = Attendance(UUID.randomUUID(), user, LocalDateTime.now(), null)
//
//        //when
//        val attendanceJpaEntity = meetingPersistenceMapper.mapToEntity(attendance)
//
//        //then
//        Assertions.assertThat(attendance.id).isEqualTo(attendanceJpaEntity.id)
//        Assertions.assertThat(attendance.user.id).isEqualTo(attendanceJpaEntity.user.id)
//        Assertions.assertThat(attendance.checkIn).isEqualTo(attendanceJpaEntity.checkIn)
//        Assertions.assertThat(attendance.checkOut).isEqualTo(attendanceJpaEntity.checkOut)
//    }


    @Test
    fun 미팅toDomain() {
        //given
        val userEntity = UserJpaEntity(UUID.randomUUID(), "User1", UserState.ACTIVATE, UserRole.MEMBER)
        val meetingEntity = MeetingJpaEntity(
            UUID.randomUUID(), userEntity, LocalDate.now(), 10, listOf()
        )

        // when
        val meeting = meetingPersistenceMapper.mapToDomain(meetingEntity)

        // then
        Assertions.assertThat(meetingEntity.id).isEqualTo(meeting.id)
        Assertions.assertThat(meetingEntity.masterUser.id).isEqualTo(meeting.masterUser.id)
        Assertions.assertThat(meetingEntity.date).isEqualTo(meeting.date)
        Assertions.assertThat(meetingEntity.capacityMember).isEqualTo(meeting.meetingUsers.capacity)
        Assertions.assertThat(meetingEntity.meetingUsers.size).isEqualTo(meeting.meetingUsers.meetingUsers.size)
    }

    @Test
    fun 미팅ToEntity() {
        //given
        val user = User(UUID.randomUUID(), "User1", UserState.ACTIVATE, UserRole.MEMBER)
        val meeting = Meeting(user, LocalDate.now(), 10)

        // when
        val meetingEntity = meetingPersistenceMapper.mapToEntity(meeting)

        // then
        Assertions.assertThat(meeting.id).isEqualTo(meetingEntity.id)
        Assertions.assertThat(meeting.masterUser.id).isEqualTo(meetingEntity.masterUser.id)
        Assertions.assertThat(meeting.date).isEqualTo(meetingEntity.date)
        Assertions.assertThat(meeting.meetingUsers.capacity).isEqualTo(meetingEntity.capacityMember)
        Assertions.assertThat(meeting.meetingUsers.meetingUsers.size).isEqualTo(meetingEntity.meetingUsers.size)
    }

    @Test
    fun 미팅유저ToDomain() {
        //given
        val meetingEntity = MeetingJpaEntity(
            UUID.randomUUID(), UserJpaEntity(UUID.randomUUID(), "User1", UserState.ACTIVATE, UserRole.MEMBER),
            LocalDate.now(), 10, listOf()
        )
        val userEntity = UserJpaEntity(UUID.randomUUID(), "User1", UserState.ACTIVATE, UserRole.MEMBER)
        val meetingUserEntity = MeetingUserJpaEntity(
            UUID.randomUUID(),
            meetingEntity.id,
            userEntity,
            MeetingUserState.PARTICIPATION,
            LocalTime.now(),
            false
        ).also {
            it.createdDate = LocalDateTime.now()
        }

        // when
        val meetingUser = meetingPersistenceMapper.mapToDomain(meetingUserEntity)

        // then
        Assertions.assertThat(meetingUserEntity.id).isEqualTo(meetingUser.id)
        Assertions.assertThat(meetingUserEntity.meetingId).isEqualTo(meetingUser.meetingId)
        Assertions.assertThat(meetingUserEntity.user.id).isEqualTo(meetingUser.user.id)
        Assertions.assertThat(meetingUserEntity.state).isEqualTo(meetingUser.state)
        Assertions.assertThat(meetingUserEntity.joinTime).isEqualTo(meetingUser.joinTime)
        Assertions.assertThat(meetingUserEntity.guest).isEqualTo(meetingUser.guest)
    }

    @Test
    fun 미팅유저ToEntity() {
        //given
        val user = User(UUID.randomUUID(), "User1", UserState.ACTIVATE, UserRole.MEMBER)
        val meeting = Meeting(user, LocalDate.now(), 10)
        val meetingUser =
            MeetingUser(
                UUID.randomUUID(),
                meeting.id,
                user,
                MeetingUserState.PARTICIPATION,
                LocalTime.now(),
                false,
                LocalDateTime.now(),
            )

        // when
        val meetingUserEntity = meetingPersistenceMapper.mapToEntity(meetingUser)

        // then
        Assertions.assertThat(meetingUser.id).isEqualTo(meetingUserEntity.id)
        Assertions.assertThat(meetingUser.meetingId).isEqualTo(meetingUserEntity.meetingId)
        Assertions.assertThat(meetingUser.user.id).isEqualTo(meetingUserEntity.user.id)
        Assertions.assertThat(meetingUser.state).isEqualTo(meetingUserEntity.state)
        Assertions.assertThat(meetingUser.joinTime).isEqualTo(meetingUserEntity.joinTime)
        Assertions.assertThat(meetingUser.guest).isEqualTo(meetingUserEntity.guest)
    }
}