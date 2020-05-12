package main.data.generator;

import main.data.expression.Expression;
import main.records.Operator;

import java.util.Random;

/**
 * This class creates addition problems via an expression tree.
 */
public class AddProblemGenerator implements ProblemGenerator {
    private Random rng;
    int minVal;
    int maxVal;

    public AddProblemGenerator() {
        this.rng = new Random();
        this.minVal = 1;
        this.maxVal = 9;
    }

    public AddProblemGenerator(int minVal, int maxVal) {
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
        int a = rng.nextInt(target + 1);
        int b = target - a;
        return new Expression(
                new Expression(a),
                new Expression(b),
                Operator.ADD
        );
    }
}
