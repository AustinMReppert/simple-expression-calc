import java.util.HashMap;

/**
 * A {@link Node} representing a non-constant double value.
 */
public class Variable extends Node {

  private final String name;

  /**
   * Creates a constant value double {@link Node}.
   * @param name The name of the variable.
   */
  public Variable(final String name) {
    super((final HashMap<String, Double> variableTable) -> variableTable.get(name));
    this.name = name;
  }

  /**
   * Gets a {@link String} representing {@code this} node.
   * @return A {@link String} representing {@code this} node.
   */
  @Override
  public String toString() {
    return name;
  }

}
