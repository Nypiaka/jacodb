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

package org.jacodb.testing

import kotlinx.coroutines.runBlocking
import org.jacodb.api.JcAnnotated
import org.jacodb.api.ext.findClass
import org.jacodb.testing.usages.NullAnnotationExamples
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AnnotationsTest : BaseTest() {

    companion object : WithDB()

    @Test
    fun `Test field annotations`() = runBlocking {
        val clazz = cp.findClass<NullAnnotationExamples>()

        val expectedAnnotations = mapOf(
            "refNullable" to emptyList(),
            "refNotNull" to listOf(jbNotNull),
            "explicitlyNullable" to listOf(jbNullable),
            "primitiveValue" to emptyList(),
        )
        val fields = clazz.declaredFields.filter { it.name in expectedAnnotations.keys }
        val actualAnnotations = fields.associate { it.name to it.annotationsSimple }

        assertEquals(expectedAnnotations, actualAnnotations)
    }

    @Test
    fun `Test method parameter annotations`() = runBlocking {
        val clazz = cp.findClass<NullAnnotationExamples>()
        val nullableMethod = clazz.declaredMethods.single { it.name == "nullableMethod" }

        val actualAnnotations = nullableMethod.parameters.map { it.annotationsSimple }
        val expectedAnnotations = listOf(listOf(jbNullable), listOf(jbNotNull), emptyList())
        assertEquals(expectedAnnotations, actualAnnotations)
    }

    @Test
    fun `Test method annotations`() = runBlocking {
        val clazz = cp.findClass<NullAnnotationExamples>()

        val nullableMethod = clazz.declaredMethods.single { it.name == "nullableMethod" }
        assertEquals(emptyList<String>(), nullableMethod.annotationsSimple)

        val notNullMethod = clazz.declaredMethods.single { it.name == "notNullMethod" }
        assertEquals(listOf(jbNotNull), notNullMethod.annotationsSimple)
    }

    private val jbNullable = "org.jetbrains.annotations.Nullable"
    private val jbNotNull  = "org.jetbrains.annotations.NotNull"
    private val JcAnnotated.annotationsSimple get() = annotations.map { it.name }
}
