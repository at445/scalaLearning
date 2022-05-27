abstract class Element {
  val contents: Vector[String] = null
  def height: Int = contents.length
  def width:Int = if (height == 0) 0 else contents(0).length

  def above(that:Element):Element
  def beside(that:Element):Element
  override def toString: String = contents.mkString("\n")
}

class VectorElement(
                   override val contents:Vector[String]
                   ) extends Element {
  override def above(that: Element): Element = new VectorElement(this.contents ++ that.contents)

  override def beside(that: Element): Element = {
    new VectorElement(
      for ((line1, line2) <- this.contents.zip(that.contents))
        yield line1 + line2
    )
  }

}

class LinearElement(s:String) extends VectorElement(Vector(s)) {
  override def width: Int = s.length

  override def height: Int = 1
}

class UniformElement(
                    ch: Char,
                    override val width:Int,
                    override val height: Int
                    ) extends Element {
  private val line = ch.toString * width
  override val contents: Vector[String] = Vector.fill(height)(this.line)

  override def above(that: Element): Element = this

  override def beside(that: Element): Element = this
}


