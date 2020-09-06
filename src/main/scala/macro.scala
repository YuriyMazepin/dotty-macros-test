package mazepin
package proto

import scala.quoted._

final case class N(n: Int) extends annotation.StaticAnnotation

trait MessageCodec[A] {
    def prepare(a: A): String
}

inline def caseCodecAuto[A]: MessageCodec[A] = ${ caseCodecAutoImpl[A] }

def caseCodecAutoImpl[A: Type](using qctx: QuoteContext): Expr[MessageCodec[A]] = {
  val t = summon[Type[A]]
  val symbol = t.unseal.tpe.typeSymbol

  val str = s"""
  Type annots: ${symbol.annots}
  Case fields annots ${symbol.caseFields.map(_.annots)}
  """
  val strExpr = Expr(str)
  
  '{
    new MessageCodec[A] {
      override def prepare(a: A): String = $strExpr
    }
  }

}
