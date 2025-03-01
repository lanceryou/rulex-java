package ast;

import java.util.ArrayList;
import java.util.List;

public record Function(String name, List<Expression> parameters) implements Expression {
    @Override
    public String Expr() {
        List<String> params = new ArrayList<>();
        for (Expression expr : parameters) {
            params.add(expr.Expr());
        }

        return name + "(" + String.join(", ", params) + ")";
    }
}
