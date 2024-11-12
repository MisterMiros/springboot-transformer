package com.example.mistermiros.transformer.model.interfaces

import org.springframework.stereotype.Service

@Service
interface Transformer {
    val transformerId: String
    val groupId: String

    fun transform(input: String, parameters: Map<String, String>): String
}