import java.util.HashMap;

/**
 * Represents a binary operator.
 */
public abstract class BinaryOperator extends Node {

  private final Node left;
  private final Node right;
  private final String operatorString;

  /**
   *
   * @param left Left operand.
   * @param right Right operand.
   * @param operatorString A string representation of this operator.
   * @param eval A mapping from the left and right operands to a double.
   */
  public BinaryOperator(final Node left, final Node right, final String operatorString, final Evaluable eval) {
    super(eval);
    this.left = left;
    this.right = right;
    this.operatorString = operatorString;
  }

  /**
   * Gets a {@link String} representing {@code this} node.
   * @return A {@link String} representing {@code this} node.
   */
  @Override
  public String toString() {
    return "(" + left + " " + operatorString + " " + right + ")";
  }

  // I could not justify 4 new files for one line calls. It also looks nice like BinaryOperator.Plus.

  public static class Plus extends BinaryOperator {
    public Plus(final Node left, final Node right) {
      super(left, right, "+", (final HashMap<String, Double> variableTable) -> left.eval(variableTable) + right.eval(variableTable));
    }
  }

  public static class Minus extends BinaryOperator {
    public Minus(final Node left, final Node right) {
      super(left, right, "-", (final HashMap<String, Double> variableTable) -> left.eval(variableTable) - right.eval(variableTable));
    }
  }

  public static class Power extends BinaryOperator {
    public Power(final Node left, final Node right) {
      super(left, right, "^", (final HashMap<String, Double> variableTable) -> Math.pow(left.eval(variableTable), right.eval(variableTable)));
    }
  }

  public static class Multiply extends BinaryOperator {
    public Multiply(final Node left, final Node right) {
      super(left, right, "*", (final HashMap<String, Double> variableTable) -> left.eval(variableTable) * right.eval(variableTable));
    }
  }

  public static class Divide extends BinaryOperator {
    public Divide(final Node left, final Node right) {
      super(left, right, "/", (final HashMap<String, Double> variableTable) -> left.eval(variableTable) / right.eval(variableTable));
    }
  }

}