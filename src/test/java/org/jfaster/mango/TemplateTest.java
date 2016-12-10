package org.jfaster.mango.plugin.stats;

import org.jfaster.mango.stat.OperatorStat;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by wuxi on 16/12/2.
 */
public class TemplateTest {
    @Test
    public void render() throws Exception {
        String a="2015/08/11";
        String b="2016/08/11";
        Map<String, OperatorStat> osMap=new HashMap<>();
        Map<String, ExtendStat> esMap=new HashMap<>();
        boolean c=true;
        String d=Template.render(a, b, osMap, esMap, c);

    }

}
