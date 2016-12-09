package org.jfaster.mango.util.logging;
import java.util.*;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by wuxi
 */
public class MessageFormatterTest {
    @Test
    public void format() throws Exception {
        String str="select a from b;";
        Object[] array=new Object[10];
        MessageFormatter.arrayFormat(str,array);
        assertThat(str, containsString("select"));

    }

    @Test
    public void format1() throws Exception {
        String str="select a from b;";
        boolean j=MessageFormatter.isEscapedDelimeter(str,10);
        assertThat(j, equalTo(false));
        str="select a from b;";
        j=MessageFormatter.isDoubleEscaped(str,10);
        assertThat(j, equalTo(false));


    }



}
