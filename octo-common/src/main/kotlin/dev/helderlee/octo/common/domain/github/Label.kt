package dev.helderlee.octo.common.domain.github

data class Label(
    val color: String,
    val default: Boolean,
    val id: Int,
    val name: String,
    val node_id: String,
    val url: String
)