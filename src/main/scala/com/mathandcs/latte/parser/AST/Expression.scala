package com.mathandcs.latte.parser.AST

/**
  * Created by dashwang on 8/25/17.
  */
abstract class Expression {
  def eval(): Double
}

case class Number(value: Double) extends Expression {
  override def eval(): Double = value
}

case class UnaryOp(operator: String, expr: Expression) extends Expression {
  override def eval(): Double = {
    if ("-".equals(operator)) {
      -1 * expr.eval()
    } else {
      throw new IllegalArgumentException("Unsupported operator: " + operator)
    }
  }
}

case class BinaryOp(operator: String, left: Expression, right: Expression) extends Expression {
  override def eval(): Double = {
    operator match {
      case "+" => left.eval() + right.eval()
      case "-" => left.eval() - right.eval()
      case "*" => left.eval() * right.eval()
      case "/" => if (0 == right.eval()) Double.NaN else left.eval() / right.eval()
      case _ => throw new IllegalArgumentException("Unsupported operator: " + operator)
    }
  }
}


