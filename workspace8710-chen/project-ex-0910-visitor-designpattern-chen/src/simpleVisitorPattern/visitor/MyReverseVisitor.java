package simpleVisitorPattern.visitor;

import simpleVisitorPattern.part.Body;
import simpleVisitorPattern.part.Break;
import simpleVisitorPattern.part.Engine;
import simpleVisitorPattern.part.Wheel;

public class MyReverseVisitor extends CartPartVisitor {
	@Override
	public void visit(Break breakPart) {
		breakPart.setModelNumberBreak(reverseHelper(breakPart.getModelNumberBreak()));
		breakPart.setModelYearBreak(reverseHelper(breakPart.getModelYearBreak()));
		breakPart.setName(reverseHelper(breakPart.getName()));
	}

	@Override
	public void visit(Wheel wheel) {
		wheel.setModelNumberWheel(reverseHelper(wheel.getModelNumberWheel()));
		wheel.setModelYearWheel(reverseHelper(wheel.getModelYearWheel()));
		wheel.setName(reverseHelper(wheel.getName()));
	}

	@Override
	public void visit(Engine engine) {
		engine.setModelNumberEngine(reverseHelper(engine.getModelNumberEngine()));
		engine.setModelYearEngine(reverseHelper(engine.getModelYearEngine()));
		engine.setName(reverseHelper(engine.getName()));
	}

	@Override
	public void visit(Body body) {
		body.setModelNumberBody(reverseHelper(body.getModelNumberBody()));
		body.setModelYearBody(reverseHelper(body.getModelYearBody()));
		body.setName(reverseHelper(body.getName()));
	}
	
	public String reverseHelper(String s) {
		String str = "";
		
		for (int i = s.length() - 1; i >= 0; i--) {
			str += s.charAt(i);
		}
		return str;
	}
}
