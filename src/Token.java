public class Token {

  private final TokenType tokenType;
  private final String lexemes;

  public Token(final TokenType tokenType, final String lexemes) {
    this.tokenType = tokenType;
    this.lexemes = lexemes;
  }

  @Override
  public String toString() {
    return "Token{" +
        "tokenType=" + tokenType +
        ", lexemes='" + lexemes + '\'' +
        '}';
  }

  public String getLexemes() {
    return lexemes;
  }

  public TokenType getTokenType() {
    return tokenType;
  }

}