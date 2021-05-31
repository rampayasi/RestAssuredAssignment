package TestNGFrameWork.TestNGFrameWork;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class CaseTwo {



@Test
public void soapexample() throws IOException {
	RestAssured.baseURI = "http://www.dneonline.com";

	FileInputStream fis = new FileInputStream(".\\Payload\\addreq.xml");

	given()
		.headers("content-type","text/xml")
		.body(IOUtils.toString(fis, "UTF-8")).
	when()
		.post("/calculator.asmx").
	then()
	.statusCode(200)
		.log().all();
}
}