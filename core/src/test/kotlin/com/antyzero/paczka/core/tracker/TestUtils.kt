package com.antyzero.paczka.core.tracker

import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer

/**
 * Kept for class loader reference
 */
private class Null

fun readResource(file: String) = Null::class.java.getResource("/$file").readText()

fun MockWebServer.url(): HttpUrl = this.url("/")

fun server(block: MockWebServer.() -> Unit): MockWebServer = MockWebServer().apply {
    block.invoke(this)
    start()
}

