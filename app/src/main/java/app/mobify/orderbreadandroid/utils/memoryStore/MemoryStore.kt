package app.mobify.orderbreadandroid.utils.memoryStore

import app.mobify.orderbreadandroid.api.models.Bread

class MemoryStore: MemoryStoreContract {
    override var maxPerItem: Int = 2
    override var maxItemsCart: Int = 2
    override lateinit var bread: Bread
}