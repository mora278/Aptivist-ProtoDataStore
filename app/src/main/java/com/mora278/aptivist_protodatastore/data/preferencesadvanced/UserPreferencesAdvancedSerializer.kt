package com.mora278.aptivist_protodatastore.data.preferencesadvanced

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.mora278.aptivist_protodatastore.UserPreferencesAdvanced
import com.mora278.aptivist_protodatastore.UserPreferencesBasic
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesAdvancedSerializer : Serializer<UserPreferencesAdvanced> {
    override val defaultValue: UserPreferencesAdvanced =
        UserPreferencesAdvanced.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferencesAdvanced {
        try {
            return UserPreferencesAdvanced.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserPreferencesAdvanced, output: OutputStream) =
        t.writeTo(output)

}