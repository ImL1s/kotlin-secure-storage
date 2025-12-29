package io.github.iml1s.storage

interface SecureStorage {
    /**
     * Store a string value securely.
     */
    suspend fun put(key: String, value: String)

    /**
     * Retrieve a string value securely.
     * Returns null if key not found.
     */
    suspend fun get(key: String): String?

    /**
     * Delete a value securely.
     */
    suspend fun delete(key: String)

    /**
     * Clear all values.
     */
    suspend fun clear()
}

expect class PlatformSecureStorage : SecureStorage
