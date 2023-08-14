package com.mora278.aptivist_protodatastore.data

import com.mora278.aptivist_protodatastore.TaskModel
import com.mora278.aptivist_protodatastore.UserModel
import com.mora278.aptivist_protodatastore.domain.models.User
import com.mora278.aptivist_protodatastore.domain.models.Task

object Mapper {
    fun UserModel.toDomain(): User = User(
        userName = this.userName,
        age = this.age
    )

    fun User.toProtoModel(): UserModel = UserModel
        .newBuilder()
        .setUserName(userName)
        .setAge(age)
        .build()

    fun TaskModel.toDomain(): Task = Task(
        isDone = this.isDone,
        description = this.description,
        userAssigned =
        this.userAssigned.toDomain().run { if (userName.isNotBlank()) this else null }
    )

    fun Task.toProtoModel(): TaskModel = TaskModel
        .newBuilder()
        .setIsDone(isDone)
        .setDescription(description)
        .setUserAssigned(
            userAssigned?.toProtoModel() ?: UserModel.getDefaultInstance()
        )
        .build()
}