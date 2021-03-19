import java.util.HashMap;

/**
 * A {@link Node} representing a constant double value.
 */
public class Constant extends Node {

  private final double val;

  /**
   * Creates a constant value double {@link Node}.
   * @param val The constant double value.
   */
  public Constant(final double val) {
    super((HashMap<String, Double> variableTable) -> val);
    this.val = val;
  }

  /**
   * Gets a {@link String} representing {@code this} node. Values are rounded to 2 decimal places.
   * @return A {@link String} representing {@code this} node.
   */
  @Override
  public String toString() {
    return Double.toString(val);
  }

}
