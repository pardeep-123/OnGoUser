package com.ongouser.Login

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.facebook.FacebookException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult

import com.ongouser.Home.HomeActivity

import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status

import com.ongouser.pojo.SocialLoginResponse
import com.ongouser.utils.helperclasses.FacebookHelper
import com.ongouser.utils.helperclasses.FacebookHelper.*
import com.ongouser.utils.helperclasses.GoogleHelper
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.Constants.Companion.SecurityKeyValue
import com.ongouser.utils.others.MyApplication
import com.ongouser.utils.others.SharedPrefUtil
import com.ongouser.viewmodel.AuthViewModel
import com.trutraits.pojo.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable>,
    FacebookHelper.FacebookHelperCallback {
    private val viewModel: AuthViewModel
            by lazy { ViewModelProviders.of(this).get(AuthViewModel::class.java) }

    lateinit var mContext: LoginActivity
    var facebookHelper: FacebookHelper? = null
    lateinit var googleHelper: GoogleHelper
    var isFb = ""
    var socialId = ""
    var socialType = ""
    var socialName = ""
    var socialEmail = ""
    var socialImage = ""
    var newToken = ""
    lateinit var emailVerificationDialog: Dialog

    override fun getContentId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        facebookHelper = FacebookHelper(mContext, this)

        tvForgotpassword.setOnClickListener(mContext)
        tvCreateAccount.setOnClickListener(mContext)
        btnSignin.setOnClickListener(mContext)

/*
        iv_fb.setOnClickListener {
            if (MyApplication.instance!!.checkIfHasNetwork()) {
                isFb = "fb"
                facebookHelper!!.login(mContext)
            } else {
                showAlerterRed(getString(R.string.no_internet))
            }
        }
*/

/*
        iv_google.setOnClickListener {
            if (MyApplication.instance!!.checkIfHasNetwork()) {
                isFb = "google"
                googleHelper.signIn()
            } else {
                showAlerterRed(getString(R.string.no_internet))
            }
        }
*/

        googleHelper = GoogleHelper(mContext, object : GoogleHelper.GoogleHelperCallback {
            override fun onSuccessGoogle(account: GoogleSignInAccount) {
                try {
                    Log.i("GoogleHelper", "" + account)
                    var photo = ""
                    if (account.photoUrl != null) {
                        photo = account.photoUrl.toString()
                    }
                    googleHelper.signOut()

                    // val fatchName = account.displayName!!.split(" ")

                    try {

                        socialId = account.id!!
                        socialImage = photo
                        socialEmail = account.email!!
                        socialName = account.displayName!!
                        socialType = Constants.FOR_GOOGLE_TYPE

                        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(
                            mContext
                        ) { instanceIdResult: InstanceIdResult ->
                            newToken = instanceIdResult.token
                            Log.e("newToken", newToken)
                            SharedPrefUtil.getInstance().saveDeviceToken(newToken)
                        }

                        Log.e("getDeviceToken__", SharedPrefUtil.getInstance().deviceToken)

                        if (SharedPrefUtil.getInstance().deviceToken.equals("") || SharedPrefUtil.getInstance().deviceToken == null) {
                            Log.e("getDeviceToken__", SharedPrefUtil.getInstance().deviceToken)
                            showErrorToast(mContext, "Device Token Not Found")
                        } else {
                            socialLoginApi()
                        }

                        //   viewModel!!.socialLoginApi(socialId,socialEmail,socialType,mContext)


                    } catch (e: Exception) {
                    }

                } catch (ex: Exception) {
                    ex.localizedMessage
                }
            }

            override fun onErrorGoogle() {

                CommonMethods.failureMethod(mContext, "Cancel google login")
            }
        })

    }

