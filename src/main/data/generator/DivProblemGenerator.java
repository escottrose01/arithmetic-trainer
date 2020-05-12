package main.data.generator;

import main.data.expression.Expression;
import main.records.Operator;

import java.util.Random;

/**
 * This class creates division problems via an expression tree.
 */
public class DivProblemGenerator implements ProblemGenerator {
    private Random rng;
    int minVal;
    int maxVal;
    int maxDivisor;

    public DivProblemGenerator() {
        this.rng = new Random();
        this.minVal = 1;
        this.maxVal = 10;
        this.maxDivisor = 10;
    }

    public DivProblemGenerator(int minVal, int maxVal) {
        this.rng = new Random();
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.maxDivisor = 10;
    }

    public DivProblemGenerator(int minVal, int maxVal, int maxDivisor) {
        this.rng = new Random();
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.maxDivisor = maxDivisor;
    }

    @Override
    public Expression generateProblem() {
        return generateProblem(minVal + rng.nextInt(maxVal - minVal + 1));
    }

    @Override
    public Expression generateProblem(int target) {
        int b = 1 + rng.nextInt(maxDivisor);
        int a = target * b;
        return new Expression(
                new Expression(a),
                new Expression(b),
                Operator.DIVIDE
        );
    }
}
