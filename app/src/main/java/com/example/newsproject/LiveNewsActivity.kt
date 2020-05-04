package com.example.newsproject

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_news.*
import kotlinx.android.synthetic.main.mainsports.*

class LiveNewsActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_news)

        setSupportActionBar(toolbarNews)

        // app doesn't crash when no internet connevction
        var connectivityManager = this.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE)as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected){

        }
        else if (networkInfo == null){
            Toast.makeText(this,"Network connection not available",Toast.LENGTH_SHORT).show()
        }

        aajTak.setOnClickListener{
            Toast.makeText(this,"aaj tak",Toast.LENGTH_SHORT).show()

            val intent = Intent(this,PlayLiveActivity::class.java)
            intent.putExtra("movieid","yIO3S8A135A")
            startActivity(intent)
        }

//        abpNews.setOnClickListener {
//            Toast.makeText(this,"ABP News",Toast.LENGTH_SHORT).show()
//
//            val intent = Intent(this,PlayLiveActivity::class.java)
//            intent.putExtra("movieid","f05_dHL15pM")
//            startActivity(intent)
//        }

//        ndtv.setOnClickListener {
//            Toast.makeText(this,"NDTV",Toast.LENGTH_SHORT).show()
//
//            val intent = Intent(this,PlayLiveActivity::class.java)
//            intent.putExtra("movieid","AFNUeUed8Ro")
//            startActivity(intent)
//        }

        ndtvIndia.setOnClickListener {
            Toast.makeText(this,"NDTV India",Toast.LENGTH_SHORT).show()

            val intent = Intent(this,PlayLiveActivity::class.java)
            intent.putExtra("movieid","l9ViEIip9q4")
            startActivity(intent)
        }

        ddNews.setOnClickListener {
            Toast.makeText(this,"DD News",Toast.LENGTH_SHORT).show()

            val intent = Intent(this,PlayLiveActivity::class.java)
            intent.putExtra("movieid","wtU0zh9J2uc")
            startActivity(intent)
        }

        news24.setOnClickListener {
            Toast.makeText(this,"News24",Toast.LENGTH_SHORT).show()

            val intent = Intent(this,PlayLiveActivity::class.java)
            intent.putExtra("movieid","l9ViEIip9q4")
            startActivity(intent)
        }

        indiaTv.setOnClickListener {
            Toast.makeText(this,"India Tv",Toast.LENGTH_SHORT).show()

            val intent = Intent(this,PlayLiveActivity::class.java)
            intent.putExtra("movieid","dyx3j9Sc4Zk")
            startActivity(intent)
        }

        cnnNews18.setOnClickListener {
            Toast.makeText(this,"CNN News18",Toast.LENGTH_SHORT).show()

            val intent = Intent(this,PlayLiveActivity::class.java)
            intent.putExtra("movieid","4PWPGyYg7SM")
            startActivity(intent)
        }

        indiaToday.setOnClickListener {
            Toast.makeText(this,"India Today",Toast.LENGTH_SHORT).show()

            val intent = Intent(this,PlayLiveActivity::class.java)
            intent.putExtra("movieid","l_NIgnb9J2g")
            startActivity(intent)
        }

        news18India.setOnClickListener {
            Toast.makeText(this,"News18 India",Toast.LENGTH_SHORT).show()

            val intent = Intent(this,PlayLiveActivity::class.java)
            intent.putExtra("movieid","MEqwduoH7Zg")
            startActivity(intent)
        }


        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbarNews,
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
}
