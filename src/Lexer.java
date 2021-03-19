import java.util.ArrayList;

/**
 * Perform lexical analysis on simple algebraic expressions.
 */
public class Lexer {

  private final String lexemes;
  private final ArrayList<Token> tokens;
  private int cur;
  private int start;
  private char lexeme;

  /**
   * Initializes the {@link Lexer}.
   * @param lexemes The lexemes to lex.
   */
  public Lexer(final String lexemes) {
    this.lexemes = lexemes;
    tokens = new ArrayList<>();
    cur = 0;
  }

  /**
   * Performs lexical analysis.
   * @return The list of lexed tokens.
   */
  public ArrayList<Token> lex() {
    final char[] rawLexemes = lexemes.toCharArray();
    while(cur < lexemes.length()) {
      start = cur;
      lexeme = rawLexemes[cur];
      if(lexeme == '(') {
        appendToken(TokenType.LEFT_PARENTHESIS);
        next();
      } else if(lexeme == ')') {
        appendToken(TokenType.RIGHT_PARENTHESIS);
        next();
      } else if(lexeme == '^') {
        appendToken(TokenType.POWER);
        next();
      } else if(lexeme == '*') {
        appendToken(TokenType.MULTIPLY);
        next();
      } else if(lexeme == '/') {
        appendToken(TokenType.DIVIDE);
        next();
      } else if(lexeme == '+') {
        appendToken(TokenType.PLUS);
        next();
      } else if(lexeme == '-') {
        appendToken(TokenType.MINUS);
        next();
      } else if(Character.isLetter(lexeme) || lexeme == 'π') {
        appendToken(TokenType.VARIABLE);
        next();
      } else if(Character.isDigit(lexeme) || lexeme == '.') {
        while(Character.isDigit(lexeme)) {
          if(hasNext()) {
            advance();
          } else {
            next();
            break;
          }
        }
        if(lexeme != '.') {
          appendToken(TokenType.CONSTANT);
          continue;
        }
        if(hasNext()) {
          advance();
          while(Character.isDigit(lexeme)) {
            if(hasNext()) {
              advance();
            } else {
              appendToken(TokenType.CONSTANT);
              next();
              break;
            }
          }
          appendToken(TokenType.CONSTANT);
        } else {
          throw new RuntimeException("Invalid token(" + getCurLexemes() + ") at " + start);
        }

      } else if(Character.isWhitespace(lexeme)) {
        next();
      } else {
        throw new RuntimeException("Invalid lexeme: " + lexeme);
      }
    }
    tokens.add(new Token(TokenType.EOF, ""));
    return tokens;
  }

  /**
   * Move to the next char.
   */
  private void next() {
    ++cur;
  }

  /**
   * Adds a {@link Token} of {@code tokenType} to the current token list.
   * @param tokenType The type of token to add.
   */
  private void appendToken(TokenType tokenType) {
    tokens.add(new Token(tokenType, getCurLexemes()));
  }

  /**
   * Gets the current lexemes being lexed.
   * @return The current lexemes being lexed.
   */
  private String getCurLexemes() {
    return lexemes.substring(start, Math.max(cur, start + 1));
  }

  /**
   * Determines if there is a next char.
   * @return {@code true} if there is a next char or {@code false}.
   */
  private boolean hasNext() {
    return cur + 1 < lexemes.length();
  }

  /**
   * Move to the next char and update the current lexeme.
   */
  private void advance() {
    next();
    lexeme = lexemes.charAt(cur);
  }

}
