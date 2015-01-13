package controller;

import gui.FormEvent;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	Database db = new Database();
	
	public List<Person> getPeople() {
		return db.getPeople();
	}
	
	public void save() throws SQLException
	{
		db.save();
	}
	
	public void load() throws SQLException
	{
		db.load();
	}
	public void connect() throws Exception
	{
		db.connect();
	}
	
	public void disconnect()
	{
		db.disconnect();
	}
	
	
	
	public void addPerson(FormEvent ev) {
		String name = ev.getName();
		String occupation = ev.getOccupation();
		int ageCatId = ev.getAgeCategory();
		String empCat = ev.getEmploymentCategory();
		boolean isUs = ev.isUsCitizen();
		String taxId = ev.getTaxId();
		String gender = ev.getGender();
		
		AgeCategory ageCategory = null;
		
		switch(ageCatId) {
		case 0:
			ageCategory = AgeCategory.Nissan;
			break;
		case 1:
			ageCategory = AgeCategory.Mazda;
			break;
		case 2:
			ageCategory = AgeCategory.Land_Rover;
			break;
		case 3:
			ageCategory = AgeCategory.Lada;
			break;
		case 4:
			ageCategory = AgeCategory.Honda;
			break;
		
		}
		
		
		EmploymentCategory empCategory;
		
		if(empCat.equals("есть")) {
			empCategory = EmploymentCategory.yes;
		}
		else if(empCat.equals("нет")) {
			empCategory = EmploymentCategory.no;
		}
		else if(empCat.equals("склад")) {
			empCategory = EmploymentCategory.sklad;
		}
		else {
			empCategory = EmploymentCategory.other;
			System.err.println(empCat);
		}
		
		Gender genderCat;
		
		if(gender.equals("yes")) {
			genderCat = Gender.yes;
		}
		else {
			genderCat = Gender.no;
		}
		
		Person person = new Person(name, occupation, ageCategory, empCategory, 
				taxId, isUs, genderCat);
		
		db.addPerson(person);
	}
	public void removePerson(int index)
	{
		db.removePerson(index);
	}
	
	public void saveToFile(File file) throws IOException
	{
		db.saveToFile(file);
	}
	public void loadFromFile(File file) throws IOException
	{
		db.loadFromFile(file);
	}
	
}
