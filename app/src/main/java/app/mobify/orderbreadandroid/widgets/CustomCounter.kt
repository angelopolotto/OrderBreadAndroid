package app.mobify.orderbreadandroid.widgets

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import app.mobify.orderbreadandroid.R
import kotlinx.android.synthetic.main.custom_counter.view.*

class CustomCounter @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var initialValueCounter: Int = 1
        set(value) {
            valueCounter = value
            updateMinusColorText()
        }
    var maxValueCounter: Int = 99

    private var valueCounter: Int = 1
    private var valueUpdatedCallback: ((valueCounter: Int) -> Unit)? = null
    private var valueMaxValueCallback: ((valueCounter: Int) -> Unit)? = null
    private var itemRemovedCallback: (() -> Unit)? = null

    init {
        inflate(context, R.layout.custom_counter, this)

        // Load the styled attributes and set their properties
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomCounter, defStyleAttr, defStyleRes
        )
        updateMinusColorText()
        bMinus.setOnClickListener {
            if (valueCounter > 1) {
                valueCounter--
                valueUpdatedCallback?.invoke(valueCounter)
            } else if (valueCounter == 1) {
                itemRemovedCallback?.invoke()
            }
            updateMinusColorText()
        }
        bPlus.setOnClickListener {
            if (valueCounter < maxValueCounter) {
                valueCounter++
                valueUpdatedCallback?.invoke(valueCounter)
            } else {
                valueMaxValueCallback?.invoke(valueCounter)
            }
            updateMinusColorText()
        }
        typedArray?.recycle()
    }

    fun valueUpdatedListener(callback: ((valueCounter: Int) -> Unit)?) {
        valueUpdatedCallback = callback
    }

    fun valueMaxValueListener(callback: ((valueCounter: Int) -> Unit)?) {
        valueMaxValueCallback = callback
    }

    fun itemRemoved(callback: (() -> Unit)?) {
        itemRemovedCallback = callback
    }

    private fun updateMinusColorText() {
        if (valueCounter == 1) {
            bMinus.text = context.resources.getString(R.string.custom_counter_delete)
            bMinus.setBackgroundColor(ContextCompat.getColor(context, R.color.redColor))
        } else {
            bMinus.text = context.resources.getString(R.string.custom_counter_minus)
            bMinus.setBackgroundColor(ContextCompat.getColor(context, R.color.primaryColor))
        }
        tvCounter.text = valueCounter.toString()
    }
}