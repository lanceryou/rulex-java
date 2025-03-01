package lexer;

// Lexer 词法解析器
public class Lexer {
    private final String input;
    private Integer position;
    private Integer read_position;
    private char ch;

    // read_char 读取一个字符, 并且更新位置
    private void readChar() {
        if (read_position >= input.length()) {
            ch = (char) Token.ILLEGAL.ordinal();
        } else {
            ch = input.charAt(read_position);
        }
        position = read_position;
        read_position = read_position + 1;
    }

    private char peekChar() {
        if (read_position >= input.length()) {
            return 0;
        } else {
            return input.charAt(read_position);
        }
    }

    private TokenLiteral peekToken(Token cur, char peek, Token peek_token) {
        if (peekChar() == peek) {
            String tmp = Character.toString(this.ch);
            readChar();
            return new TokenLiteral(peek_token, tmp + ch);
        } else {
            return new TokenLiteral(cur, Character.toString(ch));
        }
    }

    private void skipWhitespace() {
        while (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
            readChar();
        }
    }

    private Boolean is_digit(char ch) {
        return ch >= '0' && ch <= '9' || ch == '.';
    }

    private Boolean is_alpha(char ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch == '_' || ch == ':' || ch == '"';
    }

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
        this.read_position = 0;
        this.readChar();
    }

    public TokenLiteral nextToken() {
        TokenLiteral tok = new TokenLiteral();
        skipWhitespace();
        switch (ch) {
            case '=':
                tok = peekToken(Token.EQ, '=', Token.EQ);
                break;
            case ';':
                tok = new TokenLiteral(Token.SEMICOLON, ch);
                break;
            case '(':
                tok = new TokenLiteral(Token.LPAREN, ch);
                break;
            case ')':
                tok = new TokenLiteral(Token.RPAREN, ch);
                break;
            case ',':
                tok = new TokenLiteral(Token.COMMA, ch);
                break;
            case '+':
                tok = new TokenLiteral(Token.PLUS, ch);
                break;
            case '-':
                tok = new TokenLiteral(Token.MINUS, ch);
                break;
            case '*':
                tok = new TokenLiteral(Token.MUL, ch);
                break;
            case '/':
                tok = new TokenLiteral(Token.DIV, ch);
                break;
            case '%':
                tok = new TokenLiteral(Token.MOD, ch);
                break;
            case '!':
                tok = peekToken(Token.NOT, '=', Token.NE);
                break;
            case '<':
                tok = peekToken(Token.LT, '=', Token.LTE);
                break;
            case '>':
                tok = peekToken(Token.GT, '=', Token.GTE);
                break;
            case '&':
                tok = peekToken(Token.ILLEGAL, '&', Token.AND);
                break;
            case '|':
                tok = peekToken(Token.ILLEGAL, '|', Token.OR);
                break;
            case '[':
                tok = new TokenLiteral(Token.LBRACKET, ch);
                break;
            case ']':
                tok = new TokenLiteral(Token.RBRACKET, ch);
                break;
            case '{':
                tok = new TokenLiteral(Token.LBRACE, ch);
                break;
            case '}':
                tok = new TokenLiteral(Token.RBRACE, ch);
                break;
            case 0:
                tok.literal = "";
                tok.token = Token.EOF;
                break;
            default:
                if (!is_alpha(ch) && !is_digit(ch)) {
                    tok = new TokenLiteral(Token.ILLEGAL, ch);
                } else {
                    Integer pos = position;
                    tok.token = Token.INT;
                    boolean isStrLetter = false;
                    while (is_alpha(ch) || is_digit(ch) || isStrLetter) {
                        if (is_alpha(ch)) {
                            tok.token = Token.IDENT;
                        }

                        if (ch == '"') {
                            isStrLetter = !isStrLetter;
                        }
                        readChar();
                    }

                    Integer start = input.charAt(pos) == '"' ? 1 : 0;
                    tok.literal = input.substring(pos + start, position - start);
                    if (tok.token == Token.IDENT) {
                        tok.token = Token.lookup(tok.literal);
                    }
                    return tok;
                }
        }

        readChar();
        return tok;
    }
}
