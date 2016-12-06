 package org.jfaster.mango.plugin.stats;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.Sharding;
import org.jfaster.mango.sharding.NotUseTableShardingStrategy;
import org.jfaster.mango.stat.OperatorStat;
import org.jfaster.mango.util.Strings;
import org.jfaster.mango.util.ToStringHelper;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
public class Test1 {
  @Test
  public void test() throws Exception {
  CombinedStat stat = CombinedStat.create();
  OperatorStat os = new OperatorStat();
  ExtendStat es = new ExtendStat(os);
  MetaStat metaStat = stat.getMetaStat();
  metaStat.setMethod(m);
  metaStat.setOperatorType(OperatorType.UPDATE);
  metaStat.setCacheable(true);
  metaStat.setUseMultipleKeys(true);
  metaStat.setCacheNullObject(true);
  InitStat initStat = stat.getInitStat();
  initStat.recordInit(1000);
  OperatorStat operatorStat = stat.toOperatorStat();
}
}
