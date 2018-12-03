package app.mobify.orderbread.feature.activities.breadDetails

import app.mobify.orderbread.feature.api.models.BreadItem

interface BreadDetailsContract {
    interface View {
        fun showDetails(bread: BreadItem)
    }
    interface Presenter {
        fun loadDetails()
    }
}