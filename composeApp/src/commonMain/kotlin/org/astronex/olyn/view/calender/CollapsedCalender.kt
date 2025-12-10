package org.astronex.olyn.view.calender

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.datetime.YearMonth

@Composable
fun CollapsedCalender(
    initialMonth: YearMonth
) {
    val maximumPage = 1000000
    val initialPage = ((maximumPage * 0.5).toInt())
    val pagerState = rememberPagerState(initialPage = initialPage, pageCount = { maximumPage })
    val startDayOfWeek by remember {
        mutableStateOf(
            initialMonth.days.first().dayOfWeek.ordinal
        )
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
    ) { page ->

        val offsetWeeks = page - initialPage
        val daysInWeek = List(7) { index ->
            startDayOfWeek
                .plus(offsetWeeks.toLong())
                .plus(index.toLong())
        }

        Row {

        }
    }
}