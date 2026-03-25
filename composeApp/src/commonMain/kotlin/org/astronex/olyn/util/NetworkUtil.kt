package org.astronex.olyn.util

/**
 * Optional hook for connectivity checks. Remote I/O uses Ktor in [org.astronex.olyn.network].
 * Implement per platform (e.g. Android ConnectivityManager, iOS NWPathMonitor) if you need offline UX.
 */
expect class NetworkUtil {
}