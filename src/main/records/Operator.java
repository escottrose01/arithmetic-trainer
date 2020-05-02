package main.records;

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
