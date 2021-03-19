import java.util.ArrayList;
import java.util.List;

/**
 * Parses basic algebraic expressions. See grammar.txt for the implemented grammar.
 */
public class Parser {

  private final ArrayList<Token> tokens;
  private int cur;

  /**
   * Initializes the {@link Parser}.
   * @param tokens The tokens list to parse.
   */
  public Parser(final ArrayList<Token> tokens) {
    this.tokens = tokens;
    cur = 0;
  }

  /**
   * Parses for an algebraic expression.
   * @return A root level node for the expression.
   */
  public Node parse() {
    Node expr = expression();
    if(cur < tokens.size() && !matches(List.of(TokenType.EOF)))
      error();
    return expr;
  }

  private Node expression() {
    Node left = term();
    if (matches(List.of(TokenType.PLUS, TokenType.MINUS))) {
      TokenType tokenType = cur().getTokenType();
      next();
      Node right = expression();
      return switch (tokenType) {
        case PLUS -> new BinaryOperator.Plus(left, right);
        case MINUS -> new BinaryOperator.Minus(left, right);
        default -> throw new IllegalStateException();
      };
    } else {
      return left;
    }
  }

  private void error() {
    throw new RuntimeException("Error while parsing token: " + cur());
  }

  private Node term() {
    Node left = poweredTerm();
    if (matches(List.of(TokenType.MULTIPLY, TokenType.DIVIDE))) {
      TokenType tokenType = cur().getTokenType();
      next();
      Node right = term();
      return switch (tokenType) {
        case MULTIPLY -> new BinaryOperator.Multiply(left, right);
        case DIVIDE -> new BinaryOperator.Divide(left, right);
        default -> throw new IllegalStateException();
      };
    } else {
      return left;
    }
  }

  private Node poweredTerm() {
    Node left = factor();
    if (matches(List.of(TokenType.POWER))) {
      next();
      Node right = poweredTerm();
      return new BinaryOperator.Power(left, right);
    } else {
      return left;
    }
  }

  private Node factor() {
    boolean negative = false;
    if(matches(List.of(TokenType.MINUS))) {
      negative = true;
      next();
    }
    if (matches(List.of(TokenType.LEFT_PARENTHESIS))) {
      next();
      Node expression = expression();
      if (matches(List.of(TokenType.RIGHT_PARENTHESIS))) {
        next();
        if(negative) {
          return new BinaryOperator.Multiply(expression, new Constant(-1));
        } else {
          return expression;
        }
      } else {
        error();
        return null;
      }
    } else if (matches(List.of(TokenType.CONSTANT))) {
      var constant = new Constant(Double.parseDouble(cur().getLexemes()) * (negative ? -1 : 1));
      next();
      return constant;
    } else if (matches(List.of(TokenType.VARIABLE))) {
      var variable = new Variable(cur().getLexemes());
      next();
      if(negative) {
        return new BinaryOperator.Multiply(variable, new Constant(-1));
      } else {
        return variable;
      }
    } else {
      error();
      return null;
    }
  }

  private boolean matches(List<TokenType> tokenTypes) {
    return tokenTypes.contains(cur().getTokenType());
  }

  private Token cur() {
    return tokens.get(cur);
  }

  private void next() {
    ++cur;
  }

}
