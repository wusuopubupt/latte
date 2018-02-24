package com.mathandcs.latte.antlr4;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Scanner;

/**
 * Created by dash wang on 10/24/17.
 */
public class Entrance {

    private static final String lineStart = "CALC> ";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(lineStart);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line != null) {
                    line = line.trim();
                    if (line.length() != 0) {
                        if ("exit".equals(line) || "bye".equals(line))
                            break;
                        ANTLRInputStream input = new ANTLRInputStream(line);
                        CalcLexer lexer = new CalcLexer(input);
                        CommonTokenStream tokens = new CommonTokenStream(lexer);
                        CalcParser parser = new CalcParser(tokens);
                        ParseTree tree = parser.exprs();
                        System.out.println("Parsed tree is: " + tree.toStringTree(parser));
                        CalcVisitorImpl calcVisitor = new CalcVisitorImpl();
                        Double res = calcVisitor.visit(tree);
                        if (res != null)
                            System.out.println(res);
                    }
                }

                System.out.print(lineStart);
            }
        }
    }

}