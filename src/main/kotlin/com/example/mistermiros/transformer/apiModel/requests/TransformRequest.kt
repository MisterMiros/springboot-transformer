package com.example.mistermiros.transformer.apiModel.requests

data class TransformRequest(
    val input: String,
    val transformers: List<TransformerElement>
)