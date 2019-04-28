package dev.helderlee.octo.query.module

import dev.helderlee.octo.persistence.repository.IssueEventRepository
import dev.helderlee.octo.persistence.repository.IssueEventRepositoryImpl
import dev.helderlee.octo.query.endpoint.QueryEndpointGroup
import dev.helderlee.octo.query.service.QueryService
import dev.helderlee.octo.query.service.QueryServiceImpl
import org.koin.dsl.module.module

val queryModule = module {
    single { QueryEndpointGroup() }
    single { QueryServiceImpl(get()) as QueryService }
    single { IssueEventRepositoryImpl() as IssueEventRepository }
}