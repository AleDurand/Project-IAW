package project;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.robwin.swagger2markup.Swagger2MarkupConverter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjectApplication.class)
@WebIntegrationTest
public class Swagger2MarkupTest {

	@Test
	public void convertRemoteSwaggerToAsciiDoc() {
		try {
			Swagger2MarkupConverter.from("http://localhost:8080/swagger.json").build()
					.intoFolder("src/docs/asciidoc/generated");
			// Then validate that three AsciiDoc files have been created
			String[] files = new File("src/docs/asciidoc/generated").list();
			Assert.assertTrue(files.length == 3);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}