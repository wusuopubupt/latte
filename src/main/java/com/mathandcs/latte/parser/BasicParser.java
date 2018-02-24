package com.mathandcs.latte.parser;

import com.mathandcs.latte.Lexer;
import com.mathandcs.latte.ast.*;
import com.mathandcs.latte.exception.ParseException;
import com.mathandcs.latte.parser.Parser.Operators;
import com.mathandcs.latte.tokens.Token;

import java.util.HashSet;

import static com.mathandcs.latte.parser.Parser.rule;

/**
 * BNF definitions:
 * <p>
 * primary		: "(" expr ")" | NUMBER | IDENTIFIER | STRING
 * factor		: "-" primary | primary
 * expr		: factor {OP factor}
 * block	    : "{" [ statement ] {(";" | EOL) [z statement ] } "}"
 * simple		: expr
 * statement   : "if" expr block [ "else" block ]
 * | "while" expr block
 * | simple
 * program		: [ statement ] (";" | EOL)
 */
public class BasicParser {
    HashSet<String> reserved = new HashSet<String>();
    Operators operators = new Operators();

    // an empty Parser
    private Parser expr0 = rule();

    private Parser primary = rule(PrimaryExpr.class)
            .or(rule().sep("(").ast(expr0).sep(")"),
                    rule().number(NumberLiteral.class),
                    rule().identifier(Variable.class, reserved),
                    rule().string(StringLiteral.class));

    private Parser factor = rule().or(rule(NegativeExpr.class).sep("-").ast(primary), primary);

    private Parser expr = expr0.expression(BinaryExpr.class, factor, operators);

    private Parser statement0 = rule();

    private Parser block = rule(BlockStatement.class)
            .sep("{").option(statement0)
            .repeat(rule().sep(";", Token.EOL).option(statement0))
            .sep("}");

    private Parser simple = rule(PrimaryExpr.class).ast(expr);

    private Parser statement = statement0.or(
            rule(IfStatement.class).sep("if").ast(expr).ast(block)
                    .option(rule().sep("else").ast(block)),
            rule(WhileStatement.class).sep("while").ast(expr).ast(block),
            simple);

    private Parser program = rule().or(statement, rule(NullStatement.class))
            .sep(";", Token.EOL);

    public BasicParser() {
        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=", 1, Operators.RIGHT);
        operators.add("==", 2, Operators.LEFT);
        operators.add(">", 2, Operators.LEFT);
        operators.add("<", 2, Operators.LEFT);
        operators.add("+", 3, Operators.LEFT);
        operators.add("-", 3, Operators.LEFT);
        operators.add("*", 4, Operators.LEFT);
        operators.add("/", 4, Operators.LEFT);
        operators.add("%", 4, Operators.LEFT);
    }

    public ASTree parse(Lexer lexer) throws ParseException {
        return program.parse(lexer);
    }
}
