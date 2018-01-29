package com.demo.multipledb;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "userEntityManager",
		transactionManagerRef = "userTransactionManager",
		basePackages = "com.demo.multipledb.repository.user")
public class UserDataSourceConfig {

	@Autowired(required = false)
	private PersistenceUnitManager persistenceUnitManager;

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.user.datasource")
	public DataSource userDataSource() {
		return (DataSource) DataSourceBuilder.create().type(DataSource.class).build();
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean userEntityManager(
			JpaProperties userJpaProperties) {
		EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(userJpaProperties);
		return builder
				.dataSource(userDataSource())
				.packages("com.demo.multipledb.dto.user")
				.persistenceUnit("User")
				.build();
	}

	@Bean
	@Primary
	public JpaTransactionManager userTransactionManager(EntityManagerFactory userEntityManager) {
		return new JpaTransactionManager(userEntityManager);
	}

	private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(JpaProperties userJpaProperties) {
		JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(userJpaProperties);
		return new EntityManagerFactoryBuilder(jpaVendorAdapter,
				userJpaProperties.getProperties(), this.persistenceUnitManager);
	}

	private JpaVendorAdapter createJpaVendorAdapter(JpaProperties jpaProperties) {
		AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(jpaProperties.isShowSql());
		adapter.setDatabase(jpaProperties.getDatabase());
		adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
		adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
		return adapter;
	}

}
