package app.mobify.orderbread.feature.utils.repository

import app.mobify.orderbread.feature.api.models.Bread

interface RepositoryContract {
    fun loadBreads(result: (breads: ArrayList<Bread>)->Unit)
}