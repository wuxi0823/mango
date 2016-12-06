package org.jfaster.mango;

 import org.jfaster.mango.annotation.*;
 import org.jfaster.mango.operator.Mango;
 import org.jfaster.mango.operator.cache.Day;
 import org.jfaster.mango.operator.cache.LocalCacheHandler;
 import org.jfaster.mango.support.DataSourceConfig;
 import org.jfaster.mango.support.Randoms;
 import org.jfaster.mango.support.Table;
 import org.jfaster.mango.support.model4table.Msg;
 import org.junit.Before;
 import org.junit.Test;

 import javax.sql.DataSource;
 import java.sql.Connection;
 import java.util.ArrayList;
 import java.util.List;

 import static org.hamcrest.CoreMatchers.nullValue;
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.contains;
 import static org.hamcrest.Matchers.hasSize;
 
 public class CacheEmptyListTest {

     private final static DataSource ds = DataSourceConfig.getDataSource();
     private final static Mango mango = Mango.newInstance(ds);

     @Before
     public void before() throws Exception {
         Connection conn = ds.getConnection();
         Table.MSG.load(conn);
         conn.close();
     }

     @SuppressWarnings("unchecked")
     @Test
     public void testSingleKeyReturnList() throws Exception {
         LocalCacheHandler cacheHandler = new LocalCacheHandler();
         List<Msg> msgs = new ArrayList<Msg>();
         MsgDao dao = mango.create(MsgDao.class, cacheHandler);
         int uid = 100;
         String key = getMsgKey(uid);

         List<Msg> actual = dao.getMsgs(uid);
         assertThat(actual, hasSize(0));
         assertThat(cacheHandler.get(key), nullValue());

         Msg msg = createRandomMsg(uid);
         msgs.add(msg);
         msg.setId(dao.insert(msg));

         actual = dao.getMsgs(uid);
         assertThat(actual, hasSize(msgs.size()));
         assertThat(actual, contains(msgs.toArray()));
         List<Msg> cacheActual = (List<Msg>) cacheHandler.get(key);
         assertThat(cacheActual, hasSize(msgs.size()));
         assertThat(cacheActual, contains(msgs.toArray()));


         msg = msgs.remove(0);
         dao.delete(msg.getUid(), msg.getId());
         actual = dao.getMsgs(uid);
         assertThat(actual, hasSize(0));
         assertThat(cacheHandler.get(key), nullValue());
     }

     private String getMsgKey(int uid) {
         return "msg_" + uid;
     }

     private Msg createRandomMsg(int uid) {
         String content = Randoms.randomString(20);
         Msg msg = new Msg();
         msg.setUid(uid);
         msg.setContent(content);
         return msg;
     }

     @DB
     @Cache(prefix = "msg", expire = Day.class, cacheEmptyList = false)
     interface MsgDao {

         @ReturnGeneratedId
         @SQL("insert into msg(uid, content) values(:m.uid, :m.content)")
         public int insert(@CacheBy("uid") @Rename("m") Msg msg);

         @SQL("delete from msg where uid=:1 and id=:2")
         public int delete(@CacheBy int uid, int id);

         @SQL("select id, uid, content from msg where uid=:1 order by id")
         public List<Msg> getMsgs(@CacheBy int uid);

     }
   }
