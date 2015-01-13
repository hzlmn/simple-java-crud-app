package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.alee.laf.WebLookAndFeel;

import controller.Controller;

public class MainFrame extends JFrame {

	
	private static final long serialVersionUID = -7906808792207756237L;
	private TextPanel textPanel;
	private TextPanel textPanel2;
	private TextPanel2 textPanel3;
	private TextPanel2 textPanel4;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private JTabbedPane tabbedPane;
	private JTabbedPane tabbedPane2;
	private JPopupMenu popup;
	
	private int pos  = 2;
	private int number = 2;
	

	public MainFrame() {
		
		super("Course work");
		
		
	
		try
		{
		    // Setting up WebLookAndFeel style
		    UIManager.setLookAndFeel ( WebLookAndFeel.class.getCanonicalName () );
		}
		catch ( Throwable e )
		{
		    // Something went wrong
		}
	
			
		

		setLayout(new BorderLayout());
		
		

		toolbar = new Toolbar();
		
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		
		popup = new JPopupMenu();
		
		
		JMenuItem removeItem = new JMenuItem("Закрыть");
		popup.add(removeItem);
		
		
		removeItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				
				CloseLastText();
						
			}
			
		});
		
		tabbedPane2 = new JTabbedPane();
		tabbedPane = new JTabbedPane();
           
        getContentPane().add(tabbedPane);
		getContentPane().add(tabbedPane);
		
		textPanel = new TextPanel();
		textPanel2 = new TextPanel();
		textPanel3 = new TextPanel2();
		textPanel4 = new TextPanel2();
		
		
		tabbedPane.addTab(" Console " , textPanel);
		tabbedPane.addTab(" Errors " , textPanel2);
		tabbedPane.addTab(" Messages " , textPanel3);
		tabbedPane.setIconAt(0, createIcon("/images/1400444918_application_view_xp_terminal.png"));
		tabbedPane.setIconAt(1, createIcon("/images/1400445120_Error.png"));
		tabbedPane.setIconAt(2, createIcon("/images/1400445243_text_horizontalrule.png"));
		
		
		tabbedPane2.addTab(" Талица  ", tablePanel);
		tabbedPane2.addTab(" Блокнот ", textPanel4);
		tabbedPane2.setIconAt(0, createIcon("/images/1400446085_Table_16x16.png"));
		tabbedPane2.setIconAt(1, createIcon("/images/notepad.gif"));
		
		tabbedPane2.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e) {
				
				
				
				
				if(e.getButton() == MouseEvent.BUTTON3 )
				{
					popup.show(tabbedPane2, e.getX(), e.getY());
				
				}
				
			}
			
		});
		
		textPanel4.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e) {
				
				
				
				
				if(e.getButton() == MouseEvent.BUTTON3 )
				{
					popup.show(tabbedPane2, e.getX(), e.getY());
				
				}
				
			}
			
		});
		
		
		controller = new Controller();
		
		tablePanel.setData(controller.getPeople());
		tablePanel.setPersonTableListener(new PersonTableListener()
		{
			public void rowDeleted(int row)
			{
				controller.removePerson(row);
			}
			
		});
	
		
		fileChooser = new JFileChooser();
		
		fileChooser.addChoosableFileFilter(new PersonFileFilter());

		setJMenuBar(createMenuBar());
		
		
		addFormEvent();
		
		textPanel3.AddKeyListenerRevelance(new KeyAdapter()
		{
			 public void  keyPressed(KeyEvent e) {
	            	if(e.getKeyCode() == KeyEvent.VK_ENTER)
	            	{
	            		if(textPanel3.getTextContains("%CLEAR%"))
	            		{
	            			
	            			textPanel3.DeleteText();
	            			textPanel.appendText("Messages window is clear");
	            			System.out.println("OK");
	            		
	            				
	            		}
	            		else
	            		{
	            			System.out.println("чистое поле");
	            		}
	            	}
	            	else
	            	{
	            		
	            	}
	            }
		});
		
		textPanel3.AddKeyListenerRevelance(new KeyAdapter()
		{
			 public void  keyPressed(KeyEvent e) {
	            	if(e.getKeyCode() == KeyEvent.VK_ENTER)
	            	{
	            		if(textPanel3.getTextContains("%NEW_TPANEL%"))
	            		{
	            			
	            			textPanel3.DeleteText();
	            			 CreateNewTable(new TextPanel2());
	            			
	            			 textPanel.appendText("New text panel created...");
	            		
	            				
	            		}
	            		else
	            		{
	            			System.out.println("чистое поле");
	            		}
	            	}
	            	else
	            	{
	            		
	            	}
	            }
		});
		
		toolbar.setToolbarListener(new ToolbarListener() {
			public void saveEventOccured() {
				
				
				connect();
				
				
				try {
					controller.connect();
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(MainFrame.this, "Can not connect to database","Database connection problems",JOptionPane.ERROR_MESSAGE);
				}
				try {
					controller.save();
				} catch (SQLException e) {
					
					JOptionPane.showMessageDialog(MainFrame.this, "Can not connect to database","Database connection problems",JOptionPane.ERROR_MESSAGE);
				}
			
				textPanel.appendText("Added to database\n");
				
				
			}

			public void refreshEventOccured() {
				
				connect();
				try {
					controller.load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Are refresh", "Refreshing progress", JOptionPane.ERROR_MESSAGE);
				}
				tablePanel.refresh();
				textPanel.appendText("Database is refreshed\n");
			}
		});
		
		///////////////////////////////
		

		add(formPanel, BorderLayout.WEST);
		add(toolbar, BorderLayout.NORTH);
		add(tabbedPane2, BorderLayout.CENTER);
		add(tabbedPane, BorderLayout.SOUTH);
	

		setMinimumSize(new Dimension(600, 550));
		setSize(800, 650);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		refresh();
	}
	private void connect()
	{
		try {
			controller.connect();
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(MainFrame.this, "Can not connect to database","Database connection problems",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void refresh()
	{
		connect();
		try {
			controller.load();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Are refresh", "Refreshing progress", JOptionPane.ERROR_MESSAGE);
		}
		tablePanel.refresh();
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		exportDataItem.setIcon(createIcon("/images/1400444602_table-export.png"));
		importDataItem.setIcon(createIcon("/images/1400444586_import.png"));

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu paramMenu = new JMenu("Помощь");
		JMenuItem aboutItem = new JMenuItem("О программе");
		aboutItem.setIcon(createIcon("/images/1400445676_Help.png"));
		JMenuItem licenseItem = new JMenuItem("License");
		
		paramMenu.add(aboutItem);
		paramMenu.add(licenseItem);
		
		aboutItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
			 
				
				JOptionPane.showMessageDialog(MainFrame.this, "Created by KN NTU","О программе",1);
				
			
				
			}
			
		});
		
		

		JMenu windowMenu = new JMenu("Window");
		
		JMenu showMenu = new JMenu("Show");

		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Add detail");
		showFormItem.setIcon(createIcon("/images/1400445557_Windows_16x16.png"));
		JCheckBoxMenuItem showConsole = new JCheckBoxMenuItem("Console");
		showConsole.setIcon(createIcon("/images/1400445557_Windows_16x16.png"));
		showFormItem.setSelected(true);
		showConsole.setSelected(true);

		showMenu.add(showFormItem);
		showMenu.add(showConsole);
		windowMenu.add(showMenu);

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		menuBar.add(paramMenu);

		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();

				formPanel.setVisible(menuItem.isSelected());
			}
		});
		showConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();

				tabbedPane.setVisible(menuItem.isSelected());
			}
		});

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						
						JOptionPane.showMessageDialog(MainFrame.this, "Failed load","Error",JOptionPane.ERROR_MESSAGE);
					}
				
				}
			}
		});
		
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						
						JOptionPane.showMessageDialog(MainFrame.this, "Failed load","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the application?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		return menuBar;
	}
	private ImageIcon createIcon(String path)
	{
		URL url = getClass().getResource(path);
		
		if(url == null)
		{
			System.err.println("Unabled to load image"+path);
		}
		
		ImageIcon icon = new ImageIcon(url);
		
		return icon;
	}
	private void addFormEvent()
	{
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				controller.addPerson(e);
				tablePanel.refresh();
				textPanel.appendText("Writting proccess...\n");
			}
		});
		formPanel.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				
			}
		});
		
	}
	private void CreateNewTable(TextPanel2 text)
	{
		
		 
		tabbedPane2.addTab(" Блокнот "+ number, text);
		tabbedPane2.setIconAt(pos, createIcon("/images/notepad.gif"));
		pos++;
		number++;
	
		
	}
	private void CloseLastText()
	{
		int index = pos -1;
		tabbedPane2.remove(index);
		pos--;
	}

}
