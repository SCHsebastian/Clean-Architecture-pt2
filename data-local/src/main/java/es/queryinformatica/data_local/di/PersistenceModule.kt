package es.queryinformatica.data_local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.queryinformatica.data_local.db.AppDatabase
import es.queryinformatica.data_local.db.post.PostDao
import es.queryinformatica.data_local.db.user.UserDao
import es.queryinformatica.data_local.source.LocalInteractionDataSourceImpl

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_preferences")

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "my-database"
        ).build()

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    fun providePostDao(appDatabase: AppDatabase): PostDao = appDatabase.postDao()

    @Provides
    fun provideLocalInteractionDataSourceImpl(@ApplicationContext context: Context) =
        LocalInteractionDataSourceImpl(context.dataStore)

}