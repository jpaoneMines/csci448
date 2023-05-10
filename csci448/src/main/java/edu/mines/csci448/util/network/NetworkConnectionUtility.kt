package edu.mines.csci448.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object NetworkConnectionUtility {
    private const val LOG_TAG = "448.NetworkConnectionUtility"

    fun isNetworkAvailableAndConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        return activeNetwork != null && (connectivityManager.getNetworkCapabilities(activeNetwork)?.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_VALIDATED) ?: false)
    }

    private val mNetworkAvailableStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val networkAvailableStateFlow: StateFlow<Boolean>
        get() = mNetworkAvailableStateFlow.asStateFlow()

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d(LOG_TAG, "onAvailable() called: network is available for use")
            mNetworkAvailableStateFlow.update { true }
        }

        // Network capabilities have changed for the network
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
            val validated = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            Log.d(LOG_TAG, "onCapabilitiesChanged() called - validated/unmetered = $validated/$unmetered")
            mNetworkAvailableStateFlow.update { validated }
        }

        // lost network connection
        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d(LOG_TAG, "onLost() called: lost network connection")
            mNetworkAvailableStateFlow.update { false }
        }
    }

    fun monitorNetworkStatus(context: Context) {
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    fun stopMonitoringNetworkStatus(context: Context) {
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}