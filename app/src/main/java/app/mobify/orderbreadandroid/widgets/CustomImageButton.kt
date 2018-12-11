package app.mobify.orderbreadandroid.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import app.mobify.orderbreadandroid.R
import kotlinx.android.synthetic.main.custom_image_button.view.*


class CustomImageButton(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    RelativeLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.custom_image_button, this)

        // Load the styled attributes and set their properties
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.CustomImageButton, defStyleAttr, 0)

        val src = typedArray?.getDrawable(R.styleable.CustomImageButton_cib_src)
        val text = typedArray?.getText(R.styleable.CustomImageButton_cib_text)
        val contentDescription = typedArray?.getText(R.styleable.CustomImageButton_cib_contentDescription)

        ivIcon.setImageDrawable(src)
        tvText.text = text
        ivIcon.contentDescription = contentDescription

        typedArray?.recycle()
    }
}