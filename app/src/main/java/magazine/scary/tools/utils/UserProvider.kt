package magazine.scary.tools.utils

import com.pixabay.utils.models.UserModel
import javax.inject.Inject

class UserProvider @Inject constructor(
    private val dataProvider: DataProvider
) {

    fun setUser(user: UserModel?) {
        if (user != null)
            dataProvider.saveData("user", user)
    }

    fun getUser(): UserModel? {
        return (dataProvider.getData("user") as UserModel)
    }
    fun logOff() {
        return dataProvider.removeData("user")
    }


}