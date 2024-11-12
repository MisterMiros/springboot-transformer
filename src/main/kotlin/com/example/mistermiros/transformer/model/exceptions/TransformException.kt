package com.example.mistermiros.transformer.model.exceptions

open class TransformException(val groupId: String, val transformerId: String): RuntimeException() {
    override val message: String = "Transformer '$groupId:$transformerId' error"
}