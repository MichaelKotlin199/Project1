package org.example.exception

class WrongOperationTypeException(message: String) : AppException("Wrong operation type: $message")