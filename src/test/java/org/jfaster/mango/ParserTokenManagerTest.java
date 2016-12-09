package org.jfaster.mango.parser;

import org.jfaster.mango.DbTest;
import org.jfaster.mango.support.DataSourceConfig;
import org.jfaster.mango.util.ScriptRunner;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Created by wuxi on 16/12/8.
 */
public class ParserTokenManagerTest {
    SimpleCharStream jj_input_stream;

    @Test
    public void jjMoveNfa_1() throws Exception {
        ParserTokenManager a=new ParserTokenManager(jj_input_stream);
       int b=0;
        b= a.jjMoveNfa_1(9,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(10,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(11,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(12,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(12,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(13,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(14,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(15,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(16,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(17,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(18,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(19,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(20,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(21,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(22,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(23,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(24,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(26,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(27,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(28,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(29,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(30,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(31,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(32,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(33,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_1(34,2);
        assertThat(b, equalTo(3));

    }
    @Test
    public void jjMoveNfa_0() throws Exception {
        ParserTokenManager a=new ParserTokenManager(jj_input_stream);
        int b=0;
        b= a.jjMoveNfa_0(9,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(10,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(11,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(12,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(12,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(13,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(14,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(15,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(16,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(17,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(18,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(19,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(20,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(21,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(22,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(23,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(24,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(26,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(27,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(28,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(29,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(30,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(31,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(32,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(33,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(35,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(36,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(37,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(38,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(39,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(40,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(41,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(42,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(43,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(44,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(45,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(46,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(47,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(48,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(49,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(50,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(51,2);
        assertThat(b, equalTo(3));
        b= a.jjMoveNfa_0(53,2);
        assertThat(b, equalTo(3));


    }


}
