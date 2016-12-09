package org.jfaster.mango.parser;

import com.google.common.collect.Lists;
import org.jfaster.mango.binding.*;
import org.jfaster.mango.descriptor.ParameterDescriptor;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by wuxi.
 */
public class ASTOrNodeTest {
    SimpleCharStream jj_input_stream;
    @Test
    public void evaluate() throws Exception {
        String encoding="select";
        Object o=encoding;
        ParserTokenManager a=new ParserTokenManager(jj_input_stream);
        Parser b=new Parser(a);
        ASTOrNode g=new ASTOrNode(b,10);
        InvocationContext context = DefaultInvocationContext.create();
        context.addParameter("1", 100);
        context.addParameter("2", Arrays.asList(1, 2, 3, 4));
        context.addParameter("3", "xi");
        SimpleNode w=new SimpleNode(b,10);
        assertNotNull(w);
    }

    @Test
    public void value() throws Exception {
        String sql = "select where 1=1 #if(:1==false && :2!=null && :3==true) and id>10 #end";
        ASTRootNode root = new Parser(sql).parse().init();
        ParameterContext ctx = getParameterContext(Lists.newArrayList((Type) Boolean.class,
                Object.class, Boolean.class));
        root.checkAndBind(ctx);
        InvocationContext context = DefaultInvocationContext.create();
        context.addParameter("1", false);
        context.addParameter("2", new Object());
        context.addParameter("3", true);
        root.render(context);
        BoundSql boundSql = context.getBoundSql();
        assertThat(boundSql.getSql().toString(), equalTo("select where 1=1  and id>10 "));

    }

    @Test
    public void jjtAccept() throws Exception {

    }
    private ParameterContext getParameterContext(List<Type> types) {
        List<Annotation> empty = Collections.emptyList();
        List<ParameterDescriptor> pds = Lists.newArrayList();
        int pos = 0;
        for (Type type : types) {
            ParameterDescriptor pd = ParameterDescriptor.create(pos++, type, empty, String.valueOf(pos));
            pds.add(pd);
        }
        ParameterContext ctx = DefaultParameterContext.create(pds);
        return ctx;
    }

}
