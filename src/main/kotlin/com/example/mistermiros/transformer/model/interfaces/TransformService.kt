package com.example.mistermiros.transformer.model.interfaces

import org.springframework.stereotype.Service

@Service
interface TransformService {
    fun transform(groupId: String, transformerId: String, input: String, parameters: Map<String, String>): String
}