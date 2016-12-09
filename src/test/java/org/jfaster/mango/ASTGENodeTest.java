package org.jfaster.mango.parser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wuxi.
 */
public class ASTGENodeTest {
    SimpleCharStream jj_input_stream;

    @Test
    public void evaluate() throws Exception {
        ParserTokenManager a=new ParserTokenManager(jj_input_stream);
        Parser b=new Parser(a);
        ASTGENode g=new ASTGENode(b,10);
        ASTGENode h=new ASTGENode(1);


    }

}
