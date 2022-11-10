/*
 * This file is generated by jOOQ.
 */
package org.utbot.jcdb.impl.storage.jooq.tables.records


import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record7
import org.jooq.Row7
import org.jooq.impl.UpdatableRecordImpl
import org.utbot.jcdb.impl.storage.jooq.tables.Methods


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class MethodsRecord() : UpdatableRecordImpl<MethodsRecord>(Methods.METHODS), Record7<Long?, Int?, Long?, String?, String?, Long?, Long?> {

    var id: Long?
        set(value): Unit = set(0, value)
        get(): Long? = get(0) as Long?

    var access: Int?
        set(value): Unit = set(1, value)
        get(): Int? = get(1) as Int?

    var name: Long?
        set(value): Unit = set(2, value)
        get(): Long? = get(2) as Long?

    var signature: String?
        set(value): Unit = set(3, value)
        get(): String? = get(3) as String?

    var desc: String?
        set(value): Unit = set(4, value)
        get(): String? = get(4) as String?

    var returnClass: Long?
        set(value): Unit = set(5, value)
        get(): Long? = get(5) as Long?

    var classId: Long?
        set(value): Unit = set(6, value)
        get(): Long? = get(6) as Long?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record1<Long?> = super.key() as Record1<Long?>

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    override fun fieldsRow(): Row7<Long?, Int?, Long?, String?, String?, Long?, Long?> = super.fieldsRow() as Row7<Long?, Int?, Long?, String?, String?, Long?, Long?>
    override fun valuesRow(): Row7<Long?, Int?, Long?, String?, String?, Long?, Long?> = super.valuesRow() as Row7<Long?, Int?, Long?, String?, String?, Long?, Long?>
    override fun field1(): Field<Long?> = Methods.METHODS.ID
    override fun field2(): Field<Int?> = Methods.METHODS.ACCESS
    override fun field3(): Field<Long?> = Methods.METHODS.NAME
    override fun field4(): Field<String?> = Methods.METHODS.SIGNATURE
    override fun field5(): Field<String?> = Methods.METHODS.DESC
    override fun field6(): Field<Long?> = Methods.METHODS.RETURN_CLASS
    override fun field7(): Field<Long?> = Methods.METHODS.CLASS_ID
    override fun component1(): Long? = id
    override fun component2(): Int? = access
    override fun component3(): Long? = name
    override fun component4(): String? = signature
    override fun component5(): String? = desc
    override fun component6(): Long? = returnClass
    override fun component7(): Long? = classId
    override fun value1(): Long? = id
    override fun value2(): Int? = access
    override fun value3(): Long? = name
    override fun value4(): String? = signature
    override fun value5(): String? = desc
    override fun value6(): Long? = returnClass
    override fun value7(): Long? = classId

    override fun value1(value: Long?): MethodsRecord {
        this.id = value
        return this
    }

    override fun value2(value: Int?): MethodsRecord {
        this.access = value
        return this
    }

    override fun value3(value: Long?): MethodsRecord {
        this.name = value
        return this
    }

    override fun value4(value: String?): MethodsRecord {
        this.signature = value
        return this
    }

    override fun value5(value: String?): MethodsRecord {
        this.desc = value
        return this
    }

    override fun value6(value: Long?): MethodsRecord {
        this.returnClass = value
        return this
    }

    override fun value7(value: Long?): MethodsRecord {
        this.classId = value
        return this
    }

    override fun values(value1: Long?, value2: Int?, value3: Long?, value4: String?, value5: String?, value6: Long?, value7: Long?): MethodsRecord {
        this.value1(value1)
        this.value2(value2)
        this.value3(value3)
        this.value4(value4)
        this.value5(value5)
        this.value6(value6)
        this.value7(value7)
        return this
    }

    /**
     * Create a detached, initialised MethodsRecord
     */
    constructor(id: Long? = null, access: Int? = null, name: Long? = null, signature: String? = null, desc: String? = null, returnClass: Long? = null, classId: Long? = null): this() {
        this.id = id
        this.access = access
        this.name = name
        this.signature = signature
        this.desc = desc
        this.returnClass = returnClass
        this.classId = classId
    }
}