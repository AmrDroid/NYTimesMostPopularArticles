package com.ouday.test.customer.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrmustafa.nytimes.R
import com.amrmustafa.nytimes.mostpopular.data.models.Media
import com.amrmustafa.nytimes.mostpopular.data.models.Article
import com.amrmustafa.nytimes.mostpopular.utils.BindingUtils
import kotlinx.android.synthetic.main.articles_item.view.*
import javax.inject.Inject

class ArticlesRecyclerViewAdapter @Inject constructor() :
    RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ViewHolder>() {

    private val news: ArrayList<Article> = ArrayList()

    private var onNewsArticleClickListener: ((article: Article) -> Unit)? = null

    fun setOnArticlesClickListener(onNewsArticleClickListener: ((article: Article) -> Unit)) {
        this.onNewsArticleClickListener = onNewsArticleClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.articles_item,
                parent,
                false
            )
        )
    }

    fun setData(news: List<Article>) {
        this.news.clear()
        this.news.addAll(news)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) {

            itemView.tvNewsTitle.text = article.title
            itemView.tvNewsDate.text = article.publishedDate
            itemView.authorTextView.text = article.byline
            article.media.firstOrNull()?.getMetaData(Media.MediaSize.THUMB)?.let {
                BindingUtils.setImageUrlCrop(itemView.ivImage,it.url)
            }

            itemView.setOnClickListener {
                onNewsArticleClickListener?.invoke(article)
            }
        }

    }

}