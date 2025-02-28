package es.tierno.amanda.mz.tareasapp.data.room.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.tierno.amanda.mz.tareasapp.data.room.TareaDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val CLIENTE_DATABASE_NAME = "tareas-db"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TareaDatabase::class.java, CLIENTE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideTareaDao(db: TareaDatabase) = db.tareaDao()

    @Singleton
    @Provides
    fun providePrioridadDao(db:TareaDatabase) = db.prioridadDao()

}