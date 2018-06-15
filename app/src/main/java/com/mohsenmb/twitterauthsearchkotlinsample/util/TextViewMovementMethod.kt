package com.mohsenmb.twitterauthsearchkotlinsample.util

import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.MotionEvent
import android.widget.TextView

class TextViewMovementMethod(private val mOnLinkClickedListener: OnLinkClickedListener) : LinkMovementMethod() {

    override fun onTouchEvent(widget: TextView, buffer: android.text.Spannable,
                              event: android.view.MotionEvent): Boolean {
        val action = event.action

        if (action == MotionEvent.ACTION_UP) {
            var x = event.x.toInt()
            var y = event.y.toInt()

            x -= widget.totalPaddingLeft
            y -= widget.totalPaddingTop

            x += widget.scrollX
            y += widget.scrollY

            val layout = widget.layout
            val line = layout.getLineForVertical(y)
            val off = layout.getOffsetForHorizontal(line, x.toFloat())

            val link = buffer.getSpans(off, off, URLSpan::class.java)
            if (link.isNotEmpty()) {
                val url = link[0].url
                val handled = mOnLinkClickedListener.onLinkClicked(url)
                return if (handled) {
                    true
                } else super.onTouchEvent(widget, buffer, event)
            }
        }
        return super.onTouchEvent(widget, buffer, event)
    }
}

interface OnLinkClickedListener {
    fun onLinkClicked(url: String): Boolean
}
