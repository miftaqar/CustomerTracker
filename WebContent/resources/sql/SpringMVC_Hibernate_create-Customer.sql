/*
 * Queries to be executed in SQL Server
 */

--Delete the Table If Exists
IF EXISTS (
	SELECT * FROM 
	INFORMATION_SCHEMA.TABLES WHERE 
	TABLE_NAME = 'tbl_SpringMVC_Customer'
)

BEGIN 
	DROP TABLE tbl_Spring_Customer
END


--Create The Table tbl_SpringMVC_Customer
CREATE TABLE tbl_SpringMVC_Customer(
	customerId INT IDENTITY,
	first_name NVARCHAR(50) DEFAULT NULL,
	last_name NVARCHAR(50) DEFAULT NULL,
	email NVARCHAR(50) DEFAULT NULL,
	PRIMARY KEY (customerId)
)

SELECT * 
FROM tbl_SpringMVC_Customer

INSERT INTO tbl_SpringMVC_Customer VALUES 
	('David','Adams','david@luv2code.com'),
	('John','Doe','john@luv2code.com'),
	('Ajay','Rao','ajay@luv2code.com'),
	('Mary','Public','mary@luv2code.com'),
	('Maxwell','Dixon','max@luv2code.com');
