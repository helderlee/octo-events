package dev.helderlee.octo.persistence.repository

import dev.helderlee.octo.common.domain.github.Issue

interface IssueRepository {
    fun save(issue: Issue): Boolean
}

