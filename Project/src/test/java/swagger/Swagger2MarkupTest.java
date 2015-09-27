package swagger;

import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.ProjectApplication;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjectApplication.class)
@WebIntegrationTest
public class Swagger2MarkupTest {

    @Test
    public void convertRemoteSwaggerToAsciiDoc() {
        try {
            Swagger2MarkupConverter.from("http://localhost:8080/swagger.json")
                    .build()
                    .intoFolder("src/docs/asciidoc/generated");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}