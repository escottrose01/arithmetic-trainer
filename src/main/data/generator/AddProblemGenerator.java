package main.data.generator;

import main.data.expression.Expression;
import main.records.Operator;

import java.util.Random;

public class AddProblemGenerator implements ProblemGenerator {
    private Random rng;
    int minVal;
    int maxVal;

    public AddProblemGenerator() {
        this.rng = new Random();
        this.minVal = 1;
        this.maxVal = 10;
    }

    public AddProblemGenerator(int minVal, int maxVal) {
        this.rng = new Random();
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    @Override
    public Expression generateProblem() {
        return generateProblem(minVal + rng.nextInt(maxVal - minVal));
    }

    @Override
    public Expression generateProblem(int target) {
        int a = 1 + rng.nextInt(target);
        int b = target - a;
        return new Expression(
                new Expression(a),
                new Expression(b),
                Operator.ADD
        );
    }
}
