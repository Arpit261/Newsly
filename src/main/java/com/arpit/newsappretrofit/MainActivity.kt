package com.arpit.newsappretrofit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.browser.customtabs.CustomTabsIntent
import com.arpit.newsappretrofit.adaptor.NewsAdaptor

import com.arpit.newsappretrofit.models.Article
import com.arpit.newsappretrofit.models.News
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.single_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NewsAdaptor.onItemClicked {

    private lateinit var newsAdaptor: NewsAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()

    }

    private fun getNews() {

        val news = NewsServices.newsInstance.getHeadlines("IN")
        news.enqueue(object : Callback<News> {

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("News", "Error in fetching news", t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news = response.body()
                if (news != null) {
                    Log.d("News", news.toString())
                    newsAdaptor = NewsAdaptor(news.articles, this@MainActivity)
                    recyclerView.adapter = newsAdaptor
                    val manager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
                    manager.setPagerMode(true)
                    manager.setPagerFlingVelocity(500)
                    recyclerView.layoutManager = manager
                }
            }
        })
    }

    override fun clicked(item: Article) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))

    }
    //To Create Menus Items
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.categories_menus, menu)
        return true
    }


    fun shareNews() {
        shareNews.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT ,"Checkout This News ,$currentUrl")
            val chooser = Intent.createChooser(intent ,"News")
            startActivity(chooser)
        }
    }
}