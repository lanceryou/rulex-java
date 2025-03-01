package ast;

import java.util.List;

// Set 集合 比如 x in [1,2,3]
public class Set implements Expression {
    public final String op;
    public final Expression left;
    public final List<Expression> express;

    Set(String op, Expression left, List<Expression> express) {
        this.op = op;
        this.left = left;
        this.express = express;
    }

    @Override
    public String Expr() {
        StringBuilder build = new StringBuilder();
        build.append(left.Expr()).append(" ").append(op).append(" ").append("[");
        for (Expression expr : express) {
            build.append(expr.Expr()).append(",");
        }
        build.replace(build.length() - 1, build.length(), "]");
        return build.toString();
    }
}
