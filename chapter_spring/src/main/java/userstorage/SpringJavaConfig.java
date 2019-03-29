package xmlconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringJavaConfig {

    @Bean
    public UserStorage userStorage(MemoryStorage memoryStorage) {
        return new UserStorage(memoryStorage);
    }

    @Bean
    public MemoryStorage memoryStorage() {
        return new MemoryStorage();
    }

}
