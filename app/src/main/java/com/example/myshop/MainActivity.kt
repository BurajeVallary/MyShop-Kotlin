package com.example.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        fetchProducts()
    }

    fun fetchProducts(){
        var apiClient = ApiClient.buildClient(ApiInterface::class.java)
        var request = apiClient.getProducts()

        request.enqueue (object :Callback<ProductsResponse>{
           override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                if (response.isSuccessful){
                    var product = response.body()?.products
                    //responsible to make sure everything is working
//                    binding.rvProducts.layoutManager = GridLayoutManager(MainActivity.this@, 2);
//                  var productAdapter=ProductAdapter(product?: emptyList())
                    val productAdapter = ProductAdapter(product ?: emptyList())
                    binding.rvProducts.layoutManager = GridLayoutManager(this@MainActivity,2)
//                   binding.rvProducts.layoutManager=LinearLayoutManager(this@MainActivity)
                    binding.rvProducts.adapter=productAdapter
                    Toast.makeText(baseContext,
                    "fetched ${product?.size} products" ,Toast.LENGTH_SHORT).show()

                }
                else{
                    Toast.makeText(baseContext,response.errorBody()?.string(),Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()             //if the request fails
            }
        }
    )

        }}