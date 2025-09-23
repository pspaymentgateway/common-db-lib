# Common DB Library (common-db-lib)

This library provides a shared database access layer for Paysecure applications.
It contains only schemas, SQL queries, mappers, and POJOs.
⚠️ No business logic should be added here.

📂 Project Structure
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

🔑 Key Principles

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

⚙️ How to Use in Your Application

Add Dependency

First, install or publish the library (common-db-lib) to your Maven repository or GitHub Packages.
Then include it in your application’s pom.xml:

<dependency>
    <groupId>paysecure.common.db</groupId>
    <artifactId>common-db-lib</artifactId>
    <version>1.0.0</version>
</dependency>


Use DAO in Service Layer

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


Direct Imports

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

🚫 What NOT to Do

❌ Don’t add business logic
❌ Don’t handle DTOs or service orchestration
❌ Don’t mix queries with business validations

This library’s purpose is pure DB access.

📜 Changelog

See CHANGELOG.yaml
 for version history.