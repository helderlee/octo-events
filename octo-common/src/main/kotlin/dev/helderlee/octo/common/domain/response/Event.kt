package dev.helderlee.octo.common.domain.response

data class Event(
    val action: String,
    val created_at: String,
    val updated_at: String,
    val closed_at: String?,
    val user_login: String
)