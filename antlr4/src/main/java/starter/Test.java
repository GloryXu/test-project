package starter;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.TokenSource;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

/**
 * @author xuguangrong
 * @description
 * @date Created at 10:23 2019/12/13
 */
public class Test {
    public static void main(String[] args) throws IOException {
        // 新建一个CharStream，从标准输入读取数据
        ANTLRInputStream input = new ANTLRInputStream(System.in);

        ArrayInitLexer lexer = new ArrayInitLexer((CharStream) input);

        CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);

        ArrayInitParser parser = new ArrayInitParser((TokenStream) tokens);

        ParseTree tree = parser.init();

        System.out.println(tree.toStringTree(parser));
    }
}
