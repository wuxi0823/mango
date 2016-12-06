package org.jfaster.mango.stat;

import org.jfaster.mango.util.jdbc.OperatorType;
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

