package dev.helderlee.octo.query.service

import dev.helderlee.octo.persistence.repository.IssueEventRepository

class QueryServiceImpl (
    private val issueEventRepository: IssueEventRepository
) : QueryService {

    override fun getEvents(issueNumber: Int) = issueEventRepository.list(issueNumber)

}
