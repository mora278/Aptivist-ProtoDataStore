package com.mora278.aptivist_protodatastore.domain.models

data class Task(
    val isDone: Boolean,
    val description: String,
    val userAssigned: User?
)