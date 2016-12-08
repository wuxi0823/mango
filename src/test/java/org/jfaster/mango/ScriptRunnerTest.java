package org.jfaster.mango.util;

import org.jfaster.mango.DbTest;
import org.jfaster.mango.support.DataSourceConfig;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

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
    @Test
    public void setDelimiter() throws Exception {
        boolean autoCommit=false;
        boolean stopOnError=false;

        ScriptRunner a=new ScriptRunner(b,autoCommit,stopOnError);
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



}
