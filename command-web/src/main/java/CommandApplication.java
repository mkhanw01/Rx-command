import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by khan on 12/28/17.
 */
@SpringBootApplication(scanBasePackages = "com.waseem.command")
@EnableAsync
@EnableAutoConfiguration
@EnableScheduling
@EnableReactiveMongoRepositories(basePackages = "com.waseem.command.repository", reactiveMongoTemplateRef = "reactiveMongoTemplate")
public class CommandApplication {
  public static void main(String[] args) {
    SpringApplication.run(CommandApplication.class, args);
  }
}
