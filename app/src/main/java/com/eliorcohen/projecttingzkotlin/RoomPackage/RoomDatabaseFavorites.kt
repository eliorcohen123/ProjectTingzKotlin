package elior.com.infrastructurekotlin.RoomPackage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eliorcohen.projecttingzkotlin.RoomPackage.Converters

@Database(entities = [RoomFavorites::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RoomDatabaseFavorites : RoomDatabase() {

    abstract fun roomDaoFavorites(): RoomDaoFavorites

    companion object {
        @Volatile
        private var INSTANCE: RoomDatabaseFavorites? = null

        fun getDatabase(context: Context): RoomDatabaseFavorites {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDatabaseFavorites::class.java,
                        "room_database_favorites"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                    instance
                }
        }
    }

}
