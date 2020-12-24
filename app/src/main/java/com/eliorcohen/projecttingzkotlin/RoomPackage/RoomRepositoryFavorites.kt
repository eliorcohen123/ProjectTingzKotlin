package elior.com.infrastructurekotlin.RoomPackage

import androidx.lifecycle.LiveData

class RoomRepositoryFavorites(private val mRoomDaoFavorites: RoomDaoFavorites) {

    val getAllData: LiveData<List<RoomFavorites>> = mRoomDaoFavorites.getAllData()

    fun insertData(roomFavorites: List<RoomFavorites>) {
        mRoomDaoFavorites.insertData(roomFavorites)
    }

}
