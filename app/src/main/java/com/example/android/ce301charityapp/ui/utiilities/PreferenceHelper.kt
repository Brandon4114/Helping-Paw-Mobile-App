package com.example.android.ce301charityapp.ui.utiilities

import android.content.Context
import android.content.SharedPreferences

const val ITEM_TYPE_KEY = "item_type_key"

class PreferenceHelper {

    companion object{
        private fun preference(context: Context):SharedPreferences =
            context.getSharedPreferences("default",0)

        fun setItemType(context: Context, type:String){

            preference(context).edit()
                .putString(ITEM_TYPE_KEY, type)
                .apply()
        }

        fun getItemType(context: Context): String =
            preference(context).getString(ITEM_TYPE_KEY, "List")!!


    }
}