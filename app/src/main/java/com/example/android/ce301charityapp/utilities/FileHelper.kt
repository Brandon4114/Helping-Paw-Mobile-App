package com.example.android.ce301charityapp.utilities

import android.app.Application
import android.content.Context
import java.io.File

class FileHelper {
    companion object {
        
        fun getTextFromResources(context: Context, resourceId: Int): String {
            return context.resources.openRawResource(resourceId).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }

    }
}