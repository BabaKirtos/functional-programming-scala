package playground

import com.github.benmanes.caffeine.cache.{Caffeine, AsyncCache}
import java.util.concurrent.{CompletableFuture, TimeUnit}

object CaffeineAsyncCacheTest {
  def main(args: Array[String]): Unit = {
    // Create a Caffeine cache with asynchronous loading
    val cache: AsyncCache[String, Int] = Caffeine.newBuilder()
      .maximumSize(1) // Maximum number of entries in the cache
      .buildAsync()

    // Initialize the cache with a key "key" and an initial value of 99
    val initialValue = 99
    val key = "key"
    val initialFuture = CompletableFuture.completedFuture(initialValue)
    cache.put(key, initialFuture)

    // Create thread 0 to modify the value to 10 using BiFunction and compute
    val modifyThread1 = new Thread(() => {
      val threadName = Thread.currentThread().getName
      println(s"$threadName: starting")

      // Use asMap to get the cache and compute to modify the value
      val modifiedFuture = cache.asMap().compute(key, (k, existingValue) => {
        println(s"$threadName: inside compute, blocking others for 5 seconds")
        TimeUnit.SECONDS.sleep(5)
        if (existingValue != null) {
          println(s"$threadName: modifying value, existing value ${existingValue.get()}")
          val newValue = 10
          println(s"$threadName: blocking again for 5 seconds")
          TimeUnit.SECONDS.sleep(5)
          CompletableFuture.completedFuture(newValue)
        } else {
          existingValue // Key not found, do not modify
        }
      })

      // Wait for the modification to complete and retrieve the updated value
      val updatedValue = modifiedFuture.get()

      println(s"$threadName: modified value for $key: $updatedValue")
      println(s"$threadName: trying to get cache value ${cache.get("key", k => 0).get()}")
    })

    // Create thread 1 to modify the value to 20 using BiFunction and compute
    val modifyThread2 = new Thread(() => {
      val threadName = Thread.currentThread().getName

      println(s"$threadName: starting, sleeping for 1 second")
      TimeUnit.SECONDS.sleep(1)
      println(s"$threadName: waiting for other thread to complete")

      // Use asMap to get the cache and compute to modify the value
      val modifiedFuture = cache.asMap().compute(key, (k, existingValue) => {
        println(s"$threadName: inside compute")
        if (existingValue != null) {
          val newValue = 20
          println(s"$threadName: modifying value, existing value: ${existingValue.get()}")
          CompletableFuture.completedFuture(newValue)
        } else {
          existingValue // Key not found, do not modify
        }
      })

      // Wait for the modification to complete and retrieve the updated value
      val updatedValue = modifiedFuture.get()

      println(s"$threadName: modified value for $key: $updatedValue")
      println(s"$threadName: trying to get cache value ${cache.get("key", k => 0).get()}")
    })

    // Create thread 2 to get and put values in async
    val getPutThread3 = new Thread(() => {
      val threadName = Thread.currentThread().getName

      println(s"$threadName: starting, sleeping for 1 second")
      TimeUnit.SECONDS.sleep(1)
      println(s"$threadName: waiting for other thread to complete")
      println(s"$threadName: getting value for $key from cache ${cache.get("key", k => 0).get()}")
      println(s"$threadName: putting value 200 in $key")
      cache.synchronous().put("key", 200)
      println(s"$threadName: getting value for $key from cache ${cache.get("key", k => 0).get()}")
    })

    // Start thread 1, thread 2 and thread 3
    modifyThread1.start()
    modifyThread2.start()
    getPutThread3.start()

    // Wait for all threads to complete
    modifyThread1.join()
    modifyThread2.join()
    getPutThread3.join()

    // Retrieve and print the final value from the cache
    val finalValue = cache.get(key, k => 0).get()

    println(s"Final value for $key: $finalValue")
  }
}