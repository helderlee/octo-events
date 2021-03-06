package dev.helderlee.octo.common.domain.github

data class Issue(
    val assignee: Any,
    val assignees: List<Any>,
    val author_association: String,
    val body: String,
    val closed_at: String?,
    val comments: Int,
    val comments_url: String,
    val created_at: String,
    val events_url: String,
    val html_url: String,
    val id: Long,
    val labels: List<Label>,
    val labels_url: String,
    val locked: Boolean,
    val milestone: Any,
    val node_id: String,
    val number: Int,
    val repository_url: String,
    val state: String,
    val title: String,
    val updated_at: String,
    val url: String,
    val user: User
)