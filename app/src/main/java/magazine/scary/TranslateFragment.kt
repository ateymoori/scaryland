package magazine.scary

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pixabay.utils.models.LanguageModel
import com.pixabay.utils.tools.log
import com.pixabay.utils.tools.toast
import com.pixabay.utils.views.ExpandedBottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_translate.*
import magazine.scary.tools.utils.Cons
import java.io.IOException
import java.io.InputStream


class TranslateFragment : ExpandedBottomSheetDialog() {
    lateinit var word: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        arguments?.let {
            word = it.getString(Cons.ITEM_BUNDLE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        languagesList.setTitle("Select Language");
        languagesList.setPositiveButton("OK");

        word.toast(activity)

        val langs = getLanguages("languages.json")

        val gson = Gson()
        val arrayTutorialType = object : TypeToken<Array<LanguageModel>>() {}.type
        var languagesListJS: Array<LanguageModel> = gson.fromJson(langs, arrayTutorialType)

        val languages = mutableListOf<String>()
        languagesListJS.forEach {
            languages.add("${it.name} (${it.nativeName})")
        }

        if (activity != null) {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                activity as Context,
                android.R.layout.simple_spinner_item, languages
            )
            languagesList.adapter = adapter
        }

        langs.toString().log("ddddddd")
    }

    private fun getLanguages(file: String): String? {
        var json: String? = null
        try {
            val inputStream: InputStream? = activity?.assets?.open(file)
            if (inputStream != null) {

                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }
}
