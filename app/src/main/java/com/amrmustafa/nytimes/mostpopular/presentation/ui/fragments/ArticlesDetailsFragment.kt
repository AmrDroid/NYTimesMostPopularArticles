package com.amrmustafa.nytimes.mostpopular.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.amrmustafa.nytimes.base.components.ViewModelFactory
import com.amrmustafa.nytimes.mostpopular.presentation.ui.activities.ArticlesActivity
import com.amrmustafa.nytimes.R
import com.amrmustafa.nytimes.mostpopular.presentation.viewmodel.ArticlesViewModel
import com.amrmustafa.nytimes.mostpopular.utils.BindingUtils
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_articles_details.*
import javax.inject.Inject


class ArticlesDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mArticlesViewModel: ArticlesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            try {
                mArticlesViewModel =
                    ViewModelProvider(
                        requireActivity(),
                        viewModelFactory
                    ).get(ArticlesViewModel::class.java)

                init()

            } finally {
                //empty
            }
        }
    }

    private fun init() {
        setupHeader()
        initViews()
    }

    private fun setupHeader() {
        (activity as ArticlesActivity).updateTitle(R.string.articles_details_title)
        (activity as ArticlesActivity).toggleFilterAction(false)
    }

    private fun initViews() {
        mArticlesViewModel.article?.let { articles ->
            tvArticleTitle.text = articles.title
            urlTextView.text=articles.url
            dateTextView.text=articles.publishedDate
            contentTextView.text = articles.abstract
            authorTextView.text = articles.byline
            articles.media.firstOrNull()?.getMetaData()?.let {
                BindingUtils.setImageUrl(coverImageView,it.url)
            }
        }
    }

}
