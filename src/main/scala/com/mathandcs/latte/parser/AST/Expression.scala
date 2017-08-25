package com.mathandcs.latte.parser.AST

/**
  * Created by dashwang on 8/25/17.
  */
abstract class Expression {
  def evaluate(): Double
}

case class Number(value: Double) extends Expression {
  override def evaluate(): Double = value
}

case class UnaryOp(operator: String, arg: Expression) extends Expression {
  override def evaluate(): Double = {
    Double.NaN
  }
}

case class BinaryOp(operator: String, left: Expression, right: Expression) extends Expression {
  override def evaluate(): Double = {
    Double.NaN
  }
}