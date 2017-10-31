import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class TestModules {

	@BeforeClass
	public static void prepare() {
		
		RestAssured.port = 8080;
		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath = "/FundooNote";
	}
	
	@Test
	@Ignore
	public void send() {
		
		given().get("/sendMail");
	}
}
