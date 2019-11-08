package valueClass

trait NotUniversalTrait
trait Printable extends Any {
  def printHaha: Unit = println("haha! " + this)
}

class RichInt(val value: Int) extends AnyVal with Printable {
//  val field: Int /* Field definition not allowed */
//  class InsideRichInt() /* Nested class not allowed */
//  def this(doubleValue: Double) = this(doubleValue.toInt) /* Secondary constructor not allowed */
  def square: RichInt = new RichInt(value * value)
  def toHexString: String = java.lang.Integer.toHexString(value)
  def +(another: RichInt): RichInt = new RichInt(value + another.value)
}
//class AnotherRichInt(i: Int) extends RichInt(i) {} /* Value class cannot be extended */
//class OuterRichInt(val richInt: RichInt) extends AnyVal /* Value class cannot wrap another value class */
//class AnotherRichInt(val int: Int) extends AnyVal with NotUniversalTrait /* Value class cannot extend non-universal trait */

/* Value class has to be top-level or a member of statically accessible object */
//class OuterRichInt(val richInt: RichInt) {
//  class InnerRichInt(val value: Int) extends AnyVal
//}

case class CaseRichInt(value: Int) extends AnyVal with Printable {
  def square: RichInt = new RichInt(value * value)
}

object ValueClassExample {
  implicit class ImplicitRichInt(val value: Int) extends AnyVal with Printable {
    def square: ImplicitRichInt = new ImplicitRichInt(value * value)
  }

  def main(args: Array[String]): Unit = {
    /* At runtime, it's using primitive class, no RichInt instance is allocated */
    val richInt = new RichInt(15)
    richInt.square.printHaha
    println(richInt.toHexString)

    /* With implicit, the method call is optimized on a static object, instead of on newly instantiated object */
    3.square.printHaha

    /* Because JVM doesn't support value class, sometimes the value class has to be instantiate */
    /* Case 1: When treated as another type */
    val printable: Printable = CaseRichInt(10)
    printable.printHaha

    /* Case 1.2: When used as type argument */
    def identity[T](t: T): T = t
    identity(CaseRichInt(7)).printHaha

    /* Case 2: When assigned to an array */
    val caseRichInt = CaseRichInt(10)
    val richIntArray = Array[CaseRichInt](caseRichInt)
    caseRichInt.printHaha

    /* Case 3: When tested in pattern matching or asInstanceOf */
    val caseRichInt2 = CaseRichInt(2)
    caseRichInt2 match {
      case CaseRichInt(2) => println("Match!!")
      case CaseRichInt(x) => println("Not match!!")
    }
  }
}
