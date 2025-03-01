package lexer;

public class TokenLiteral {
    public Token token;
    public String literal;

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }

        if (anObject instanceof TokenLiteral) {
            return literal.equals(((TokenLiteral) anObject).literal) && token.equals(((TokenLiteral) anObject).token);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = literal.hashCode();
        result = 31 * result + token.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "token: " + token + ", literal: " + literal;
    }

    TokenLiteral() {
        this.literal = "";
        this.token = Token.ILLEGAL;
    }

    TokenLiteral(Token t, String literal) {
        this.literal = literal;
        this.token = t;
    }

    TokenLiteral(Token t, char ch) {
        this.literal = Character.toString(ch);
        this.token = t;
    }
}
