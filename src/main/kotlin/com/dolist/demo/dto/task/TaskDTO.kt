package com.dolist.demo.dto.task

import com.dolist.demo.enums.PriorityType
import java.time.LocalDateTime
import java.util.*

data class TaskDTO(
    val id: UUID,
    var description: String,
    var isReminderSet: Boolean,
    var isTaskOpen: Boolean,
    var createdOn: LocalDateTime,
    var priority: PriorityType
)
