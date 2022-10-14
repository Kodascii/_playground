package codelab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
//> Lox.class::fields
    /**
     * The variable {@code hadError} will be set to true, if an error occurred during compilation.
     **/
    static boolean hadError = false;
//< Lox.class::fields


//> Lox.class::entry-point
    public static void main(String[] args) throws IOException {
        if (args.length > 1)
        {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        }
        else if (args.length == 1)
            runFile(args[0]);
        else
            runPrompt();
    }
//< Lox.class::entry-point


//> Lox.class::runnables
    /**
     * Reads a file, and executes it with the {@link Lox#run(String)} method.
     * @param path The absolute path of the file.
     **/
    private static void runFile(String path) throws IOException
    {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        if (hadError) System.exit(65);
    }

    /**
     * Run the interactive console prompt.
     **/
    private static void runPrompt() throws IOException
    {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;)
        {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false;
        }
    }

    /**
     * Scan the source code, to create a list of tokens.
     **/
    private static void run(String source)
    {
        Lexer scanner = new Lexer(source);
        List<Token> tokens = scanner.scanTokens();

        tokens.forEach(System.out::println);
    }
//> Lox.class::runnables


//> Lox.class::error-prompt
    /**
     * Display the error message with a given line.
     **/
    static void error(int line, String message)
    {
        report(line, "", message);
    }

    /**
     * <p>Report the error for a given type with the corresponding line.</p>
     * <p><strong>Example :</strong></p>
     * <p>{@code [line 7] Error#Overflow : ...}</p>
     **/
    private static void report(int line, String where, String message)
    {
        System.err.println("[line " + line + "] Error#" + where + ": " + message);
        hadError = true;
    }
//> Lox.class::error-prompt
}
