package edu.hw02;

public sealed interface Task01 {
    double evaluate();

    public record Constant(double a) implements Task01 {
        @Override
        public double evaluate() {
            return this.a();
        }
    }

    public record Negate(Task01 a) implements Task01 {
        @Override
        public double evaluate() {
            return -1 * a.evaluate();
        }
    }

    public record Exponent(Task01 a, double b) implements Task01 {
        @Override
        public double evaluate() {
            return Math.pow(a.evaluate(), b);
        }
    }

    public record Addition(Task01 a, Task01 b) implements Task01 {
        @Override
        public double evaluate() {
            return a.evaluate() + b.evaluate();
        }
    }

    public record Multiplication(Task01 a, Task01 b) implements Task01 {
        @Override
        public double evaluate() {
            return a.evaluate() * b.evaluate();
        }
    }
}
