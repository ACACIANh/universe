package kr.bomiza.universe.common.model

abstract class Event {
    val timestamp: Long = System.currentTimeMillis()
}