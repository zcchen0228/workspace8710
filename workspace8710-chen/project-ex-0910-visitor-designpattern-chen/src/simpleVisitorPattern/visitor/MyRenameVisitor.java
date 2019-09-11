package simpleVisitorPattern.visitor;

import simpleVisitorPattern.part.Body;
import simpleVisitorPattern.part.Break;
import simpleVisitorPattern.part.Engine;
import simpleVisitorPattern.part.Wheel;

public class MyRenameVisitor extends CartPartVisitor {
   public void visit(Wheel part) {
      String newName = "New " + part.getName(); // Suppose a new name is notified.
      // System.out.println("[DBG] Changing the name property to : " + newName);
      part.setName(newName);
   }

   public void visit(Engine part) {
      String newName = "New " + part.getName(); // Suppose a new name is notified.
      // System.out.println("[DBG] Changing the name property to : " + newName);
      part.setName(newName);
   }

   public void visit(Body part) {
      String newName = "New " + part.getName(); // Suppose a new name is notified.
      // System.out.println("[DBG] Changing the name property to : " + newName);
      part.setName(newName);
   }

	@Override
	public void visit(Break breakPart) {
		// TODO Auto-generated method stub
		String newName = "New " + breakPart.getName(); // Suppose a new name is notified.
	    // System.out.println("[DBG] Changing the name property to : " + newName);
		breakPart.setName(newName);
	}
}