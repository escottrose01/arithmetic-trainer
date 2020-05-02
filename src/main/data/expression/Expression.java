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

        return "(" +
                left.toString() +
                ") " +
                operator.toString() +
                " (" +
                right.toString() +
                ")";
    }
}
