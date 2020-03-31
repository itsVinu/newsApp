package com.example.newsproject

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsproject.TopHeadlineModel.topheadlineresponse.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    val list = arrayListOf<ArticlesItem>()

    lateinit var url:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)

        var image = intent.getStringExtra("category image")
        var title1 = intent.getStringExtra("category title")
        var desc = intent.getStringExtra("category desc")
        var source = intent.getStringExtra("category source")
        var published = intent.getStringExtra("category published")
        var author = intent.getStringExtra("category author")
        url = intent.getStringExtra("category url")
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

        if (Build.VERSION.SDK_INT >=21){
            val window = this.window

            window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.colorTransparent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu2, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var id= item.itemId

        if (id == R.id.share){

            try {

                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plan"
                i.putExtra(Intent.EXTRA_SUBJECT, Uri.parse("source"))

//                val body: String = mTitle + "\n" + mUrl + "\n" + "Share from news app" + "\n"

                i.putExtra(Intent.EXTRA_TEXT, url)
                startActivity(Intent.createChooser(i, "Share with: "))


//                var intent = Intent(Intent.ACTION_SEND)
//                intent.setType("text/plan")
//                intent.putExtra(Intent.EXTRA_SUBJECT, SOURCE_CHOICE)
//                val body:String = Title.toTitle() + "\n" + Url + "\n" + "share from news app" + "\n"


            }
            catch(e:Exception) {
                Toast.makeText(this,"sorry \n cannot be shared", Toast.LENGTH_SHORT).show()
            }

        }

        return super.onOptionsItemSelected(item)
    }


}

