package org.jfaster.mango.stat;

import org.jfaster.mango.util.jdbc.OperatorType;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test1 {

  @Test
  public void test() throws Exception {
    Method m = CombinedStatTest.class.getDeclaredMethod("test");
    assertThat(m, notNullValue());

    CombinedStat stat = CombinedStat.create();

    MetaStat metaStat = stat.getMetaStat();
    metaStat.setMethod(m);
    metaStat.setOperatorType(OperatorType.UPDATE);
    metaStat.setCacheable(true);
    metaStat.setUseMultipleKeys(true);
    metaStat.setCacheNullObject(true);

    InitStat initStat = stat.getInitStat();
    initStat.recordInit(500);

    ExecuteStat executeStat = stat.getExecuteStat();
    OneExecuteStat oneExecuteStat = OneExecuteStat.create();
    oneExecuteStat.recordDatabaseExecuteSuccess(17);
    oneExecuteStat.recordDatabaseExecuteException(16);
    oneExecuteStat.recordHits(15);
    oneExecuteStat.recordMisses(14);
    oneExecuteStat.recordCacheGetSuccess(13);
    oneExecuteStat.recordCacheGetException(12);
    oneExecuteStat.recordCacheGetBulkSuccess(11);
    oneExecuteStat.recordCacheGetBulkException(10);
    oneExecuteStat.recordCacheSetSuccess(9);
    oneExecuteStat.recordCacheSetException(8);
    oneExecuteStat.recordCacheAddSuccess(7);
    oneExecuteStat.recordCacheAddException(6);
    oneExecuteStat.recordCacheDeleteSuccess(5);
    oneExecuteStat.recordCacheDeleteException(4);
    oneExecuteStat.recordCacheBatchDeleteSuccess(3);
    oneExecuteStat.recordCacheBatchDeleteException(2);
    executeStat.accumulate(oneExecuteStat);

    OperatorStat operatorStat = stat.toOperatorStat();

  }

}
