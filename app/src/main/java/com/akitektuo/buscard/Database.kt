package com.akitektuo.buscard

import com.google.firebase.database.FirebaseDatabase

class Database {
    private val database = FirebaseDatabase.getInstance().reference

    init {
        database.keepSynced(true)
    }

    data class Request(
        val message: String = "",
        val id: String = ""
    )

    private val databaseRequests = database.child("requests")

    fun generateId() = database.push().key.toString()

    fun createRequest(onSuccess: () -> Unit) {
        val id = databaseRequests.push().key.toString()
        val request = Request("Request with id $id sent", id)
        databaseRequests.child(request.id).setValue(request)
        onSuccess()
    }
}