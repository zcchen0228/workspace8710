/**
 * @(#) DeclarationVisitor.java
 */
package visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import model.ModelProvider;
import model.ProgramElement;

/**
 * @since J2SE-1.8
 */
public class DeclarationVisitor extends ASTVisitor {
   private String pkgName;
   private String className;
   private String methodName;

   public DeclarationVisitor() {
   }

   @Override
   public boolean visit(PackageDeclaration pkgDecl) {
      pkgName = pkgDecl.getName().getFullyQualifiedName();
      return super.visit(pkgDecl);
   }

   /**
    * A type declaration is the union of a class declaration and an interface declaration.
    */
   @Override
   public boolean visit(TypeDeclaration typeDecl) {
      className = typeDecl.getName().getIdentifier();
      return super.visit(typeDecl);
   }

   @Override
   public boolean visit(MethodDeclaration methodDecl) {
      methodName = methodDecl.getName().getIdentifier();
      int parmSize = methodDecl.parameters().size();
      Type returnType = methodDecl.getReturnType2();
      boolean isRetVoid = false;
      if (returnType.isPrimitiveType()) {
         PrimitiveType pt = (PrimitiveType) returnType;
         if (pt.getPrimitiveTypeCode().equals(PrimitiveType.VOID)) {
            isRetVoid = true;
         }
      }
      ProgramElement p = new ProgramElement(pkgName, className, methodName, isRetVoid, parmSize);
//      p.setPublic(true);
      boolean isPublic = (methodDecl.getModifiers() & Modifier.PUBLIC) != 0;
      p.setPublic(isPublic);
      ModelProvider.INSTANCE.addProgramElements(p.getPkgName(), p.getClassName(), p.getMethodName(), p.isReturnVoid(), p.getParameterSize());
      ModelProvider.INSTANCE.getProgramElements().get(ModelProvider.INSTANCE.getProgramElements().size() - 1).setPublic(isPublic);;
      return super.visit(methodDecl);
   }
}
