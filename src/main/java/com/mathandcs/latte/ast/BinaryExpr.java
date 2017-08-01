package com.mathandcs.latte.ast;


import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.exception.LatteException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BinaryExpr extends ASTList {

    @Getter
    private ASTree left;
    @Getter
    private ASTree right;
    @Getter
    private ASTLeaf operator;

    private static final String ASSIGN_OPERATOR = "=";
    private static final String CONCATENATE_OPERATOR = "+";
    private static final String EQUAL_OPERATOR = "==";
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryExpr.class);


    public BinaryExpr(List<ASTree> child) {
        super(child);
        LOGGER.info("Initializing Binary Expression.");
        if (3 != child.size()) {
            throw new LatteException("Binary expression's child size must be 3, current is: " + child.size());
        }
        left = child.get(0);
        right = child.get(2);
        operator = (ASTLeaf) child.get(1);
    }

    @Override
    public Object evaluate(Environment env) {
        String op = operator.token().getText();
        if (ASSIGN_OPERATOR.equals(op)) {
            return computeAssign(env, right.evaluate(env));
        } else {
            return computeOp(left.evaluate(env), op, right.evaluate(env));
        }
    }

    /**
     * Assign operation
     * <p>
     * left value must be Name
     */
    protected Object computeAssign(Environment env, Object rightValue) {
        if (left instanceof Name) {
            env.put(((Name) left).name(), rightValue);
            return rightValue;
        } else {
            throw new LatteException("bad assignment, left is not Name!", this);
        }
    }

    /**
     * Router
     * <p>
     * Dispatch computation
     */
    protected Object computeOp(Object leftValue, String op, Object rightValue) {
        if (leftValue instanceof Integer && rightValue instanceof Integer) {
            // left and right are both Integers
            return computeNumber((Integer) leftValue, op, (Integer) rightValue);
        } else if (CONCATENATE_OPERATOR.equals(op)) {
            return String.valueOf(leftValue) + String.valueOf(rightValue);
        } else if (EQUAL_OPERATOR.equals(op)) {
            if (leftValue == null) {
                return rightValue == null ? true : false;
            } else {
                return leftValue.equals(rightValue) ? true : false;
            }
        } else {
            throw new LatteException("Bad type", this);
        }
    }

    /**
     * Integer operations
     */
    protected Object computeNumber(Integer leftValue, String op, Integer rightValue) {
        int a = leftValue.intValue();
        int b = rightValue.intValue();

        // switch statement in Java support byte, char, short, int only
        if (op.equals("+")) {
            return a + b;
        } else if (op.equals("-")) {
            return a - b;
        } else if (op.equals("*")) {
            return a * b;
        } else if (op.equals("/")) {
            if (0 == b) {
                throw new LatteException("Division by zero!", this);
            }
            return a / b;
        } else if (op.equals("==")) {
            return a == b;
        } else if (op.equals(">")) {
            return a > b;
        } else if (op.equals("<")) {
            return a < b;
        } else {
            throw new LatteException("bad operator", this);
        }
    }

}
