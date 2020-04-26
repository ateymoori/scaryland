package magazine.scary.tools.utils

import android.content.Context
import com.orhanobut.hawk.Hawk
import javax.inject.Inject

class DataProvider @Inject constructor(
    private val context: Context
) {
    fun saveData(key: String, data: Any?) {
        Hawk.put(key, data)
    }

    fun getData(key: String): Any? {
        return Hawk.get(key, null)
    }

    fun removeData(key: String){
        Hawk.delete(key)
    }
    fun cleanAll(key: String){
        Hawk.deleteAll()
    }

}