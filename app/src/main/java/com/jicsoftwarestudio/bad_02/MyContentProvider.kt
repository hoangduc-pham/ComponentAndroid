package com.jicsoftwarestudio.bad_02

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log

class MyContentProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        Log.d("MyContentProvider", "onCreate")
        return true
    }

    override fun query(
        uri: Uri, projection: Array<out String>?, selection: String?,
        selectionArgs: Array<out String>?, sortOrder: String?
    ): Cursor? {
        Log.d("MyContentProvider", "query")
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d("MyContentProvider", "insert")
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        Log.d("MyContentProvider", "update")
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.d("MyContentProvider", "delete")
        return 0
    }

    override fun getType(uri: Uri): String? {
        Log.d("MyContentProvider", "getType")
        return null
    }
}