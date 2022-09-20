package org.utbot.jcdb.impl.bytecode

import org.objectweb.asm.tree.MethodNode
import org.utbot.jcdb.api.JcAnnotation
import org.utbot.jcdb.api.JcClassOrInterface
import org.utbot.jcdb.api.JcDeclaration
import org.utbot.jcdb.api.JcMethod
import org.utbot.jcdb.api.JcParameter
import org.utbot.jcdb.api.TypeName
import org.utbot.jcdb.api.ext.findClass
import org.utbot.jcdb.impl.ClassIdService
import org.utbot.jcdb.impl.signature.MethodResolutionImpl
import org.utbot.jcdb.impl.signature.MethodSignature
import org.utbot.jcdb.impl.types.MethodInfo
import org.utbot.jcdb.impl.types.TypeNameImpl
import org.utbot.jcdb.impl.vfs.ClassVfsItem

class JcMethodImpl(
    private val methodInfo: MethodInfo,
    private val classNode: ClassVfsItem,
    override val jcClass: JcClassOrInterface,
    private val classIdService: ClassIdService
) : JcMethod {

    override val name: String get() = methodInfo.name
    override val access: Int get() = methodInfo.access
    override val signature: String? get() = methodInfo.signature
    override val returnType: TypeName get() = TypeNameImpl(methodInfo.returnClass)

    override suspend fun exceptions(): List<JcClassOrInterface> {
        val methodSignature = MethodSignature.of(methodInfo.signature)
        if (methodSignature is MethodResolutionImpl) {
            return methodSignature.exceptionTypes.map {
                jcClass.classpath.findClass(it.name)
            }
        }
        return emptyList()
    }

    override val declaration: JcDeclaration
        get() = JcDeclarationImpl.of(location = jcClass.declaration.location, this)

    override val parameters: List<JcParameter>
        get() = methodInfo.parametersInfo.map { JcParameterImpl(it, classIdService.cp) }

    override val annotations: List<JcAnnotation>
        get() = methodInfo.annotations.map { JcAnnotationImpl(it, classIdService.cp) }


    override val description get() = methodInfo.desc

    fun signature(internalNames: Boolean) = methodInfo.signature(internalNames)

    override suspend fun body(): MethodNode {
        return classNode.fullByteCode().methods.first { it.name == name && it.desc == methodInfo.desc }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is JcMethodImpl) {
            return false
        }
        return other.name == name && jcClass == other.jcClass && methodInfo.desc == other.methodInfo.desc
    }

    override fun hashCode(): Int {
        return 31 * jcClass.hashCode() + name.hashCode()
    }


}