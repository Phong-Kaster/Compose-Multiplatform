package org.astronex.olyn.injection

import org.astronex.olyn.buildMenstruationDatabase
import org.astronex.olyn.buildSymptomDiaryDatabase
import org.astronex.olyn.data.database.menstruation.MenstruationDatabase
import org.astronex.olyn.data.database.menstruation.getMenstruationRoomDatabase
import org.astronex.olyn.data.database.symptom.SymptomDiaryDatabase
import org.astronex.olyn.data.database.symptom.getSymptomDiaryDatabase
import org.koin.dsl.module

val databaseModule = module {
    // Menstruation Database
    single<MenstruationDatabase> { getMenstruationRoomDatabase(builder = buildMenstruationDatabase()) }

    // Symptom Diary Database
    single<SymptomDiaryDatabase> { getSymptomDiaryDatabase(builder = buildSymptomDiaryDatabase()) }
}