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

package org.jacodb.testing.types

import org.jacodb.api.JcArrayType
import org.jacodb.api.JcPrimitiveType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TypesTest : BaseTypesTest() {

    @Test
    fun `primitive and array types`() {
        val primitiveAndArrays = findType<PrimitiveAndArrays>()
        val fields = primitiveAndArrays.declaredFields
        assertEquals(2, fields.size)

        with(fields.first()) {
            assertTrue(fieldType is JcPrimitiveType)
            assertEquals("value", name)
            assertEquals("int", fieldType.typeName)
        }
        with(fields.get(1)) {
            assertTrue(fieldType is JcArrayType)
            assertEquals("intArray", name)
            assertEquals("int[]", fieldType.typeName)
        }


        val methods = primitiveAndArrays.declaredMethods.filterNot { it.method.isConstructor }
        with(methods.first()) {
            assertTrue(returnType is JcArrayType)
            assertEquals("int[]", returnType.typeName)

            assertEquals(1, parameters.size)
            with(parameters.get(0)) {
                assertTrue(type is JcArrayType)
                assertEquals("java.lang.String[]", type.typeName)
            }
        }
    }

    @Test
    fun `parameters test`() {
        class Example {
            fun f(notNullable: String, nullable: String?): Int {
                return 0
            }
        }

        val type = findType<Example>()
        val actualParameters = type.declaredMethods.single { it.name == "f" }.parameters
        assertEquals(listOf("notNullable", "nullable"), actualParameters.map { it.name })
        assertEquals(false, actualParameters.first().nullable)
        assertEquals(true, actualParameters.get(1).nullable)
    }
}