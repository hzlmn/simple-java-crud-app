package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.EmploymentCategory;
import model.Person;

public class TablePanel extends JPanel {
	
	private JTable table;
	private PersonTableModel tableModel;
	private JPopupMenu popup;
	private JPopupMenu popup2;
	private PersonTableListener personTableListener;
	
	
	public TablePanel() {
		
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		popup = new JPopupMenu();
		popup2 = new JPopupMenu();
		
		IconRenderer icRen = new IconRenderer();
		icRen.setValue("/images/gear.png");
		
		
		
		table.setDefaultRenderer(EmploymentCategory.class, new EmploymentCategoryRenderer());
		table.setDefaultEditor(EmploymentCategory.class, new EmploymentCategoryEditor());
		table.setDefaultRenderer(ImageIcon.class,icRen);

		table.setRowHeight(24);
		
		
		
		
		JMenuItem removeItem = new JMenuItem("Delete Row");
		JMenuItem selectAllItem = new JMenuItem("Select All");
		popup.add(removeItem);
		popup.add(selectAllItem);
		
		selectAllItem.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				table.selectAll();
				
				}
		});
			
		
		removeItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				if(personTableListener != null)
				{
					personTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
						
			}
			
		});
		
		
		table.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e) {
				
				int row = table.rowAtPoint(e.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row,row );
				if(e.getButton() == MouseEvent.BUTTON3 )
				{
					popup.show(table, e.getX(), e.getY());
				
				}
				
			}
			
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	
	public void setData(List<Person> db) {
		tableModel.setData(db);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
	public void setPersonTableListener(PersonTableListener listener)
	{
		this.personTableListener = listener;
	}
}
