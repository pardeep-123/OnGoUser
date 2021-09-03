package com.ongouser.home.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.CategoryAdapter
import com.ongouser.home.HomeActivity
import com.ongouser.R
import com.ongouser.base.BaseFragment
import com.ongouser.home.activity.ViewAllActivity
import com.ongouser.home.activity.product.SubCategoriesActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.CategoryListingResponse
import com.ongouser.pojo.SubCategoryListResponse
import com.ongouser.utils.others.CategoryClick
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_favorites_frag.*
import kotlinx.android.synthetic.main.fragment_favorites_frag.rec_category
import kotlinx.android.synthetic.main.fragment_favorites_frag.rl_search
import kotlinx.android.synthetic.main.fragment_favorites_frag.tvnoproduct_Fav


class CategoriesFragment : BaseFragment(), View.OnClickListener, Observer<RestObservable>,CategoryClick {

    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    lateinit var back: ImageView
    lateinit var v: View
    lateinit var rvCategory: RecyclerView
    private var categoryId=""
    private var categoryName=""
    companion object {

        fun newInstance(): CategoriesFragment {
            return CategoriesFragment()
        }
    }

    lateinit var searchEdit:EditText
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_categories, container, false)

        rvCategory = v.findViewById<RecyclerView>(R.id.rec_category)
        back = v.findViewById(R.id.back)

        back.setOnClickListener(this)
        getcategoriesApi()
        searchEdit=v.findViewById(R.id.et_search_category)
        searchEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (shop != null)
                    shop!!.filter(s.toString().trim(), rvCategory!!, tvnoproduct_category!!);
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        return v
    }

    override fun onResume() {
        super.onResume()

    }

    private fun getcategoriesApi() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map.put("searchKeyword", "")

            viewModel.getCategoryListing(requireActivity(), true, map)
            viewModel.mResponse.observe(requireActivity(), this)
        }
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.back -> {
                val i = Intent(activity, HomeActivity::class.java)
                startActivity(i)            }

        }
    }

    lateinit  var  shop : CategoryAdapter
    fun setCategoryAdapter(categoryList: ArrayList<CategoryListingResponse.Body>?) {
          shop = CategoryAdapter(requireContext(), categoryList!!,this)
        rvCategory.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvCategory.adapter = shop

    }
    private fun getSubcategoriesApi() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map.put("searchKeyword", "")
            map.put("categoryId", categoryId)

            viewModel.getshopbycatid(requireActivity()
                    , true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    fun emptyCheck(){
        if (list.isEmpty()){

            tvnoproduct_category.visibility=View.VISIBLE
            rvCategory!!.visibility=View.GONE
            rl_search_category!!.visibility=View.GONE

        }else{
            tvnoproduct_category.visibility=View.GONE
            rvCategory!!.visibility=View.VISIBLE
            rl_search_category!!.visibility=View.VISIBLE

        }
    }

    lateinit var list:ArrayList<CategoryListingResponse.Body>
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is CategoryListingResponse) {
                    val categoryListingResponse: CategoryListingResponse = it.data
                    if (categoryListingResponse.getCode() == Constants.success_code) {
                        showSuccessToast(categoryListingResponse!!.getMessage()!!)
                        list=categoryListingResponse.getBody()!!
                         setCategoryAdapter(list)

                        emptyCheck()

                    } else {
                        CommonMethods.AlertErrorMessage(
                                activity,
                                categoryListingResponse.getMessage()
                        )
                    }
                }
                if (it.data is SubCategoryListResponse) {
                    val subCategoryListResponse: SubCategoryListResponse = it.data
                    if (subCategoryListResponse.code == Constants.success_code) {
                        showSuccessToast(subCategoryListResponse!!.getMessage()!!)

                        if (subCategoryListResponse.body.size == 0) {
                            val i = Intent(context, ViewAllActivity::class.java)
                            i.putExtra("categoryId", categoryId)
                            i.putExtra("categoryName", categoryName)
                            requireActivity().startActivity(i)
                        } else {
                            val i = Intent(context, SubCategoriesActivity::class.java)
                            i.putParcelableArrayListExtra("categoryList", subCategoryListResponse.body)
                            i.putExtra("categoryName", categoryName)
                            requireActivity().startActivity(i)
                        }

                    } else {
                        CommonMethods.AlertErrorMessage(
                                activity,
                                subCategoryListResponse.getMessage()
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

    override fun categoryClickk(pos: Int, id: String, categoryName: String, listSize: Int) {
        categoryId= id
        this.categoryName= categoryName
        //  pos= position
        val i = Intent(context, ViewAllActivity::class.java)
        i.putExtra("categoryId", categoryId)
        i.putExtra("categoryName", categoryName)
        requireActivity().startActivity(i)
    }
}