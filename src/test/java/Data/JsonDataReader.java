package Data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonDataReader 
{
	public String firstname, lastname , email , password , phone ,invalidPass, invalidEmail; 

	public void JsonReader() throws IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/Data/UserData.json";

		File srcFile = new File(filePath); 

		JSONParser parser = new JSONParser(); 
		JSONArray jarray = (JSONArray)parser.parse(new FileReader(srcFile)); 

		for(Object jsonObj : jarray) 
		{
			JSONObject person = (JSONObject) jsonObj ; 
			firstname  = (String) person.get("firstname"); 
			System.out.println(firstname);

			lastname = (String) person.get("lastname"); 
			System.out.println(lastname);

			email = (String) person.get("email"); 
			System.out.println(email);

			phone = (String) person.get("phone"); 
			System.out.println(phone);

			password = (String) person.get("password"); 
			System.out.println(password);
			
			invalidPass = (String) person.get("invalidPass"); 
			System.out.println(invalidPass);
			
			invalidEmail = (String) person.get("invalidEmail"); 
			System.out.println(invalidEmail);

		}

	}

}
