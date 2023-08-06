package com.example.foodapplication.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.navPages(it: View, id:Int){
    Navigation.findNavController(it).navigate(id)
}

fun Navigation.navPages(it: View, id: NavDirections){
    Navigation.findNavController(it).navigate(id)
}