package com.example.compose_sochprofile

sealed class Screens(var path:String,var title:String){
    object s1:Screens(path = "s1","Screen1")
    object s2:Screens("s2","Screen2")
}
