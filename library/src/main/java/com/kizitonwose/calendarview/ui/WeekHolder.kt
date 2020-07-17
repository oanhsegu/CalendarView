package com.kizitonwose.calendarview.ui

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.kizitonwose.calendarview.model.CalendarDay

internal class WeekHolder(val dayHolders: List<DayHolder>) {

    private lateinit var container: LinearLayout

    fun inflateWeekView(parent: LinearLayout): View {
        container = LinearLayout(parent.context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            orientation = LinearLayout.HORIZONTAL
            weightSum = dayHolders.count().toFloat()
            for (holder in dayHolders) {
                addView(holder.inflateDayView(this))
            }
        }
        return container
    }

    fun bindWeekView(daysOfWeek: List<CalendarDay>) {
        container.visibility = if (daysOfWeek.isEmpty()) View.GONE else View.VISIBLE
        dayHolders.forEachIndexed { index, holder ->
            // Indices can be null if OutDateStyle is NONE. We set the
            // visibility for the views at these indices to INVISIBLE.
            holder.bindDayView(daysOfWeek.getOrNull(index))
        }
    }
}
