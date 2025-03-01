package ast;

public class Identifier implements Expression {
    private final String val;

    public Identifier(String val) {
        this.val = val;
    }

    @Override
    public String Expr() {
        return val;
    }
}
