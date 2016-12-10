package org.jfaster.mango.util.logging;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by wuxi on 16/12/3.
 */
public class Slf4JLoggerTest {
    @Test
    public void isTraceEnabled() throws Exception {
        String name = "xiwu";
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        boolean a = b.isTraceEnabled();
        assertThat(a, equalTo(false));

    }

    @Test
    public void trace() throws Exception {
        String name = "xiwu";
        Object o = null;
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        b.trace(name);
        b.trace(name, o);

    }

    @Test
    public void trace1() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        b.trace(name, one, two);
    }



    @Test
    public void trace3() throws Exception {

    }

    @Test
    public void isDebugEnabled() throws Exception {
        String name = "xiwu";
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        boolean a = b.isDebugEnabled();
        assertThat(a, equalTo(false));

    }

    @Test
    public void debug() throws Exception {
        String name = "xiwu";
        Object o = null;
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        b.debug(name);
        b.debug(name, o);
    }

    @Test
    public void debug1() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        b.debug(name, one, two);

    }

    @Test
    public void debug2() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        int[] three = new int[3];
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));

    }

    @Test
    public void isInfoEnabled() throws Exception {
        String name = "xiwu";
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        boolean a = b.isInfoEnabled();
        assertThat(a, equalTo(false));


    }

    @Test
    public void info() throws Exception {
        String name = "xiwu";
        Object o = null;
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        b.info(name);
        b.info(name, o);


    }

    @Test
    public void info1() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        b.info(name, one, two);
    }

    @Test
    public void info2() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        int[] three = new int[3];
        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
    }


    @Test
    public void isWarnEnabled() throws Exception {

        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        int[] three = new int[3];
        Object o = null;

        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        boolean a = b.isWarnEnabled();
        b.warn(name);
        b.warn(name, o);
        b.warn(name, one, two);
        assertThat(a, equalTo(false));


    }

    @Test
    public void isErrorEnabled() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        int[] three = new int[3];
        Object o = null;

        Slf4JLogger b =  new Slf4JLogger(LoggerFactory.getLogger(name));
        boolean a = b.isErrorEnabled();
        b.error(name);
        b.error(name, o);
        b.error(name, one, two);
        assertThat(a, equalTo(true));

    }
}
