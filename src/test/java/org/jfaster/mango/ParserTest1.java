package org.jfaster.mango.parser;

import com.google.common.collect.Lists;
import org.jfaster.mango.DbTest;
import org.jfaster.mango.binding.*;
import org.jfaster.mango.descriptor.ParameterDescriptor;
import org.jfaster.mango.support.DataSourceConfig;
import org.jfaster.mango.util.ScriptRunner;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.InputStream;


/**
 * Created by wuxi on 16/12/9.
 */
public class ParserTest1 {
    private Connection conn;



    @Test
    public void relationalExpression() throws Exception {
        String sql = "select where 1=1 #if(:1==false && :2!=null && :3==true) and id>10 #end";
        ASTRootNode root = new Parser(sql).parse().init();
        ParameterContext cont = getParameterContext(Lists.newArrayList((Type) Boolean.class,
                Object.class, Boolean.class));
        root.checkAndBind(cont);
        InvocationContext context = DefaultInvocationContext.create();
        context.addParameter("1", true);
        context.addParameter("2", new Object());
        context.addParameter("3", true);
        root.render(context);
        BoundSql boundSql = context.getBoundSql();
        assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 "));
        Parser a=new Parser(sql);
    }


    private ParameterContext getParameterContext(List<Type> types) {
        List<Annotation> empty = Collections.emptyList();
        List<ParameterDescriptor> pds = Lists.newArrayList();
        int pos = 0;
        for (Type type : types) {
            ParameterDescriptor pd = ParameterDescriptor.create(pos++, type, empty, String.valueOf(pos));
            pds.add(pd);
        }
        ParameterContext cont = DefaultParameterContext.create(pds);
        return cont;
    }

}
