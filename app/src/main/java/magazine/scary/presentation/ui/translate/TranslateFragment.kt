package magazine.scary.presentation.ui.translate

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pixabay.utils.models.LanguageModel
import com.pixabay.utils.models.Success
import com.pixabay.utils.tools.AppUtils
import com.pixabay.utils.tools.log
import com.pixabay.utils.views.ExpandedBottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_translate.*
import kotlinx.android.synthetic.main.fragment_translate.back
import kotlinx.android.synthetic.main.fragment_movie_details.*
import magazine.scary.R
import magazine.scary.tools.utils.Cons
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

        back.setOnClickListener { dismiss() }

        originalText.text = word

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

        viewModel.translateViewState.observe(viewLifecycleOwner, Observer {
            translated.text = it.data?.results
        })

        languagesList.setTitle("Select Language (${languagesListJS.size} Languages)")
        languagesList.setPositiveButton("OK")


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(adapter: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val selectedLang = adapter?.getItemAtPosition(position) as? String
        languagesListJS.forEach {
            if ("${it.name} (${it.nativeName})" == selectedLang) {
                selectedLangCode = it.code
                viewModel.translate(selectedLangCode, word.replace(";", ""))
            }
        }
    }
}
