package codelab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {
//> Lexer.class::constructors
    public Lexer(String source)
    {
        this.source = source;
        tokens = new ArrayList<>();
    }
//< Lexer.class::constructors


//< Lexer.class::static-fields
    private static final Map<String, TokenType> keywords;

    static
    {
        keywords = new HashMap<>();
        keywords.put("print", TokenType.PRINT);

        keywords.put("true", TokenType.TRUE);
        keywords.put("false", TokenType.FALSE);

        keywords.put("if", TokenType.IF);
        keywords.put("else", TokenType.ELSE);

        keywords.put("or", TokenType.OR);
        keywords.put("and", TokenType.AND);

        keywords.put("while", TokenType.WHILE);
        keywords.put("for", TokenType.FOR);

        keywords.put("var", TokenType.VAR);
        keywords.put("nil", TokenType.NIL);
        keywords.put("return", TokenType.RETURN);

        keywords.put("fun", TokenType.FUN);
        keywords.put("class", TokenType.CLASS);

        keywords.put("this", TokenType.THIS);
        keywords.put("super", TokenType.SUPER);
    }
//< Lexer.class::static-fields


//> Lexer.class::fields
    /**
     * Raw string of the source code.
     **/
    private final String source;
    private final List<Token> tokens;

    private int start = 0;
    private int current = 0;
    private int line = 1;
//< Lexer.class::fields


//> Lexer.class::common-fields
    public List<Token> scanTokens()
    {
        while(!isAtEnd())
        {
            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }
//> Lexer.class::common-fields


//> Lexer.class::lexical-analysis
    private void scanToken()
    {
        char c = consume();
        switch (c)
        {
            case ' ':
            case '\r':
            case '\t': break;
            case '\n': line++; break;
            case '(': addToken(TokenType.LEFT_PAREN); break;
            case ')': addToken(TokenType.RIGHT_PAREN); break;
            case '{': addToken(TokenType.LEFT_BRACE); break;
            case '}': addToken(TokenType.RIGHT_BRACE); break;
            case ',': addToken(TokenType.COMMA); break;
            case '.': addToken(TokenType.DOT); break;
            case '-': addToken(TokenType.MINUS); break;
            case '+': addToken(TokenType.PLUS); break;
            case ';': addToken(TokenType.SEMICOLON); break;
            case '*': addToken(TokenType.STAR); break;
            case '!': addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG); break;
            case '=': addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL); break;
            case '<': addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS); break;
            case '>': addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER); break;
            case '/': comment(); break;
            case '"': string(); break;
            default:
                if (isNumeric(c)) number();
                else if (isAlpha(c)) identifier();
                else Lox.error(line, "Unexpected character.");
        }
    }

    /**
     * Predicts if the expected token matches the next character code. If so a {@link Lexer#advance()};
     **/
    private boolean match(char expected)
    {
        if (isAtEnd()) return false;
        if (peek() != expected) return false;

        advance();
        return true;
    }
//< Lexer.class::lexical-analysis


//> Lexer.class::source-operations
    /**
     * Advance to the next character in the {@link Lexer#source}.
     **/
    private void advance()
    {
        current++;
    }

    /**
     * Advance to the next character in the {@link Lexer#source}, and consumes it.
     **/
    private char consume()
    {
        return source.charAt(current++);
    }

    /**
     * Returns the character in read.
     **/
    private char peek()
    {
        return peek(0);
    }

    /**
     * @return lookup the {@code index}-nth character after the one in read.
     **/
    private char peek(int index)
    {
        if (current + index >= source.length())
            return '\0';
        return source.charAt(current + index);
    }

    /**
     * @return false if it didn't analyze the entire {@link Lexer#source}.
     **/
    private boolean isAtEnd()
    {
        return current >= source.length();
    }
//> Lexer.class::source-operations


//< Lexer.class::grammars-checking
    public boolean isNumeric(char c)
    {
        return c >= '0' && c <= '9';
    }

    public boolean isAlpha(char c)
    {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    public boolean isAlphaNumeric(char c)
    {
        return (isAlpha(c) || isNumeric(c));
    }
//> Lexer.class::grammars-checking


//< Lexer.class::classifiers
    /**
     * Parse a comment.
     **/
    private void comment()
    {
        if (match('/'))
            while(peek() != '\n' && !isAtEnd()) advance();
        else
            addToken(TokenType.SLASH);
    }

    /**
     * Parse a string.
     **/
    private void string()
    {
        while (peek() != '"' && !isAtEnd())
        {
            if (peek() == '\n') line++;
            advance();
        }

        if (isAtEnd())
        {
            Lox.error(line, "Unterminated string.");
            return;
        }

        advance();

        String value = source.substring(start + 1, current - 1);
        addToken(TokenType.STRING, value);
    }

    private void number()
    {
        while (isNumeric(peek())) advance();

        if (peek() == '.' && isNumeric(peek(1)))
        {
            advance();
            while (isNumeric(peek())) advance();
        }

        addToken(TokenType.NUMBER, Double.parseDouble(source.substring(start, current)));
    }

    private void identifier()
    {
        while (isAlphaNumeric(peek())) advance();

        String text = source.substring(start, current);
        TokenType type = keywords.get(text);
        if (type == null) type = TokenType.IDENTIFIER;
        addToken(type);
    }
//> Lexer.class::classifiers


//> Lexer.class::utils
    private void addToken(TokenType type)
    {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal)
    {
        String lexeme = source.substring(start, current);
        tokens.add(new Token(type, lexeme, literal, line));
    }
//< Lexer.class::utils
}
