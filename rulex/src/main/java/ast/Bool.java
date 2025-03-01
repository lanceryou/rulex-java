package ast;

public record Bool(Boolean value) implements Expression {
    @Override
    public String Expr() {
        return value.toString();
    }
}
