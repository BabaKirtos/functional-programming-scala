package playground

import scala.::
import scala.util.Random

class Buffer() {
  var list: List[Int] = List.empty

  def add(value: Int): Unit = {
    list = list.::(value)
  }
  
  def remove(): Int = {
    val head = list.head
    list = list.tail
    head
  }

  def isEmpty = list.isEmpty
}

object BufferTest extends App {

  val bufferSize = 3
  val buffer = Buffer()
  
  val consumer = new Thread(() => {
    var consumedCount = 0

    while (true) {
      buffer.synchronized {
        Thread.sleep(1000)
        if (consumedCount < bufferSize) {
          if (buffer.isEmpty) {
            println("consumer is waiting...")
            buffer.wait()
          }

          println(s"consumer has consumed: ${buffer.remove()}")
          consumedCount += 1
        }
      }
    }
  })

  val producer = new Thread(() => {
    var producedCount = 0

    while (true) {
      buffer.synchronized {
        if (producedCount < bufferSize) {
          Thread.sleep(1000)

          println("producer is producing...")
          val int = Random.nextInt
          buffer.add(int)

          println(s"producer has added: $int")
          producedCount += 1

          buffer.notify()
        }
      }
    }
  })

  consumer.start()
  producer.start()

  consumer.join()
  producer.join()

}
