# MyShoppingWeb

<p>MyShoppingWeb is a simple Java Servlet Shopping Website, which user can</p> 
<ul>
  <li>register</li>
  <li>login</li>
  <li>logout</li>
</ul>
<ul>
  <li>add product into the cart</li>
  <li>delete product from the cart</li>
</ul>

## Environment

- Windows 10 (64 bits) <br>
- Eclipse IDE for Enterprise Java Developers 4.16.0 <br>
  - jre1.8.0_251 <br>
  - Servlet 3.0 <br>
  - JSP 2.2 <br>
  - JSTL 1.2 <br>
  - JDBC for MySQL 5.1.44 <br>
  - JDBC for PostgreSQL 42.2.5 <br>
  - Hibernate 4.1.6.Final <br>
  - JUnit 4.12 <br>
  - Mockito 2.7.9 <br>
  - Bootstrap 3 <br>
- Apache Tomcat v7.0.105 <br>
- MySQL Server v8.0.20 <br>
- PostgreSQL Server 13.0 <br>

## Usage
### Connection Setting for Local Database Server
#### MySQL
```
Server: localhost
Port: 3306
Username: root
Password: test1234
```

#### PostgreSQL
```
Server: localhost
Port: 5432
Username: postgres
Password: test1234
```

### Create SQL Tables In Local Database
#### MySQL
<p>Open MySQL Shell, and connect to MySQL Server using connection setting above</p>

<p>Create a Database, named myshoppingweb.</p>
<p>And Connect to it.</p>

```sql
CREATE DATABASE myshoppingweb;
USE myshoppingweb;
```

<p>Use the command below to execute users.sql and products.sql in MyShoppingWeb/sql-tables/PostgreSql/</p>

```sql
source {.sql file path}
```

<p>For example, if users.sql and products.sql are all in C:/MyShoppingWeb/sql-tables/PostgreSql/</p>

```sql
source C:/MyShoppingWeb/sql-tables/PostgreSql/users.sql
source C:/MyShoppingWeb/sql-tables/PostgreSql/products.sql
```

<p>Finally, see the created tables by using commands below</p>

```sql
SHOW TABLES;
```

#### PostgreSQL
<p>Open SQL Shell (psql), and connect to PostgreSQL Server using connection setting above</p>
<p>Create a Database, named myshoppingweb.</p>
<p>And Connect to it.</p>

```sql
CREATE DATABASE myshoppingweb;
\c myshoppingweb;
```

<p>Use the command below to execute users.sql and products.sql in MyShoppingWeb/sql-tables/PostgreSql/</p>

```
\i {.sql file path}
```

<p>For example, if users.sql and products.sql are all in C:/MyShoppingWeb/sql-tables/PostgreSql/</p>

```
\i C:/MyShoppingWeb/sql-tables/PostgreSql/users.sql
\i C:/MyShoppingWeb/sql-tables/PostgreSql/products.sql
```

<p>Finally, see the created tables by using commands below</p>

```sql
\dt
```

### Config Setting In MyShoppingWeb
<p>Choose <b>JDBC</b> or <b>HIBERNATE</b> for DATABASE_CONNECTOR, <br>
  and <b>MYSQL</b> OR <b>POSTGRESQL</b> for DATABASE_CATEGORY.</p>

![](https://i.imgur.com/ylxoXaE.png)

### Start Tomcat Server
![](https://i.imgur.com/TscZkDz.png)

### View MyShoppingWeb
http://localhost:8080/MyShoppingWeb/


## Architecture
![](https://i.imgur.com/igXcT92.png)
