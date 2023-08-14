package com.mora278.aptivist_protodatastore.data.preferencesbasic

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.mora278.aptivist_protodatastore.UserPreferencesBasic
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesBasicSerializer : Serializer<UserPreferencesBasic> {
    override val defaultValue: UserPreferencesBasic =
        UserPreferencesBasic.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferencesBasic {
        try {
            return UserPreferencesBasic.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserPreferencesBasic, output: OutputStream) =
        t.writeTo(output)
}