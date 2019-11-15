# PostgreSQL
[src](https://www.postgresql.org/docs/12) we are going to read full documentation...


#### Summary of PostgreSQL 12 Documentation


PostgreSQL is a powerful, open source object-relational database system that uses and extends the SQL language combined with many features that safely store and scale the most complicated data workloads. The origins of PostgreSQL date back to 1986 as part of the POSTGRES project at the University of California at Berkeley and has more than 30 years of active development on the core platform.

As is typical of client/server applications, the client and the server can be on different hosts. In that case they communicate over a TCP/IP network connection. You should keep this in mind, because the files that can be accessed on a client machine might not be accessible (or might only be accessible using a different file name) on the database server machine.

The PostgreSQL server can handle multiple concurrent connections from clients. To achieve this it starts (“forks”) a new process for each connection. From that point on, the client and the new server process communicate without intervention by the original postgres process. Thus, the master server process is always running, waiting for client connections, whereas client and associated server processes come and go. (All of this is of course invisible to the user. We only mention it here for completeness.)


$ createdb -T template0 dbname
$ createdb mydb
$ dropdb mydb

Accessing a Database : $ psql mydb

The psql program has a number of internal commands that are not SQL commands. They begin with the backslash character, “\”. For example, you can get help on the syntax of various PostgreSQL SQL commands by typing:


mydb=> \i basics.sql

The \i command reads in commands from the specified file. psql's -s option puts you in single step mode which pauses before sending each statement to the server. The commands used in this section are in the file basics.sql.

### SQL Basics

Each table is a named collection of rows. Each row of a given table has the same set of named columns, and each column is of a specific data type. Whereas columns have a fixed order in each row, it is important to remember that SQL does not guarantee the order of the rows within the table in any way (although they can be explicitly sorted for display).

**Tables are grouped into databases, and a collection of databases managed by a single PostgreSQL server instance constitutes a database cluster.**

CREATE TABLE weather (
    city            varchar(80),
    temp_lo         int,           -- low temperature
    temp_hi         int,           -- high temperature
    prcp            real,          -- precipitation
    date            date
);

PostgreSQL supports the standard SQL types int, smallint, real, double precision, char(N), varchar(N), date, time, timestamp, and interval, as well as other types of general utility and a rich set of geometric types

DROP TABLE weather;
INSERT INTO weather VALUES ('San Francisco', 46, 50, 0.25, '1994-11-27');


You could also have used COPY to load large amounts of data from flat-text files. This is usually faster because the COPY command is optimized for this application while allowing less flexibility than INSERT. An example would be:

COPY weather FROM '/home/user/weather.txt';


SELECT city, temp_lo, temp_hi, prcp, date FROM weather;
SELECT city, (temp_hi+temp_lo)/2 AS temp_avg, date FROM weather;


##### Joins Between Tables

SELECT * FROM weather, cities WHERE city = name;

SELECT weather.city, weather.temp_lo, weather.temp_hi,
       weather.prcp, weather.date, cities.location
    FROM weather, cities
    WHERE cities.name = weather.city;

**Join queries of the kind seen thus far can also be written in this alternative form:**

SELECT * FROM weather INNER JOIN cities ON (weather.city = cities.name);

This syntax is not as commonly used as the one above, but we show it here to help you understand the following topics.

SELECT W1.city, W1.temp_lo AS low, W1.temp_hi AS high,
    W2.city, W2.temp_lo AS low, W2.temp_hi AS high
    FROM weather W1, weather W2
    WHERE W1.temp_lo < W2.temp_lo
    AND W1.temp_hi > W2.temp_hi;

#### Aggregate Functions

SELECT max(temp_lo) FROM weather;
SELECT city FROM weather WHERE temp_lo = **max(temp_lo);**    WRONG

However, as is often the case the query can be restated to accomplish the desired result, here by using a subquery:

SELECT city FROM weather
    WHERE temp_lo = (SELECT max(temp_lo) FROM weather);

This is OK because the subquery is an independent computation that computes its own aggregate separately from what is happening in the outer query.

Aggregates are also very useful in combination with GROUP BY clauses. For example, we can get the maximum low temperature observed in each city with:

SELECT city, max(temp_lo)
    FROM weather
    GROUP BY city;

which gives us one output row per city. Each aggregate result is computed over the table rows matching that city. We can filter these grouped rows using HAVING:

SELECT city, max(temp_lo)
    FROM weather
    GROUP BY city
    HAVING max(temp_lo) < 40;

SELECT city, max(temp_lo)
    FROM weather
    WHERE city LIKE 'S%'            -- (1)
    GROUP BY city
    HAVING max(temp_lo) < 40;

S% : S..........
%S% : .....S......
%S : ......S

UPDATE weather SET temp_hi = temp_hi - 2,  temp_lo = temp_lo - 2 WHERE date > '1994-11-28';
DELETE FROM weather WHERE city = 'Hayward';

###  Advanced Features 

Refer back to the queries in Section 2.6. Suppose the combined listing of weather records and city location is of particular interest to your application, but you do not want to type the query each time you need it. You can create a view over the query, which gives a name to the query that you can refer to like an ordinary table:

**Views**

CREATE VIEW myview AS
    SELECT city, temp_lo, temp_hi, prcp, date, location
        FROM weather, cities
        WHERE city = name;

SELECT * FROM myview;

Making liberal use of views is a key aspect of good SQL database design. Views allow you to encapsulate the details of the structure of your tables, which might change as your application evolves, behind consistent interfaces.

Views can be used in almost any place a real table can be used. Building views upon other views is not uncommon.

**Foreign key**

CREATE TABLE cities (
        city     varchar(80) primary key,
        location point
);

CREATE TABLE weather (
        city      varchar(80) references cities(city),
        temp_lo   int,
        temp_hi   int,
        prcp      real,
        date      date
);
INSERT INTO weather VALUES ('Berkeley', 45, 53, 0.0, '1994-11-28');

ERROR:  insert or update on table "weather" violates foreign key constraint "weather_city_fkey"
DETAIL:  Key (city)=(Berkeley) is not present in table "cities".

**Transactions**

BEGIN;
UPDATE accounts SET balance = balance - 100.00
    WHERE name = 'Alice';
-- etc etc
COMMIT;

If, partway through the transaction, we decide we do not want to commit (perhaps we just noticed that Alice's balance went negative), we can issue the command ROLLBACK instead of COMMIT, and all our updates so far will be canceled.

A group of statements surrounded by BEGIN and COMMIT is sometimes called a transaction block.

It's possible to control the statements in a transaction in a more granular fashion through the use of savepoints. Savepoints allow you to selectively discard parts of the transaction, while committing the rest. After defining a savepoint with SAVEPOINT, you can if needed roll back to the savepoint with ROLLBACK TO. All the transaction's database changes between defining the savepoint and rolling back to it are discarded, but changes earlier than the savepoint are kept.

After rolling back to a savepoint, it continues to be defined, so you can roll back to it several times. Conversely, if you are sure you won't need to roll back to a particular savepoint again, it can be released, so the system can free some resources. Keep in mind that either releasing or rolling back to a savepoint will automatically release all savepoints that were defined after it.

BEGIN;
UPDATE accounts SET balance = balance - 100.00
    WHERE name = 'Alice';
SAVEPOINT **my_savepoint**;
UPDATE accounts SET balance = balance + 100.00
    WHERE name = 'Bob';
-- oops ... forget that and use Wally's account
ROLLBACK TO **my_savepoint**;
UPDATE accounts SET balance = balance + 100.00
    WHERE name = 'Wally';
COMMIT; 

**Window Functions**

Here is an example that shows how to compare each employee's salary with the average salary in his or her department:
SELECT depname, empno, salary, avg(salary) OVER (PARTITION BY depname) FROM empsalary;

  depname  | empno | salary |          avg          
-----------+-------+--------+-----------------------
 develop   |    11 |   5200 | 5020.0000000000000000
 develop   |     7 |   4200 | 5020.0000000000000000
 develop   |     9 |   4500 | 5020.0000000000000000
 develop   |     8 |   6000 | 5020.0000000000000000
 develop   |    10 |   5200 | 5020.0000000000000000
 personnel |     5 |   3500 | 3700.0000000000000000
 personnel |     2 |   3900 | 3700.0000000000000000
 sales     |     3 |   4800 | 4866.6666666666666667
 sales     |     1 |   5000 | 4866.6666666666666667
 sales     |     4 |   4800 | 4866.6666666666666667
(10 rows)

A window function call always contains an OVER clause directly following the window function's name and argument(s). This is what syntactically distinguishes it from a normal function or non-window aggregate. The OVER clause determines exactly how the rows of the query are split up for processing by the window function. The PARTITION BY clause within OVER divides the rows into groups, or partitions,that share the same values of the PARTITION BY expression(s). For each row, the window function is computed across the rows that fall into the same partition as the current row.

SELECT depname, empno, salary,
       rank() OVER (PARTITION BY depname ORDER BY salary DESC)
FROM empsalary;

 depname  | empno | salary | rank 
-----------+-------+--------+------
 develop   |     8 |   6000 |    1
 develop   |    10 |   5200 |    2
 develop   |    11 |   5200 |    2
 develop   |     9 |   4500 |    4
 develop   |     7 |   4200 |    5
 personnel |     2 |   3900 |    1
 personnel |     5 |   3500 |    2
 sales     |     1 |   5000 |    1
 sales     |     4 |   4800 |    2
 sales     |     3 |   4800 |    2
(10 rows)

As shown here, the **rank function** produces a numerical rank for each distinct ORDER BY value in the current row's partition, using the order defined by the ORDER BY clause. rank needs no explicit parameter, because its behavior is entirely determined by the OVER clause.


SELECT salary, sum(salary) OVER () FROM empsalary;
 salary |  sum  
--------+-------
   5200 | 47100
   5000 | 47100
   3500 | 47100
   4800 | 47100
   3900 | 47100
   4200 | 47100
   4500 | 47100
   4800 | 47100
   6000 | 47100
   5200 | 47100
(10 rows)
Above, since there is no ORDER BY in the OVER clause, the window frame is the same as the partition, which for lack of PARTITION BY is the whole table; in other words each sum is taken over the whole table and so we get the same result for each output row. But if we add an ORDER BY clause, we get very different results:

SELECT salary, sum(salary) OVER (ORDER BY salary) FROM empsalary;
 salary |  sum  
--------+-------
   3500 |  3500
   3900 |  7400
   4200 | 11600
   4500 | 16100
   4800 | 25700
   4800 | 25700
   5000 | 30700
   5200 | 41100
   5200 | 41100
   6000 | 47100
(10 rows)
Here the sum is taken from the first (lowest) salary up through the current one, including any duplicates of the current one (notice the results for the duplicated salaries).;

Window functions are permitted only in the SELECT list and the ORDER BY clause of the query. They are forbidden elsewhere, such as in GROUP BY, HAVING and WHERE clauses. 

**Inheritance**

Inheritance is a concept from object-oriented databases. It opens up interesting new possibilities of database design.

CREATE TABLE capitals (
  name       text,
  population real,
  altitude   int,    -- (in ft)
  state      char(2)
);

CREATE TABLE non_capitals (
  name       text,
  population real,
  altitude   int     -- (in ft)
);

CREATE VIEW cities AS
  SELECT name, population, altitude FROM capitals
    UNION
  SELECT name, population, altitude FROM non_capitals;

**This works OK as far as querying goes, but it gets ugly when you need to update several rows, for one thing.**

CREATE TABLE cities (
  name       text,
  population real,
  altitude   int     -- (in ft)
);

CREATE TABLE capitals (
  state      char(2)
) **INHERITS (cities);**

SELECT name, altitude
    FROM **ONLY** cities
    WHERE altitude > 500;

Here the ONLY before cities indicates that the query should be run over only the cities table, and not tables below cities in the inheritance hierarchy. Many of the commands that we have already discussed — SELECT, UPDATE, and DELETE — support this ONLY notation.

## The SQL Language

#### SQL Syntax

SQL input consists of a sequence of commands. A command is composed of a sequence of tokens, terminated by a semicolon (“;”). The end of the input stream also terminates a command. Which tokens are valid depends on the syntax of the particular command.

A token can be a key word, an identifier, a quoted identifier, a literal (or constant), or a special character symbol. Tokens are normally separated by whitespace (space, tab, newline), but need not be if there is no ambiguity (which is generally only the case if a special character is adjacent to some other token type).

For instance the UPDATE command always requires a **SET token** to appear in a certain position, and this particular variation of INSERT also requires a VALUES in order to be complete. The precise syntax rules for each command are described in Part VI.

SQL identifiers and key words must begin with a letter (a-z, but also letters with diacritical marks and non-Latin letters) or an underscore (_). Subsequent characters in an identifier or key word can be letters, underscores, digits (0-9), or dollar signs ($). Note that dollar signs are not allowed in identifiers according to the letter of the SQL standard, so their use might render applications less portable. The SQL standard will not define a key word that contains digits or starts or ends with an underscore, so identifiers of this form are safe against possible conflict with future extensions of the standard.


A variant of quoted identifiers allows including escaped Unicode characters identified by their code points. This variant starts with U& (upper or lower case U followed by ampersand) immediately before the opening double quote, without any spaces in between, for example U&"foo". (Note that this creates an ambiguity with the operator &. Use spaces around the operator to avoid this problem.) Inside the quotes, Unicode characters can be specified in escaped form by writing a backslash followed by the four-digit hexadecimal code point number or alternatively a backslash followed by a plus sign followed by a six-digit hexadecimal code point number. For example, the identifier "data" could be written as

U&"d\0061t\+000061" 



The escape character can be any single character other than a hexadecimal digit, the plus sign, a single quote, a double quote, or a whitespace character. Note that the escape character is written in single quotes, not double quotes.

U&"d!0061t!+000061" UESCAPE '!'

Quoting an identifier also makes it case-sensitive, whereas unquoted names are always folded to lower case. For example, the identifiers FOO, foo, and "foo" are considered the same by PostgreSQL, but "Foo" and "FOO" are different from these three and each other.

Backslash Escape Sequence	Interpretation
\b	backspace
\f	form feed
\n	newline
\r	carriage return
\t	tab
\o, \oo, \ooo (o = 0 - 7)	octal byte value
\xh, \xhh (h = 0 - 9, A - F)	hexadecimal byte value
\uxxxx, \Uxxxxxxxx (x = 0 - 9, A - F)	16 or 32-bit hexadecimal Unicode character value

If the configuration parameter standard_conforming_strings is off, then PostgreSQL recognizes backslash escapes in both regular and escape string constants. However, as of PostgreSQL 9.1, the default is on, meaning that backslash escapes are recognized only in escape string constants.

If you need to use a backslash escape to represent a special character, write the string constant with an E.
In addition to standard_conforming_strings, the configuration parameters escape_string_warning and backslash_quote govern treatment of backslashes in string constants.

To allow more readable queries in such situations, PostgreSQL provides another way, called “dollar quoting”, to write string constants. A dollar-quoted string constant consists of a dollar sign ($), an optional “tag” of zero or more characters, another dollar sign, an arbitrary sequence of characters that makes up the string content, a dollar sign, the same tag that began this dollar quote, and a dollar sign. For example, here are two different ways to specify the string “Dianne's horse” using dollar quoting:

$$Dianne's horse$$
$SomeTag$Dianne's horse$SomeTag$

$function$
BEGIN
    RETURN ($1 ~ $q$[\t\r\n\v\\]$q$);
END;
$function$
Here, the sequence $q$[\t\r\n\v\\]$q$ represents a dollar-quoted literal string [\t\r\n\v\\], which will be recognized when the function body is executed by PostgreSQL. But since the sequence does not match the outer dollar quoting delimiter $function$, it is just some more characters within the constant so far as the outer string is concerned.

The tag, if any, of a dollar-quoted string follows the same rules as an unquoted identifier, except that it cannot contain a dollar sign. Tags are case sensitive, so $tag$String content$tag$ is correct, but $TAG$String content$tag$ is not.

~ ! @ # % ^ & | ` ?

For example, @- is an allowed operator name, but *- is not. This restriction allows PostgreSQL to parse SQL-compliant queries without requiring spaces between tokens.

When working with non-SQL-standard operator names, you will usually need to separate adjacent operators with spaces to avoid ambiguity. For example, if you have defined a left unary operator named @, you cannot write X*@Y; you must write X* @Y to ensure that PostgreSQL reads it as two operator names not one.


Operator	Associativity	Description
.	     left	            table/column name separator
::	     left	            PostgreSQL-style typecast
[ ]	     left	            array element selection
+ -	     right	            unary plus, unary minus
^	     left	            exponentiation
* / %	 left	            multiplication, division, modulo
+ -	     left	            addition, subtraction
(any other operator)	left	all other native and user-defined operators
BETWEEN IN LIKE ILIKE SIMILAR	 	range containment, set membership, string matching
< > = <= >= <>	 	comparison operators
IS ISNULL NOTNULL	 	IS TRUE, IS FALSE, IS NULL, IS DISTINCT FROM, etc
NOT	      right	                 logical negation
AND	      left	                 logical conjunction
OR	      left	                 logical disjunction


SELECT 3 OPERATOR(pg_catalog.+) 4;

#####  Value Expressions

A positional parameter reference is used to indicate a value that is supplied externally to an SQL statement. Parameters are used in SQL function definitions and in prepared queries. Some client libraries also support specifying data values separately from the SQL command string, in which case parameters are used to refer to the out-of-line data values. The form of a parameter reference is:

$number
For example, consider the definition of a function, dept, as:

CREATE FUNCTION dept(text) RETURNS dept
    AS $$ SELECT * FROM dept WHERE name = $1 $$
    LANGUAGE SQL;
Here the $1 references the value of the first function argument whenever the function is invoked.

An aggregate expression represents the application of an aggregate function across the rows selected by a query. An aggregate function reduces multiple inputs to a single output value, such as the sum or average of the inputs. The syntax of an aggregate expression is one of the following:

----
STRING_AGG ( expression, separator [order_by_clause] )

The STRING_AGG() function accepts two arguments and an optional ORDER BY clause.
expression is any valid expression that can resolve to a character string. If you use other types than character string type, you need to explicitly cast these values of that type to the character string type.
separator is the separator for concatenated strings.
The order_by_clause is an optional clause that specifies the order of concatenated results. It has the following form:

ORDER BY expression1 {ASC | DESC}, [...]
----


Most aggregate functions ignore null inputs, so that rows in which one or more of the expression(s) yield null are discarded. This can be assumed to be true, unless otherwise specified, for all built-in aggregates.

For example, **count(*)** yields the total number of input rows; count(f1) yields the number of input rows in which f1 is non-null, since count ignores nulls; and **count(distinct f1)** yields the number of distinct non-null values of f1.

SELECT array_agg(a ORDER BY b DESC) FROM table;

When dealing with multiple-argument aggregate functions, note that the ORDER BY clause goes after all the aggregate arguments. For example, write this:

SELECT string_agg(a, ',' ORDER BY a) FROM table;

not this:

SELECT string_agg(a ORDER BY a, ',') FROM table;  -- incorrect

-----

The COLLATE clause overrides the collation of an expression. It is appended to the expression it applies to:

expr COLLATE collation

where collation is a possibly schema-qualified identifier. The COLLATE clause binds tighter than operators; parentheses can be used when necessary.

If no collation is explicitly specified, the database system either derives a collation from the columns involved in the expression, or it defaults to the default collation of the database if no column is involved in the expression.

The two common uses of the COLLATE clause are overriding the sort order in an ORDER BY clause, for example:

SELECT a, b, c FROM tbl WHERE ... ORDER BY a COLLATE "C";
and overriding the collation of a function or operator call that has locale-sensitive results, for example:

SELECT * FROM tbl WHERE a > 'foo' COLLATE "C";
-----

Scalar Subqueries

SELECT name, (SELECT max(pop) FROM cities WHERE cities.state = states.name)
    FROM states;

-----

Array Constructors

SELECT ARRAY[1,2,3+4];
  array
---------
 {1,2,7}
(1 row)

CREATE TABLE arr(f1 int[], f2 int[]);

INSERT INTO arr VALUES (ARRAY[[1,2],[3,4]], ARRAY[[5,6],[7,8]]);

SELECT ARRAY[f1, f2, '{{9,10},{11,12}}'::int[]] FROM arr;
                     array
------------------------------------------------
 {{{1,2},{3,4}},{{5,6},{7,8}},{{9,10},{11,12}}}
(1 row)

SELECT ARRAY(SELECT oid FROM pg_proc WHERE proname LIKE 'bytea%');

**The subquery must return a single column**
-----

[We-are-going-to-continue-from-here](https://www.postgresql.org/docs/12/sql-syntax-calling-funcs.html)
We have to repeat everyday...


