package com.avelycure.serializabletestapplication.ui

import java.io.Serializable

data class NotificationMeta(
    val pushType: NotificationUpdater.PushMessageType,
    val hasSmartReplies: Boolean = false,
    val isReminder: Boolean = false,
) : Serializable {
    companion object {
        private const val serialVersionUID = 167805866091887717L
    }
}