package sample

fun cantChangeThis() {
  println("i ${DefaultGlobalState.i}")
  DefaultGlobalState.i++
  println("i ${DefaultGlobalState.i}") //We won't get here
}

object DefaultGlobalState {
  var i = 5
}

fun canChangeThreadLocal() {
  println("i ${ThreadLocalGlobalState.i}")
  ThreadLocalGlobalState.i++
  println("i ${ThreadLocalGlobalState.i}")
}

@ThreadLocal
object ThreadLocalGlobalState {
  var i = 5
}

fun threadLocalDifferentThreads() {
  println("main thread: i ${ThreadLocalGlobalState.i}")
  ThreadLocalGlobalState.i++
  println("main thread: i ${ThreadLocalGlobalState.i}")
  background {
    println("other thread: i ${ThreadLocalGlobalState.i}")
  }
}

fun companionAlsoFrozen() {
  println("i ${GlobalStateCompanion.i}")
  GlobalStateCompanion.i++
  println("i ${GlobalStateCompanion.i}") //We won't get here
}

class GlobalStateCompanion {
  companion object {
    var i = 5
  }
}

fun globalCounting() {
  globalCounterData.i++
  println("count ${globalCounterData.i}")
  globalCounterData.i++
  println("count ${globalCounterData.i}")
}

var globalCounterData = SomeMutableData(33)

fun globalCountingFail() {
  background {
    try {
      println("globalCountingFail $globalCounterData")
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
}

@SharedImmutable
val globalCounterDataShared = SomeMutableData(33)

fun globalCountingSharedFail() {
  globalCounterDataShared.i++
}