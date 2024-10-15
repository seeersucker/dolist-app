package com.dolist.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DolistAppApplication

fun main(args: Array<String>) {
	runApplication<DolistAppApplication>(*args)
	println("Running")
}
