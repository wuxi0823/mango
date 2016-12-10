package org.jfaster.mango.util.logging;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by wuxi on 16/12/4.
 */
public class LoggerTest {

    @Test
    public void isTraceEnabled() throws Exception {
        String name = "xiwu";
        ConsoleLogger b = new ConsoleLogger("xiwu");
        boolean a = b.isTraceEnabled();
        assertThat(a, equalTo(true));

    }

    @Test
    public void trace() throws Exception {
        String name = "xiwu";
        Object o = null;
        ConsoleLogger b = new ConsoleLogger("xiwu");
        b.trace(name);
        b.trace(name, o);

    }

    @Test
    public void trace1() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        ConsoleLogger b = new ConsoleLogger("xiwu");
        b.trace(name, one, two);
    }

    @Test
    public void trace2() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        String three = "where";
        ConsoleLogger b = new ConsoleLogger("xiwu");
        b.trace(name, one, two, three);

    }

    @Test
    public void trace3() throws Exception {

    }

    @Test
    public void isDebugEnabled() throws Exception {
        String name = "xiwu";
        ConsoleLogger b = new ConsoleLogger("xiwu");
        boolean a = b.isDebugEnabled();
        assertThat(a, equalTo(true));

    }

    @Test
    public void debug() throws Exception {
        String name = "xiwu";
        Object o = null;
        ConsoleLogger b = new ConsoleLogger("xiwu");
        b.debug(name);
        b.debug(name, o);
    }

    @Test
    public void debug1() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        ConsoleLogger b = new ConsoleLogger("xiwu");
        b.debug(name, one, two);

    }

    @Test
    public void debug2() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        String three = "where";
        ConsoleLogger b = new ConsoleLogger("xiwu");
        b.debug(name, one, two, three);

    }

    @Test
    public void isInfoEnabled() throws Exception {
        String name = "xiwu";
        ConsoleLogger b = new ConsoleLogger("xiwu");
        boolean a = b.isInfoEnabled();
        assertThat(a, equalTo(true));


    }

    @Test
    public void info() throws Exception {
        String name = "xiwu";
        Object o = null;
        ConsoleLogger b = new ConsoleLogger("xiwu");
        b.info(name);
        b.info(name, o);


    }

    @Test
    public void info1() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        ConsoleLogger b = new ConsoleLogger("xiwu");
        b.info(name, one, two);
    }

    @Test
    public void info2() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        String three = "where";
        ConsoleLogger b = new ConsoleLogger("xiwu");
        b.info(name, one, two, three);
    }


    @Test
    public void isWarnEnabled() throws Exception {

        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        String three = "where";
        Object o = null;

        ConsoleLogger b = new ConsoleLogger("xiwu");
        boolean a = b.isWarnEnabled();
        b.warn(name);
        b.warn(name, o);
        b.warn(name, one, two);
        b.warn(name, one, two, three);
        assertThat(a, equalTo(true));


    }

    @Test
    public void isErrorEnabled() throws Exception {
        String name = "Xi Wu";
        String one = "select";
        String two = "join";
        String three = "where";
        Object o = null;

        ConsoleLogger b = new ConsoleLogger("xiwu");
        boolean a = b.isErrorEnabled();
        b.error(name);
        b.error(name, o);
        b.error(name, one, two);
        b.error(name, one, two, three);
        assertThat(a, equalTo(true));

    }
}
