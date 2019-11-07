package noReturn

/**
 * What is the type of a return expression?
 */
object noReturnExample3 {
  def test1: Int = { val a: Int = return 2; 1 }
  def test2: Int = { val a: String = return 2; 1 }
  def test3: Int = { val a: Nothing = return 2; 1 }

  def main(args: Array[String]): Unit = {
    println(test1)
    println(test2) // String a doesn't matter...?
    println(test3)
  }
}
