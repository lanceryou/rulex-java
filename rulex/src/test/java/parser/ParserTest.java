package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void testParse() throws Exception {
        assertEquals("(-100 * market)", Parser.ParserExpression("-100 * market").Expr());
        assertEquals("max()", Parser.ParserExpression("max()").Expr());
        assertEquals("(-1 + (2 * 3))", Parser.ParserExpression("-1+2*3").Expr());
        assertEquals("(-1 + (2 * 3.5))", Parser.ParserExpression("-1+2*3.5").Expr());
        assertEquals("(max(2, 1) + abs(-1))", Parser.ParserExpression("max(2,1) + abs(-1)").Expr());
    }
}