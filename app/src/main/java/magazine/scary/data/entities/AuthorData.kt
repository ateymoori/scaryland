package magazine.scary.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "authors")
data class AuthorData(
    @PrimaryKey
    val id: Int,
    val name_family: String,
    val description: String? = null,
    val image: String
) : Parcelable
