package main.data.expression;

import main.records.Operator;

public class Expression {
    Expression left;
    Expression right;
    Operator operator;
    Integer value;

    public Expression(Expression left, Expression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        value = null;
    }

    public Expression(int value) {
        this.left = null;
        this.right = null;
        this.operator = null;
        this.value = value;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public int evaluate() {
        if (value != null) {
            return value;
        }

        switch (operator) {
            case ADD:
                value = left.evaluate() + right.evaluate();
                break;
            case SUBTRACT:
                value = left.evaluate() - right.evaluate();
                break;
            case MULTIPLY:
                value = left.evaluate() * right.evaluate();
                break;
            case DIVIDE:
                value = left.evaluate() / right.evaluate();
                break;
        }

        return value;
    }

    @Override
    public String toString() {
        if (operator == null) {
            return value.toString();
        }

        String l = left.toString();
        String r = right.toString();

        switch (operator) {
            case SUBTRACT:
                if (right.operator == Operator.SUBTRACT) {
                    r = "(" + r + ")";
                }
                break;
            case MULTIPLY:
                if (left.operator == Operator.ADD ||
                        left.operator == Operator.SUBTRACT) {
                    l = "(" + l + ")";
                }
                if (right.operator == Operator.ADD ||
                        right.operator == Operator.SUBTRACT) {
                    r = "(" + r + ")";
                }
                break;
            case DIVIDE:
                if (left.operator == Operator.ADD ||
                        left.operator == Operator.SUBTRACT) {
                    l = "(" + l + ")";
                }
                if (right.operator == Operator.ADD ||
                        right.operator == Operator.SUBTRACT ||
                        right.operator == Operator.DIVIDE ||
                        right.operator == Operator.MULTIPLY) {
                    r = "(" + r + ")";
                }
                break;
        }

        return l + " " + operator.toString() + " " + r;
    }
}
