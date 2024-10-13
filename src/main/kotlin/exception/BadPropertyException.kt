package org.example.exception

class BadPropertyException(message: String) : AppException("Bad property error: $message")