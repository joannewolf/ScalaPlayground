package caseClass

object CaseClassExample {
  case class Book(title: String, author: String)

  def main(args: Array[String]): Unit = {
    val book1 = Book("title", "author") /* No need the new keyword for instantiation */
    val book2 = Book("title", "author") /* No need the new keyword for instantiation */

    /* the parameters of case class are public val */
    println(book1.title)
//    book1.title = "title1.1" // boo!

    /* Case classes are compared by structure, not reference */
    println(book1 eq book2) // compare reference
    println(book1 == book2) // compare structure

    /* Shallow copy, and one can optionally change the constructor argument */
    val bookCopy = book1.copy(author = "another")
    println(bookCopy)
  }
}
