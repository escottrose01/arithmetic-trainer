package main.data.generator;

import main.data.expression.Expression;

/**
 * This interface defines the behavior of an arithmetic expression generator.
 */
public interface ProblemGenerator {
    /**
     * Creates a problem without taking the target value as a parameter.
     * @return a new Expression object
     */
    Expression generateProblem();

    /**
     * Creates a problem given a target value.
     * @param target the value to which the expression evaluates
     * @return a new Expression object
     */
    Expression generateProblem(int target);
}
