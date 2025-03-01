package parser;

import ast.*;
import lexer.Lexer;
import lexer.Token;
import lexer.TokenLiteral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {
    private final Lexer lexer;
    private TokenLiteral curToken;
    private TokenLiteral peekToken;
    private final HashMap<Token, PrefixParseFn> prefixParseFns = new HashMap<>();
    private final HashMap<Token, InfixParseFn> infixParseFns = new HashMap<>();

    static public Expression ParserExpression(String input) throws Exception {
        Parser parser = new Parser(input);
        return parser.ParserExpression();
    }

    public Expression ParserExpression() throws Exception {
        return parseExpression(Precedence.LOWEST);
    }

    private void init() {
        nextToken();
        nextToken();

        initInfixParser();
        initPrefixParser();
    }

    // 初始化注册前缀解析器
    private void initPrefixParser() {
        prefixParseFns.put(Token.IDENT, this::parseIdent);
        prefixParseFns.put(Token.INT, this::parseIdent);
        prefixParseFns.put(Token.TRUE, this::parseBool);
        prefixParseFns.put(Token.FALSE, this::parseBool);
        prefixParseFns.put(Token.MINUS, this::parsePrefixExpression);
        prefixParseFns.put(Token.LPAREN, this::parseParenExpression);
    }

    private void initInfixParser() {
        infixParseFns.put(Token.PLUS, this::infixParse);
        infixParseFns.put(Token.MINUS, this::infixParse);
        infixParseFns.put(Token.MUL, this::infixParse);
        infixParseFns.put(Token.DIV, this::infixParse);
        infixParseFns.put(Token.MOD, this::infixParse);
        infixParseFns.put(Token.AND, this::infixParse);
        infixParseFns.put(Token.OR, this::infixParse);
        infixParseFns.put(Token.EQ, this::infixParse);
        infixParseFns.put(Token.NE, this::infixParse);
        infixParseFns.put(Token.LT, this::infixParse);
        infixParseFns.put(Token.LTE, this::infixParse);
        infixParseFns.put(Token.GT, this::infixParse);
        infixParseFns.put(Token.GTE, this::infixParse);
        infixParseFns.put(Token.ASSIGN, this::infixParse);
    }

    private Expression parseFunctionLiteral() throws Exception {
        String fn = curToken.literal;
        if (!expectPeekToken(Token.LPAREN)) {
            throw new RuntimeException("Expecting a function literal");
        }

        nextToken();
        List<Expression> params = new ArrayList<>();
        while (!curTokenIs(Token.RPAREN)) {
            params.add(parseExpression(Precedence.LOWEST));
            nextToken();
            if (curTokenIs(Token.COMMA)) {
                nextToken();
            }
        }

        return new Function(fn, params);
    }

    private Expression parseIdent() throws Exception {
        if (peekTokenIs(Token.LPAREN)) {
            return parseFunctionLiteral();
        }
        return new Identifier(curToken.literal);
    }

    private Expression parseBool() {
        return new Bool(curTokenIs(Token.TRUE));
    }

    private Boolean curTokenIs(Token token) {
        return curToken.token == token;
    }

    private Boolean peekTokenIs(Token token) {
        return peekToken.token == token;
    }

    private Expression parseExpression(Precedence precedence) throws Exception {
        PrefixParseFn prefix = prefixParseFns.get(curToken.token);
        if (prefix == null) {
            throw new RuntimeException("no prefix parse function for " + curToken.token);
        }

        Expression left = prefix.parse();
        while (!peekTokenIs(Token.EOF) && precedence.compareTo(Precedence.lookup(peekToken.token)) < 0) {
            InfixParseFn infix = infixParseFns.get(peekToken.token);
            if (infix == null) {
                throw new RuntimeException("no infix parse function for" + curToken.token);
            }
            nextToken();
            left = infix.parse(left);
        }

        return left;
    }

    private Expression parseParenExpression() throws Exception {
        nextToken();
        Expression left = parseExpression(Precedence.LOWEST);
        if (!expectPeekToken(Token.RPAREN)) {
            throw new RuntimeException("Expecting a ) literal");
        }

        return left;
    }

    private Expression parsePrefixExpression() throws Exception {
        String op = curToken.literal;
        nextToken();
        return new Prefix(op, parseExpression(Precedence.PREFIX));
    }

    private Expression infixParse(Expression left) throws Exception {
        String op = curToken.literal;
        Precedence precedence = Precedence.lookup(curToken.token);
        nextToken();
        Expression right = parseExpression(precedence);
        return new Infix(op, left, right);
    }

    private Boolean expectPeekToken(Token token) {
        if (peekTokenIs(token)) {
            nextToken();
            return true;
        }
        return false;
    }

    private void nextToken() {
        curToken = peekToken;
        peekToken = lexer.nextToken();
    }

    Parser(Lexer lexer) {
        this.lexer = lexer;
        init();
    }

    Parser(String input) {
        this.lexer = new Lexer(input);
        init();
    }
}

@FunctionalInterface
interface PrefixParseFn {
    Expression parse() throws Exception; // 使用 throws Exception 来处理错误
}

@FunctionalInterface
interface InfixParseFn {
    Expression parse(Expression expression) throws Exception;
}

