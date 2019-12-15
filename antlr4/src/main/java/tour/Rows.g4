grammar Rows;

@parser::members {// 在生成的RowsParser中添加一些成员
    int col;
    public RowsParser(TokenStream input, int col) {// 自定义的函数
        this(input);
        this.col = col;
    }
}

file: (row NL)+;

row
locals [int i=0]
    : ( STUFF
        {
            $i++;
            if ($i == col) System.out.println($STUFF.text);
        }
      )+
    ;

TAB :   '\t' -> skip;
NL  :   '\r'?'\n';
STUFF:  ~[\t\r\n]+;