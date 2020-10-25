package com.example.hatewait.retrofit2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyApi {

    private const val BASE_URL = "https://hatewait-server.herokuapp.com/"


    private fun retrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // JSON
            .build()
    }

    val nonMemberRegisterService: RetrofitNonMemberRegister by lazy {
        retrofit().create(RetrofitNonMemberRegister::class.java)
    }

    val memberRegisterService: RetrofitMemberRegister by lazy {
        retrofit().create(RetrofitMemberRegister::class.java)
    }

    val checkMemberIdService: RetrofitCheckMemberIdRegister by lazy {
        retrofit().create(RetrofitCheckMemberIdRegister::class.java)
    }

    val CustomerSignUpService: RetrofitSignUp by lazy {
        retrofit().create(RetrofitSignUp::class.java)
    }






}
