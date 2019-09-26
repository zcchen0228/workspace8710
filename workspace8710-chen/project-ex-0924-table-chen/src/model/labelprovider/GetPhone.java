package model.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import model.Person;

public class GetPhone extends ColumnLabelProvider  {
	@Override
	   public String getText(Object element) {
	      Person p = (Person) element;
	      return p.getPhone();
	   }
}
