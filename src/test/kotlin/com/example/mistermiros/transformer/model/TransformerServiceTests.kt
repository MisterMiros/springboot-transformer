package com.example.mistermiros.transformer.model

import com.example.mistermiros.transformer.model.exceptions.TransformerNotFoundException
import com.example.mistermiros.transformer.model.interfaces.Transformer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TransformerServiceTests {

    @Test
    fun `transform should return transformed value`() {
        // Arrange
        val groupId = "group"
        val transformerId = "transformer"
        val input = "input";
        val parameters = mapOf("parameter" to "value")
        val transformed = "transformed input"

        val transformer = mockk<Transformer>()
        every { transformer.groupId } returns groupId
        every { transformer.transformerId } returns transformerId
        every { transformer.transform(input, parameters) } returns transformed

        val transformService =  TransformService(listOf(transformer))

        // Act
        val result = transformService.transform(groupId, transformerId, input, parameters)

        // Assert
        assertEquals(transformed, result)
        verify(exactly = 1) { transformer.transform(input, parameters) }
    }


    @Test
    fun `transform should find correct transformer and return transformed value`() {
        // Arrange
        val groupId = "group"
        val transformerId = "transformer"
        val input = "input";
        val parameters = mapOf("parameter" to "value")
        val transformed = "transformed input"

        val correctTransformer = mockk<Transformer>()
        every { correctTransformer.groupId } returns groupId
        every { correctTransformer.transformerId } returns transformerId
        every { correctTransformer.transform(input, parameters) } returns transformed

        val incorrectTransformer = mockk<Transformer>()
        every { incorrectTransformer.groupId } returns "wrong group id"
        every { incorrectTransformer.transformerId } returns "wrong transformer id"
        every { incorrectTransformer.transform(input, parameters) } returns "another transformed input"

        val transformService =  TransformService(listOf(incorrectTransformer, correctTransformer))

        // Act
        val result = transformService.transform(groupId, transformerId, input, parameters)

        // Assert
        assertEquals(transformed, result)
        verify(exactly = 1) { correctTransformer.transform(input, parameters) }
        verify(exactly = 0) { incorrectTransformer.transform(any(), any()) }
    }

    @Test
    fun `transform should throw when no transformers`() {
        // Arrange
        val groupId = "group"
        val transformerId = "transformer"
        val input = "input";
        val parameters = mapOf("parameter" to "value")

        val transformService =  TransformService(emptyList())

        // Act
        val exception = assertThrows<TransformerNotFoundException> {  transformService.transform(groupId, transformerId, input, parameters) }

        // Assert
        assertEquals(exception.groupId, groupId)
        assertEquals(exception.transformerId, transformerId)
    }

    @Test
    fun `transform should throw when no fitting transformers`() {
        // Arrange
        val groupId = "group"
        val transformerId = "transformer"
        val input = "input";
        val parameters = mapOf("parameter" to "value")

        val transformer1 = mockk<Transformer>()
        every { transformer1.groupId } returns "wrong group id 1"
        every { transformer1.transformerId } returns "wrong transformer id 1"

        val transformer2 = mockk<Transformer>()
        every { transformer2.groupId } returns "wrong group id 2"
        every { transformer2.transformerId } returns "wrong transformer id 2"

        val transformer3 = mockk<Transformer>()
        every { transformer3.groupId } returns "wrong group id 3"
        every { transformer3.transformerId } returns "wrong transformer id 3"

        val transformService =  TransformService(listOf(transformer1, transformer2, transformer3))

        // Act
        val exception = assertThrows<TransformerNotFoundException> {  transformService.transform(groupId, transformerId, input, parameters) }

        // Assert
        assertEquals(exception.groupId, groupId)
        assertEquals(exception.transformerId, transformerId)
        verify(exactly = 0) { transformer1.transform(any(), any()) }
        verify(exactly = 0) { transformer2.transform(any(), any()) }
        verify(exactly = 0) { transformer3.transform(any(), any()) }
    }
}