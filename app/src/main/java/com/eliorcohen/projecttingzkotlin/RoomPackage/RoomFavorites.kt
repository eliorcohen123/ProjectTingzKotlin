package elior.com.infrastructurekotlin.RoomPackage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.eliorcohen.projecttingzkotlin.RoomPackage.Converters
import java.io.Serializable

@Entity(tableName = "room_database_favorites")
class RoomFavorites(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "rating") var rating: Double,
    @ColumnInfo(name = "releaseYear") var releaseYear: Int,
    @TypeConverters(Converters::class) @ColumnInfo(name = "genre") var genre: List<String>
) : Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var ID: Long = 0

}
