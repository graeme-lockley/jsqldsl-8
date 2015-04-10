jsqldsl-8
=========

An SQL DSL grounded in DRY and strong typing.

I am suspending this project and breaking it up into individual projects with each one being pushed separately.  The reason for this is because this project is covering multiple concerns and, schemadoc, one of the subprojects is useful independent of the rest of the main project.

The new projects are:

- jsqldsl-8-core: this contains the basic behaviour and classes for the SQL DSL.
- jdbc-maven-plugin: a maven plugin that is able to interrogate a database, pulls the database into a set of value objects and allows a freemarker template to be applied.
- jsqldsl-8-handler: a plugin to be used with jdbc-maven-runner which generates Java classes to describe the table structure and thereby supporting a typesafe JDBC interface into the associated database.
- schemadoc-8-handler: a plugin to be used with jdbc-maven-runner which generates a schema diagram based on the underlying tables and foreign key structures.
 
