package com.ongouser.home.activity.payment

/*tasks needs to be done from backend
* delete car
*
*  */


import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.AddCardResponse
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.viewmodel.HomeViewModel
import com.stripe.android.view.CardNumberEditText
import kotlinx.android.synthetic.main.activity_add_card.*
import java.util.*


class AddCardDetailActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable> {
    var temp = 2
    var saveCard = "2"
    private lateinit var mContext: AddCardDetailActivity
    var etCardNumber: CardNumberEditText? = null

    //   private var signInViewModel: SignupViewModel? = null
    var current_year: Int = 0
    var future_year = 40
    var cardType = ""
    var cardId = ""
    lateinit var yearArray: Array<String?>
    var expDate = ""
    internal var items = arrayOf<CharSequence>(
            "01",
            "02",
            "03",
            "04",
            "05",
            "06",
            "07",
            "08",
            "09",
            "10",
            "11",
            "12"
    )

    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }


    override fun getContentId(): Int {
        return R.layout.activity_add_card
    }

    var isEdit=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        etCardNumber = findViewById(R.id.et_card_number)
        btnSave.setOnClickListener(mContext)
        //   Relativ_checkbox22.setOnClickListener(mContext)
        ivBack.setOnClickListener(mContext)





        tv_expiry_month.setOnClickListener(mContext)
        tv_expiry_year.setOnClickListener(mContext)

        current_year = Calendar.getInstance().get(Calendar.YEAR)

        yearArray = arrayOfNulls<String>(future_year)

        for (i in 0 until future_year) {
            yearArray[i] = (current_year + i).toString()
        }
        if (intent.extras !=null){
            isEdit="1"
            tv_title_add.text="Edit Card"
            btnSave.text="Update"
            cardId= intent.getStringExtra("id")!!
            et_card_name.setText(intent.getStringExtra("name"))

            etCardNumber!!.setText(intent.getStringExtra("cardNumber"))
            tv_expiry_year!!.setText(intent.getStringExtra("year"))
            if (intent.getStringExtra("month").toString().length==1){
                tv_expiry_month!!.setText("0"+intent.getStringExtra("month"))

            }else{
                tv_expiry_month!!.setText(intent.getStringExtra("month"))

            }
            cardType=intent.getStringExtra("cardType")!!
            if (cardType.equals("0")) {
                etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_visa, 0, 0, 0)
            } else if (cardType.equals("1")) {
                etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mastercard, 0, 0, 0)
            }
        }
        etCardNumber!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val ccNum = s.toString()

                if (ccNum.length >= 2) {
                    for (i in 0 until CommonMethods.cardTypeModelSet().size) {
                        if (ccNum.substring(0, 2).matches(CommonMethods.cardTypeModelSet().get(i).regx.toRegex())) {
                            cardType = CommonMethods.cardTypeModelSet().get(i).getType()
                            if (cardType.equals("0")) {
                                etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_visa, 0, 0, 0)
                            } else if (cardType.equals("1")) {
                                etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mastercard, 0, 0, 0)
                            } else {
                            }
                            Log.e("card_type_and_name", CommonMethods.cardTypeModelSet().get(i).getType().toString() + "," +
                                    CommonMethods.cardTypeModelSet().get(i).getName())
                        }

                    }
                } else {
                    cardType = ""
                    if (cardType.equals("0")) {
                        etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_visa, 0, 0, 0)
                    } else if (cardType.equals("1")) {
                        etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mastercard, 0, 0, 0)
                    } else {
                        etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    }
                    Log.e("card_type_and_name", " ")
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val ccNum = s.toString()
                if (ccNum.length >= 2) {


                }
                if (!etCardNumber!!.text.toString().equals("")) {
/*
                    for (i in 0 until CommonMethods.cardTypeModelSet().size) {


                        if (ccNum.substring(0, 2).matches(CommonMethods.cardTypeModelSet().get(i).getRegx().toRegex())) {
                            //etCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, imageArray[i], 0);
                            cardType = CommonMethods.cardTypeModelSet().get(i).getType()
                            if (cardType == "0") {
                                etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_visa, 0, 0, 0)
                            } else if (cardType == "1") {
                                etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mastercard, 0, 0, 0)
                            } else {
                                etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            }
                            Log.e("card_type_and_name", CommonMethods.cardTypeModelSet().get(i).getType().toString() + "," +
                                    CommonMethods.cardTypeModelSet().get(i).getName())
                        }


                    }
*/
                } else {
                    cardType = ""
                    if (cardType == "0") {
                        etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_visa, 0, 0, 0)
                    } else if (cardType == "1") {
                        etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mastercard, 0, 0, 0)
                    } else {
                        etCardNumber!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    }
                    Log.e("card_type_and_name", " ")

                    // etCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.allcards, 0);
                }
            }
        })


    }


    private fun isValid(): Boolean {
        var check = false
        if (!mValidationClass.isNetworkConnected)
            showAlerterRed(resources.getString(R.string.no_internet))
        else if (mValidationClass.checkStringNull(et_card_name.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.cardname_missing_message))
        else if (mValidationClass.checkStringNull(et_card_number.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.cardnumber_missing_message))
        else if (etCardNumber!!.text!!.length != 19)
            showAlerterRed(resources.getString(R.string.invalid_card_message))
        else if (mValidationClass.checkStringNull(tv_expiry_month.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.month_missing_message))
        else if (mValidationClass.checkStringNull(tv_expiry_year.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.year_missing_message))
        /*else if (mValidationClass.checkStringNull(et_cvv.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_cvv))
        else if (et_cvv.text.toString().length < 3)
            showAlerterRed(resources.getString(R.string.error_cvv_length))*/
        else
            check = true
        return check
    }


    private fun openMonth() {
        val builder = AlertDialog.Builder(mContext)
        builder.setTitle(resources.getString(R.string.expiry_month))
        builder.setItems(items, DialogInterface.OnClickListener { dialog, item ->
            // Do something with the selection
            tv_expiry_month.setText(items[item])
        })
        val alert = builder.create()
        alert.show()

    }


    private fun openYear() {
        val builder = AlertDialog.Builder(mContext)
        builder.setTitle(resources.getString(R.string.expiry_year))
        builder.setItems(
                yearArray,
                DialogInterface.OnClickListener { dialog, item -> tv_expiry_year.setText(yearArray!![item]) })
        val alert = builder.create()
        alert.show()
    }

    fun addCardAPI() {
        if (isValid()) {

            if (etCardNumber!!.text!!.length == 19) {

                if (!etCardNumber!!.isCardNumberValid) {
                    CommonMethods.AlertErrorMessage(mContext, "Please enter valid card number")
                } else {
                    if (!mValidationClass.isNetworkConnected){
                        showAlerterRed(resources.getString(R.string.no_internet))
                    }
                    else{

                        if (isEdit.isEmpty()){
                            val map = HashMap<String, String>()
                            map.put("cardType", cardType)
                            map.put("name", et_card_name.text.toString().trim())
                            map.put("cardNumber", et_card_number.text.toString())
                            map.put("month", tv_expiry_month.text.toString())
                            map.put("year", tv_expiry_year.text.toString())

                            viewModel.addCardAPI(this, true, map)
                            viewModel.mResponse.observe(this, this)
                        }else{
                            val map = HashMap<String, String>()
                            map.put("id", cardId)
                            map.put("cardType", cardType)
                            map.put("name", et_card_name.text.toString().trim())
                            map.put("cardNumber", et_card_number.text.toString())
                            map.put("month", tv_expiry_month.text.toString())
                            map.put("year", tv_expiry_year.text.toString())

                            viewModel.updateCardAPI(this, true, map)
                            viewModel.mResponse.observe(this, this)
                        }


                    }

                }
            } else {
                CommonMethods.failureMethod(mContext, "Please enter minimum 16 digit card number")
            }


        }
    }

    override fun onClick(view: View?) {

        val itemid = view!!.id
        when (itemid) {
            R.id.ivBack -> {
                onLeftIconClick()
            }
            R.id.btnSave -> {
                addCardAPI()
            }
            R.id.tv_expiry_year -> {
                openYear()
            }
            R.id.tv_expiry_month -> {
                openMonth()
            }
        }

    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is AddCardResponse) {
                    val addCardResponse: AddCardResponse = it.data
                    if (addCardResponse!!.getCode() == Constants.success_code) {
                        showSuccessToast(mContext, addCardResponse.getMessage()!!)
                        finish()
                    }

                    else{
                        showAlerterRed(addCardResponse.getMessage() as String)

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