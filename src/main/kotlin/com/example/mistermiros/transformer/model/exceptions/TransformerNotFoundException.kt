package com.example.mistermiros.transformer.model.exceptions

class TransformerNotFoundException(groupId: String, transformerId: String): TransformException(groupId, transformerId) {
    override val message: String = "Transformer '$groupId:$transformerId' not found"
}