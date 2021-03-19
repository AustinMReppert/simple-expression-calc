import java.util.HashMap;

/**
 * Represents an operator or operand in a tree. Operators do not have precedence, but are evaluated recursively.
 */
public abstract class Node implements Evaluable {

  private final Evaluable eval;

  /**
   * Creates a node with some evaluation scheme.
   * @param eval A mapping from {@code this} to a double representation.
   */
  public Node(final Evaluable eval) {
    this.eval = eval;
  }

  /**
   * Get a double representation of {@code this}.
   * @param variableTable {@link HashMap} that may be used to evaluate {@link Variable}s.
   * @return A double representation of {@code this}.
   */
  @Override
  public double eval(final HashMap<String, Double> variableTable) {
    return eval.eval(variableTable);
  }

}