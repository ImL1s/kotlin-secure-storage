package io.github.iml1s.storage

/**
 * JVM implementation using basic file storage (Placeholder).
 * In a real desktop app, we should use WinCred/libsecret/Keychain or encrypted file.
 * For this MVP student project context, a simple file map is sufficient but we iterate to safe usage.
 */

// Since we don't have easy desktop secret store in pure kotlin-jvm without large dependencies (like keytar), 
// We will leave this as a stub that warns or implements basic obfuscation if needed.
// For now, simple throw or memory-only map for testing.

actual class PlatformSecureStorage : SecureStorage {
    private val memoryStore = mutableMapOf<String, String>()

    actual override suspend fun put(key: String, value: String) {
        memoryStore[key] = value // WARNING: In-memory only for JVM target in this MVP
    }

    actual override suspend fun get(key: String): String? {
        return memoryStore[key]
    }

    actual override suspend fun delete(key: String) {
        memoryStore.remove(key)
    }

    actual override suspend fun clear() {
        memoryStore.clear()
    }
}
