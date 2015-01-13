package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class TextPanel2 extends JPanel {
	
	private JTextArea textArea;
	private JPopupMenu popup;
	
	public TextPanel2() {
		
		Dimension dim = getPreferredSize();
		dim.height = 75;
		setPreferredSize(dim);
		textArea = new JTextArea();
		popup = new JPopupMenu();
		
		JMenuItem removeItem = new JMenuItem("Очистить");
		popup.add(removeItem);
		
		removeItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				
				DeleteText();
						
			}
			
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	public void appendText(String text) {
		textArea.append(text);
	}
	public boolean getTextContains(String text)
	{
		if(textArea.getText().contains(text))
		{
			
			return true;
		}
		else
		{
			return false;
		}
		
	}
	public void DeleteText()
	{
		textArea.setText(null);
	}
	public void AddKeyListenerRevelance(KeyAdapter listener)
	{
		textArea.addKeyListener(listener);
	}
	
	

}