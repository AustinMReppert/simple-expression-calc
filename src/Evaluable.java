import java.util.HashMap;

public interface Evaluable {

  /**
   * Maps a value to a single double value.
   * @param variableTable {@link HashMap} that may be used to evaluate {@link Variable}s.
   * @return A double value representation of some value.
   */
  double eval(final HashMap<String, Double> variableTable);

}
