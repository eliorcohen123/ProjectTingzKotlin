package elior.com.infrastructurekotlin.RoomPackage

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoomDaoFavorites {

    @Query("SELECT * from room_database_favorites ORDER BY releaseYear DESC")
    fun getAllData(): LiveData<List<RoomFavorites>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(roomFavorites: List<RoomFavorites>)
}
