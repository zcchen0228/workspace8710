package simpleVisitorPattern.visitor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import simpleVisitorPattern.part.Body;
import simpleVisitorPattern.part.Break;
import simpleVisitorPattern.part.Engine;
import simpleVisitorPattern.part.Wheel;



public class MyFileSaveVisitor extends CartPartVisitor {
	public int count = 0;
	@Override
	public void visit(Break breakPart) {
		try {
			writeFileHelper(breakPart.getName()+","
					+breakPart.getModelNumberBreak()+","+breakPart.getModelYearBreak());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(Wheel wheel) {
		try {
			writeFileHelper(wheel.getName()+","
					+wheel.getModelNumberWheel()+","+wheel.getModelYearWheel());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(Engine engine) {
		try {
			writeFileHelper(engine.getName()+","
					+engine.getModelNumberEngine()+","+engine.getModelYearEngine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(Body body) {
		try {
			writeFileHelper(body.getName()+","
					+body.getModelNumberBody()+","+body.getModelYearBody());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFileHelper(String input) throws IOException {
//		BufferedWriter writer = new BufferedWriter(
//                new FileWriter("outputdata.csv", true)  //Set true for append mode
//            );
//		writer.newLine();   //Add new line
//		writer.write(input);
//		writer.close();
//		
		FileWriter fileWriter = new FileWriter("outputdata.csv", true); //Set true for append mode
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(input);
	    printWriter.close();
	}

}
