package main.data.generator;

import main.data.expression.Expression;
import main.records.Operator;

import java.util.Random;

/**
 * This class creates subtraction problems via an expression tree.
 */
public class SubProblemGenerator implements ProblemGenerator {
    private Random rng;
    int minVal;
    int maxVal;

    public SubProblemGenerator() {
        this.rng = new Random();
        this.minVal = 1;
        this.maxVal = 9;
    }

    public SubProblemGenerator(int minVal, int maxVal) {
        this.rng = new Random();
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    @Override
    public Expression generateProblem() {
        return generateProblem(minVal + rng.nextInt(maxVal - minVal + 1));
    }

    @Override
    public Expression generateProblem(int target) {
        int a = 2 * target - rng.nextInt(target + 1) + 1;
        int b = a - target;
        return new Expression(
                new Expression(a),
                new Expression(b),
                Operator.SUBTRACT
        );
    }
}
