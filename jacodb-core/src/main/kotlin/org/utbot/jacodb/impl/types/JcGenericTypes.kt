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

package org.utbot.jacodb.impl.types

import org.utbot.jacodb.api.JcBoundedWildcard
import org.utbot.jacodb.api.JcClasspath
import org.utbot.jacodb.api.JcRefType
import org.utbot.jacodb.api.JcTypeVariable
import org.utbot.jacodb.api.JcTypeVariableDeclaration
import org.utbot.jacodb.api.JcUnboundWildcard

class JcUnboundWildcardImpl(override val classpath: JcClasspath) :
    JcUnboundWildcard {

    override val nullable: Boolean = true

    override val typeName: String
        get() = "*"

    override fun copyWithNullability(nullability: Boolean?): JcRefType {
        if (nullability != true)
            error("Attempting to make wildcard not-nullable, which are always nullable by convention")
        return this
    }
}

class JcBoundedWildcardImpl(
    override val upperBounds: List<JcRefType>,
    override val lowerBounds: List<JcRefType>,
) : JcBoundedWildcard {
    override val nullable: Boolean = true

    override val classpath: JcClasspath
        get() = upperBounds.firstOrNull()?.classpath ?: lowerBounds.firstOrNull()?.classpath
        ?: throw IllegalStateException("Upper or lower bound should be specified")

    override val typeName: String
        get() {
            val (name, bounds) = when{
                upperBounds.isNotEmpty() -> "extends" to upperBounds
                else -> "super" to lowerBounds
            }
            return "? $name ${bounds.joinToString(" & ") { it.typeName }}"
        }


    override fun copyWithNullability(nullability: Boolean?): JcRefType {
        if (nullability != true)
            error("Attempting to make wildcard not-nullable, which are always nullable by convention")
        return this
    }
}


class JcTypeVariableImpl(
    override val classpath: JcClasspath,
    private val declaration: JcTypeVariableDeclaration,
    override val nullable: Boolean?
) : JcTypeVariable {

    override val typeName: String
        get() = symbol

    override val symbol: String get() = declaration.symbol

    override val bounds: List<JcRefType>
        get() = declaration.bounds

    override fun copyWithNullability(nullability: Boolean?): JcRefType {
        return JcTypeVariableImpl(classpath, declaration, nullability)
    }
}