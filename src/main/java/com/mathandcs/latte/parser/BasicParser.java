package com.mathandcs.latte.parser;

import com.mathandcs.latte.Lexer;
import com.mathandcs.latte.ast.*;
import com.mathandcs.latte.exception.ParseException;
import com.mathandcs.latte.parser.Parser.Operators;
import com.mathandcs.latte.tokens.Token;

import java.util.HashSet;

import static com.mathandcs.latte.parser.Parser.newParser;

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
    HashSet<String> reserved = new HashSet<>();
    Operators operators = new Operators();

    // 因为要递归引用(此时expr还不存在),所以先声明为空的Parser, 在创建expr时更新expr0, 引用传递
    private Parser expr0 = newParser();

    private Parser primary = newParser(PrimaryExpr.class)
            .or(newParser().sep("(").ast(expr0).sep(")"),
                    newParser().number(NumberLiteral.class),
                    newParser().identifier(Variable.class, reserved),
                    newParser().string(StringLiteral.class));

    private Parser factor = newParser().or(newParser(NegativeExpr.class).sep("-").ast(primary), primary);

    private Parser expr = expr0.expression(BinaryExpr.class, factor, operators);

    // 和expr0同理
    private Parser statement0 = newParser();

    private Parser block = newParser(BlockStatement.class)
            .sep("{").option(statement0)
            .repeat(newParser().sep(";", Token.EOL).option(statement0))
            .sep("}");

    private Parser simple = newParser(PrimaryExpr.class).ast(expr);

    private Parser statement = statement0.or(
            newParser(IfStatement.class).sep("if").ast(expr).ast(block)
                    .option(newParser().sep("else").ast(block)),
            newParser(WhileStatement.class).sep("while").ast(expr).ast(block),
            simple);

    private Parser program = newParser().or(statement, newParser(NullStatement.class))
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
