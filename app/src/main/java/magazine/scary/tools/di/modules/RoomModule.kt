package magazine.scary.tools.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RoomModule {

//    @Singleton
//    @Provides
//    fun getImagesDAO(context: Context, @Named("dbName") dbName: String): DBHelper {
//        return Room.databaseBuilder(
//            context,
//            DBHelper::class.java, dbName
//        )
//            //.allowMainThreadQueries()
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun getDAO(imgDB: DBHelper): ImagesDAO {
//        return imgDB.getImagesDAO()
//    }
//
//    @Singleton
//    @Provides
//    @Named("dbName")
//    fun provideDbName(): String {
//        return Cons.DB_NAME
//    }


}