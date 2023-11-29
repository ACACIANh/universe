package kr.bomiza.universe.meeting.application.legacy

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDate

const val EVERY_SECOND = "* * * * * *"
const val EVERY_MONDAY = "0 0 12 ? * MON"

@Service
class ScheduledMeetingService(
    val meetingService: MeetingService
) {
    /*** EVERY_SECOND
    @Scheduled(cron = EVERY_SECOND)
    fun testScheduler() {
    log.info("localTime : ${LocalTime.now()}")
    }
     ***/
    @Scheduled(cron = EVERY_MONDAY)
    fun createWeekendMeetingOnMonday() {
        meetingService.createWeekendMeetingOnMonday();
        log.info("createWeekendMeetingOnMonday success : ${LocalDate.now()}")
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ScheduledMeetingService::class.java)
    }
}