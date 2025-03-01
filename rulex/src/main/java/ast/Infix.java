package ast;

public class Infix implements Expression {
    public final Expression left;
    public final String op;
    public final Expression right;

    public Infix(String op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public String Expr() {
        return "(" + left.Expr() + " " + op + " " + right.Expr() + ")";
    }
}
