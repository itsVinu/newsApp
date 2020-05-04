package com.example.newsproject

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.Adapter.EveryAdapter
import com.example.newsproject.Adapter.ScienceAdapter
import com.example.newsproject.TopHeadlineModel.topheadlinescienceresponse.ArticlesItem
import com.example.newsproject.client.Client
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.mainscience.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScienceActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    lateinit var mAdView : AdView

    val list = arrayListOf<ArticlesItem>()
    val scienceadapter = ScienceAdapter(list)

    val list1 = arrayListOf<com.example.newsproject.TopHeadlineModel.everythingresponse.ArticlesItem>()
    val everyadapter = EveryAdapter(list1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science)

        setSupportActionBar(toolbar6)

        val adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-1351937667691042/4620415174"

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        val adSize = AdSize(300, 50)

        recyclerView8.apply {
            layoutManager = LinearLayoutManager(this@ScienceActivity, RecyclerView.VERTICAL,false)
            adapter = scienceadapter
        }

        scienceadapter.onItemClick = {

            Toast.makeText(this, "Top Headlines", Toast.LENGTH_SHORT).show()

            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("category desc",it.description.toString())
            intent.putExtra("category url",it.url.toString())
            intent.putExtra("category author",it.author.toString())
            intent.putExtra("category published",it.publishedAt.toString())
            intent.putExtra("category title",it.title.toString())
            intent.putExtra("category image",it.urlToImage.toString())
            intent.putExtra("category source",it.source.toString())
            intent.putExtra("category titlebar", it.source?.name.toString())
            startActivity(intent)
        }

        var connectivityManager = this.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE)as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected){
            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getTopHeadlinesScience() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.articles?.let { list.addAll(it)
                            Log.i("abc",it.toString())}
                        runOnUiThread { scienceadapter.notifyDataSetChanged() }
                    }
                }
            }
        }
        else if (networkInfo == null){
            Toast.makeText(this,"Network connection not available",Toast.LENGTH_SHORT).show()
        }


        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar6,
            R.string.open,
            R.string.close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.topHeadlines -> {
                startActivity(Intent(this, MainActivity::class.java))
                Toast.makeText(this,"TopHeadlines", Toast.LENGTH_SHORT).show()
                finish()
            }
//            R.id.sources -> {
//                startActivity(Intent(this, Main3Activity::class.java))
//                 Toast.makeText(this,"Sources", Toast.LENGTH_SHORT).show()
//                finish()
//            }
            R.id.sports -> {
                startActivity(Intent(this, SportsActivity::class.java))
                Toast.makeText(this,"Sports", Toast.LENGTH_SHORT).show()
//                finish()
            }
            R.id.business -> {
                startActivity(Intent(this, BusinessActivity::class.java))
                Toast.makeText(this,"Business", Toast.LENGTH_SHORT).show()
//                finish()
            }
            R.id.entertainment -> {
                startActivity(Intent(this, EntertainmentActivity::class.java))
                Toast.makeText(this,"Entertainment", Toast.LENGTH_SHORT).show()
//                finish()
            }
            R.id.health -> {
                startActivity(Intent(this, HealthActivity::class.java))
                Toast.makeText(this,"Health", Toast.LENGTH_SHORT).show()
//                finish()
            }
            R.id.science -> {
                startActivity(Intent(this, ScienceActivity::class.java))
                Toast.makeText(this,"Science", Toast.LENGTH_SHORT).show()
//                finish()
            }
            R.id.liveNews -> {
                startActivity(Intent(this, LiveNewsActivity::class.java))
                Toast.makeText(this,"Politics", Toast.LENGTH_SHORT).show()
//                finish()
            }
            R.id.technology -> {
                startActivity(Intent(this, TechnologyActivity::class.java))
                Toast.makeText(this,"Technology", Toast.LENGTH_SHORT).show()
//                finish()
            }
        }

        drawer.closeDrawer(GravityCompat.START)   //used to close the navigation drawer when the items inside the drawer are clicked
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)

        val item = menu?.findItem(R.id.search)

        val searchView = item?.actionView as androidx.appcompat.widget.SearchView

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.queryHint = "search here..."
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(p0: String?): Boolean {

                if (p0?.length!! > 2){
                    loadJson(p0.toString())
                    everyadapter.notifyDataSetChanged()
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                loadJson(p0.toString())
                everyadapter.notifyDataSetChanged()
                return false
            }

        })
        item.icon.setVisible(false,false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id:Int = item.itemId

        if (id == R.id.search){
            Toast.makeText(this,"search the news content here", Toast.LENGTH_LONG).show()
        }
        return true
    }

    public fun loadJson(keyword:String){

        var country = Utils.country
//        var language = Utils.Language

        everyadapter.notifyDataSetChanged()
        if (keyword.length > 2){

            recyclerView8.apply {
                layoutManager = LinearLayoutManager(this@ScienceActivity,RecyclerView.VERTICAL,false)
                adapter = everyadapter
            }
            everyadapter.notifyDataSetChanged()

            everyadapter.onItemClick = {

                Toast.makeText(this, "Top Headlines", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,DetailActivity::class.java)
                intent.putExtra("category desc",it.description.toString())
                intent.putExtra("category url",it.url.toString())
                intent.putExtra("category author",it.author.toString())
                intent.putExtra("category published",it.publishedAt.toString())
                intent.putExtra("category title",it.title.toString())
                intent.putExtra("category image",it.urlToImage.toString())
                intent.putExtra("category source",it.source.toString())
                intent.putExtra("category titlebar", it.source?.name.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO){Client.api.getEveryHeadlines(keyword,
                    "87549592e55f41b986f248237a219d90")
                }
                if (response.isSuccessful){
                    response.body()?.let { res ->
                        res.articles?.let {
                            list1.clear()
                            list1.addAll(it)
                            Log.i("abc", it.toString())
                        }
                        runOnUiThread { everyadapter.notifyDataSetChanged() }
                    }
                }
            }
        }
        else {
            recyclerView8.apply {
                layoutManager = LinearLayoutManager(this@ScienceActivity,RecyclerView.VERTICAL,false)
                adapter = scienceadapter
            }

            scienceadapter.onItemClick = {

                Toast.makeText(this, "Top Headlines", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,DetailActivity::class.java)
                intent.putExtra("category desc",it.description.toString())
                intent.putExtra("category url",it.url.toString())
                intent.putExtra("category author",it.author.toString())
                intent.putExtra("category published",it.publishedAt.toString())
                intent.putExtra("category title",it.title.toString())
                intent.putExtra("category image",it.urlToImage.toString())
                intent.putExtra("category source",it.source.toString())
                intent.putExtra("category titlebar", it.source?.name.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getTopHeadlinesScience() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.articles?.let {
                            list1.clear()
                            list.addAll(it)
                            Log.i("abc",it.toString())}
                        runOnUiThread { scienceadapter.notifyDataSetChanged() }
                    }
                }
            }
        }
    }
}

