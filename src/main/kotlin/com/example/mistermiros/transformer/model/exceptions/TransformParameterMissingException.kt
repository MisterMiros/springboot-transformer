package com.example.mistermiros.transformer.model.exceptions

class TransformParameterMissingException(groupId: String, transformerId: String, val parameterKey: String) : TransformException(groupId, transformerId) {
    override val message: String = "Parameter '$parameterKey' for transformer '$groupId:$transformerId' is missing"
}