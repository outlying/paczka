package com.antyzero.paczka.core.tracker

/**
 * Kept for class loader reference
 */
private class Null

fun readResource(file: String): String {

    return Null::class.java.getResource(file).readText()
}