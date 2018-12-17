package app.mobify.orderbreadandroid.api.models.user

import app.mobify.orderbreadandroid.api.models.Bread
import java.math.BigDecimal

class Cart(var breads: MutableList<Bread>, var total: BigDecimal)