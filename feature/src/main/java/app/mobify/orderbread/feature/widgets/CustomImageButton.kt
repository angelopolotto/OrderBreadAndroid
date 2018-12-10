//package app.mobify.orderbread.feature.widgets
//
//import android.content.Context
//import android.util.AttributeSet
//import android.widget.RelativeLayout
//import android.content.res.TypedArray
//import app.mobify.orderbread.R
//
//
//class CustomImageButton(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
//    init {
////        https://medium.com/@otoloye/creating-custom-components-in-android-3d24a2bdaebd
//        inflate(context, R.layout.custom_image_button, this)
//
//        val sets = intArrayOf(R.attr.cib_src, R.attr.cib_text, R.atrr.cib_contentDescription)
//        val typedArray = context?.obtainStyledAttributes(attrs, sets)
//        val artist = typedArray.getText(index0)
//        val track = typedArray.getText(index1)
//        val buyButton = typedArray.getText(index2)
//        typedArray.recycle()
//
//        initComponents()
//
//        setArtistText(artist)
//        setTrackText(track)
//        setButton(buyButton)
//    }
//}