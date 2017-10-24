package com.rviannaoliveira.vreddit.core.data

/**
 * Criado por rodrigo on 18/10/17.
 */
object DataManagerFactory {
    private var dataManager: DataManager? = null

    fun getDefaultInstance(): DataManager? {
        if (dataManager == null) {
            return DataManager()
        }
        return dataManager
    }
}