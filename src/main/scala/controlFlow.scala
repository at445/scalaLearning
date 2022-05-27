import java.io.File

case class controlFlow(path:String) {
  private def filesHere = new File(path).listFiles()

  override def toString: String = {
    //val temp = for (file <- filesHere) yield file.toString
    val temp = filesHere.map(_.toString)
    temp.mkString("\n")
  }

  private def filesMatching(
                             matcher:(String) => Boolean): Array[File] = {
    for (file <-filesHere if matcher(file.getName)) yield file
  }

  def fileEnding(query:String): Array[File] = {
    filesMatching(_.endsWith(query))
  }
}
