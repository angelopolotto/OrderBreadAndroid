package app.mobify.orderbread.feature.utils.repository

import app.mobify.orderbread.feature.api.models.BreadItem

interface RepositoryContract {
    fun loadBreads(result: (breads: ArrayList<BreadItem>)->Unit)
}