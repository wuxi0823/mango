package org.jfaster.mango.util;

import org.jfaster.mango.DbTest;
import org.jfaster.mango.support.DataSourceConfig;
import org.junit.Test;
import org.junit.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.lang.reflect.*;
import static org.junit.Assert.*;

/**
 * Created by wuxi on 16/12/8.
 */
public class ScriptRunnerTest {
    private Connection b;
    private PrintWriter logWriter = new PrintWriter(System.out);
    private PrintWriter errorLogWriter = new PrintWriter(System.err);
    private String name="xiwu";
    private Reader reader;
    private String delimiter = ";";
    private Objects h;
    private final Method methods[] = ScriptRunner.class.getDeclaredMethods();
    private static final String DEFAULT_DELIMITER = ";";

    private Connection connection;

    private boolean stop=false;
    private boolean auto=false;
    @Test
    public void setDelimiter() throws Exception {
        boolean autoCommit=false;
        boolean stopOnError=false;

        ScriptRunner a=new ScriptRunner(b,auto,stop);
        String delimiter = "haha";
        boolean fullLineDelimiter = false;
        a.setDelimiter(delimiter,fullLineDelimiter);

    }
    @Test
    public void setLogWriter() throws Exception {
        boolean autoCommit=true;
        boolean stopOnError=true;
        ScriptRunner a=new ScriptRunner(b,autoCommit,stopOnError);
        a.setLogWriter(logWriter);
    }
    @Test
    public void setErrorLogWriter() throws Exception {
        boolean autoCommit=true;
        boolean stopOnError=true;
        ScriptRunner a=new ScriptRunner(b,autoCommit,stopOnError);
        a.setErrorLogWriter(errorLogWriter);
    }
    @Test
    public void runScript() throws Exception {
        boolean autoCommit=true;
        boolean stopOnError=true;
        ScriptRunner a=new ScriptRunner(b,autoCommit,stopOnError);


    }
    @Test
    public void print() throws Exception {
        boolean autoCommit = true;
        boolean stopOnError = true;
        ScriptRunner a = new ScriptRunner(b, autoCommit, stopOnError);
        for (int i = 0; i < methods.length; ++i) {
            if (methods[i].getName().equals("getData2")) {//找到要测试的private method
                methods[i].setAccessible(true);            //设置可以访问
                Object para[] = {"s"};                    //参数列表
                Object o = methods[i].invoke(a, para);    //相当于c.privateMethod(para)
                Assert.assertEquals(name, o); //测试
            }
        }
    }



    }
