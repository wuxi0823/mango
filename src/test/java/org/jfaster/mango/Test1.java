import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.operator.Mango;
import org.jfaster.mango.support.DataSourceConfig;
import org.jfaster.mango.support.Table;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class Test1 {
private final static DataSource ds = DataSourceConfig.getDataSource();
private final static Mango mango = Mango.newInstance(ds);
private final static ByteInfoDao dao = mango.create(ByteInfoDao.class);
@Before
public void before() throws Exception {
Connection conn = ds.getConnection();
Table.BYTE_INFO.load(conn);
conn.close();
}
@Test
public void testByteInfo() {
byte[] arrayByte = new byte[]{1, 2, 3};
byte singleByte = 10;
dao.insert(arrayByte, singleByte);
int id = dao.insert(arrayByte, singleByte);
assertThat(Arrays.toString(dao.getArrayByte(id)), equalTo(Arrays.toString(arrayByte)));
for (Byte b : dao.getByteSingles(singleByte)) {
 assertThat(b, equalTo(singleByte));
}
}
@DB
interface ByteInfoDao {
  @ReturnGeneratedId
 @SQL("insert into byte_info(array_byte, single_byte) values(:1, :2)")
 public int insert(byte[] arrayByte, byte singleByte);
 @SQL("select array_byte from byte_info where id=:1")
 public byte[] getArrayByte(int id);
 @SQL("select single_byte from byte_info where single_byte=:1")
  public Byte[] getByteSingles(int singByte);

 }
 }
