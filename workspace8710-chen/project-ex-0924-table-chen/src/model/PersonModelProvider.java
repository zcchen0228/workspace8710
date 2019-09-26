package model;

import java.util.ArrayList;
import java.util.List;

import util.UtilFile;

public enum PersonModelProvider {
   INSTANCE;

   private List<Person> persons;
   private static List<Person> persons2;

   private PersonModelProvider() {
      persons = new ArrayList<Person>();
      persons.add(new Person("Rainer", "Zufall", "male", true));
      persons.add(new Person("Reiner", "Babbel", "male", true));
      persons.add(new Person("Marie", "Dortmund", "female", false));
      persons.add(new Person("Holger", "Adams", "male", true));
      persons.add(new Person("Juliane", "Adams", "female", true));
   }

   
   
   public List<Person> getPersons2() {
	   persons2 = new ArrayList<Person>();
	      persons2 = new ArrayList<Person>();
	      persons2.add(new Person("Emma", "Smith", "402-111-1111", "emmasmith@email.com"));
	      persons2.add(new Person("Olivia", "Johnson", "402-111-2222", "oliviajohnson@email.com"));
	      persons2.add(new Person("Liam", "Williams", "402-111-3333", "liamwilliams@email.com"));    
//	  List<String> list = UtilFile.readFile("input2.csv");
//	  for (String str : list) {
//		  String[] p2 = str.split(",");
//		  persons2.add(new Person(p2[0], p2[1], p2[2], p2[3]));
//	  }
	  
	  return persons2;
   }
   
//   public static void main(String[] args) {
//	   INSTANCE.getPersons2();
//	   for (Person p : persons2) {
//		   System.out.println(p.toString());
//	   }
//   }
   
   
   public List<Person> getPersons() {
      return persons;
   }
}
