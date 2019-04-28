package dev.helderlee.octo.common.domain.github

data class IssueEvent(
    val action: String,
    val changes: Changes,
    val issue: Issue,
    val repository: Repository,
    val sender: Sender
)