package mazepin
package proto

object Main {

  @N(100)
  final case class Basic1(@N(21) int: Int)

  def main(args: Array[String]): Unit = {
    val codec = caseCodecAuto[Basic1]
    val basic = Basic1(int=111)
    val res = codec.prepare(basic)
    println(res)
  }

}