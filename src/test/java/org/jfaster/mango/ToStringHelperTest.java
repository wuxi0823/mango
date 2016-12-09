package org.jfaster.mango.util;

import org.junit.Test;
import java.util.*;
import java.lang.reflect.Method;
import java.lang.reflect.*;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.junit.Assert.*;

/**
 * Created by wuxi on 16/12/8.
 */
public class ToStringHelperTest {
    private final Method m[]= ToStringHelper.class.getDeclaredMethods();
    private Map<String, String> map;

    @Test
    public void toStringtest() throws Exception {
        ToStringHelper a=new ToStringHelper();
        String str = new String();

        str=a.toString(m[0]);
        assertThat(str, containsString(")"));

    }

    @Test
    public void toString1() throws Exception {
        ToStringHelper a=new ToStringHelper();
        String str = new String();
        Type[] types = m[0].getGenericParameterTypes();
        str=a.toString(types[0]);
        assertNotNull( str);
    }

    @Test
    public void toString2() throws Exception {
        ToStringHelper a=new ToStringHelper();
        String str = new String();


    }


}
