/*
 *  Copyright 2022 UnitTestBot contributors (utbot.org)
 * <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.jacodb.testing.types.nullability

import kotlinx.coroutines.runBlocking
import org.jacodb.api.ext.isNullable
import org.jacodb.testing.types.BaseTypesTest
import org.jacodb.testing.usages.NullAnnotationExamples
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class NullabilityByAnnotationsTest : BaseTypesTest() {

    @Test
    fun `Test field nullability`() = runBlocking {
        val clazz = findType<NullAnnotationExamples>()

        val expectedNullability = mapOf(
            "refNullable" to null,
            "refNotNull" to false,
            "explicitlyNullable" to true,
            "primitiveValue" to false,
        )

        val fields = clazz.declaredFields.filter { it.name in expectedNullability.keys }
        val actualFieldNullability = fields.associate { it.name to it.field.isNullable }
        val actualFieldTypeNullability = fields.associate { it.name to it.fieldType.nullable }

        assertEquals(expectedNullability, actualFieldNullability)
        assertEquals(expectedNullability, actualFieldTypeNullability)
    }

    @Test
    fun `Test method parameter nullability`() = runBlocking {
        val clazz = findType<NullAnnotationExamples>()
        val nullableMethod = clazz.declaredMethods.single { it.name == "nullableMethod" }

        val expectedNullability = listOf(true, false, null)
        val actualParameterNullability = nullableMethod.parameters.map { it.nullable }
        val actualParameterTypeNullability = nullableMethod.parameters.map { it.type.nullable }

        assertEquals(expectedNullability, actualParameterNullability)
        assertEquals(expectedNullability, actualParameterTypeNullability)
    }

    @Test
    fun `Test method nullability`() = runBlocking {
        val clazz = findType<NullAnnotationExamples>()

        val nullableMethod = clazz.declaredMethods.single { it.name == "nullableMethod" }
        assertEquals(null, nullableMethod.method.isNullable)
        assertEquals(null, nullableMethod.returnType.nullable)

        val notNullMethod = clazz.declaredMethods.single { it.name == "notNullMethod" }
        assertEquals(false, notNullMethod.method.isNullable)
        assertEquals(false, notNullMethod.returnType.nullable)
    }
}