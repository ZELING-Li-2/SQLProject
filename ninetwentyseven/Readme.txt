To use: First run the python file you want to populate (names, principals, ratings or crew)
  populate_name_tables_fast.py
  populate_principals_tables_fast.py
  populate_rating_tables_fast.py
  populate_title_tables_fast.py
  populate_crew.py

Then to populate database, run java
Compile and run by doing:
javac jdbcpostgreSQL.java
java -cp ".;postgresql-42.2.8.jar" jdbcpostgreSQL
In Ubuntu: java -cp ".:postgresql-42.2.8.jar" jdbcpostgreSQL

test.csv syntax:
separate sql commands with newlines
modes for running each line are Query or Update. There is no default mode, must switch to one or the other on the first line
to switch to Query mode, put Query in its own line
to switch to Update mode, put Update in its own line

Query mode prints the results of your query
Update mode does not

We just have to modify my python to work with the rest of the tables too
We can definitely write python to adjust to any table in the right format automatically
But theres only like 5 tables so I'm not sure its worth the time.
