package xmlconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("xmlconfiguration")
public class SpringComplexConfig {

    @Bean
    public UserStorage userStorage(MemoryStorage memoryStorage) {
        return new UserStorage(memoryStorage);
    }
}
