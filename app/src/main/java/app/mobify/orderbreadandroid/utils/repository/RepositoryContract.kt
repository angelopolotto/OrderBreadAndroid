package app.mobify.orderbreadandroid.utils.repository

import app.mobify.orderbreadandroid.api.models.Bread

interface RepositoryContract {
    fun loadBreads(result: (breads: ArrayList<Bread>)->Unit)
}