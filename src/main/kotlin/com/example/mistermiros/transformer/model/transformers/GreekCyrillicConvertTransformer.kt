package com.example.mistermiros.transformer.model.transformers

import com.example.mistermiros.transformer.model.interfaces.Transformer
import com.ibm.icu.text.Transliterator
import org.springframework.stereotype.Service
import ru.homyakin.iuliia.Schemas
import ru.homyakin.iuliia.Translator

@Service
class GreekCyrillicConvertTransformer : Transformer {

    override val transformerId: String = "greekCyrillic"
    override val groupId: String = "convert"

    private val cyrillicTranslator = Translator(Schemas.GOST_779_ALT)
    private val greekTranslator = Transliterator.getInstance("Greek-Latin")

    override fun transform(input: String, parameters: Map<String, String>): String {
        return greekTranslator.transliterate(cyrillicTranslator.translate(input))
    }
}