package com.dolist.demo.service

import com.dolist.demo.dto.task.TaskCreateDTO
import com.dolist.demo.dto.task.TaskDTO
import com.dolist.demo.entity.TaskEntity
import com.dolist.demo.exception.AlreadyExistsException
import com.dolist.demo.repository.TaskRepository
import com.dolist.demo.utils.constants.ALREADY_EXISTS
import com.dolist.demo.utils.constants.DELETED
import com.dolist.demo.utils.constants.TASK
import com.dolist.demo.utils.extensions.findByIdOrNotFound
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.stream.Collectors
import kotlin.reflect.KFunction1

@Service
class TaskService(private val repository: TaskRepository) {

    fun getTaskById(id: UUID): TaskDTO {
//        val task = repository.findTaskById(id)
        val task = repository.findByIdOrNotFound(id)
        return mapToDTO(task)
    }

//    fun getAllTasks(): List<TaskDTO> = repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList())

    fun getAllTasks(): List<TaskDTO> = repository.findAll().map { mapToDTO(it) }

    fun getAllOpenTasks(): List<TaskDTO> = repository.retrieveAllOpenTasks().map { mapToDTO(it) }

    fun getAllClosedTasks(): List<TaskDTO> = repository.retrieveAllClosedTasks().map { mapToDTO(it) }

    fun createTask(request: TaskCreateDTO): TaskDTO {
        if (repository.descriptionAlreadyExists(request.description)) {
            throw AlreadyExistsException("$TASK $ALREADY_EXISTS")
        }

        val task = TaskEntity()
        mapToEntity(task, request)
        val savedTask = repository.save(task)
        return mapToDTO(savedTask)
    }

    fun updateTask(id: UUID, request: TaskCreateDTO): TaskDTO {
        val task = repository.findByIdOrNotFound(id, TASK)
        mapToEntity(task, request)
        val savedTask = repository.save(task)
        return mapToDTO(savedTask)
    }

    fun deleteTask(id: UUID): String {
        repository.findByIdOrNotFound(id)
        repository.deleteById(id)
        return "$TASK $DELETED"
    }

    private fun mapToDTO(task: TaskEntity) = TaskDTO(
        task.id,
        task.description,
        task.isReminderSet,
        task.isTaskOpen,
        task.createdOn,
        task.priority
    )

    private fun mapToEntity(task: TaskEntity, request: TaskCreateDTO) {
        task.description = request.description
        task.isReminderSet = request.isReminderSet
        task.isTaskOpen = request.isTaskOpen
        task.priority = request.priority
    }

    private fun taskExists(id: UUID): Boolean {
        return repository.existsById(id)
    }

}

