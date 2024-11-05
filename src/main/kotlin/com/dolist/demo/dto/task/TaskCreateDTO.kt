package com.dolist.demo.dto.task

import com.dolist.demo.enums.PriorityType
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime
import java.util.*

data class TaskCreateDTO(
    @NotBlank(message = "Task description can't be empty")
    var description: String,

    var isReminderSet: Boolean,

    var isTaskOpen: Boolean,

    @NotBlank(message = "Task creation date can't be empty")
    var createdOn: LocalDateTime,

    var priority: PriorityType
)
