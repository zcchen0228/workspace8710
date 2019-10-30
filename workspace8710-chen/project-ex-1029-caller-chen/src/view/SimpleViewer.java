/**
 * @file SimpleViewer.java
 */
package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import analysis.ProjectAnalyzerSearchMethodCallers;
import util.SWTResourceManager;
import visitor.ASTVisitorSearchMethodCallers;

/**
 * @since JavaSE-1.8
 */
public class SimpleViewer {
   public final static String VIEWID = "simplebindingproject.partdescriptor.simplecodesearchview";
   private StyledText styledText;
   private Text searchText;
   public String callee;
   public List<String> list;
   public SimpleViewer() {
   }

   @PostConstruct
   public void createControls(Composite parent) {
      GridLayout layout = new GridLayout(2, false);
      parent.setLayout(layout);
      createSearchTextV1(parent);

      // Composite composite = new Composite(parent, SWT.NONE);
      // composite.setLayout(new FillLayout(SWT.HORIZONTAL));
      styledText = new StyledText(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
      styledText.setFont(SWTResourceManager.getFont("Fixedsys", 12, SWT.NORMAL));

      GridData gridData = new GridData();
      gridData.verticalAlignment = GridData.FILL;
      gridData.horizontalSpan = 2;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = GridData.FILL;
      styledText.setLayoutData(gridData);
   }

   public StyledText getStyledText() {
      return styledText;
   }

   public void appendText(String s) {
      this.styledText.append(s);
   }

   public void reset() {
      this.styledText.setText("");
   }

   @PreDestroy
   public void dispose() {
   }

   @Focus
   public void setFocus() {
      this.searchText.setFocus();
   }

   private void createSearchTextV1(Composite parent) {
      Label searchLabel = new Label(parent, SWT.NONE);
      searchLabel.setText("Method Caller Reference Search: ");

      Composite container = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout(1, true);
      container.setLayout(layout);
      GridData gridData = new GridData();
      gridData.horizontalAlignment = GridData.FILL;
      container.setLayoutData(gridData);

      searchText = new Text(container, SWT.BORDER | SWT.SEARCH);
      searchText.setText("foo");
      searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

      searchText.addListener(SWT.KeyDown, new Listener() {
         public void handleEvent(Event e) {
            if (e.keyCode == SWT.CR) {
               reset();
               
               try {
                   ProjectAnalyzerSearchMethodCallers analyzer = new ProjectAnalyzerSearchMethodCallers();
                   analyzer.analyze();
                   List<Map<IMethod, IMethod[]>> calleeCallers = analyzer.getListCallers();
                   list = new ArrayList<>();
                   display2(calleeCallers, searchText.getText());
                } catch (CoreException e1) {
                   e1.printStackTrace();
                }
               String temp = "";
               for (String str : list) {
//            	   System.out.println(str);
            	   temp += str;
               }
               
               // TODO: Update below by referring to HandlerSearchMethodCaller.java
//               styledText.append((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + "\n" + searchText.getText());
               styledText.append((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + "\n" + temp);
            }
         }
      });
//      this.callee = searchText.getText();
      
   }

   public void display(List<Map<IMethod, IMethod[]>> calleeCallers) {
      for (Map<IMethod, IMethod[]> iCalleeCaller : calleeCallers) {
         for (Entry<IMethod, IMethod[]> entry : iCalleeCaller.entrySet()) {
        	 
            IMethod callee = entry.getKey();
            IMethod[] callers = entry.getValue();
            display(callee, callers);
         }
      }
   }

   private void display(IMethod callee, IMethod[] callers) {
      for (IMethod iCaller : callers) {
         String type = callee.getDeclaringType().getFullyQualifiedName();
         String calleeName = type + "." + callee.getElementName();
         System.out.println(calleeName + "!!!!!");
         this.styledText.append("'" + calleeName + //
               "' CALLED FROM '" + iCaller.getElementName() + "'\n");
      }
   }
   
   public void display2(List<Map<IMethod, IMethod[]>> calleeCallers, String calleeNameInput) {
	      for (Map<IMethod, IMethod[]> iCalleeCaller : calleeCallers) {
	         for (Entry<IMethod, IMethod[]> entry : iCalleeCaller.entrySet()) {
	        	 
	            IMethod callee = entry.getKey();
	            IMethod[] callers = entry.getValue();
	            display2(callee, callers, calleeNameInput);
	         }
	      }
	   }
   
   private void display2(IMethod callee, IMethod[] callers, String calleeNameInput) {
	      for (IMethod iCaller : callers) {
	         String type = callee.getDeclaringType().getFullyQualifiedName();
	         String calleeName = type + "." + callee.getElementName();
//	         System.out.println("calleeName " + calleeName + " " + "");
	         if (calleeName.indexOf(calleeNameInput) != -1) {
//	        	 this.styledText.append("'" + calleeName + //
//	  	               "' CALLED FROM '" + iCaller.getElementName() + "'\n");
	  	         this.list.add("'" + calleeName + //
	  		               "' CALLED FROM '" + iCaller.getElementName() + "'\n");
	         }
	         
//	         this.styledText.append("'" + calleeName + //
//	               "' CALLED FROM '" + iCaller.getElementName() + "'\n");
//	         this.list.add("'" + calleeName + //
//		               "' CALLED FROM '" + iCaller.getElementName() + "'\n");
	      }
	   }

}
