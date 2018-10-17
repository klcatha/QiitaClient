package com.example.kengomaruyama.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import com.example.kengomaruyama.qiitaclient.model.Article
import com.example.kengomaruyama.qiitaclient.model.User
import com.example.kengomaruyama.qiitaclient.view.ArticleView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject
import kotterknife.bindView

class MainActivity : RxAppCompatActivity() {

    @Inject
    lateinit var articleClient: ArticleClient

    val listView: ListView by bindView(R.id.list_view)

    val progressBar: ProgressBar by bindView(R.id.progress_bar)

    val searchButton: Button by bindView(R.id.search_button)

    val queryEditText: EditText by bindView(R.id.query_edit_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QiitaClientApp).component.inject(this)
        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(applicationContext)
        listView.adapter = listAdapter
        listView.setOnItemClickListener{ adapterView, view, position, id ->
            val intent = ArticleActivity.intent(this, listAdapter.articles[position])
            startActivity(intent)
        }

        searchButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate{
                        progressBar.visibility = View.GONE
                    }
                    .bindToLifecycle(this)
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    },{
                        toast("エラー:$it")
                    })
        }
    }
}
