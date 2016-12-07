package org.jfaster.mango.parser;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.jfaster.mango.binding.*;
import org.jfaster.mango.binding.BoundSql;
import org.jfaster.mango.support.ParserVisitorAdapter;
import org.jfaster.mango.util.jdbc.JdbcType;
import org.jfaster.mango.util.jdbc.SQLType;
import org.jfaster.mango.util.reflect.TypeToken;
import org.jfaster.mango.descriptor.ParameterDescriptor;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;


public class test4Test {

  @Test
  public void test1() throws Exception {
    String sql = "select #{:1} from user where id in (:2) and name=:3";
    ASTRootNode root = new Parser(sql).parse().init();
    Type listType = new TypeToken<List<Integer>>() {
    }.getType();
    ParameterContext context = getParameterContext(Lists.newArrayList(String.class, listType, String.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", "id");
    context.addParameter("2", Arrays.asList(1, 2, 3,4));
    context.addParameter("3", "xi");
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select id from user where id in (?,?,?,?) and name=?"));
    assertThat(boundSql.getArgs(), contains(new Object[]{1, 2, 3,4, "xi"}));
  }

  @Test
  public void testIf() throws Exception {
    String sql = "select where 1=1 #if(:1) and id>:1 #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Integer.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", 100);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select where 1=1  and id>? "));
    assertThat(boundSql.getArgs(), contains(new Object[]{100}));
  }

  @Test
  public void testIf2() throws Exception {
    String sql = "select where 1!=1 #if(!:1) and id>:1 #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Integer.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", 100);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 "));
    assertThat(boundSql.getArgs().size(), equalTo(0));
  }

