package org.astronex.olyn.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private var maximumStatusBarHeight by mutableStateOf(0.dp)
@Composable
fun Modifier.dynamicStatusBarPadding(): Modifier = this.composed {

    // Lay ra chieu cao cua status bar hien tai
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    // Cai dat mau background bandau
    var backgroundColor by remember { mutableStateOf(Color.Transparent) }

    // Khi chieu cao cua status bar thay doi & status bar bi an khi inter splash xuat hien
    // thi delay 1 giay truoc khi chuyen mau background sang mau den
    LaunchedEffect(statusBarHeight) {
        if (statusBarHeight == 0.dp) {
            delay(1000)
            backgroundColor = Color.Black
        } else {
            backgroundColor = Color.Transparent
        }
    }

    // Bat cu khi nao chieu cao status bar lon hon chieu cao cua chinh no truoc do
    // thi se cap nhat lai chieu cao moi nhat (maximumStatusBarHeight)
    LaunchedEffect(statusBarHeight) {
        if (statusBarHeight > maximumStatusBarHeight) maximumStatusBarHeight = statusBarHeight
    }

    Modifier
        .background(color = backgroundColor)
        .padding(top = maximumStatusBarHeight)
}