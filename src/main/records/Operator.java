package main.records;

/**
 * An enum representing a set of arithmetic operations.
 */
public enum Operator {
    ADD {
        @Override
        public String toString() {
            return "+";
        }
    },
    SUBTRACT {
        @Override
        public String toString() {
            return "-";
        }
    },
    MULTIPLY {
        @Override
        public String toString() {
            return "×";
        }
    },
    DIVIDE {
        @Override
        public String toString() {
            return "÷";
        }
    }
}
