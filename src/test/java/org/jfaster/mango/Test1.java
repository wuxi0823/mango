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
    
    CombinedStat ST = CombinedStat.create();

    MetaStat meta = ST.getMetaStat();
    meta.setUseMultipleKeys(true);
    meta.setCacheNullObject(true);
    meta.setMethod(m);
    meta.setOperatorType(OperatorType.UPDATE);
    meta.setCacheable(true);

    InitStat inist = ST.getInitStat();
    inist.recordInit(500);

    ExecuteStat execute = ST.getExecuteStat();
    OneExecuteStat oneex = OneExecuteStat.create();
    oneex.recordDatabaseExecuteSuccess(17);
    oneex.recordDatabaseExecuteException(16);
    oneex.recordHits(15);
    oneex.recordMisses(14);
    oneex.recordCacheGetSuccess(13);
    oneex.recordCacheGetException(12);
    oneex.recordCacheGetBulkSuccess(11);
    oneex.recordCacheGetBulkException(10);
    oneex.recordCacheSetSuccess(9);
    oneex.recordCacheSetException(8);
    oneex.recordCacheAddSuccess(7);
    oneex.recordCacheAddException(6);
    oneex.recordCacheDeleteSuccess(5);
    oneex.recordCacheDeleteException(4);
    oneex.recordCacheBatchDeleteSuccess(3);
    oneex.recordCacheBatchDeleteException(2);
    execute.accumulate(oneex);

    OperatorStat operatorStat = ST.toOperatorStat();

  }

}
