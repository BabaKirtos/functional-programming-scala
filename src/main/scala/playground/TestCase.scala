package playground

object TestCase extends App {

  //Q. we need to find the list where we need to compare the elements right
  //to that and largest of all elements should come on the place of that element .
  //
  //list1 = [1,2,3,4,5]
  //output = [5,5,5,5,5]

  def listReplacer(input: List[Int]): List[Int] = {

    def findMax(in: List[Int], acc: Int = -1): Int = {
      if (in.tail == Nil) if (in.head > acc) in.head else acc
      else findMax(in.tail, if (in.head > acc) in.head else acc)
    }

    def helper(in: List[Int], size: Int, max: Int, acc: List[Int] = List.empty): List[Int] = {
      if (in.tail == Nil) acc ++ List(max)
      else helper(in.tail, size, max, acc ++ List(max))
    }

    helper(input, input.size, findMax(input))
  }

  val list1 = List(1, 2, 3, 4, 5)

  println(listReplacer(list1))

  //Input = "hi hello how are you"
  //Output = ["ih","olleh","woh" , "era" ,"uoy"];

  val newInput = "hi hello how are you"

  println(newInput.split(" ").map(customReverse).mkString("Array(", ", ", ")"))

  def customReverse(input: String): String = {

    def helper(in: String, size: Int, counter: Int = 0, acc: String = ""): String = {
      if (counter == size) acc
      else helper(in, size, counter + 1, acc + in((size - 1) - counter))
    }

    helper(input, input.length)
  }

  println(customReverse("hello"))

}
