package com.dolist.demo.dto.task

import com.dolist.demo.enums.PriorityType
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime
import java.util.*

data class TaskUpdateDTO(
    var description: String?,
    var isReminderSet: Boolean?,
    var isTaskOpen: Boolean?,
    var priority: PriorityType?
)
