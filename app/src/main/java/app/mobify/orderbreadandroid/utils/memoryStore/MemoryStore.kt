package app.mobify.orderbreadandroid.utils.memoryStore

import app.mobify.orderbreadandroid.api.models.Bread

class MemoryStore: MemoryStoreContract {
    override lateinit var bread: Bread
}