package org.utbot.jcdb.impl.cfg

import kotlinx.coroutines.runBlocking
import org.utbot.jcdb.api.JCDB
import org.utbot.jcdb.api.JcClassOrInterface
import org.utbot.jcdb.api.JcClasspath
import org.utbot.jcdb.api.ext.findClass
import org.utbot.jcdb.api.methods
import org.utbot.jcdb.impl.JcGraphChecker
import org.utbot.jcdb.impl.allClasspath
import org.utbot.jcdb.jcdb
import java.io.Closeable
import java.io.File

class IRSvgGenerator(private val folder: File) : Closeable {

    private val db: JCDB
    private val cp: JcClasspath

    init {
        if (!folder.exists()) {
            folder.mkdir()
        } else {
            folder.list()?.forEach { File(folder, it).delete() }
        }
        db = runBlocking {
            jcdb {
                loadByteCode(allClasspath)
            }
        }
        cp = runBlocking { db.classpath(allClasspath) }
    }

    fun generate() {
        dumpClass(cp.findClass<IRExamples>())
    }

    private fun dumpClass(klass: JcClassOrInterface) {
        klass.methods.filter { it.enclosingClass == klass }.mapIndexed { index, it ->
            val instructionList = it.instructionList()
            val fixedName = it.name.replace(Regex("[^A-Za-z0-9]"), "")
            val fileName = "${it.enclosingClass.simpleName}-$fixedName-$index.svg"
            val graph = instructionList.graph(it)
            JcGraphChecker(graph).check()
            graph.toFile("dot", false, file = File(folder, "graph-$fileName"))
            graph.blockGraph().toFile("dot", file = File(folder, "block-graph-$fileName"))
        }
    }


    override fun close() {
        cp.close()
        db.close()
    }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        throw IllegalStateException("Please provide folder for target svgs")
    }
    val folder = args[0]
    IRSvgGenerator(folder = File(folder)).generate()
}