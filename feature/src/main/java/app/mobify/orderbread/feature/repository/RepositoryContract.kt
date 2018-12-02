package app.mobify.orderbread.feature.repository

import app.mobify.orderbread.feature.api.models.BreadItem

interface RepositoryContract {
    fun loadBreads(result: (breads: ArrayList<BreadItem>)->Unit)
}