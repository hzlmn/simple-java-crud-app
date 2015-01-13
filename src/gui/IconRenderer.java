package gui;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

public class IconRenderer extends DefaultTableCellRenderer {
	  public IconRenderer() { super(); }

	  public void setValue(String value) {
	    if (value == null) {
	      setText("");
	    }
	    else
	    {
	    	
	      setIcon(createIcon(value));
	    }
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
}
