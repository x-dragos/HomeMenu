package ro.bcsolutions.homemenu

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView


class ScrollableDatePicker(context: Context, attrs: AttributeSet): DatePicker(context, attrs) {
    private val spinnerCount = 3

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Utils.hideKeyboard(context as Activity)

        if (ev!!.actionMasked == MotionEvent.ACTION_DOWN && Build.VERSION.SDK_INT < 22) {
            val p = parent
            p?.requestDisallowInterceptTouchEvent(true)
        }
        return false
    }

    fun formatDate(ymdOrder: String) {
        val system = Resources.getSystem()
        val idYear = system.getIdentifier("year", "id", "android")
        val idMonth = system.getIdentifier("month", "id", "android")
        val idDay = system.getIdentifier("day", "id", "android")
        val idLayout = system.getIdentifier("pickers", "id", "android")
        val spinnerYear = findViewById<View>(idYear) as NumberPicker
        val spinnerMonth = findViewById<View>(idMonth) as NumberPicker
        val spinnerDay = findViewById<View>(idDay) as NumberPicker
        val layout = findViewById<View>(idLayout) as LinearLayout
        layout.removeAllViews()
        for (i in 0 until spinnerCount) {
            when (ymdOrder[i]) {
                'y' -> {
                    layout.addView(spinnerYear)
                    setImeOptions(spinnerYear, i)
                }
                'm' -> {
                    layout.addView(spinnerMonth)
                    setImeOptions(spinnerMonth, i)
                }
                'd' -> {
                    layout.addView(spinnerDay)
                    setImeOptions(spinnerDay, i)
                }
                else -> throw IllegalArgumentException("Invalid char[] ymdOrder")
            }
        }
    }

    private fun setImeOptions(spinner: NumberPicker, spinnerIndex: Int) {
        val imeOptions: Int = if (spinnerIndex < spinnerCount - 1) {
            EditorInfo.IME_ACTION_NEXT
        } else {
            EditorInfo.IME_ACTION_DONE
        }
        val idPickerInput: Int = Resources.getSystem().getIdentifier("numberpicker_input", "id", "android")
        val input = spinner.findViewById<View>(idPickerInput) as TextView
        input.imeOptions = imeOptions
    }
}