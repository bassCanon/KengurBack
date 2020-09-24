package ba.com.kengur;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@SpringBootApplication(exclude = { LiquibaseAutoConfiguration.class })
public class ApplicationConfiguration {

    @Value("${spring.datasource.url}")
    String dbUrl;

    @Value("${spring.datasource.username}")
    String dbUsername;

    @Value("${spring.datasource.password}")
    String dbPassword;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl(dbUrl);
        driverManagerDataSource.setUsername(dbUsername);
        driverManagerDataSource.setPassword(dbPassword);
        return driverManagerDataSource;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/static/templates/");
        resolver.setSuffix(".ftl");
        return resolver;
    }

}
