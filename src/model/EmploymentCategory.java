package model;

public enum EmploymentCategory {
	yes("есть"),
	no("нет"),
	sklad("склад"),
	other("другое");
	
	private String text;
	private EmploymentCategory(String text)
	{
		this.text = text;
		
	}
	@Override
	public String toString() {
		return text;
	}
}
