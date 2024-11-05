package com.dolist.demo.repository

import com.dolist.demo.entity.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaskRepository : JpaRepository<TaskEntity, UUID> {

    fun findTaskById(id: UUID): TaskEntity

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM task WHERE is_task_open = TRUE"
    )
    fun retrieveAllOpenTasks(): List<TaskEntity>

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM task WHERE is_task_open = FALSE"
    )
    fun retrieveAllClosedTasks(): List<TaskEntity>

    @Query(
        nativeQuery = true,
        value = """
            SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END 
            FROM task t WHERE t.description =? 1
        """
    )
    fun descriptionAlreadyExists(description: String): Boolean
}