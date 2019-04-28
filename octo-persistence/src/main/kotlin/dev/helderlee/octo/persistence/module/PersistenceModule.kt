package dev.helderlee.octo.persistence.module

import dev.helderlee.octo.persistence.repository.IssueEventRepository
import dev.helderlee.octo.persistence.repository.IssueEventRepositoryImpl
import dev.helderlee.octo.persistence.repository.IssueRepository
import dev.helderlee.octo.persistence.repository.IssueRepositoryImpl
import org.koin.dsl.module.module

val persistenceModule = module {
    single { IssueRepositoryImpl() as IssueRepository }
    single { IssueEventRepositoryImpl() as IssueEventRepository }
}
