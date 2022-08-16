package by.tms.eshop;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class EshopApplication {

  private Environment environment;

  public EshopApplication(Environment environment) {
    this.environment = environment;
  }

  public static void main(String[] args) {
    SpringApplication.run(EshopApplication.class, args);
  }

  @Bean(name = "dataSource")
  public DataSource getDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(environment.getProperty("spring.datasource.url"));
    dataSource.setUsername(environment.getProperty("spring.datasource.username"));
    dataSource.setPassword(environment.getProperty("spring.datasource.password"));
    return dataSource;
  }

  @Autowired
  @Bean(name = "sessionFactory")
  public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
    LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
    localSessionFactoryBean.setDataSource(dataSource);
    localSessionFactoryBean.setPackagesToScan("by");

    Properties properties = new Properties();
    properties.put("hibernate.dialect",
        environment.getProperty("spring.jpa.properties.hibernate.dialect"));
    properties.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
    properties.put("current_session_context_class",
        environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
    localSessionFactoryBean.setHibernateProperties(properties);
    return localSessionFactoryBean;
  }

  @Autowired
  @Bean(name = "transactionManager")
  public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
    return new HibernateTransactionManager(sessionFactory);
  }

}
