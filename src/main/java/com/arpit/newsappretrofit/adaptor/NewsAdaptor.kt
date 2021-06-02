package com.arpit.newsappretrofit.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arpit.newsappretrofit.R
import com.arpit.newsappretrofit.models.Article
import com.bumptech.glide.Glide
class NewsAdaptor(private var itemList:MutableList<Article>, private val listner:onItemClicked):
    RecyclerView.Adapter<NewsAdaptor.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
      val view=LayoutInflater.from(parent.context)
          .inflate(R.layout.single_layout,parent,false)

        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
      val news=itemList[position]
        holder.sourceName.text="Tap to read more about this article ${news.source.name}"
        holder.newsDescription.text=news.description
        holder.newsTitle.text=news.title


        holder.relativeLayout.setOnClickListener {
            listner.clicked(itemList[holder.adapterPosition])
        }

        Glide.with(holder.itemView.context).load(news.urlToImage).into(holder.newsImage)


    }



    class NewsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val newsImage:ImageView=view.findViewById(R.id.newsImage)
        val newsTitle:TextView=view.findViewById(R.id.Title)
        val newsDescription:TextView=view.findViewById(R.id.description)
        val relativeLayout :RelativeLayout = view.findViewById(R.id.RelativeLayout)
        val sourceName:TextView=view.findViewById(R.id.SourceName)

    }



    interface onItemClicked{
        fun clicked(item:Article)
    }

fun updateList(list:MutableList<Article>){
    itemList.clear()
    itemList.addAll(list)
    notifyDataSetChanged()
}
}

