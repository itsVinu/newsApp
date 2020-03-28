package com.example.newsproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.R
import com.example.newsproject.TopHeadlineModel.topheadlinetechnologyresponse.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class TechnologyAdapter(val list: List<ArticlesItem>):
    RecyclerView.Adapter<TechnologyAdapter.UserViewHolder>() {

    var onItemClick: ((user: ArticlesItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(user: ArticlesItem) {
            itemView.apply {
                title.text = user.title
                author.text = user.author.toString()
                publishedAt.text = user.publishedAt
                desc.text = user.description
                source.text = user.source.toString()
                Picasso.get().load( user.urlToImage.toString()).into(img)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }
}