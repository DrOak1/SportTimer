package com.nikitamedvedev.cybertimer

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month
import java.util.UUID

data class SportEvent(
    val Name:String,
    val Date:LocalDate,
    val Teams:List<String>,
    val Result:String,
    val Winner:String,
    val Active:Boolean,
    val id: UUID = UUID.randomUUID(),
)
class HomeViewModel(): ViewModel() {
    val items: SnapshotStateList<SportEvent> = DefaultEvents.toMutableStateList()
    fun onClickRemoveEvent(event: SportEvent) = items.remove(event)

    private companion object {

        private val DefaultEvents = listOf(
            SportEvent(
                "Football",
                LocalDate.of(2023, Month.APRIL, 15),
                listOf("Manchester United", "Bavaria"),
                "2:0",
                "Manchester City",
                false
            ),
            SportEvent(
                "Football",
                LocalDate.of(2023, Month.APRIL, 15),
                listOf("Barcelona", "Real Madrid"),
                "2:3",
                "Real Madrid",
                false
            )
        )
    }
}