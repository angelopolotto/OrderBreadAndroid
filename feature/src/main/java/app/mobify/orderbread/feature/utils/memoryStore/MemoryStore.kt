package app.mobify.orderbread.feature.utils.memoryStore

import app.mobify.orderbread.feature.api.models.Bread

class MemoryStore: MemoryStoreContract {
    lateinit var bread: Bread
}