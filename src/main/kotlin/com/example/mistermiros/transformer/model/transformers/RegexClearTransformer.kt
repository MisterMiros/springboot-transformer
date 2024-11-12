package com.example.mistermiros.transformer.model.transformers

import com.example.mistermiros.transformer.model.exceptions.TransformParameterMissingException
import com.example.mistermiros.transformer.model.interfaces.Transformer
import org.springframework.stereotype.Service

@Service
class RegexClearTransformer: Transformer {

    override val transformerId: String = "regex"
    override val groupId: String = "clear"

    override fun transform(input: String, parameters: Map<String, String>): String {
        val regexParameter = parameters[TransformerParameters.REGEX]
            ?:  throw TransformParameterMissingException(groupId, transformerId, TransformerParameters.REGEX)

        val regex = Regex(regexParameter, setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.MULTILINE))
        return regex.replace(input, "")
    }
}