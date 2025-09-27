# Common Library Usage Guide

This library provides a shared database access layer for Paysecure applications.
It contains only schemas, SQL queries, mappers, and POJOs.
⚠️ No business logic should be added here.

This library provides utilities such as RedisUtils, CacheableQueryService, and other shared components.
Important: Before using this library in your Spring Boot application, ensure the required configurations are present.

1. Required Configurations
1.1 MySQL

For primary and secondary databases (HikariCP recommended):

# Primary database
spring.datasource.url=jdbc:mysql://localhost:3306/primary_db
spring.datasource.username=root
spring.datasource.password=secret
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=2

# Secondary/dashboard database
dashboard.datasource.url=jdbc:mysql://localhost:3306/dashboard_db
dashboard.datasource.username=root
dashboard.datasource.password=secret
dashboard.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
dashboard.datasource.hikari.maximum-pool-size=5
dashboard.datasource.hikari.minimum-idle=1

1.2 Redis

For caching via RedisUtils:

spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=yourRedisPassword
spring.redis.database=0
spring.redis.timeout=60000
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0
spring.redis.lettuce.pool.max-wait=10000

1.3 MongoDB

For primary and optional secondary/secondary databases:

# Primary MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/primary_db

# Secondary MongoDB (optional)
spring.data.mongodb.secondary.uri=mongodb://localhost:27017/secondary_db

1.4 Application Name

Used for logging and monitoring:

spring.application.name=batch-processor

2. Using the Library

Add the library dependency to your pom.xml:

<!-- <dependency> -->
    <groupId>paysecure.common</groupId>
    <artifactId>common-lib</artifactId>
    <version>1.0.2</version>
<!-- </dependency> -->


# Ensure your Spring Boot application scans the library packages:

@SpringBootApplication(scanBasePackages = {"org.yourapp", "paysecure.common.library"})
public class YourApplication {
    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }
}


Beans provided by the library:

RedisUtils (requires Redis configuration)

CacheableQueryService (requires primary/secondary JDBC)

Other utilities may require MongoDB configuration


# 🔑 Key Principles

Keep business logic outside this library
Only put database-related code here (queries, schemas, models, mappers).
Your application layer (services) will consume this library.

Queries go inside repository/
Each DAO is responsible for executing SQL queries.

Row mapping is done inside mapper/
Every DAO uses a dedicated mapper to map query results to a POJO.

POJOs inside model/ only
Keep models clean, representing DB schema.

Qualifiers must be used
Always inject the correct JdbcTemplate (primaryJdbcTemplate, dashbrdJdbcTemplate, etc.)
Example:

public AuthLoginDao(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}


# Use DAO in Service Layer

Example: Fetch login info from DB using AuthLoginDao:

import org.springframework.stereotype.Service;
import paysecure.common.db.mysql.repository.AuthLoginDao;
import paysecure.common.db.mysql.model.pgs.AuthLogin;

@Service
public class UserLoginInfo {

    private final AuthLoginDao authLoginDao;

    public UserLoginInfo(AuthLoginDao authLoginDao) {
        this.authLoginDao = authLoginDao;
    }

    public AuthLogin getUserByName(String username) {
        return authLoginDao.getLoginByName(username);
    }
}


# Direct Imports

Since this is a library, simply import and use:

import paysecure.common.db.mysql.repository.*;
import paysecure.common.db.mysql.model.*;

✅ Example Query in DAO
@Repository
public class AuthLoginDao {

    private static final String SQL_FIND_AUTH_LOGIN_BY_NAME =
        "SELECT * FROM auth_login a, login_type l " +
        "WHERE a.login_type = l.type_id AND login_name = ?";

    private final JdbcTemplate jdbcTemplate;

    public AuthLoginDao(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AuthLogin getLoginByName(String username) {
        try {
            return jdbcTemplate.queryForObject(
                SQL_FIND_AUTH_LOGIN_BY_NAME,
                new Object[]{username},
                new LoginMapper()
            );
        } catch (Exception e) {
            return null;
        }
    }
}

# 🔨 Rebuild & Install Library Locally

From inside your common-db-lib repo:

mvn clean install


✔️ This will:

Clean → remove all previous compiled files and target directory

Compile & Test → build the code and run unit tests

Install → put the JAR into your local Maven repository (~/.m2/repository)

Example log at the end:

[INFO] Installing /path/to/common-db-lib/target/common-db-lib-1.0.0.jar to ~/.m2/repository/paysecure/common/db/common-db-lib/1.0.0/common-db-lib-1.0.0.jar
[INFO] BUILD SUCCESS

# 📥 Use in Your Application

In your main application’s pom.xml, declare the dependency:

<!-- <dependency> -->
    <groupId>paysecure.common.db</groupId>
    <artifactId>common-db-lib</artifactId>
    <version>1.0.0</version>
<!-- </dependency> -->


Then, rebuild your application:

mvn clean install


Maven will automatically pull the library from your local repo.

# 🚀 Deploying to Shared Repo (Optional)

If you want others (team/CI/CD) to use it:

mvn clean deploy


This pushes to GitHub Packages / Nexus / Artifactory (depending on your distributionManagement config).

Applications can then consume it without needing a local mvn install.

# 🚫 What NOT to Do

❌ Don’t add business logic
❌ Don’t handle DTOs or service orchestration
❌ Don’t mix queries with business validations

This library’s purpose is pure DB access.

# 📜 Changelog

See CHANGELOG.yaml
 for version history.