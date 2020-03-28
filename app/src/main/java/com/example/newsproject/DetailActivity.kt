package com.example.newsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import com.example.newsproject.TopHeadlineModel.topheadlineresponse.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    val list = arrayListOf<ArticlesItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var image = intent.getStringExtra("category image")
        var title1 = intent.getStringExtra("category title")
        var desc = intent.getStringExtra("category desc")
        var source = intent.getStringExtra("category source")
        var published = intent.getStringExtra("category published")
        var author = intent.getStringExtra("category author")
        var url = intent.getStringExtra("category url")
        var titlebar = intent.getStringExtra("category titlebar")


        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        fun onBackPressed(){
            if (webView.canGoBack()){
                webView.goBack()
            }else
                super.onBackPressed()
        }

        Picasso.get().load(image).into(backdrop)
        subtitle_on_appbar.setText(url)
        title_of_news.setText(title1)
        date_ago.setText(published)
        time_ago.setText(source)
        title_on_appbar.setText(titlebar)
    }
}
