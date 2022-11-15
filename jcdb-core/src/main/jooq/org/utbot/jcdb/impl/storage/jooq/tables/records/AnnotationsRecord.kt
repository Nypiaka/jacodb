/*
 * This file is generated by jOOQ.
 */
package org.utbot.jcdb.impl.storage.jooq.tables.records


import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record8
import org.jooq.Row8
import org.jooq.impl.UpdatableRecordImpl
import org.utbot.jcdb.impl.storage.jooq.tables.Annotations


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class AnnotationsRecord() : UpdatableRecordImpl<AnnotationsRecord>(Annotations.ANNOTATIONS), Record8<Long?, Long?, Boolean?, Long?, Long?, Long?, Long?, Long?> {

    var id: Long?
        set(value) = set(0, value)
        get() = get(0) as Long?

    var annotationName: Long?
        set(value) = set(1, value)
        get() = get(1) as Long?

    var visible: Boolean?
        set(value) = set(2, value)
        get() = get(2) as Boolean?

    var parentAnnotation: Long?
        set(value) = set(3, value)
        get() = get(3) as Long?

    var classId: Long?
        set(value) = set(4, value)
        get() = get(4) as Long?

    var methodId: Long?
        set(value) = set(5, value)
        get() = get(5) as Long?

    var fieldId: Long?
        set(value) = set(6, value)
        get() = get(6) as Long?

    var paramId: Long?
        set(value) = set(7, value)
        get() = get(7) as Long?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record1<Long?> = super.key() as Record1<Long?>

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    override fun fieldsRow(): Row8<Long?, Long?, Boolean?, Long?, Long?, Long?, Long?, Long?> = super.fieldsRow() as Row8<Long?, Long?, Boolean?, Long?, Long?, Long?, Long?, Long?>
    override fun valuesRow(): Row8<Long?, Long?, Boolean?, Long?, Long?, Long?, Long?, Long?> = super.valuesRow() as Row8<Long?, Long?, Boolean?, Long?, Long?, Long?, Long?, Long?>
    override fun field1(): Field<Long?> = Annotations.ANNOTATIONS.ID
    override fun field2(): Field<Long?> = Annotations.ANNOTATIONS.ANNOTATION_NAME
    override fun field3(): Field<Boolean?> = Annotations.ANNOTATIONS.VISIBLE
    override fun field4(): Field<Long?> = Annotations.ANNOTATIONS.PARENT_ANNOTATION
    override fun field5(): Field<Long?> = Annotations.ANNOTATIONS.CLASS_ID
    override fun field6(): Field<Long?> = Annotations.ANNOTATIONS.METHOD_ID
    override fun field7(): Field<Long?> = Annotations.ANNOTATIONS.FIELD_ID
    override fun field8(): Field<Long?> = Annotations.ANNOTATIONS.PARAM_ID
    override fun component1(): Long? = id
    override fun component2(): Long? = annotationName
    override fun component3(): Boolean? = visible
    override fun component4(): Long? = parentAnnotation
    override fun component5(): Long? = classId
    override fun component6(): Long? = methodId
    override fun component7(): Long? = fieldId
    override fun component8(): Long? = paramId
    override fun value1(): Long? = id
    override fun value2(): Long? = annotationName
    override fun value3(): Boolean? = visible
    override fun value4(): Long? = parentAnnotation
    override fun value5(): Long? = classId
    override fun value6(): Long? = methodId
    override fun value7(): Long? = fieldId
    override fun value8(): Long? = paramId

    override fun value1(value: Long?): AnnotationsRecord {
        this.id = value
        return this
    }

    override fun value2(value: Long?): AnnotationsRecord {
        this.annotationName = value
        return this
    }

    override fun value3(value: Boolean?): AnnotationsRecord {
        this.visible = value
        return this
    }

    override fun value4(value: Long?): AnnotationsRecord {
        this.parentAnnotation = value
        return this
    }

    override fun value5(value: Long?): AnnotationsRecord {
        this.classId = value
        return this
    }

    override fun value6(value: Long?): AnnotationsRecord {
        this.methodId = value
        return this
    }

    override fun value7(value: Long?): AnnotationsRecord {
        this.fieldId = value
        return this
    }

    override fun value8(value: Long?): AnnotationsRecord {
        this.paramId = value
        return this
    }

    override fun values(value1: Long?, value2: Long?, value3: Boolean?, value4: Long?, value5: Long?, value6: Long?, value7: Long?, value8: Long?): AnnotationsRecord {
        this.value1(value1)
        this.value2(value2)
        this.value3(value3)
        this.value4(value4)
        this.value5(value5)
        this.value6(value6)
        this.value7(value7)
        this.value8(value8)
        return this
    }

    /**
     * Create a detached, initialised AnnotationsRecord
     */
    constructor(id: Long? = null, annotationName: Long? = null, visible: Boolean? = null, parentAnnotation: Long? = null, classId: Long? = null, methodId: Long? = null, fieldId: Long? = null, paramId: Long? = null): this() {
        this.id = id
        this.annotationName = annotationName
        this.visible = visible
        this.parentAnnotation = parentAnnotation
        this.classId = classId
        this.methodId = methodId
        this.fieldId = fieldId
        this.paramId = paramId
    }
}