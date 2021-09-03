package com.ongouser.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ongouser.manager.restApi.RestObservable

import com.ongouser.utils.others.MyApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel :ViewModel() {
    private val TAG = HomeViewModel::class.java.name
    val restApiInterface = MyApplication.getnstance().provideAuthservice()
    var mResponse: MutableLiveData<RestObservable> = MutableLiveData()


    fun getHomeListing(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.homeListing(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun getCategoryListing(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.categoryListing(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun getnotificationapi(activity: Activity, showLoader:Boolean) {
        restApiInterface.getnotificationlisting()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }
    fun getshopbycatid(activity: Activity, showLoader:Boolean,
                       map: HashMap<String, String>
    ) {
        restApiInterface.getshopbycatid(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
                .subscribe(
                        { mResponse.value = RestObservable.success(it) },
                        { mResponse.value = RestObservable.error(activity,it) }
                )
    }
    fun getproductbyshopid(activity: Activity, showLoader:Boolean,
                       map: HashMap<String, String>
    ) {
        restApiInterface.getproductbyshopid(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
                .subscribe(
                        { mResponse.value = RestObservable.success(it) },
                        { mResponse.value = RestObservable.error(activity,it) }
                )
    }
  fun getmyorder(activity: Activity, showLoader:Boolean,
                       map: HashMap<String, String>) {
        restApiInterface.getorders(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
                .subscribe(
                        { mResponse.value = RestObservable.success(it) },
                        { mResponse.value = RestObservable.error(activity,it) }
                )
    }
    fun getorderdetail(activity: Activity, showLoader:Boolean,
                       map: HashMap<String, String>) {
        restApiInterface.getorderdetail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
                .subscribe(
                        { mResponse.value = RestObservable.success(it) },
                        { mResponse.value = RestObservable.error(activity,it) }
                )
    }


    fun addtofavorite(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.addtofavorite(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }
 fun add_review(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.add_review(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun getdeliverytimeslots(activity: Activity, showLoader:Boolean,vendorid:String) {
        restApiInterface.gettimeslots(vendorid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }
    fun nearbyVendorsList(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.nearbyVendorsList(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun addtocartAPI(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.addtocart(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun emptycartapi(activity: Activity, showLoader:Boolean) {
        restApiInterface.emptycart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun getcartlisting(activity: Activity, showLoader:Boolean) {
        restApiInterface.getcartlisting()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }
  fun getfavoriteproducts(activity: Activity, showLoader:Boolean) {
        restApiInterface.getfavoriteproducts("")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun addCardAPI(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.addCard(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun updateCardAPI(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.updateCard(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun addUserAddressAPI(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.addUserAddress(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun updateUserAddressAPI(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.updateUserAddress(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }
    fun placeorderAPI(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.placeorder(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun deleteUserAddressAPI(activity: Activity, showLoader:Boolean, id:String) {
        restApiInterface.deleteUserAddress(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun deleteCardAPI(activity: Activity, showLoader:Boolean, id:String) {
        restApiInterface.deleteCard(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun deletecartitem(activity: Activity, showLoader:Boolean, id:String) {
        restApiInterface.deletecartitem(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }
    fun updatecartitem(activity: Activity, showLoader:Boolean, id:String,qty:String) {
        restApiInterface.updatecartitem(id,qty)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }


    fun getProfile(activity: Activity, showLoader:Boolean) {
        restApiInterface.getProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun allCardsAPI(activity: Activity, showLoader:Boolean) {
        restApiInterface.allCards()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun getUserAddressListing(activity: Activity, showLoader:Boolean) {
        restApiInterface.getUserAddressListing()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }

    fun getUserAddressListing(activity: Activity, showLoader:Boolean,map: HashMap<String, String>) {
        restApiInterface.getUserAddressListing(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }


/*
    fun notificationListApi(activity: Activity, showLoader:Boolean) {
        restApiInterface.notificationsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }
*/



}