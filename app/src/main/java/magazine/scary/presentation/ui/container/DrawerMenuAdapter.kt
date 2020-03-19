package magazine.scary.presentation.ui.container

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.pixabay.utils.views.MyTextView
import magazine.scary.R
import magazine.scary.domain.entities.MenuModel

class DrawerMenuAdapter(private val context: Context?, private val items: List<MenuModel>) : BaseAdapter() {

    private val mInflator: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.menu_list_item, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.title.text = items[position].title
        vh.icon.setImageDrawable(context?.let { ContextCompat.getDrawable(it, items[position].icon) })
        return view
    }

    class ListRowHolder(row: View?) {
        val title: MyTextView = row?.findViewById(R.id.title) as MyTextView
        val icon: ImageView = row?.findViewById(R.id.icon) as ImageView
    }


}
