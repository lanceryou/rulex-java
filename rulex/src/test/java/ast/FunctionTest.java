package ast;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {
    @Test
    public void testExpression() {
        Function fn = new Function("add", List.of(
                new Identifier("1"),
                new Identifier("2")));
        assertEquals("add(1, 2)", fn.Expr());
    }
}