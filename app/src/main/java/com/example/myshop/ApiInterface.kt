package com.example.myshop

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("/product")
    fun getProducts():Call<ProductsResponse>    //call for gettiing a list of product

    @GET("/product/{id}")
    fun getProductById(@Path("id")productId:Int):Call<Product>


    @POST("/product")
    fun postProduct (@Body product: Product):Call<Product>
}