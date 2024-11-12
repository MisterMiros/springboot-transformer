package com.example.mistermiros.transformer.model.transformers

import com.example.mistermiros.transformer.model.exceptions.TransformParameterMissingException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RegexReplaceTransformerTests {

    private val regexReplaceTransformer = RegexReplaceTransformer()

    @Test
    fun `transform should return transformed input value`() {
        // Arrange
        val input = "Transform Should Work"
        val regex = "Should.{2}"
        val replaceWith = "does w"

        val parameters = mapOf(TransformerParameters.REGEX to regex, TransformerParameters.REPLACE_WITH to replaceWith)

        // Act
        val result = regexReplaceTransformer.transform(input, parameters)

        // Assert
        assertEquals(result, "Transform does work")
    }

    @Test
    fun `transform should return transformed input value when multiple matches`() {
        // Arrange
        val input = "Transform Should Work Should Should Work"
        val regex = "Should.{2}"
        val replaceWith = "does w"

        val parameters = mapOf(TransformerParameters.REGEX to regex, TransformerParameters.REPLACE_WITH to replaceWith)

        // Act
        val result = regexReplaceTransformer.transform(input, parameters)

        // Assert
        assertEquals(result, "Transform does work does whould Work")
    }


    @Test
    fun `transform should throw exception when no parameters`() {
        // Arrange
        val input = "Transform Should Work"
        val parameters = emptyMap<String, String>()

        // Act
        val exception =
            assertThrows<TransformParameterMissingException> { regexReplaceTransformer.transform(input, parameters) }

        // Assert
        assertEquals(exception.groupId, regexReplaceTransformer.groupId)
        assertEquals(exception.transformerId, regexReplaceTransformer.transformerId)
        assertEquals(exception.parameterKey, TransformerParameters.REGEX)
    }

    @Test
    fun `transform should throw exception when no regex parameter`() {
        // Arrange
        val input = "Transform Should Work"
        val parameters = mapOf(TransformerParameters.REPLACE_WITH to "ignored")

        // Act
        val exception =
            assertThrows<TransformParameterMissingException> { regexReplaceTransformer.transform(input, parameters) }

        // Assert
        assertEquals(exception.groupId, regexReplaceTransformer.groupId)
        assertEquals(exception.transformerId, regexReplaceTransformer.transformerId)
        assertEquals(exception.parameterKey, TransformerParameters.REGEX)
    }

    @Test
    fun `transform should throw exception when no replaceWith parameter`() {
        // Arrange
        val input = "Transform Should Work"
        val parameters = mapOf(TransformerParameters.REGEX to "ignored")

        // Act
        val exception =
            assertThrows<TransformParameterMissingException> { regexReplaceTransformer.transform(input, parameters) }

        // Assert
        assertEquals(exception.groupId, regexReplaceTransformer.groupId)
        assertEquals(exception.transformerId, regexReplaceTransformer.transformerId)
        assertEquals(exception.parameterKey, TransformerParameters.REPLACE_WITH)
    }
}