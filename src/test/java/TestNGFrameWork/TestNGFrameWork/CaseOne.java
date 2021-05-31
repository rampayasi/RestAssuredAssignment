package TestNGFrameWork.TestNGFrameWork;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DataProvider.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import pojopackage.Pojo;

public class CaseOne {
	
	@Test(enabled = true, dataProvider = "ExcelReader")
	public void test1(String petname) throws JsonGenerationException, JsonMappingException, IOException
	{
		//Response obj = RestAssured.post("https://petstore.swagger.io/v2/pet");
		
		RestAssured.baseURI = "https://petstore.swagger.io";
		JSONObject rootbodyobject = new JSONObject();
		JSONObject categoryobject = new JSONObject();
		JSONObject tagsobject = new JSONObject();
		rootbodyobject.put("id", 0);
		rootbodyobject.put("name", petname);
		rootbodyobject.put("status", "available");

		String name = "test";
		categoryobject.put("id", 0);
		categoryobject.put("name", "test");
		


		tagsobject.put("id", 0);
		tagsobject.put("name", name);

		// Adding the Category and Tags object into the Rootbody
		rootbodyobject.put("category", categoryobject);
		
		JSONArray arraybodytag = new JSONArray();
		arraybodytag.add(tagsobject);
		
		
		rootbodyobject.put("tags", arraybodytag);

		// JSON Array Body
		JSONArray arraybody = new JSONArray();
		arraybody.add("abc");
		//arraybody.add("values1");

		// Adding the Arrayobject into Root body
		rootbodyobject.put("photoUrls", arraybody);

		System.out.println(rootbodyobject.toString());
		
		
		//given().body(rootbodyobject.toJSONString()).headers("content-type", "Application/JSON").when().post("pet")
		//.then().log().all();

		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootbodyobject));
		
		given().body(rootbodyobject.toJSONString()).headers("content-type", "Application/JSON").when().post("/v2/pet")
		.then().statusCode(200).log().all();
		
		
		
	}
	
	@DataProvider(name = "ExcelReader")
	public Object[][] exceldata() throws IOException {
		Object[][] data = ExcelReader.gettestdata();

		return data;

	}

}
