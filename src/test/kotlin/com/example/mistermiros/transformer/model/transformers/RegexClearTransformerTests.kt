package com.example.mistermiros.transformer.model.transformers

import com.example.mistermiros.transformer.model.exceptions.TransformParameterMissingException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RegexClearTransformerTests {

    private val regexClearTransformer: RegexClearTransformer = RegexClearTransformer();

    @Test
    fun `transform should return transformed input value`() {
        // Arrange
        val input = "Transform Should Work"
        val regex = "Should.{2}"

        val parameters = mapOf(TransformerParameters.REGEX to regex)

        // Act
        val result = regexClearTransformer.transform(input, parameters)

        // Assert
        assertEquals(result, "Transform ork")
    }

    @Test
    fun `transform should return transformed input value when multiple matches`() {
        // Arrange
        val input = "Transform Should Work Should Should Work"
        val regex = "Should.{2}"

        val parameters = mapOf(TransformerParameters.REGEX to regex)

        // Act
        val result = regexClearTransformer.transform(input, parameters)

        // Assert
        assertEquals(result, "Transform ork hould Work")
    }


    @Test
    fun `transform should throw exception when no regex parameter`() {
        // Arrange
        val input = "Transform Should Work"
        val parameters = emptyMap<String, String>()

        // Act
        val exception =
            assertThrows<TransformParameterMissingException> { regexClearTransformer.transform(input, parameters) }

        // Assert
        assertEquals(exception.groupId, regexClearTransformer.groupId)
        assertEquals(exception.transformerId, regexClearTransformer.transformerId)
        assertEquals(exception.parameterKey, TransformerParameters.REGEX)
    }
}