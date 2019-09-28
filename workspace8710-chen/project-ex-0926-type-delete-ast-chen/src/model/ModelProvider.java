/**
 * @(#) ModelProvider.java
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @since J2SE-1.8
 */
public enum ModelProvider {
   INSTANCE;
   private List<ProgramElement> progElements = new ArrayList<ProgramElement>();;

   public void addProgramElements(String pkgName, String className, String methodName, boolean isRetVoid, int parmSize) {
	   ProgramElement p = new ProgramElement(pkgName, className, methodName, isRetVoid, parmSize);
	   
//	   p.setPublic(true);
      progElements.add(p);
   }

   public List<ProgramElement> getProgramElements() {
      return progElements;
   }

   public void clearProgramElements() {
      progElements.clear();
   }
}
