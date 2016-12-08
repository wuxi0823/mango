package org.jfaster.mango.util;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by wuxi on 16/12/8.
 */
public class ScriptRunnerTest {
    private Connection b;
    @Test
    public void setDelimiter() throws Exception {
        boolean autoCommit=false;
        boolean stopOnError=false;

        ScriptRunner a=new ScriptRunner(b,autoCommit,stopOnError);
        String delimiter = "haha";
        boolean fullLineDelimiter = false;
        a.setDelimiter(delimiter,fullLineDelimiter);

    }

}
