package com.mathandcs.latte.parser

import com.mathandcs.latte.parser.AST._

import scala.util.parsing.combinator.JavaTokenParsers

class CalculatorParser extends JavaTokenParsers {

  // 解析器组合子
  def expression: Parser[Expression] = term ~ rep(("+" | "-") ~ term) ^^ {
    case t ~ list => list.foldLeft(t)(
      (res, e) => {
        e match {
          case "+" ~ num => BinaryOp("+", res, num)
          case "-" ~ num => BinaryOp("-", res, num)
        }
      }
    )
  }

  // 解决了1*2*3的问题, rep -> (t ~ list => list.foldleft(t))
  // https://github.com/wusuopubupt/scala-for-the-impatient/blob/master/src/main/scala/com/basile/scala/ch19/Ex10.scala
  def term: Parser[Expression] = factor ~ rep(("*" | "/") ~ factor) ^^ {
    case t ~ list => list.foldLeft(t)(
      (res, e) => {
        e match {
          case "*" ~ num => BinaryOp("*", res, num)
          case "/" ~ num => BinaryOp("/", res, num)
        }
      }
    )
  }

  def factor: Parser[Expression] = "(" ~> expression <~ ")" | floatingPointNumber ^^ (x => Number(x.toDouble))

  def evaluate(text: String): Double = {
    // RegexParser.scala: def parseAll[T](p: Parser[T], in: java.lang.CharSequence): ParseResult[T]
    val parseResult = parseAll(expression, text)
    parseResult match {
      case res: Success[Expression] => {
        val expression = parseResult.get
        evaluate(expression)
      }
      case res: Failure => throw new RuntimeException("Parse failed!" + res.msg)
      case res: Error => throw new RuntimeException("Parse error!" + res.msg)
    }
  }

  def evaluate(e: Expression): Double = {
    simplify(e).eval()
  }

  // 表达式化简
  def simplify(e: Expression): Expression = {
    val simpSubs = e match {
      case BinaryOp(op, left, right) => BinaryOp(op, simplify(left), simplify(right))
      case UnaryOp(op, operand) => UnaryOp(op, simplify(operand))
      case _ => e
    }

    def simplifyTop(x: Expression) = x match {
      case UnaryOp("-", UnaryOp("-", x)) => x
      case UnaryOp("+", x) => x
      case BinaryOp("*", x, Number(1)) => x
      case BinaryOp("*", Number(1), x) => x
      case BinaryOp("*", x, Number(0)) => Number(0)
      case BinaryOp("*", Number(0), x) => Number(0)
      case BinaryOp("/", x, Number(1)) => x
      case BinaryOp("/", x1, x2) if x1 == x2 => Number(1)
      case BinaryOp("+", x, Number(0)) => x
      case BinaryOp("+", Number(0), x) => x
      case e => e
    }
    simplifyTop(simpSubs)
  }

}

object CalculatorParser {

  def main(args: Array[String]) {
    val parser = new CalculatorParser
    val expressions = List(
      "1",
      "(1)",
      "1+ 1",
      "1 + 1",
      "-1 + 1",
      "(1 + 1)",
      "1 + 1 + 1",
      "(1 + 1) + 1",
      "(1 + 1) + (1 + 1)",
      "(1 * 1) / (1 * 1)",
      "1 - 1",
      "1 - 1 - 1",
      "(1 - 1) - 1",
      "1 * 1 * 1",
      "1 / 1 / 1",
      "(1 / 1) / 1"
    )
    for (x <- expressions)
      println(x + " => " + parser.evaluate(x))
  }

}
