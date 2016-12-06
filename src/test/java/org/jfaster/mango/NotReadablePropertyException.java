 package org.jfaster.mango.reflect;
 
 import org.junit.Test;
 
 import java.io.Serializable;
 import java.util.*;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 import static org.hamcrest.Matchers.is;
 
 /**
  * @author ash
  */
 public class TypeTokenTest {
 
     private abstract static class StringList implements List<String> {}
 
     @Test
     public void testGetType() throws Exception {
         TypeToken<List<String>> token = new TypeToken<List<String>>() {};
         assertThat(token.getType(), equalTo(StringList.class.getGenericInterfaces()[0]));
 
         TypeToken<String> token2 = new TypeToken<String>() {};
         assertThat(token2.getType().equals(String.class), is(true));
     }
 
     @Test
     public void testGetRawType() throws Exception {
         TypeToken<List<String>> token = new TypeToken<List<String>>() {};
         assertThat(token.getRawType().equals(List.class), is(true));
 
         TypeToken<String> token2 = new TypeToken<String>() {};
         assertThat(token2.getRawType().equals(String.class), is(true));
     }
 
     @Test
     public void testOf() throws Exception {
         TypeToken<String> token = TypeToken.of(String.class);
         assertThat(token.getType().equals(String.class), is(true));
         assertThat(token.getRawType().equals(String.class), is(true));
     }
 
     @Test
     public void testResolveType() throws Exception {
         TypeToken<HashMap<String, Integer>> mapToken = new TypeToken<HashMap<String, Integer>>() {};
         TypeToken<?> entrySetToken = mapToken.resolveType(Map.class.getMethod("entrySet").getGenericReturnType());
         assertThat(entrySetToken.toString(), equalTo("java.util.Set<java.util.Map.java.util.Map$Entry<java.lang.String, java.lang.Integer>>"));
     }
 
     @Test
     public void testGetTypes() throws Exception {
         TypeToken<HashMap<String,Integer>> t = new TypeToken<HashMap<String, Integer>>() {
         };
         Set<TypeToken<?>> types = t.getTypes();
         assertThat(types.size(), equalTo(6));
         types.contains(new TypeToken<Map<String, Integer>>() {
         });
         types.contains(new TypeToken<HashMap<String, Integer>>() {
         });
         types.contains(new TypeToken<AbstractMap<String, Integer>>() {});
         types.contains(TypeToken.of(Cloneable.class));
         types.contains(TypeToken.of(Serializable.class));
         types.contains(TypeToken.of(Object.class));
     }
 
 }
