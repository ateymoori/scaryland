package magazine.scary.presentation.ui.translate

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pixabay.utils.models.LanguageModel
import com.pixabay.utils.models.Success
import com.pixabay.utils.tools.AppUtils
import com.pixabay.utils.tools.log
import com.pixabay.utils.tools.toast
import com.pixabay.utils.views.ExpandedBottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_translate.*
import magazine.scary.R
import magazine.scary.presentation.ui.story_detail.StoryDetailReaderViewModel
import magazine.scary.tools.utils.Cons
import okhttp3.ResponseBody
import org.json.JSONArray
import javax.inject.Inject

class TranslateFragment : ExpandedBottomSheetDialog(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TranslateViewModel

    private lateinit var languagesListJS: Array<LanguageModel>
    private lateinit var selectedLangCode: String
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
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(TranslateViewModel::class.java)


        originalText.text = word

        languagesList.setTitle("Select Language");
        languagesList.setPositiveButton("OK");


        val langs = AppUtils.assetsToString(activity, "languages.json")

        val gson = Gson()
        val arrayTutorialType = object : TypeToken<Array<LanguageModel>>() {}.type
        languagesListJS = gson.fromJson(langs, arrayTutorialType)

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
        languagesList.onItemSelectedListener = this


        viewModel.translateResult.observe(viewLifecycleOwner, Observer {
            if (it is Success) {
                val value = (it.data as String)
                val jsArrayA = JSONArray(value)
                val jsArrayB = JSONArray(jsArrayA[0].toString())
                val jsArrayC = JSONArray(jsArrayB[0].toString())
                val result = jsArrayC[0].toString()
                translated.text = result
            }
        })
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(adapter: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val selectedLang = adapter?.getItemAtPosition(position) as? String
        selectedLang?.toast(activity)
        languagesListJS.forEach {
            if ("${it.name} (${it.nativeName})" == selectedLang) {
                selectedLangCode = it.code
                viewModel.translate(selectedLangCode, word)
            }
        }
    }


}
