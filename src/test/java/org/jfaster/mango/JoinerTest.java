package org.jfaster.mango.util;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by wuxi on 16/12/8.
 */
public class JoinerTest {
    String str = "xiwu";
    char a= str.charAt(0);
    @Test
    public void on() throws Exception {

        Joiner COMMA_JOINER = Joiner.on(a).useForNull("null");
        assertThat(COMMA_JOINER, anything());

    }



}
