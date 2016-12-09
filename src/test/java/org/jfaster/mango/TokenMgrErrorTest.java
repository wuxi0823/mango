package org.jfaster.mango.parser;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by wuxi
 */
public class TokenMgrErrorTest {
    @Test
    public void addEscapes() throws Exception {
        TokenMgrError a=new TokenMgrError();
        String str="asds\\t\\n\\f\\t\\b\\rsdfads\\\\\\";
        String h=a.addEscapes(str);
        assertThat(str, containsString("asds\\t\\n\\f\\t\\b\\rsdfads\\\\\\"));
        str="\t\\n\\f\\t\\b\\rsdfads\\\\\\";
        h=a.addEscapes(str);
        assertThat(str, containsString("\t\\n\\f\\t\\b\\rsdfads\\\\\\"));
        str="\n\\f\\t\\b\\rsdfads\\\\\\";
        h=a.addEscapes(str);
        assertThat(str, containsString("\n\\f\\t\\b\\rsdfads\\\\\\"));
        str="\f\\t\\b\\rsdfads\\\\\\";
        h=a.addEscapes(str);
        assertThat(str, containsString("\f\\t\\b\\rsdfads\\\\\\"));
        str="\r\\t\\b\\rsdfads\\\\\\";
        h=a.addEscapes(str);
        assertThat(str, containsString("\r\\t\\b\\rsdfads\\\\\\"));
        str="\b\\rsdfads\\\\\\";
        h=a.addEscapes(str);
        assertThat(str, containsString("\b\\rsdfads\\\\\\"));
        str="\\rsdfads\\\\\\";
        h=a.addEscapes(str);
        assertThat(str, containsString("\\rsdfads\\\\\\"));






    }

    @Test
    public void lexicalError() throws Exception {
        TokenMgrError a=new TokenMgrError();
        boolean EOFSeen=false;
        int lexState=1;
        int errorLine=2;
        int errorColumn=2;
        String errorAfter="select";
        char curChar='i';
        String h=a.LexicalError(EOFSeen,lexState,errorLine,errorColumn,errorAfter,curChar);
        assertNotNull(h);
    }

    @Test
    public void getMessage() throws Exception {
        String i="random";
        TokenMgrError a=new TokenMgrError(i,1);
        boolean EOFSeen=false;
        int lexState=1;
        int errorLine=2;
        int errorColumn=2;
        String errorAfter="select";
        char curChar='i';
        TokenMgrError b=new TokenMgrError(EOFSeen,lexState,errorLine,errorColumn,errorAfter,curChar,1);
    }

}
