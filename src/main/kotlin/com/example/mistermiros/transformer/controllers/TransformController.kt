package com.example.mistermiros.transformer.controllers

import com.example.mistermiros.transformer.apiModel.requests.TransformRequest
import com.example.mistermiros.transformer.apiModel.responses.ErrorResponse
import com.example.mistermiros.transformer.apiModel.responses.TransformResponse
import com.example.mistermiros.transformer.model.interfaces.TransformService
import com.example.mistermiros.transformer.model.exceptions.TransformException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TransformController(
    @Autowired val transformService: TransformService
) {
    @PostMapping("/applyTransformers")
    fun applyTransformers(@RequestBody request: TransformRequest): ResponseEntity<TransformResponse> {
        var transformed = request.input
        for (transformer in request.transformers) {
            transformed = transformService.transform(transformer.groupId, transformer.transformerId, transformed, transformer.parameters)
        }

        return ResponseEntity.ok(TransformResponse(transformed))
    }

    @ExceptionHandler(TransformException::class)
    fun handleTransformException(ex: TransformException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse(ex.message))
    }
}