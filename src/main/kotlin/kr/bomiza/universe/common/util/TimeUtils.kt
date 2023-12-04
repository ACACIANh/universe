package kr.bomiza.universe.common.util

import kr.bomiza.universe.common.enums.MDCKeys
import org.slf4j.MDC
import java.time.LocalDateTime

class TimeUtils {
    companion object {
        fun requestTime(): LocalDateTime {
            return LocalDateTime.parse(MDC.get(MDCKeys.REQUEST_TIME.name))
        }

        fun currentTime(): LocalDateTime {
            return LocalDateTime.now()
        }
    }
}