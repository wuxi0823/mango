 package org.jfaster.mango.exception;

 import org.jfaster.mango.annotation.Cache;
 import org.jfaster.mango.annotation.CacheBy;
 import org.jfaster.mango.annotation.DB;
 import org.jfaster.mango.annotation.SQL;
 import org.jfaster.mango.operator.Mango;
 import org.jfaster.mango.operator.cache.Day;
 import org.jfaster.mango.operator.cache.LocalCacheHandler;
 import org.jfaster.mango.support.DataSourceConfig;
 import org.junit.Rule;
 import org.junit.Test;
 import org.junit.rules.ExpectedException;

 import java.util.ArrayList;
 import java.util.List;

 /**
  * 测试{@link NotReadablePropertyException}
  *
  * @author ash
  */
 public class NotReadablePropertyException {

     private final static Mango mango = Mango.newInstance(DataSourceConfig.getDataSource());
     static {
         mango.setDefaultLazyInit(true).setDefaultCacheHandler(new LocalCacheHandler());
     }

     @Rule
     public ExpectedException thrown = ExpectedException.none();

     @Test
     public void test2() {
         thrown.expect(NotReadablePropertyException.class);
         thrown.expectMessage("property :1.c is not readable, " +
                 "the type of :1 is class org.jfaster.mango.exception.NotReadablePropertyExceptionTest$A, " +
                 "please check it's get method");
         Dao dao = mango.create(Dao.class);
         dao.add2(new A());
     }

     @Test
     public void test3() {
         thrown.expect(NotReadablePropertyException.class);
         thrown.expectMessage("if use cache and sql has one in clause, property c of " +
                 "class org.jfaster.mango.exception.NotReadablePropertyExceptionTest$A " +
                 "expected readable but not");
         Dao2 dao = mango.create(Dao2.class);
         dao.gets(new ArrayList<Integer>());
     }

     @DB
     static interface Dao {
         @SQL("insert into user(uid) values (:1.b.d)")
         public int add(A a);

         @SQL("insert into user(uid) values (:1.c.d)")
         public int add2(A a);
     }

     @DB
     @Cache(prefix = "dao2_", expire = Day.class)
     static interface Dao2 {
         @SQL("select ... where c in (:1)")
         public List<A> gets(@CacheBy List<Integer> ids);
     }

     static class A {
         B b;

         public B getB() {
             return b;
         }
     }

     static class B {
         C c;

         public C getC() {
             return c;
         }
     }

     static class C {

     }

 }
