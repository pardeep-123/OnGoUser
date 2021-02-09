package com.ongouser.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.CategoryAdapter
import com.ongouser.home.HomeActivity
import com.ongouser.R
import com.ongouser.base.BaseFragment
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.CategoryListingResponse
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.HomeViewModel


class CategoriesFragment : BaseFragment(), View.OnClickListener, Observer<RestObservable> {

    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    lateinit var back: ImageView
    lateinit var v: View
    lateinit var rvCategory: RecyclerView

    companion object {

        fun newInstance(): CategoriesFragment {
            return CategoriesFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_categories, container, false)

        rvCategory = v.findViewById<RecyclerView>(R.id.rec_category)
        back = v.findViewById(R.id.back)

        back.setOnClickListener(this)


        return v
    }

    override fun onResume() {
        super.onResume()
        getcategoriesApi()
    }

    private fun getcategoriesApi() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map.put("searchKeyword", "")

            viewModel.getCategoryListing(requireActivity(), true, map)
            viewModel.mResponse.observe(this, this)
        }
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.back -> {
                val i = Intent(activity, HomeActivity::class.java)
                startActivity(i)            }

        }
    }

    fun setCategoryAdapter(categoryList: ArrayList<CategoryListingResponse.Body>?) {
        val shop = CategoryAdapter(activity, categoryList)
        rvCategory.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvCategory.adapter = shop

    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is CategoryListingResponse) {
                    val categoryListingResponse: CategoryListingResponse = it.data
                    if (categoryListingResponse.getCode() == Constants.success_code) {
                        showSuccessToast(categoryListingResponse!!.getMessage()!!)

                         setCategoryAdapter(categoryListingResponse.getBody())

                    } else {
                        CommonMethods.AlertErrorMessage(
                                activity,
                                categoryListingResponse.getMessage()
                        )
                    }
                }

            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    showAlerterRed(it.data as String)
                } else {
                    showAlerterRed(it.error!!.toString())
                }
            }
            it.status == Status.LOADING -> {

            }
        }

    }
}