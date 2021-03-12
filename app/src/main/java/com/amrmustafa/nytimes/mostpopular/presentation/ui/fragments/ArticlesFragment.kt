package com.amrmustafa.nytimes.mostpopular.presentation.ui.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.Navigation
import com.amrmustafa.nytimes.base.components.ViewModelFactory
import com.amrmustafa.nytimes.base.constants.Period
import com.amrmustafa.nytimes.base.models.Status
import com.amrmustafa.nytimes.base.presentation.BaseFragment
import com.amrmustafa.nytimes.mostpopular.presentation.ui.activities.ArticlesActivity
import com.amrmustafa.nytimes.R
import com.amrmustafa.nytimes.mostpopular.presentation.viewmodel.ArticlesViewModel
import com.ouday.test.customer.presentation.ui.adapter.ArticlesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_articles.*
import javax.inject.Inject


class ArticlesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mArticlesViewModel: ArticlesViewModel

    @Inject
    lateinit var articlesRecyclerViewAdapter: ArticlesRecyclerViewAdapter

    private var period: Period = Period.DAILY
    private val periods = arrayOf("Daily", "Weekly", "Monthly")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles, container, false)
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

                mArticlesViewModel.articlesResult.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                    when (it.status) {
                        Status.LOADING -> {
                            swipeRefresh.isRefreshing = true
                        }
                        Status.ERROR -> {
                            swipeRefresh.isRefreshing = false
                            Toast.makeText(
                                requireContext(),
                                it.exception?.message ?: "",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            swipeRefresh.isRefreshing = false
                            articlesRecyclerViewAdapter.setData(it.data ?: emptyList())
                        }
                    }
                })

                if (mArticlesViewModel.articlesResult.value == null) {
                    getArticles(period)
                }

                init()

            } finally {
                //empty
            }
        }
    }

    private fun init() {
        setupHeader()
        initViews()
        implementListeners()
    }

    private fun setupHeader() {
        (activity as ArticlesActivity).updateTitle(R.string.articles_title)
        (activity as ArticlesActivity).toggleFilterAction(true)
        (activity as ArticlesActivity).setFilterAction {
            showPeriodPicker()
        }
    }

    private fun initViews() {
        rvArticles.adapter = articlesRecyclerViewAdapter
    }

    private fun implementListeners() {
        swipeRefresh.setOnRefreshListener {
            getArticles(period, true)
        }
        articlesRecyclerViewAdapter.setOnArticlesClickListener {
            view?.let { v ->
                mArticlesViewModel.article = it
                Navigation.findNavController(v).navigate(R.id.action_articles_details)
            }
        }
    }

    private fun showPeriodPicker() {
        AlertDialog.Builder(requireContext()).setItems(
            periods
        ) { dialog, itemId ->
            getArticles(Period.getPeriod(itemId))
        }.show()
    }

    private fun getArticles(period: Period, forceNetwork: Boolean = false) {
        this.period = period
        mArticlesViewModel.getArticlesAysnc(period, forceNetwork)
    }

}
