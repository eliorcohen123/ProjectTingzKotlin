package elior.com.infrastructurekotlin.RoomPackage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RoomViewModelFavorites(application: Application) : AndroidViewModel(application) {

    private val mRepositoryFavorites: RoomRepositoryFavorites

    init {
        val mRoomDaoFavorites = RoomDatabaseFavorites.getDatabase(application).roomDaoFavorites()
        mRepositoryFavorites = RoomRepositoryFavorites(mRoomDaoFavorites)
    }

    fun getAllData(): LiveData<List<RoomFavorites>> {
        return mRepositoryFavorites.getAllData
    }

    fun insertData(roomFavorites: List<RoomFavorites>) = viewModelScope.launch {
        mRepositoryFavorites.insertData(roomFavorites)
    }

}
