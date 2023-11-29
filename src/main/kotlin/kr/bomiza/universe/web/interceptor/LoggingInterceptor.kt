package kr.bomiza.universe.web.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.bomiza.universe.common.model.enums.MDCKeys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime
import java.util.*

@Component
class LoggingInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        val requestId = UUID.randomUUID().toString().uppercase(Locale.getDefault());
        val requestTime = LocalDateTime.now();

        MDC.put(MDCKeys.REQUEST_ID.name, requestId);
        MDC.put(MDCKeys.REQUEST_TIME.name, requestTime.toString());
        MDC.put(MDCKeys.REQUEST_REMOTE_ADDRESS.name, request.remoteAddr);
        MDC.put(MDCKeys.REQUEST_REMOTE_PORT.name, request.remotePort.toString());

        log.info(
            "RECV [${request.remoteAddr}:${request.remotePort}] --(${request.protocol})--> [X] - ${request.method} ${request.requestURI}"
        );

        response.addHeader("Request-Id", MDC.get(MDCKeys.REQUEST_ID.name));
        response.addHeader("Request-Time", MDC.get(MDCKeys.REQUEST_TIME.name));

        return super.preHandle(request, response, handler)
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        MDC.put(MDCKeys.RESPONSE_STATUS.name, response.status.toString())

        log.info(
            "SEND [X] --(${request.protocol})--> [${request.remoteAddr}:${request.remotePort}] - ${request.method} ${request.requestURI} ==> status=${response.status}"
        )
        super.postHandle(request, response, handler, modelAndView)
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        MDC.remove(MDCKeys.REQUEST_ID.name)
        MDC.remove(MDCKeys.REQUEST_TIME.name)
        MDC.remove(MDCKeys.REQUEST_REMOTE_ADDRESS.name)
        MDC.remove(MDCKeys.REQUEST_REMOTE_PORT.name)
        super.afterCompletion(request, response, handler, ex)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(LoggingInterceptor::class.java)
    }
}