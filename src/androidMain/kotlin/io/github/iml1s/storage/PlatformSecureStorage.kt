package io.github.iml1s.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * Android implementation of SecureStorage using EncryptedSharedPreferences.
 */
actual class PlatformSecureStorage(private val context: Context) : SecureStorage {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secure_wallet_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    actual override suspend fun put(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    actual override suspend fun get(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    actual override suspend fun delete(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    actual override suspend fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
