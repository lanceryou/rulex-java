package ast;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoolTest {

    @Test
    void test() {
        Bool bool = new Bool(true);
        assertTrue(bool.value());
    }
}