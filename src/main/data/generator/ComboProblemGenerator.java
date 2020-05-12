package main.data.generator;

import main.data.expression.Expression;
import main.records.Operator;

import java.util.Random;

/**
 * This class creates variable-length arithmetic problems with multiple operation types via an
 * expression tree.
 */
public class ComboProblemGenerator implements ProblemGenerator {
    private AddProblemGenerator addGen;
    private SubProblemGenerator subGen;
    private MultProblemGenerator multGen;
    private DivProblemGenerator divGen;
    Operator[] ops;
    private Random rng;
    int minVal;
    int maxVal;
    int minLength;
    int maxLength;

    public ComboProblemGenerator(int minVal, int maxVal, int minLength, int maxLength,
                                 Operator[] ops) {
        this.addGen = new AddProblemGenerator();
        this.subGen = new SubProblemGenerator();
        this.multGen = new MultProblemGenerator();
        this.divGen = new DivProblemGenerator();
        this.ops = ops;
        this.rng = new Random();
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public ComboProblemGenerator(int minVal, int maxVal, int minLength, int maxLength) {
        this.addGen = new AddProblemGenerator();
        this.subGen = new SubProblemGenerator();
        this.multGen = new MultProblemGenerator();
        this.divGen = new DivProblemGenerator();
        this.ops = new Operator[]{Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY, Operator.DIVIDE};
        this.rng = new Random();
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public int getMinVal() {
        return minVal;
    }

    public void setMinVal(int minVal) {
        this.minVal = minVal;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public ComboProblemGenerator() {
        this.addGen = new AddProblemGenerator();
        this.subGen = new SubProblemGenerator();
        this.multGen = new MultProblemGenerator();
        this.divGen = new DivProblemGenerator();
        this.ops = new Operator[]{Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY, Operator.DIVIDE};
        this.rng = new Random();
        this.minLength = 1;
        this.maxLength = 1;
    }

    @Override
    public Expression generateProblem() {
        return generateProblem(minVal + rng.nextInt(maxVal - minVal + 1));
    }

    @Override
    public Expression generateProblem(int target) {
        return generateProblem(target, minLength + rng.nextInt(maxLength - minLength + 1));
    }

    /**
     * Creates an expression of a desired length given a desired target value.
     * @param target the target value to which the expression evaluates
     * @param length the length of the expression, as the total number of integer literals
     * @return a new Expression object
     */
    private Expression generateProblem(int target, int length) {
        Operator op = ops[rng.nextInt(ops.length)];
        Expression tmp;

        switch (op) {
            case ADD:
                tmp = addGen.generateProblem(target);
                break;
            case SUBTRACT:
                tmp = subGen.generateProblem(target);
                break;
            case MULTIPLY:
                tmp = multGen.generateProblem(target);
                break;
            case DIVIDE:
                tmp = divGen.generateProblem(target);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        }

        int remainingLeft = length / 2 + (length % 2);
        int remainingRight = length / 2;
        return new Expression(
                remainingLeft > 1 ? generateProblem(tmp.getLeft().evaluate(), remainingLeft) :
                        tmp.getLeft(),
                remainingRight > 1 ? generateProblem(tmp.getRight().evaluate(), remainingRight) :
                        tmp.getRight(),
                op
        );
    }
}
