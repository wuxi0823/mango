package org.jfaster.mango.parser;
import java.io.*;
import org.jfaster.mango.binding.InvocationContext;
import com.google.common.collect.Lists;
import org.jfaster.mango.DbTest;
import org.jfaster.mango.binding.*;
import org.jfaster.mango.descriptor.ParameterDescriptor;
import org.jfaster.mango.support.DataSourceConfig;
import org.jfaster.mango.util.ScriptRunner;
import org.jfaster.mango.util.reflect.TypeToken;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.io.InputStream;

import org.jfaster.mango.binding.BoundSql;



/**
 * xiwu
 */
public class ParserTest1 {
    private Connection conn;
    SimpleCharStream jj_input_stream;



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

    @Test
    public void test1() throws Exception {
        String sql = "select #{:1} from user where id in (:2) and name=:3";
        ASTRootNode root = new Parser(sql).parse().init();
        Type listType = new TypeToken<List<Integer>>() {
        }.getType();
        ParameterContext ctx = getParameterContext(Lists.newArrayList(String.class, listType, String.class));
        root.checkAndBind(ctx);
        InvocationContext context = DefaultInvocationContext.create();
        context.addParameter("1", "id");
        context.addParameter("2", Arrays.asList(1, 2, 3, 4));
        context.addParameter("3", "xi");
        root.render(context);
        BoundSql boundSql = context.getBoundSql();
        assertThat(boundSql.getSql().toString(), equalTo("select id from user where id in (?,?,?,?) and name=?"));
        assertThat(boundSql.getArgs(), contains(new Object[]{1, 2, 3, 4, "xi"}));
    }
    @Test
    public void testIfElseIf() throws Exception {
        String sql = "select where 1=1" +
                "#if(:1>0)" +
                " and id>:1" +
                "#end";
        ASTRootNode n = new Parser(sql).parse().init();
        ParameterContext ctx = getParameterContext(Lists.newArrayList((Type) Integer.class));
        n.checkAndBind(ctx);
        InvocationContext context = DefaultInvocationContext.create();
        context.addParameter("1", 50);
        n.render(context);
        BoundSql boundSql = context.getBoundSql();
        assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 and id>?"));
        assertThat(boundSql.getArgs(), contains(new Object[]{50}));
    }
    @Test
    public void testIf2() throws Exception {
        String sql = "select where 1=1 #if(!:1) and id<:1 #end";
        ASTRootNode root = new Parser(sql).parse().init();
        ParameterContext ctx = getParameterContext(Lists.newArrayList((Type) Integer.class));
        root.checkAndBind(ctx);
        InvocationContext context = DefaultInvocationContext.create();
        context.addParameter("1", 30);
        root.render(context);
        BoundSql boundSql = context.getBoundSql();
        assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 "));
        assertThat(boundSql.getArgs().size(), equalTo(0));
    }
    @Test
    public void test3() throws Exception {
        String encoding="select";
        Object o=encoding;

        ParserTokenManager a=new ParserTokenManager(jj_input_stream);

        Parser b=new Parser(a);
        b.ReInit(a);
    }
    @Test
    public void testss() throws Exception {
        String encoding="select";
        Object o=encoding;

        ParserTokenManager a=new ParserTokenManager(jj_input_stream);

        Parser b=new Parser(a);
        b.ReInit(a);
    }
    @Test
    public void test8() throws Exception {
        String encoding="select";
        Object o=encoding;

        ParserTokenManager a=new ParserTokenManager(jj_input_stream);

        Parser b=new Parser(a);
        ASTLENode g=new ASTLENode(b,10);
        InvocationContext context = DefaultInvocationContext.create();
        context.addParameter("1", -100);
        context.addParameter("2", Arrays.asList(1, 2, 3, 4));
        context.addParameter("3", "xi");
        SimpleNode w=new SimpleNode(b,10);

    }
    @Test
    public void testIfElseIfElse2() throws Exception {
        String sql = "select where 1=1" +
                "#if(:1>0)" +
                " and id>:1" +
                "#elseif(:1<0)" +
                " and id<:1" +
                "#else" +
                " and id=:1" +
                "#end";
        ASTRootNode n = new Parser(sql).parse().init();
        ParameterContext ctx = getParameterContext(Lists.newArrayList((Type) Integer.class));
        n.checkAndBind(ctx);
        InvocationContext context = DefaultInvocationContext.create();
        context.addParameter("1", -100);
        n.render(context);
        BoundSql boundSql = context.getBoundSql();
        assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 and id<?"));
        assertThat(boundSql.getArgs(), contains(new Object[]{-100}));
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
