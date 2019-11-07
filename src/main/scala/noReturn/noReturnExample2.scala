package noReturn

/**
 * When a function value containing a return statement is evaluated non-locally,
 * the computation is abandoned and the result is returned by throwing a NonLocalReturnControl[A]
 */
object noReturnExample2 {
  def lazily(s: => String): String =
    try s catch { case t: Throwable => t.toString }

  def foo: () => Int = () => return () => 1

  def main(args: Array[String]): Unit = {
    println(lazily("lalala"))
    println(lazily(return "lalala2"))

    /* What if a return expression is captured and not evaluated until after the containing method has returned? */
    val x = foo // it's ok so far...
    x() // boom!
  }
}
