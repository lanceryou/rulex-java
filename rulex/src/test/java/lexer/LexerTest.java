package lexer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {
    @Test
    public void nextToken() {
        Lexer lexer = new Lexer("2%5");
        assertEquals(new TokenLiteral(Token.INT, "2"), lexer.nextToken());
        assertEquals(new TokenLiteral(Token.MOD, '%'), lexer.nextToken());
        assertEquals(new TokenLiteral(Token.INT, "5"), lexer.nextToken());

        Lexer l = new Lexer("market.TradeDatePrice(\"T-1\",ClosePrice)");
        assertEquals(new TokenLiteral(Token.IDENT, "market.TradeDatePrice"), l.nextToken());
        assertEquals(new TokenLiteral(Token.LPAREN, '('), l.nextToken());
        assertEquals(new TokenLiteral(Token.IDENT, "T-1"), l.nextToken());
        assertEquals(new TokenLiteral(Token.COMMA, ","), l.nextToken());
        assertEquals(new TokenLiteral(Token.IDENT, "ClosePrice"), l.nextToken());
        assertEquals(new TokenLiteral(Token.RPAREN, ")"), l.nextToken());
    }
}