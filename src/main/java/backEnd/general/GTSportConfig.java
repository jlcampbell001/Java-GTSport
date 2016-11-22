package backEnd.general;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Jonathan
 *
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan({"backEnd"})
public class GTSportConfig {
	/**
	 * Create a datasource to work with.
	 * Has the information for dealing with the database.
	 * @return the datasource.
	 */
	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource GTSportDataSource = new DriverManagerDataSource();

		GTSportDataSource.setDriverClassName("org.postgresql.Driver");
		GTSportDataSource.setUrl("jdbc:postgresql://localhost/GTSport_Test");
		GTSportDataSource.setUsername("GTSport");
		GTSportDataSource.setPassword("GTSport");
		return GTSportDataSource;
	}

	/**
	 * Create an entity manager factory to work with.
	 * @return the entity manager factory.
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("backEnd");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	/**
	 * Create the transaction manager to work with 
	 * @return the platform transaction manager.
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	/**
	 * Create the hibernate exception translator.
	 * I think this is needed by NGTest.
	 * @return the hibernate exception translator.
	 */
	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
}
