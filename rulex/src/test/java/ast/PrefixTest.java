package ast;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrefixTest {
    @Test
    public void prefixExpression() {
        Prefix prefix = new Prefix("-", new Identifier("1"));
        assertEquals("-1", prefix.Expr());
    }
}