package parser;

import lexer.Token;

import java.util.HashMap;

public enum Precedence {
    LOWEST,
    And,
    EQUALS, // ==
    CMP,     // > or <
    SUM,    // +
    PRODUCT, // *
    PREFIX; // -X or !X

    private static final HashMap<Token, Precedence> precedenceMap = new HashMap<>() {
        {
            put(Token.EQ, Precedence.EQUALS);
            put(Token.ASSIGN, Precedence.EQUALS);
            put(Token.NE, Precedence.EQUALS);
            put(Token.AND, Precedence.And);
            put(Token.OR, Precedence.And);
            put(Token.IN, Precedence.CMP);
            put(Token.LT, Precedence.CMP);
            put(Token.LTE, Precedence.CMP);
            put(Token.GT, Precedence.CMP);
            put(Token.GTE, Precedence.CMP);
            put(Token.PLUS, Precedence.SUM);
            put(Token.MINUS, Precedence.SUM);
            put(Token.MUL, Precedence.PRODUCT);
            put(Token.DIV, Precedence.PRODUCT);
            put(Token.MOD, Precedence.PRODUCT);
        }
    };

    static public Precedence lookup(Token token) {
        return precedenceMap.getOrDefault(token, LOWEST);
    }
}
