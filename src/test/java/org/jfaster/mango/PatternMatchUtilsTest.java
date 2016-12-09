package org.jfaster.mango.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by wuxi
 */
public class PatternMatchUtilsTest {

    @Test
    public void simpleMatch() throws Exception {
        String pattern=null;
        String str=null;
        boolean i=PatternMatchUtils.simpleMatch(pattern,str);
        assertThat(i, equalTo(false));
        pattern="*";
        str="*";
        boolean j=PatternMatchUtils.simpleMatch(pattern,str);
        assertThat(j, equalTo(true));
        pattern="s";
        boolean x=PatternMatchUtils.simpleMatch(pattern,str);
        assertThat(x, equalTo(false));
        pattern="*s";
        boolean h=PatternMatchUtils.simpleMatch(pattern,str);
        assertThat(h, equalTo(false));
        pattern="*ss";
        boolean w=PatternMatchUtils.simpleMatch(pattern,str);
        assertThat(w, equalTo(false));
        pattern="**s";
        str="s";
        boolean y=PatternMatchUtils.simpleMatch(pattern,str);
        assertThat(y, equalTo(true));
        pattern="s*s";
        boolean z=PatternMatchUtils.simpleMatch(pattern,str);
        assertThat(z, equalTo(false));
    }

    @Test
    public void simpleMatch1() throws Exception {
        String [] pattern=new String[2];
        pattern[0]="xi";
        pattern[1]="wu";
        String str="xiwu";
        boolean i=PatternMatchUtils.simpleMatch(pattern,str);
        assertThat(i, equalTo(false));
    }

}
