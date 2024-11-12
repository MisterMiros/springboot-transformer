package com.example.mistermiros.transformer.model

import com.example.mistermiros.transformer.model.exceptions.TransformerNotFoundException
import com.example.mistermiros.transformer.model.interfaces.Transformer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransformService(@Autowired transformers: List<Transformer>): com.example.mistermiros.transformer.model.interfaces.TransformService {

    val transformersByIds: Map<Pair<String, String>, Transformer> =
        transformers.associateBy { Pair(it.groupId, it.transformerId) }

    override fun transform(groupId: String, transformerId: String, input: String, parameters: Map<String, String>): String {
        val transformer = transformersByIds[Pair(groupId, transformerId)]
            ?: throw TransformerNotFoundException(groupId, transformerId)
        return transformer.transform(input, parameters)
    }
}