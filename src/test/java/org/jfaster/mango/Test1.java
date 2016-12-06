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
    OperatorStat os = new OperatorStat;
  ExtendStat es = new ExtendStat(os);
}
