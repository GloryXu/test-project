package tour;

import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author xuguangrong
 * @description
 * @date Created at 20:40 2019/12/13
 */
public class Col {

    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromFileName(args[1], Charset.forName("UTF-8"));
        TokenSource lexer = new RowsLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        int col = Integer.valueOf(args[0]);
        RowsParser parser = new RowsParser(tokens, col);
        parser.setBuildParseTree(false);
        parser.file();
    }

}
