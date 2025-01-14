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

/*
 * This file is generated by jOOQ.
 */
package org.jacodb.impl.storage.jooq.indexes


import org.jacodb.impl.storage.jooq.tables.Bytecodelocations
import org.jacodb.impl.storage.jooq.tables.Classhierarchies
import org.jacodb.impl.storage.jooq.tables.Fields
import org.jacodb.impl.storage.jooq.tables.Methods
import org.jacodb.impl.storage.jooq.tables.Symbols
import org.jooq.Index
import org.jooq.impl.DSL
import org.jooq.impl.Internal


// -------------------------------------------------------------------------
// INDEX definitions
// -------------------------------------------------------------------------

val BYTECODELOCATIONS_HASH: Index = Internal.createIndex(DSL.name("Bytecodelocations_hash"), Bytecodelocations.BYTECODELOCATIONS, arrayOf(Bytecodelocations.BYTECODELOCATIONS.UNIQUEID), true)
val `CLASS HIERARCHIES`: Index = Internal.createIndex(DSL.name("Class Hierarchies"), Classhierarchies.CLASSHIERARCHIES, arrayOf(Classhierarchies.CLASSHIERARCHIES.SUPER_ID), false)
val FIELDS_CLASS_ID_NAME: Index = Internal.createIndex(DSL.name("Fields_class_id_name"), Fields.FIELDS, arrayOf(Fields.FIELDS.CLASS_ID, Fields.FIELDS.NAME), true)
val METHODS_CLASS_ID_NAME_DESC: Index = Internal.createIndex(DSL.name("Methods_class_id_name_desc"), Methods.METHODS, arrayOf(Methods.METHODS.CLASS_ID, Methods.METHODS.NAME, Methods.METHODS.DESC), true)
val SYMBOLS_NAME: Index = Internal.createIndex(DSL.name("Symbols_name"), Symbols.SYMBOLS, arrayOf(Symbols.SYMBOLS.NAME), true)
