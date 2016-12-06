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
    // assertThat(operatorStat.getMethod(), equalTo(m));
    // assertThat(operatorStat.getOperatorType(), equalTo(OperatorType.UPDATE));
    // assertThat(operatorStat.isCacheable(), equalTo(true));
    // assertThat(operatorStat.isUseMultipleKeys(), equalTo(true));
    // assertThat(operatorStat.isCacheNullObject(), equalTo(true));
    // assertThat(operatorStat.getInitCount(), equalTo(1L));
    // assertThat(operatorStat.getTotalInitTime(), equalTo(500L));
    // assertThat(operatorStat.getDatabaseExecuteSuccessCount(), equalTo(1L));
    // assertThat(operatorStat.getDatabaseExecuteExceptionCount(), equalTo(1L));
    // assertThat(operatorStat.getTotalDatabaseExecuteTime(), equalTo(5L));
    // assertThat(operatorStat.getHitCount(), equalTo(15L));
    // assertThat(operatorStat.getMissCount(), equalTo(14L));
    // assertThat(operatorStat.getCacheGetSuccessCount(), equalTo(1L));
    // assertThat(operatorStat.getCacheGetExceptionCount(), equalTo(1L));
    // assertThat(operatorStat.getTotalCacheGetTime(), equalTo(13L));
    // assertThat(operatorStat.getCacheGetBulkSuccessCount(), equalTo(1L));
    // assertThat(operatorStat.getCacheGetBulkExceptionCount(), equalTo(1L));
    // assertThat(operatorStat.getTotalCacheGetBulkTime(), equalTo(17L));
    // assertThat(operatorStat.getCacheSetSuccessCount(), equalTo(1L));
    // assertThat(operatorStat.getCacheSetExceptionCount(), equalTo(1L));
    // assertThat(operatorStat.getTotalCacheSetTime(), equalTo(21L));
    // assertThat(operatorStat.getCacheAddSuccessCount(), equalTo(1L));
    // assertThat(operatorStat.getCacheAddExceptionCount(), equalTo(1L));
    // assertThat(operatorStat.getTotalCacheAddTime(), equalTo(25L));
    // assertThat(operatorStat.getCacheDeleteSuccessCount(), equalTo(1L));
    // assertThat(operatorStat.getCacheDeleteExceptionCount(), equalTo(1L));
    // assertThat(operatorStat.getTotalCacheDeleteTime(), equalTo(29L));
    // assertThat(operatorStat.getCacheBatchDeleteSuccessCount(), equalTo(1L));
    // assertThat(operatorStat.getCacheBatchDeleteExceptionCount(), equalTo(1L));
    // assertThat(operatorStat.getTotalCacheBatchDeleteTime(), equalTo(33L));
  }
  
}
