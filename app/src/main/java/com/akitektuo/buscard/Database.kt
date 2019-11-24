package com.akitektuo.buscard

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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

    fun checkCode(code: String, onSuccess: (found: Boolean) -> Unit) {
        databaseRequests.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                onSuccess(false)
            }

            override fun onDataChange(data: DataSnapshot) {
                val requests = ArrayList<Request>()
                data.children.mapNotNullTo(requests) {
                    it.getValue(Request::class.java)
                }
                onSuccess(requests.any { it.id == code })
            }

        })
    }
}