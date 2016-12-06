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

/**
 * @author ash
 */
public class parseranalyzeTest {

  @Test
  public void testBase1() throws Exception {
    String sql = "select #{:1} from user where id in (:2) and name=:3";
    ASTRootNode n = new Parser(sql).parse().init();
    Type listType = new TypeToken<List<Integer>>() {
    }.getType();
    ParameterContext ctx = getParameterContext(Lists.newArrayList(String.class, listType, String.class));
    n.checkAndBind(ctx);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", "id");
    context.addParameter("2", Arrays.asList(9, 5, 2, 7));
    context.addParameter("3", "ash");
    n.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select id from user where id in (?,?,?,?) and name=?"));
    assertThat(boundSql.getArgs(), contains(new Object[]{9, 5, 2, 7, "ash"}));
  }

  @Test
  public void testIf1() throws Exception {
    String sql = "select where 1=1 #if(:1) and id>:1 #end";
    ASTRootNode n = new Parser(sql).parse().init();
    ParameterContext ctx = getParameterContext(Lists.newArrayList((Type) Integer.class));
    n.checkAndBind(ctx);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", 100);
    n.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select where 1=1  and id>? "));
    assertThat(boundSql.getArgs(), contains(new Object[]{100}));
  }

  @Test
  public void testIf21() throws Exception {
    String sql = "select where 1=1 #if(!:1) and id>:1 #end";
    ASTRootNode n = new Parser(sql).parse().init();
    ParameterContext ctx = getParameterContext(Lists.newArrayList((Type) Integer.class));
    n.checkAndBind(ctx);
    InvocationContext context = DefaultInvocationContext.create();
    context.addParameter("1", 100);
    n.render(context);
    BoundSql boundSql = context.getBoundSql();
    assertThat(boundSql.getSql().toString(), equalTo("select where 1=1 "));
    assertThat(boundSql.getArgs().size(), equalTo(0));
  }

}
