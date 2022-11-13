//AMAH Gnimdou Richard
//Fanus Ludovic

import org.antlr.v4.runtime.*;


public class Test {
    public static void main(String[] args) throws Exception
    {

        CharStream input = null;
        // pick an input stream (filename from commandline or stdin)

        if(args.length > 0) input = CharStreams.fromFileName(args[0]);
        else input = CharStreams.fromStream(System.in);

        // create the lexer
        PPLexer lex = new PPLexer(input);

        // create a buffer of tokens between the lexer and parser
        CommonTokenStream tokens = new CommonTokenStream(lex);
        // create the parser, attaching it to the token buffer


        PPParser parser = new PPParser(tokens);

        PPProg prog = parser.programmes().p;
        System.out.println(prog);


    }
}

