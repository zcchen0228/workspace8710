/**
 * @(#) MethodLabelProvider.java
 */
package view.provider;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jface.viewers.StyledString;

import model.MethodElement;

/**
 * @since J2SE-1.8
 */
public class LocationLebelProvider extends ProgElemLabelProvider {

   @Override
   public StyledString getStyledText(Object element) {
      if (element instanceof MethodElement) {
          // return new StyledString(((MethodDeclaration) element).getStartPosition());
    	  return new StyledString(""+((MethodElement) element).getLocation());
      }
      return new StyledString(""); // super.getStyledText(element);
   }
}
