package app.mobify.orderbread.feature.utils.`super`

import app.mobify.orderbread.feature.api.models.BreadItem

class MemStore: MemStoreContract {
    override lateinit var breadItem: BreadItem
}