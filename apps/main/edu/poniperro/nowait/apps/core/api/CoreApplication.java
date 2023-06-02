package edu.poniperro.nowait.apps.core.api;

import edu.poniperro.nowait.apps.core.api.command.FakeCommand;
import edu.poniperro.nowait.shared.domain.Service;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.HashMap;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        value = {"edu.poniperro.nowait.apps", "edu.poniperro.nowait.core", "edu.poniperro.nowait.shared"})
public class CoreApplication extends SpringBootServletInitializer {
    public static HashMap<String, Class<?>> commands() {
        return new HashMap<String, Class<?>>() {{
            //put("fake", FakeCommand.class);
            //put("another_fake", AnotherFakeCommand.class);
            //put("domain-events:rabbitmq:consume", ConsumeRabbitMqDomainEventsCommand.class);
        }};
    }
}