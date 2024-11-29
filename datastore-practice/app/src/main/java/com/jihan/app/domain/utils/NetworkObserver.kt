package com.jihan.app.domain.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class NetworkConnectivityObserver (
   context: Context,
) : NetworkObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<NetworkObserver.Status> {
        return callbackFlow {

            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch {

                        val capability = connectivityManager.getNetworkCapabilities(network)

                        if (capability!!.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                            send(NetworkObserver.Status.HasInternet)

                        } else {
                            send(NetworkObserver.Status.Available)
                        }
                    }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch {
                        send(NetworkObserver.Status.Lost)
                    }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch {

                        send(NetworkObserver.Status.UnAvailable)
                    }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch {
                        send(NetworkObserver.Status.Losing)
                    }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback) // register callback

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback) // unregister callback
            }
        }.distinctUntilChanged()
    }


}

interface NetworkObserver {

    fun observe(): Flow<Status>


    enum class Status {
        Available, UnAvailable, Losing, Lost, HasInternet
    }

}