  @Test
  public void testIfElseIf() throws Exception {
    String sql = "select where 1=1" +
        "#if(:1>0)" +
        " and id>:1" +
        "#elseif(:1<0)" +
        " and id<:1" +
        "#end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Integer.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", 100);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 and id>?"));
    assertThat(boundSql.getArgs(), contains(new Object[]{100}));
  }

  @Test
  public void testIfElseIf2() throws Exception {
    String sql = "select where 1=1" +
        "#if(:1>0)" +
        " and id>:1" +
        "#elseif(:1<0)" +
        " and id<:1" +
        "#end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Integer.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", -100);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 and id<?"));
    assertThat(boundSql.getArgs(), contains(new Object[]{-100}));
  }

  @Test
  public void testIfElseIfElse() throws Exception {
    String sql = "select where 1=1" +
        "#if(:1>0)" +
        " and id>:1" +
        "#elseif(:1<0)" +
        " and id<:1" +
        "#else" +
        " and id=:1" +
        "#end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Integer.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", 100);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 and id>?"));
    assertThat(boundSql.getArgs(), contains(new Object[]{100}));
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
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Integer.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", -100);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 and id<?"));
    assertThat(boundSql.getArgs(), contains(new Object[]{-100}));
  }

  @Test
  public void testIfElseIfElse3() throws Exception {
    String sql = "select where 1=1" +
        "#if(:1>0)" +
        " and id>:1" +
        "#elseif(:1<0)" +
        " and id<:1" +
        "#else" +
        " and id=:1" +
        "#end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Integer.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", 0);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 and id=?"));
    assertThat(boundSql.getArgs(), contains(new Object[]{0}));
  }

  @Test
  public void testExpression() throws Exception {
    String sql = "select where 1=1 #if(:1==false && :2!=null && :3==true) and id>10 #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Boolean.class,
        Object.class, Boolean.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", false);
    context.addParameter("2", new Object());
    context.addParameter("3", true);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select where 1=1  and id>10 "));
  }

  @Test
  public void testParse() throws Exception {
    String sql = "SELECT * from user where id in ( select id from user2 )";
    ASTRootNode root = new Parser(sql).parse().init();
    InvocationContext context = DefaultInvocationContext.create();
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("SELECT * from user where id in ( select id from user2 )"));
  }

  @Test
  public void testIntegerLiteral() throws Exception {
    String sql = "select #if (:1 > 9223372036854775800) ok #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Integer.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", Long.MAX_VALUE);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("select  ok "));
  }

  @Test
  public void testIntegerLiteral2() throws Exception {
    String sql = "select #if (:1 > 10) ok #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Integer.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", Long.MAX_VALUE);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("select  ok "));
  }

  @Test
  public void testIntegerLiteral3() throws Exception {
    String sql = "select #if (:1 > 9223372036854775800) ok #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) Integer.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", 100);
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("select "));
  }

  @Test
  public void testReplace() throws Exception {
    String sql = "replace xxx into replace xxx";
    ASTRootNode root = new Parser(sql).parse().init();
    List<Type> types = Lists.newArrayList();
    ParameterContext context = getParameterContext(types);
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("replace xxx into replace xxx"));
    assertThat(root.getSQLType(), is(SQLType.REPLACE));
  }

  @Test
  public void testMerge() throws Exception {
    String sql = "merge xxx into merge xxx";
    ASTRootNode root = new Parser(sql).parse().init();
    List<Type> types = Lists.newArrayList();
    ParameterContext context = getParameterContext(types);
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("merge xxx into merge xxx"));
    assertThat(root.getSQLType(), is(SQLType.MERGE));
  }

  @Test
  public void testStringLiteral() throws Exception {
    String sql = "select #if (:1 == 'hello') ok #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) String.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", "hello");
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("select  ok "));
  }

  @Test
  public void testStringLiteral2() throws Exception {
    String sql = "select #if (:1 == 'hello') ok #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) String.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", "hello2");
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("select "));
  }

  @Test
  public void testStringLiteral3() throws Exception {
    String sql = "select #if ('') ok #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) String.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", "hello2");
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("select "));
  }

  @Test
  public void testStringLiteral4() throws Exception {
    String sql = "select #if (!'') ok #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) String.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", "hello2");
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("select  ok "));
  }

  @Test
  public void testStringLiteral5() throws Exception {
    String sql = "select #if (:1) ok #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) String.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", "he");
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("select  ok "));
  }

  @Test
  public void testStringLiteral6() throws Exception {
    String sql = "select #if (:1) ok #end";
    ASTRootNode root = new Parser(sql).parse().init();
    ParameterContext context = getParameterContext(Lists.newArrayList((Type) String.class));
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", "");
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql(), Matchers.equalTo("select "));
  }

  @Test
  public void testQuote() throws Exception {
    String sql = "insert into table ... values(':dd',':xx')";
    ASTRootNode root = new Parser(sql).parse().init();
    List<Type> types = Lists.newArrayList();
    ParameterContext context = getParameterContext(types);
    root.checkAndBind(context);
    InvocationContext context = DefaultInvocationContext.create();
    root.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("insert into table ... values(':dd',':xx')"));
    assertThat(boundSql.getArgs(), hasSize(0));
  }

  @Test
  public void testJdbcType() throws Exception {
    String sql = "insert into table ... values(:1.b.c@blob) a in (:2.x.y@clob)";
    ASTRootNode root = new Parser(sql).parse().init();
    final AtomicInteger t = new AtomicInteger(0);
    root.jjtAccept(new ParserVisitorAdapter() {
      @Override
      public Object visit(ASTJDBCParameter node, Object data) {
        BindingParameter bp = node.getBindingParameter();
        assertThat(bp.getParameterName(), equalTo("1"));
        assertThat(bp.getPropertyPath(), equalTo("b.c"));
        assertThat(bp.getJdbcType(), equalTo(JdbcType.BLOB));
        t.incrementAndGet();
        return super.visit(node, data);
      }

      @Override
      public Object visit(ASTJDBCIterableParameter node, Object data) {
        BindingParameter bp = node.getBindingParameter();
        assertThat(bp.getParameterName(), equalTo("2"));
        assertThat(bp.getPropertyPath(), equalTo("x.y"));
        assertThat(bp.getJdbcType(), equalTo(JdbcType.CLOB));
        t.incrementAndGet();
        return super.visit(node, data);
      }
    }, null);
    assertThat(t.intValue(), equalTo(2));
  }

  private ParameterContext getParameterContext(List<Type> types) {
    List<Annotation> empty = Collections.emptyList();
    List<ParameterDescriptor> pds = Lists.newArrayList();
    int pos = 0;
    for (Type type : types) {
      ParameterDescriptor pd = ParameterDescriptor.create(pos++, type, empty, String.valueOf(pos));
      pds.add(pd);
    }
    ParameterContext context = DefaultParameterContext.create(pds);
    return context;
  }



}
