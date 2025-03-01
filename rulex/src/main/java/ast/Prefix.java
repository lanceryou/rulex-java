package ast;

public record Prefix(String op, Expression right) implements Expression {
    @Override
    public String Expr() {
        return op + right.Expr();
    }
}
