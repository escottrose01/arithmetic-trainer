package main.data.generator;

import main.data.expression.Expression;
import main.records.Operator;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Random;

public class ComboProblemGenerator implements ProblemGenerator {
    private AddProblemGenerator addGen;
    private SubProblemGenerator subGen;
    private MultProblemGenerator multGen;
    private DivProblemGenerator divGen;
    Operator[] ops;
    private Random rng;

    public ComboProblemGenerator() {
        this.addGen = new AddProblemGenerator();
        this.subGen = new SubProblemGenerator();
        this.multGen = new MultProblemGenerator();
        this.divGen = new DivProblemGenerator();
        this.ops = new Operator[]{Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY, Operator.DIVIDE};
        this.rng = new Random();
    }

    @Override
    public Expression generateProblem() {
        return null;
    }

    @Override
    public Expression generateProblem(int target) {
        return generateProblem(target, 3);
    }

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

        return new Expression(
                length > 2 ? generateProblem(tmp.getLeft().evaluate(), length - 1) :
                        tmp.getLeft(),
                tmp.getRight(),
                op
        );
    }
}
