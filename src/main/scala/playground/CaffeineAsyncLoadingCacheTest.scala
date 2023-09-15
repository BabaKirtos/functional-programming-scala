package playground

import com.github.benmanes.caffeine.cache.{Caffeine, AsyncLoadingCache, CacheLoader}
import java.util.concurrent.{CompletableFuture, TimeUnit}

object CaffeineAsyncLoadingCacheTest {
  def main(args: Array[String]): Unit = {
    // Create a Caffeine cache with asynchronous loading
    val cache: AsyncLoadingCache[String, Int] = Caffeine.newBuilder()
      .maximumSize(1) // Maximum number of entries in the cache
      .buildAsync(new CacheLoader[String, Int] {
        override def load(key: String): Int = {
          // Load the initial value for the cache
          if (key == "key") 99
          else 0 // Default value for other keys
        }
      })

    // Create thread 0 to modify the value to 10 using BiFunction and compute
    cache.synchronous().refresh("key") // initiates a loading operation
    val key = "key"
    val modifyThread1 = new Thread(() => {
      val threadName = Thread.currentThread().getName
      println(s"$threadName: starting modification")

      // Use asyncCache to get the cache and compute to modify the value
      val modifiedFuture = cache.asMap().compute(key, (k, existingValue) => {
        println(s"$threadName: inside compute, blocking other threads for 10 seconds")
        TimeUnit.SECONDS.sleep(10)
        if (existingValue != null) {
          println(s"$threadName: modifying value, existing value ${existingValue.get()}")
          val newValue = 10
          println(s"$threadName: blocking again for 10 seconds")
          TimeUnit.SECONDS.sleep(10)
          println(s"$threadName: done")
          CompletableFuture.completedFuture(newValue)
        } else {
          existingValue // Key not found, do not modify
        }
      })

      // Wait for the modification to complete and retrieve the updated value
      val updatedValue = modifiedFuture.join()

      println(s"$threadName: modified value for $key: $updatedValue")
      println(s"$threadName: trying to get cache value ${cache.get(key).join()}")
    })

    // Create thread 1 to modify the value to 20 using BiFunction and compute
    val modifyThread2 = new Thread(() => {
      val threadName = Thread.currentThread().getName

      println(s"$threadName: starting, sleeping for 1 second")
      TimeUnit.SECONDS.sleep(1)
      println(s"$threadName: waiting for other thread to complete")

      // Use asyncCache to get the cache and compute to modify the value
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
      val updatedValue = modifiedFuture.join()

      println(s"$threadName: modified value for $key: $updatedValue")
      println(s"$threadName: trying to get cache value ${cache.get(key).join()}")
    })

    // Create thread 2 to get and put values in async
    val getPutThread3 = new Thread(() => {
      val threadName = Thread.currentThread().getName

      println(s"$threadName: starting, sleeping for 1 second")
      TimeUnit.SECONDS.sleep(1)
      println(s"$threadName: waiting for other thread to complete")
      println(s"$threadName: getting value for $key from cache ${cache.get(key).join()}")
      println(s"$threadName: putting value 200 in $key")
      cache.synchronous().put("key", 200)
      println(s"$threadName: getting value for $key from cache ${cache.get(key).join()}")
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
    val finalValue = cache.get(key).join()

    println(s"Final value for $key: $finalValue")
  }
}