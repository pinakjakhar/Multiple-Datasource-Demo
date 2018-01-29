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

import com.demo.multipledb.controller.user2.UserController2;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "user2EntityManager",
		transactionManagerRef = "user2TransactionManager",
		basePackages = "com.demo.multipledb.repository.user2")
public class User2DataSourceConfig {

	@Autowired(required = false)
	private PersistenceUnitManager persistenceUnitManager;

	@Bean
	@ConfigurationProperties(prefix = "user2.datasource")
	public DataSource user2DataSource() {
		return (DataSource) DataSourceBuilder.create().type(DataSource.class).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean user2EntityManager(
			JpaProperties user2JpaProperties) {
		EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(user2JpaProperties);
		return builder
				.dataSource(user2DataSource())
				.packages("com.demo.multipledb.dto.user2")
				.persistenceUnit("User2")
				.build();
	}

	@Bean
	public JpaTransactionManager user2TransactionManager(EntityManagerFactory user2EntityManager) {
		return new JpaTransactionManager(user2EntityManager);
	}

	private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(JpaProperties user2JpaProperties) {
		JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(user2JpaProperties);
		return new EntityManagerFactoryBuilder(jpaVendorAdapter,
				user2JpaProperties.getProperties(), this.persistenceUnitManager);
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
