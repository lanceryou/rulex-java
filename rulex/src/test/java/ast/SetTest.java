package ast;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {
    @Test
    public void testExpression() {
        Set set = new Set("in", new Identifier("order"), List.of(
                new Identifier("1"),
                new Identifier("2"),
                new Identifier("3"))
        );
        assertEquals("order in [1,2,3]", set.Expr());
    }
}