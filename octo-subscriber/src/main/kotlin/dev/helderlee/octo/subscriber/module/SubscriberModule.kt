package dev.helderlee.octo.subscriber.module

import dev.helderlee.octo.persistence.repository.IssueEventRepository
import dev.helderlee.octo.persistence.repository.IssueEventRepositoryImpl
import dev.helderlee.octo.persistence.repository.IssueRepository
import dev.helderlee.octo.persistence.repository.IssueRepositoryImpl
import dev.helderlee.octo.subscriber.service.SubscriberService
import dev.helderlee.octo.subscriber.service.SubscriberServiceImpl
import org.koin.dsl.module.module

val subscriberModule = module {
    single { SubscriberServiceImpl(get(), get()) as SubscriberService }
    single { IssueRepositoryImpl() as IssueRepository }
    single { IssueEventRepositoryImpl() as IssueEventRepository }
}
