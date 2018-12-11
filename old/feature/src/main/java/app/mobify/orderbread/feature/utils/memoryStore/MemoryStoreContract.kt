package app.mobify.orderbread.feature.utils.memoryStore

import app.mobify.orderbread.feature.api.models.Bread

interface MemoryStoreContract {
    var bread: Bread
}