package main.data.generator;

import main.data.expression.Expression;
import main.records.Operator;

import java.util.ArrayList;
import java.util.Random;

public class MultProblemGenerator implements ProblemGenerator {
    private Random rng;
    int minVal;
    int maxVal;

    public MultProblemGenerator() {
        this.rng = new Random();
        this.minVal = 1;
        this.maxVal = 9;
    }

    public MultProblemGenerator(int minVal, int maxVal) {
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
        ArrayList<Integer> divisors = getDivisors(target);
        int a = divisors.get(rng.nextInt(divisors.size()));
        int b = target / a;
        return new Expression(
                new Expression(a),
                new Expression(b),
                Operator.MULTIPLY
        );
    }

    private ArrayList<Integer> getDivisors(int target) {
        ArrayList<Integer> divisors = new ArrayList<>();
        divisors.add(1); // Always want at least one element

        for (int i = 2; i <= target; ++i) {
            if (target % i == 0) {
                divisors.add(i);
            }
        }

        return divisors;
    }
}
