package di

import com.russhwolf.settings.Settings
import data.local.PreferencesImpl
import data.remote.api.CurrencyApiServiceImpl
import domain.CurrencyApiService
import domain.PreferenceRepository
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.screen.HomeViewModel


//
// Created by Code For Android on 02/05/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

val appModule = module {
    single { Settings() }
    single<PreferenceRepository> { PreferencesImpl(settings = get()) }
    single<CurrencyApiService> { CurrencyApiServiceImpl(preferenceRepository = get()) }
    factory {
        HomeViewModel(
            preferenceRepository = get(),
            api = get()
        )
    }
}


fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}
