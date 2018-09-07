/*
 * Copyright © 2018 Harsh Shandilya. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wireguard.android.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.wireguard.config.Attribute

fun <T> ArrayList<T>.addExclusive(otherArray: ArrayList<T>): ArrayList<T> {
    for (item: T in otherArray)
        if (item !in this)
            this.add(item)
    return this
}

fun <T> ArrayList<T>.addExclusive(otherArray: Array<T>): ArrayList<T> {
    for (item: T in otherArray)
        if (item !in this)
            this.add(item)
    return this
}

fun String?.addExclusive(otherArray: ArrayList<String>): String {
    val stringCopy = Attribute.stringToList(this).toCollection(ArrayList())
    for (item: String in otherArray)
        if (item !in stringCopy)
            stringCopy.add(item)
    return Attribute.iterableToString(stringCopy)
}

fun copyTextView(view: View) {
    if (view !is TextView)
        return
    val text = view.text
    if (text == null || text.isEmpty())
        return
    val service = view.getContext().getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return
    val description = view.getContentDescription()
    service.primaryClip = ClipData.newPlainText(description, text)
    Snackbar.make(view, description.toString() + " copied to clipboard", Snackbar.LENGTH_LONG).show()
}