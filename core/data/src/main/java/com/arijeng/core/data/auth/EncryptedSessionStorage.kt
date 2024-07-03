package com.arijeng.core.data.auth

import android.content.SharedPreferences
import com.arijeng.core.domain.AuthInfo
import com.arijeng.core.domain.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


/**
 * Created by {Bennette Molepo} on {2024/05/20}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class EncryptedSessionStorage(
    private val sharedPreferences: SharedPreferences
) : SessionStorage{
    override suspend fun get(): AuthInfo? {
        return withContext(Dispatchers.IO){
            val json = sharedPreferences.getString(KEY_AUTH_INFO, null)
            json?.let {
                Json.decodeFromString<AuthInfoSerializable?>(it)?.toAuthInfo()
            }
        }
    }

    override suspend fun set(info: AuthInfo?) {
        withContext(Dispatchers.IO){
            if (info == null){
                sharedPreferences.edit().remove(KEY_AUTH_INFO).apply()
                return@withContext
            }
            val json = Json.encodeToString(info.toAuthInfoSerializable())

            sharedPreferences
                .edit()
                .putString(KEY_AUTH_INFO, json)
                .apply()

        }
    }

    companion object{
        private const val KEY_AUTH_INFO = "KEY_AUTH_INFO"
    }
}