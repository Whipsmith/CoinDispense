package za.co.opsmobile.coindispense.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:webSecurityConfig.xml" })
@ComponentScan("za.co.opsmobile.coindispense.security")
public class SecurityConfig {

    public SecurityConfig() {
        super();
    }

}
