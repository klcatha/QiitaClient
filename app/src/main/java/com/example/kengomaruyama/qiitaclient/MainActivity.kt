package com.example.kengomaruyama.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kengomaruyama.qiitaclient.model.Article
import com.example.kengomaruyama.qiitaclient.model.User
import com.example.kengomaruyama.qiitaclient.view.ArticleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ArticleViewオブジェクトを生成
        val articleView = ArticleView(applicationContext)

        // Articleオブジェクトを生成して、ArticleViewオブジェクトにセット
        articleView.setArticle(Article(id="123",
                title = "Kotlin入門",
                url = "http://www.example.com/articles/123",
                user = User(id="123",name = "たろう",profileImageUrl = "")))

        // このアクティビティにArticleViewオブジェクトをセット
        setContentView(articleView)
    }
}
