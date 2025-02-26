package com.vir.isekai

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableEncryptableProperties
class App

fun main(args: Array<String>) {
	runApplication<App>(*args)
}