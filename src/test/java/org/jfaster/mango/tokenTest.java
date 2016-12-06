package org.jfaster.mango.parser;
import org.jfaster.mango.binding.InvocationContext;
public class tokenTest{
  @Test
  public void testASTEQnode() throws Exception{
    ASTEQNode i=new ASTEQNode(int).class;
    i.ASTEQNode(1,2);
    i.evaluate(null);
  }
}
