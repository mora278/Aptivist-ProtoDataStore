package com.mora278.aptivist_protodatastore.data.preferencestesting

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.mora278.aptivist_protodatastore.UserPreferencesTesting
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesTestingSerializer : Serializer<UserPreferencesTesting> {
    override val defaultValue: UserPreferencesTesting =
        UserPreferencesTesting.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferencesTesting {
        try {
            return UserPreferencesTesting.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserPreferencesTesting, output: OutputStream) =
        t.writeTo(output)
}