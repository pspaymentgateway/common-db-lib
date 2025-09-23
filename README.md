# Common DB Library (common-db-lib)

This library provides a shared database access layer for Paysecure applications.
It contains only schemas, SQL queries, mappers, and POJOs.
⚠️ No business logic should be added here.

# 📂 Project Structure

paysecure.common.db.mysql
│
├── model/          # All DB entity POJOs
│   └── AuthLogin.java
│

├── mapper/         # All RowMapper implementations
│   └── LoginMapper.java
│

└── repository/     # DAO classes with SQL queries
    └── AuthLoginDao.java

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
import paysecure.common.db.mysql.model.AuthLogin;

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