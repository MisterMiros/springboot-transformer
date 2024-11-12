package com.example.mistermiros.transformer.model.transformers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GreekCyrillicConvertTransformerTests {
    private val greekCyrillicConvertTransformer: GreekCyrillicConvertTransformer = GreekCyrillicConvertTransformer();

    @Test
    fun `transform should return transformed greek input value`() {
        // Arrange
        val input = "Βασιλεία τῶν Ῥωμαίων"
        val parameters = emptyMap<String, String>()

        // Act
        val result = greekCyrillicConvertTransformer.transform(input, parameters)

        // Assert
        assertEquals(result, "Basileía tō̂n Rhōmaíōn")
    }

    @Test
    fun `transform should return transformed cyrillic input value when multiple matches`() {
        // Arrange
        val input = "Византийская Империя"
        val parameters = emptyMap<String, String>()

        // Act
        val result = greekCyrillicConvertTransformer.transform(input, parameters)

        // Assert
        assertEquals(result, "Vizantijskaya Imperiya")
    }

    @Test
    fun `transform should return transformed greek and cyrillic input value when multiple matches`() {
        // Arrange
        val input = "Империя τῶν Ῥωμαίων"
        val parameters = emptyMap<String, String>()

        // Act
        val result = greekCyrillicConvertTransformer.transform(input, parameters)

        // Assert
        assertEquals(result, "Imperiya tō̂n Rhōmaíōn")
    }
}