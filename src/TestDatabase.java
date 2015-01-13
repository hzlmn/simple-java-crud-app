import java.sql.SQLException;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;


public class TestDatabase {

	public static void main(String[] args) 
	{
		

		System.out.println("Running database test");
		
		Database db = new Database();
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.addPerson(new Person("Joe222","sdsdsdsd",AgeCategory.Nissan,EmploymentCategory.sklad,"2323",false,Gender.no));
		db.addPerson(new Person("Joe222","sdsdsdsd",AgeCategory.Nissan,EmploymentCategory.sklad,"2323",false,Gender.no));
		db.addPerson(new Person("Suzana","sdssdsdsdd",AgeCategory.Nissan,EmploymentCategory.sklad,"43",true,Gender.no));
		db.addPerson(new Person("qwqw","sd12dd",AgeCategory.Mazda,EmploymentCategory.no,"43",true,Gender.yes));
		db.addPerson(new Person("qwqw","sd12dd",AgeCategory.Mazda,EmploymentCategory.no,"43",true,Gender.yes));
		db.addPerson(new Person("qwasasqw","sd12dd",AgeCategory.Mazda,EmploymentCategory.no,"43",true,Gender.yes));
		
		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			db.load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disconnect();
	}

}
