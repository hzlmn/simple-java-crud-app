package gui;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import model.EmploymentCategory;
import model.Person;

public class PersonTableModel extends AbstractTableModel {
	
	private List<Person> db;
	
	private String[] colNames = {"ID", "Имя", "Цена", "Автомобиль", "Наличие", "Импорт", "Код","Picture"};
	
	private int colLength = colNames.length;
	
	
	



	public PersonTableModel() {
	}
	
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}



	public void setData(List<Person> db) {
		this.db = db;
	}
	@Override
	public Class<?> getColumnClass(int col) {
		
		switch(col) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return EmploymentCategory.class;
		case 5:
			return Boolean.class;
		case 6:
			return String.class;
		case 7 : 
			return ImageIcon.class;
		
			default:
				return null;
		
		
		}
		
	}
	
	
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		
		if (db == null) return;
		Person person = db.get(row);
		switch(col)
		{
		case 1:
			person.setName((String)value);
			break;
		case 2:
			person.setOccupation((String)value);
			break;
		case 4:
			person.setEmpCat((EmploymentCategory)value);
			break;
		case 5:
			person.setUsCitizen((Boolean)value);
			break;
		default:
			return ;
		}
	}
	@Override
	public boolean isCellEditable(int row, int col) {
		
		switch(col)
		{
		case 1:
			return true;
		case 2:
			return true;
		case 5:
			return true;
		case 4:
			return true;
		default:
			return false;
		}
	}

	@Override
	public int getColumnCount() {
		return colLength;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get(row);
		
		switch(col) {
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2:
			return person.getOccupation();
		case 3:
			return person.getAgeCategory();
		case 4:
			return person.getEmpCat();
		case 5:
			return person.isUsCitizen();
		case 6:
			return person.getTaxId();
		
		
		
		
		}
		
		return null;
	}

}
