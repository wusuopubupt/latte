package com.mathandcs.latte.parser.AST

/**
  * Created by dashwang on 8/25/17.
  */
abstract class Expression {
}

case class Number(value: Double) extends Expression

case class UnaryOp(operator: String, arg: Expression) extends Expression

case class BinaryOp(operator: String, left: Expression, right: Expression) extends Expression

