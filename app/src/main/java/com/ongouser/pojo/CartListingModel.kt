package com.ongouser.pojo


import com.google.gson.annotations.SerializedName

data class CartListingModel(
    @SerializedName("body")
    val body: Body,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("message")
    val message: String, // Cart Items listing fetched successfully.
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Body(
        @SerializedName("cartItems")
        val cartItems: ArrayList<CartItem>,
        @SerializedName("taxDetails")
        val taxDetails: TaxDetails,
        @SerializedName("vendorDetail")
        val vendorDetail: VendorDetail
    ) {
        data class CartItem(
            @SerializedName("created")
            val created: Int, // 1646031600
            @SerializedName("createdAt")
            val createdAt: String, // 2022-02-28T07:00:00.000Z
            @SerializedName("id")
            val id: Int, // 365
            @SerializedName("product")
            val product: Product,
            @SerializedName("productId")
            val productId: Int, // 467
            @SerializedName("qty")
            var qty: Int, // 1
            @SerializedName("updated")
            val updated: Int, // 1646031600
            @SerializedName("updatedAt")
            val updatedAt: String, // 2022-02-28T07:00:00.000Z
            @SerializedName("userId")
            val userId: Int, // 263
            @SerializedName("vendorId")
            val vendorId: Int // 189
        ) {
            data class Product(
                @SerializedName("barcode")
                val barcode: String, // 2421345
                @SerializedName("barcodeImage")
                val barcodeImage: String, // d3e70c55-e974-46c8-9654-1d955d2fbbfe.png
                @SerializedName("brandName")
                val brandName: String, // Product fun
                @SerializedName("categoryId")
                val categoryId: Int, // 65
                @SerializedName("countryOfOrigin")
                val countryOfOrigin: String, // Test
                @SerializedName("created")
                val created: Int, // 1629977404
                @SerializedName("createdAt")
                val createdAt: String, // 2021-08-26T11:30:03.000Z
                @SerializedName("description")
                val description: String, // Test
                @SerializedName("dimensionsUnit")
                val dimensionsUnit: Int, // 0
                @SerializedName("gtinNumber")
                val gtinNumber: String, // 123456
                @SerializedName("height")
                val height: String, // 0.00
                @SerializedName("id")
                val id: Int, // 467
                @SerializedName("image")
                val image: String, // http://localhost:8300/uploads/product/534eec57-716e-40fa-b33e-4fdc3f97cf6c.jpeg
                @SerializedName("isApproved")
                val isApproved: Int, // 1
                @SerializedName("isAvailable")
                val isAvailable: Int, // 1
                @SerializedName("isBarcodeItem")
                val isBarcodeItem: Int, // 1
                @SerializedName("length")
                val length: String, // 0.00
                @SerializedName("minimumSellingPrice")
                val minimumSellingPrice: String, // 0.00
                @SerializedName("mrp")
                val mrp: String, // 150
                @SerializedName("name")
                val name: String, // Product fun
                @SerializedName("percentageDiscount")
                val percentageDiscount: Int, // 0
                @SerializedName("sku")
                val sku: String,
                @SerializedName("skuImage")
                val skuImage: String,
                @SerializedName("status")
                val status: Int, // 1
                @SerializedName("subCategoryId")
                val subCategoryId: Int, // 0
                @SerializedName("taxCategoryId")
                val taxCategoryId: Int, // 0
                @SerializedName("updated")
                val updated: Int, // 1629977404
                @SerializedName("updatedAt")
                val updatedAt: String, // 2021-08-26T11:30:03.000Z
                @SerializedName("vendorEmployeeId")
                val vendorEmployeeId: Int, // 0
                @SerializedName("vendorId")
                val vendorId: Int, // 189
                @SerializedName("weight")
                val weight: String, // 10.00
                @SerializedName("weightUnit")
                val weightUnit: Int, // 0
                @SerializedName("width")
                val width: String // 0.00
            )
        }

        data class TaxDetails(
            @SerializedName("gst")
            val gst: Int, // 10
            @SerializedName("siteComission")
            val siteComission: String // 5.00
        )

        data class VendorDetail(
            @SerializedName("abn")
            val abn: String, // 475632
            @SerializedName("accountHolderName")
            val accountHolderName: String,
            @SerializedName("accountNumber")
            val accountNumber: String,
            @SerializedName("approvalStatus")
            val approvalStatus: Int, // 2
            @SerializedName("approvalStatusReason")
            val approvalStatusReason: String,
            @SerializedName("bankAddress")
            val bankAddress: String,
            @SerializedName("bankBranch")
            val bankBranch: String,
            @SerializedName("bankName")
            val bankName: String,
            @SerializedName("bsbNumber")
            val bsbNumber: String,
            @SerializedName("buildingNumber")
            val buildingNumber: String, // 123
            @SerializedName("city")
            val city: String, // mohali
            @SerializedName("country")
            val country: String, // india
            @SerializedName("created")
            val created: Int, // 1629897513
            @SerializedName("createdAt")
            val createdAt: String, // 2021-08-25T13:18:33.000Z
            @SerializedName("deliveriesPerDay")
            val deliveriesPerDay: Int, // 10
            @SerializedName("deliveryPolicy")
            val deliveryPolicy: String,
            @SerializedName("geoLocation")
            val geoLocation: String, // Sahibzada Ajit Singh Nagar
            @SerializedName("homeDelivery")
            val homeDelivery: Int, // 1
            @SerializedName("id")
            val id: Int, // 73
            @SerializedName("ifscSwiftCode")
            val ifscSwiftCode: String,
            @SerializedName("image")
            val image: String, // 0f4e5e11-05b1-44af-9e86-b49e3bc601a0.jpeg
            @SerializedName("isDeliveryChargesAdded")
            val isDeliveryChargesAdded: Int, // 0
            @SerializedName("isDeliveryDaysAdded")
            val isDeliveryDaysAdded: Int, // 1
            @SerializedName("isDeliveryOptionsAdded")
            val isDeliveryOptionsAdded: Int, // 0
            @SerializedName("isHomeDeliveryAdded")
            val isHomeDeliveryAdded: Int, // 1
            @SerializedName("isShopAdded")
            val isShopAdded: Int, // 1
            @SerializedName("latitude")
            val latitude: String, // 30.70464860
            @SerializedName("longitude")
            val longitude: String, // 76.71787260
            @SerializedName("name")
            val name: String, // sukhi1
            @SerializedName("paymentPolicy")
            val paymentPolicy: String,
            @SerializedName("phone")
            val phone: String, // 9856322541
            @SerializedName("postalCode")
            val postalCode: String, // 574869
            @SerializedName("sellerInformation")
            val sellerInformation: String,
            @SerializedName("shopAddress")
            val shopAddress: String,
            @SerializedName("shopCategory")
            val shopCategory: String, // Electronics
            @SerializedName("shop_category_id")
            val shopCategoryId: Int, // 0
            @SerializedName("shopCloseTime")
            val shopCloseTime: String, // 06:30 pm
            @SerializedName("shopDescription")
            val shopDescription: String,
            @SerializedName("shopLogo")
            val shopLogo: String, // 92acbf69-2ae5-4c57-9515-07db37854154.jpeg
            @SerializedName("shopName")
            val shopName: String, // Sukhi Shop
            @SerializedName("shopOpenTime")
            val shopOpenTime: String, // 10:00 am
            @SerializedName("state")
            val state: String, // QLD
            @SerializedName("streetNumber")
            val streetNumber: String, // Durban street
            @SerializedName("taxInPercent")
            val taxInPercent: Int, // 0
            @SerializedName("taxValue")
            val taxValue: Int, // 0
            @SerializedName("updated")
            val updated: Int, // 1629964424
            @SerializedName("updatedAt")
            val updatedAt: String, // 2021-08-26T07:53:43.000Z
            @SerializedName("userId")
            val userId: Int // 189
        )
    }
}