/*
    private fun emailVerificationDialogMethod() {
        emailVerificationDialog = Dialog(mContext!!, R.style.Theme_Dialog)
        emailVerificationDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        emailVerificationDialog.setContentView(R.layout.email_verification_alert)

        emailVerificationDialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        emailVerificationDialog.setCancelable(true)
        emailVerificationDialog.setCanceledOnTouchOutside(false)
        emailVerificationDialog.window!!.setGravity(Gravity.CENTER)

        emailVerificationDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        emailVerificationDialog.btn_email_verify_ok.setOnClickListener {
            emailVerificationDialog.dismiss()

        }

        emailVerificationDialog.show()
    }
*/


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tvForgotpassword -> {
                val intent = Intent(mContext, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
            R.id.tvCreateAccount -> {
                val intent = Intent(mContext, SignupActivity::class.java)
                intent.putExtra("socialName", socialName)
                intent.putExtra("socialEmail", socialEmail)
                intent.putExtra("socialType", socialType)
                intent.putExtra("socialId", socialId)
                startActivity(intent)
            }
            R.id.btnSignin -> {
                FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(
                    this
                ) { instanceIdResult: InstanceIdResult ->
                    newToken = instanceIdResult.token
                    Log.e("newToken", newToken)
                    SharedPrefUtil.getInstance().saveDeviceToken(newToken)
                }

                Log.e("getDeviceToken__", SharedPrefUtil.getInstance().deviceToken)

                if (SharedPrefUtil.getInstance().deviceToken.equals("") || SharedPrefUtil.getInstance().deviceToken == null) {
                    Log.e("getDeviceToken__", SharedPrefUtil.getInstance().deviceToken)
                    showErrorToast(mContext, "Device Token Not Found")
                } else {
                    LoginApi()
                }

            }
        }
    }

    private fun isValid(): Boolean {
        var check = false
        if (!mValidationClass.isNetworkConnected)
            showAlerterRed(resources.getString(R.string.no_internet))
        else if (mValidationClass.checkStringNull(edEmail.text.toString()))
            showAlerterRed(resources.getString(R.string.error_email))
        else if (!mValidationClass.isValidEmail(edEmail.text.toString()))
            showAlerterRed(resources.getString(R.string.error_validemail))
        else if (mValidationClass.checkStringNull(et_loginpassword.text.toString()))
            showAlerterRed(resources.getString(R.string.error_password))
        else
            check = true
        return check
    }

    private fun LoginApi() {
        if (isValid()) {
            val map = HashMap<String, String>()
            map.put("email", edEmail.text.toString().trim())
            map.put("password", et_loginpassword.text.toString())
            map.put("deviceType", Constants.Device_Type)
            map.put("deviceToken", SharedPrefUtil.getInstance().deviceToken)

            viewModel.loginApi(this, true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    private fun socialLoginApi() {
        if (!mValidationClass.isNetworkConnected)
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map.put("social_id", socialId)
            map.put("social_type", socialType)
            map.put("device_token", SharedPrefUtil.getInstance().deviceToken)
            map.put("device_type", Constants.Device_Type)

            viewModel.socialLoginApi(this, true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is LoginResponse) {
                    val registerResponse: LoginResponse = it.data
                    if (registerResponse.getCode() == Constants.success_code) {

                        MyApplication.getnstance()
                                .setString(
                                        Constants.AuthKey,
                                        registerResponse.getBody()!!.token!!
                                )
                        MyApplication.instance!!.setString(
                                Constants.UserData,
                                modelToString(registerResponse.getBody()!!)
                        )

                        SharedPrefUtil.getInstance()
                                .saveAuthToken(registerResponse.getBody()!!.token)
                        SharedPrefUtil.getInstance()
                                .saveImage(registerResponse.getBody()!!.image)
                        SharedPrefUtil.getInstance().saveUserId(registerResponse.getBody()!!.id.toString())
                        SharedPrefUtil.getInstance().saveEmail(registerResponse.getBody()!!.email)
                        SharedPrefUtil.getInstance().saveName(registerResponse.getBody()!!.name)
                        SharedPrefUtil.getInstance()
                                .saveDeviceToken(registerResponse.getBody()!!.deviceToken)

                        if (registerResponse.getBody()!!.verified ==0){
                            val intent = Intent(mContext, VerficationCodeActivity::class.java)
                            startActivity(intent)
                        }else{

                            SharedPrefUtil.getInstance().isLogin = true

                            val intent = Intent(mContext, HomeActivity::class.java)
                            startActivity(intent)
                            finishAffinity()
                        }

                      }

                }

/*
                if (it.data is SocialLoginResponse) {
                    val socialLoginResponse: SocialLoginResponse = it.data
                    if (socialLoginResponse.getCode()!!.equals(Constants.success_code)) {

                        if (socialLoginResponse.getBody()!!.isExist == 0) {
                            var intent = Intent(mContext, SignupActivity::class.java)
                            intent.putExtra("socialName", socialName)
                            intent.putExtra("socialEmail", socialEmail)
                            intent.putExtra("socialType", socialType)
                            intent.putExtra("socialId", socialId)
                            startActivity(intent)
                        } else {
                            MyApplication.instance!!.setString(
                                Constants.UserData,
                                modelToString(socialLoginResponse.getBody()!!))

                            MyApplication.getnstance()
                                .setString(
                                    Constants.AuthKey,
                                    socialLoginResponse.getBody()!!.authorizationKey!!
                                )
                            showSuccessToast(mContext, socialLoginResponse!!.getMessage()!!)

                            SharedPrefUtil.getInstance().isLogin = true
                            SharedPrefUtil.getInstance()
                                .saveImage(socialLoginResponse.getBody()!!.imageName)
                            SharedPrefUtil.getInstance()
                                .saveUserId(socialLoginResponse.getBody()!!.id)
                            SharedPrefUtil.getInstance()
                                .saveEmail(socialLoginResponse.getBody()!!.email)
                            SharedPrefUtil.getInstance()
                                .saveName(socialLoginResponse.getBody()!!.name)
                            SharedPrefUtil.getInstance()
                                .saveDeviceToken(socialLoginResponse.getBody()!!.deviceToken)
                            SharedPrefUtil.getInstance()
                                .savePushNotificationStatus(socialLoginResponse.getBody()!!.notificationsStatus)

                            val intent = Intent(mContext, HomeActivity::class.java)
                            startActivity(intent)
                            finishAffinity()
                        }

                    } else {
                        CommonMethods.AlertErrorMessage(mContext, socialLoginResponse.getMessage())
                    }
                }
*/
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

    override fun onResume() {
        super.onResume()
        try {
            facebookHelper!!.logout()
            googleHelper.signOut()
        } catch (e: Exception) {
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (isFb.equals("fb")) {
            facebookHelper!!.onResult(requestCode, resultCode, data)

        } else if (isFb.equals("google")) {
            googleHelper.onResult(requestCode, resultCode, data)

        } else {

        }
    }

    override fun onSuccessFacebook(bundle: Bundle?) {
        val firstName = bundle!!.getString(FIRST_NAME)
        val lastName = bundle.getString(LAST_NAME)
        socialName = firstName + " " + lastName
        socialId = bundle.getString(FACEBOOK_ID)!!

        if (bundle.getString(FacebookHelper.EMAIL) != null) {
            socialEmail = bundle.getString(FacebookHelper.EMAIL)!!
        } else {
            socialEmail = ""
        }
        socialImage = bundle.getString(PROFILE_PIC)!!
        Log.e("socialId", socialId)
        socialType = Constants.FOR_FACEBOOK_TYPE

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(
            mContext
        ) { instanceIdResult: InstanceIdResult ->
            newToken = instanceIdResult.token
            Log.e("newToken", newToken)
            SharedPrefUtil.getInstance().saveDeviceToken(newToken)
        }

        Log.e("getDeviceToken__", SharedPrefUtil.getInstance().deviceToken)

        if (SharedPrefUtil.getInstance().deviceToken.equals("") || SharedPrefUtil.getInstance().deviceToken == null) {
            Log.e("getDeviceToken__", SharedPrefUtil.getInstance().deviceToken)
            showErrorToast(mContext, "Device Token Not Found")
        } else {
            socialLoginApi()
        }


    }

    override fun onCancelFacebook() {
    }

    override fun onErrorFacebook(ex: FacebookException?) {

    }

}
