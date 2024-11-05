package com.dolist.demo.utils.extensions

import com.dolist.demo.exception.TargetNotFoundException
import com.dolist.demo.utils.constants.NOT_FOUND
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

fun <T : Any> JpaRepository<T, UUID>.findByIdOrNotFound(id: UUID, entity: String? = null): T = this
    .findById(id)
    .orElseThrow { TargetNotFoundException("$entity $NOT_FOUND") }

//fun <T : Any> JpaRepository<T, UUID>.entityExistsById(id: UUID):Boolean = this.existsById(id)

