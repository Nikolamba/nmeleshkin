package userstorage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenLoadXMLContextShouldGetBeans() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-xml-context.xml");
        UserStorage memory = context.getBean(UserStorage.class);
        memory.add(new User());
        assertNotNull(memory);
    }

    @Test
    public void whenLoadAnnotationsContextShouldGetBeans() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-annotations-context.xml");
        UserStorage memory = context.getBean(UserStorage.class);
        memory.add(new User());
        assertNotNull(memory);
    }

    @Test
    public void whenLoadJavaConfigContextShouldGetBeans() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfig.class);
        UserStorage memory = context.getBean(UserStorage.class);
        memory.add(new User());
        assertNotNull(memory);
    }

    @Test
    public void whenLoadComplexContextShouldGetBeans() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringComplexConfig.class);
        MemoryStorage memory = context.getBean(MemoryStorage.class);
        memory.add(new User());
        assertNotNull(memory);
    }

}