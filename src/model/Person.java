package model;

import java.io.Serializable;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

public class Person extends DefaultTableCellRenderer implements Serializable
{
	private static final long serialVersionUID = 4022843652518158650L;

	private static int count = 1;
	
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EmploymentCategory empCat;
	private String taxId;
	private boolean usCitizen;
	private Gender gender;
	private ImageIcon personIcon;
	
	public Person(String name, String occupation, AgeCategory ageCategory,
			EmploymentCategory empCat, String taxId,
			boolean usCitizen, Gender gender) {
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.empCat = empCat;
		this.taxId = taxId;
		this.usCitizen = usCitizen;
		this.gender = gender;
		
		this.id = count;
		count++;
	}
	
	public Person(int id, String name, String occupation, AgeCategory ageCategory,
			EmploymentCategory empCat, String taxId,
			boolean usCitizen, Gender gender) {
		
		this(name, occupation, ageCategory, empCat, taxId, usCitizen, gender);
		
		this.id = id;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	public EmploymentCategory getEmpCat() {
		return empCat;
	}
	public void setEmpCat(EmploymentCategory empCat) {
		this.empCat = empCat;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public boolean isUsCitizen() {
		return usCitizen;
	}
	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String toString()
	{
		return id + "Name:  "+ name;
	}

	public ImageIcon getPersonIcon() {
		
		personIcon = new ImageIcon(new String("/images/car.png"));
		return personIcon;
	}
	
	public void setPersonIcon(ImageIcon personIcon) {
		this.personIcon = personIcon;
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
