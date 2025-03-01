package lexer;

import java.util.HashMap;

public enum Token {
    ILLEGAL, // "ILLEGAL"
    EOF,         // "EOF"
    IDENT, // "IDENT" // foobar, x, y,...
    INT,  // "INT"   // number
    // Operators
    ASSIGN, // "="
    PLUS,   // "+"
    MINUS,  // "-"
    MUL,   // "*"
    DIV,    // "/"
    MOD,    // "%"

    LT,  // "<"
    LTE, // "<="
    GT,  // ">"
    GTE, // ">="
    EQ,  // "=="
    NE,  // "!="
    // Delimiters
    COMMA,    // ","
    SEMICOLON, // ";"
    LPAREN,    // "("
    RPAREN,   // ")"
    LBRACE,    // "{"
    RBRACE,    // "}"
    LBRACKET,  // "["
    RBRACKET,  // "]"
    NOT,      // "!"
    AND,      // "&&"
    OR,      // "||"

    // Keywords
    FUNCTION, // "fn"
    IN,       // "in"
    IF,     // "if"
    TRUE,    // "true"
    FALSE;   // "fa

    private static final HashMap<String, Token> keywords = new HashMap<>() {
        {
            put("fn", FUNCTION);
            put("if", IF);
            put("in", IN);
            put("true", TRUE);
            put("false", FALSE);
        }
    };

    static public void registerKeyword(String key, Token token) {
        keywords.put(key, token);
    }

    static public Token lookup(String keyword) {
        return keywords.getOrDefault(keyword, IDENT);
    }
}
