package noReturn

/**
 * When a return expression gets evaluated, it will abandon the current computation
 * and return to the caller of the method
 */
object noReturnExample {
  def add(n:Int, m:Int): Int = n + m
  def sum1(ns: List[Int]): Int = ns.foldLeft(0)(add)
  def addR(n:Int, m:Int): Int = return n + m
  def sumR1(ns: List[Int]): Int = ns.foldLeft(0)(addR)

  def sum2(ns: List[Int]): Int = ns.foldLeft(0)((n, m) => n + m)
  def sumR2(ns: List[Int]): Int = ns.foldLeft(0)((n, m) => return n + m)

  def foo1: Int = {
    val sumR: List[Int] => Int = _.foldLeft(0)((n, m) => return n + m)
    sumR(List(1,2,3)) + sumR(List(4,5,6))
  }

  def foo2: Int = {
    val sumR: List[Int] => Int = sumR2(_)
    sumR(List(1,2,3)) + sumR(List(4,5,6))
  }

  def bar1(n: Int): Int = {
    if (n < 100) n else return 100
  }
  def bar2(n: Int): Int = {
    val a = return 100
    if (n < 100) n else a
  }

  def main(args: Array[String]): Unit = {
    /* Same result */
    println(sum1(List(33, 42, 99)))
    println(sumR1(List(33, 42, 99)))

    println(sum2(List(33, 42, 99)))
    println(sumR2(List(33, 42, 99))) // boo!

    println(foo1) // boo!

    println(foo2) // boo!

    println(bar1(12))
    println(bar2(12)) // boo!
  }
}
