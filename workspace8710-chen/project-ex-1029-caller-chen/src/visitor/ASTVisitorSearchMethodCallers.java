/**
 * @file ASTVisitorMethodFinder.java
 */
package visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.MethodReferenceMatch;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;

/**
 * @since JavaSE-1.8
 */
public class ASTVisitorSearchMethodCallers extends ASTVisitor {
   private IPackageFragment[] packages;
   private Map<IMethod, IMethod[]> dataCallers = null;
   private String callee;
   
   public String getCallee() {
	return callee;
}

public void setCallee(String callee) {
	this.callee = callee;
}

public ASTVisitorSearchMethodCallers(IPackageFragment[] p) {
      this.packages = p;
      dataCallers = new HashMap<>();
   }

   @Override
   public boolean visit(MethodDeclaration node) {
      IJavaElement methodDecl = node.resolveBinding().getJavaElement();
//      System.out.println(methodDecl.getElementName());
      if (methodDecl.equals(this.callee)) System.out.println("continue");
      if (methodDecl instanceof IMethod) {
         IMethod callee = (IMethod) methodDecl;
         List<IMethod> callers = getCallerMethods(callee, packages);
         dataCallers.put(callee, callers.toArray(new IMethod[0]));
      }
      return true;
   }

   List<IMethod> getCallerMethods(IMethod method, IPackageFragment[] packages) {
      SearchPattern pattern = SearchPattern.createPattern(method, IJavaSearchConstants.REFERENCES);
      CallerMethodsSearchRequestor requestor = new CallerMethodsSearchRequestor();
      SearchEngine searchEngine = new SearchEngine();
      try {
         IJavaSearchScope searchScope = SearchEngine.createJavaSearchScope(packages);
         searchEngine.search(pattern, //
               new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() }, //
               searchScope, requestor, null);
         return requestor.getCallerMethods();
      } catch (CoreException e) {
         e.printStackTrace();
      }
      return null;
   }

   class CallerMethodsSearchRequestor extends SearchRequestor {
      private List<IMethod> callerMethods = null;

      public CallerMethodsSearchRequestor() {
         super();
         this.callerMethods = new ArrayList<IMethod>();
      }

      public List<IMethod> getCallerMethods() {
         return this.callerMethods;
      }

      @Override
      public void acceptSearchMatch(SearchMatch match) throws CoreException {
         if (match instanceof MethodReferenceMatch) {
            MethodReferenceMatch methodMatch = (MethodReferenceMatch) match;
            if (methodMatch.getElement() instanceof IMethod) { // do not regard BinaryType objects here
               IMethod method = (IMethod) methodMatch.getElement();

               this.callerMethods.add(method);
            }
         }
      }
   }

   public Map<IMethod, IMethod[]> getDataCallers() {
      return dataCallers;
   }
}
