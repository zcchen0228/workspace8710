package simpleVisitorPattern.visitor;

import simpleVisitorPattern.part.Body;
import simpleVisitorPattern.part.Break;
import simpleVisitorPattern.part.Engine;
import simpleVisitorPattern.part.Wheel;

public class MyAccessVisitor extends CartPartVisitor {
	public void visit(Wheel part) {
		System.out.println("[DBG] Accessing the name property: " + part.getName());
	}

	public void visit(Engine part) {
		System.out.println("[DBG] Accessing the name property: " + part.getName());
	}

	public void visit(Body part) {
		System.out.println("[DBG] Accessing the name property: " + part.getName());
	}

	public void visit(Break breakPart) {
		System.out.println("[DBG] Accessing the name property: " + breakPart.getName());
	}
}