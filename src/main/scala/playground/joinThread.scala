package playground

object joinThread extends App {

  // Create two threads
  val thread1 = new Thread(() => {
    for (i <- 1 to 5) {
      println(s"Thread 1: $i")
      Thread.sleep(2000)
    }
  })

  val thread2 = new Thread(() => {
    for (i <- 1 to 5) {
      println(s"Thread 2: $i")
      Thread.sleep(2000)
    }
  })

  // Start the threads, make it daemon, specifying it will run in the background
  thread1.setDaemon(true)
  thread1.start()
  thread2.setDaemon(true)
  thread2.start()

  // Without join, daemon threads will not complete their execution
  // Refer to the following link -> https://medium.com/@lakkuga/daemon-and-non-daemon-threads-in-ddc091fabacd#:~:text=Non%2Ddaemon%20threads%20are%20user,non%2Ddaemon%2Fuser%20thread.
  thread1.join()
  thread2.join()

  // This code will only execute after both threads have finished
  println("Both threads have finished.")
}
