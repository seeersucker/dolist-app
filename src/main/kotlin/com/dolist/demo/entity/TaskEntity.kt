package com.dolist.demo.entity

import com.dolist.demo.enums.PriorityType
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import java.util.UUID


@Entity
@Table(
    name = "task",
    uniqueConstraints = [UniqueConstraint(name = "uk_task_description", columnNames = ["description"])]
)
class TaskEntity(
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    val id: UUID = UUID.randomUUID(),

    @NotBlank
    @Column(name = "description", nullable = false, unique = true)
    var description: String = "",

    @Column(name = "is_reminder_set", nullable = false)
    var isReminderSet: Boolean = false,

    @Column(name = "is_task_open", nullable = false)
    var isTaskOpen: Boolean = true,

    @Column(name = "created_on", nullable = false)
    var createdOn: LocalDateTime = LocalDateTime.now(),

    @NotNull
    @Enumerated(EnumType.STRING)
    var priority: PriorityType = PriorityType.LOW
)