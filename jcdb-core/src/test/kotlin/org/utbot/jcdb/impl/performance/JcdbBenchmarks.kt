package org.utbot.jcdb.impl.performance


import kotlinx.coroutines.runBlocking
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.TearDown
import org.openjdk.jmh.annotations.Warmup
import org.utbot.jcdb.api.JCDB
import org.utbot.jcdb.impl.allClasspath
import org.utbot.jcdb.impl.guavaLib
import org.utbot.jcdb.impl.index.Usages
import org.utbot.jcdb.jcdb
import java.io.File
import java.util.concurrent.TimeUnit


@State(Scope.Benchmark)
@Fork(0)
@Warmup(iterations = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
class JcdbBenchmarks  {

    private var db: JCDB? = null

    @Benchmark
    fun jvmRuntime() {
        db = runBlocking {
            jcdb {
                useProcessJavaRuntime()
            }
        }
    }

    @Benchmark
    fun jvmRuntimeWithUsages() {
        db = runBlocking {
            jcdb {
                useProcessJavaRuntime()
                installFeatures(Usages)
            }
        }
    }

    @Benchmark
    fun jvmRuntimeWithAllClasspath() {
        db = runBlocking {
            jcdb {
                useProcessJavaRuntime()
                loadByteCode(allClasspath)
            }
        }
    }

    @Benchmark
    fun jvmRuntimeWithAllClasspathWithUsages() {
        db = runBlocking {
            jcdb {
                useProcessJavaRuntime()
                loadByteCode(allClasspath)
                installFeatures(Usages)
            }
        }
    }

    @Benchmark
    fun jvmRuntimeWithGuava() {
        db = runBlocking {
            jcdb {
                useProcessJavaRuntime()
                loadByteCode(listOf(guavaLib))
            }
        }
    }

    @Benchmark
    fun jvmRuntimeWithGuavaWithUsages() {
        db = runBlocking {
            jcdb {
                useProcessJavaRuntime()
                loadByteCode(listOf(guavaLib))
                installFeatures(Usages)
            }
        }
    }

    @Benchmark
    fun jvmRuntimeWithIdeaCommunity() {
        db = runBlocking {
            jcdb {
                useProcessJavaRuntime()
                persistent(File.createTempFile("jcdb-", "-db").absolutePath)
                loadByteCode(allIdeaJars)
            }
        }
    }

    @Benchmark
    fun jvmRuntimeIdeaCommunityWithUsages() {
        db = runBlocking {
            jcdb {
                useProcessJavaRuntime()
                loadByteCode(allIdeaJars)
                persistent(File.createTempFile("jcdb-", "-db").absolutePath)
                installFeatures(Usages)
            }
        }
    }

    @TearDown(Level.Iteration)
    fun tearDown() {
        db?.close()
    }
}