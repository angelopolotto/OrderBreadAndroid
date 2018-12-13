package app.mobify.orderbreadandroid.utils.memoryStore

import app.mobify.orderbreadandroid.api.models.Bread

class MemoryStore: MemoryStoreContract {
    override var maxPerItem: Int = 2 //todo get from backend
    override var maxItemsCart: Int = 2 //todo get from backend
    override lateinit var bread: Bread
}