package com.example.hatewait.model

data class MemberLoginRequestData(var id:String, var pw:String)
data class MemberLoginResponseData(var message:String, var member:String)

data class StoreLoginRequestData(var id:String, var pw:String)
data class StoreLoginResponseData(var message:String, var store:String)