# Bazinga
#Alterar o persistence.xml e criar o banco de dados "bazinga" na máquina
<properties></br>
	<property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
	<property name="hibernate.hbm2ddl.auto" value="update"/>
	<property name="hibernate.show_sql" value="true"/>
	<property name="hibernate.format_sql" value="true"/>
	<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
	<property name="javax.persistence.jdbc.url"  value="jdbc:mysql://localhost/bazinga?serverTimezone=UTC"/>
	<property name="javax.persistence.jdbc.user" value="root"/>
	<property name="javax.persistence.jdbc.password" value="12345678"/>
</properties>
