package com.example.mistermiros.transformer.apiModel.requests

data class TransformerElement(
    val groupId: String,
    val transformerId: String,
    val parameters: Map<String, String> = emptyMap()
)