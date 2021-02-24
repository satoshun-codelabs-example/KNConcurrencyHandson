package sample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.native.concurrent.isFrozen

fun main() {
  Platform.isMemoryLeakCheckerActive = false

  setupThreading()

  println("\n\n---------------")

  // 1) Simple State
  // Just your usual mutable state, in the main thread.
  runSimpleState()

  // 2) Frozen State
  freezeSomeState()
  failChanges()
  freezeChildren()

  // 3) Global State
//  cantChangeThis()
  canChangeThreadLocal()
  threadLocalDifferentThreads()
//  companionAlsoFrozen()
  globalCounting()
//  globalCountingFail()
//  globalCountingSharedFail()

  // 4) Background
  basicBackground()
  captureState()

  runBlocking {
    val sd = SomeData("Hello 🥶", 67)
    println("${sd.isFrozen}")

    withContext(Dispatchers.Default) {
      val sd2 = SomeData("Hello 🥶", 67)
      println("sd2 ${sd2.isFrozen}")
      delay(100)
      println("sd2 2 ${sd2.isFrozen}")
    }
  }

  a()

//  captureTooMuch()
//    captureTooMuchAgain()
//    captureArgs()

  // 5) Debugging
//    ensureNeverFrozenFailNow()
//    ensureNeverFrozenFailLater()
//    ensureNeverFrozenBackground()
//    captureTooMuchInit()

  //Leave this please...
  teardownThreading()

  println("---------------\n\n")
}