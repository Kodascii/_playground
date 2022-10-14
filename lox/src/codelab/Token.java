package codelab;

public class Token {
//> :class-constructors
    public Token(TokenType type, String lexeme, Object literal, int line)
    {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }
//< :class-constructors


//> :class-fields
    /**
     * The type of {@link TokenType}.
     **/
    public final TokenType type;

    public final String lexeme;

    public final Object literal;

    /**
     * The line at which the token appear in the code.
     **/
    public final int line;
//< :class-fields


//> :class-inherited-methods
    @Override
    public String toString()
    {
        return type + " \"" + lexeme + "\" " + literal;
    }
//< :class-inherited-methods
}
