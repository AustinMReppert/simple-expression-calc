import java.util.HashMap;
import java.util.Scanner;

/**
 * A simple expression calculator.
 * @author Austin M. Reppert
 */
public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("The constants e and pi are already defined. Please enter variables(a-Z) as CSV(e.g. x=1,y=2):");
    String rawVariables = scanner.nextLine().replaceAll(" ", "").replaceAll("\t", "");
    String[] variables = rawVariables.split(",");
    HashMap<String, Double> variableTable = new HashMap<>();
    for (String variable : variables) {
      if (variable.isBlank() || variable.length() < 3) continue;
      System.out.println(variable.charAt(0) + " = " + variable.substring(2));
      variableTable.put(variable.substring(0, 1), Double.parseDouble(variable.substring(2)));
    }
    variableTable.put("π", Math.PI);
    variableTable.put("e", Math.E);

    System.out.println("Please enter an expression:");
    String rawExpression = scanner.nextLine().replaceAll("pi", "π");
    scanner.close();

    final Lexer lexer = new Lexer(rawExpression);
    final var tokens = lexer.lex();

    System.out.println("Tokens:");
    for (Token token : tokens) {
      System.out.println("\t" + token);
    }

    Parser parser = new Parser(tokens);
    Node expression = parser.parse();
    System.out.println("Parsed: " + expression);
    System.out.println("Result: " + expression.eval(variableTable));

  }

}
