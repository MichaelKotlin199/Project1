package org.example.exception

sealed class AppException(message: String) : RuntimeException(message)