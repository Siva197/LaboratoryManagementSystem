# LaboratoryManagementSystem
It's an simple console based application by using Core Java , Mysql and JDBC that allows us to manage Researchers,Experiments and Samples.
## Functionalities 
### Researcher Management
  - Add new researchers
  - View researcher details
  - Update researcher information
  - Delete researchers
### Experiment Management
  - Add new experiments
  - View experiment details
  - Update experiment information
  - Delete experiments
### Sample Management
  - Register new samples
  - View sample details
  - Update sample information
  - Delete samples
## Database Schema
### Reasearcher Table 
- researcher_id (Primary Key)
- researcher_name
- specialization
- email
- phone_number
### Experiment Table
- experiment_id (Primary Key)
- experiment_name
- researcher_id (Foreign Key references Researcher Table)
- start_date
- end_date
- status (Planned, In Progress, Completed)
### Sample Table 
- sample_id (Primary Key)
- sample_name
- experiment_id (Foreign Key references Experiment Table)
- collection_date
- description
### Pre-requisites
- Integrated Development Environment(IDE). It could be Eclipse,Intelij or VS Code of your choice.
- Java Development Kit(JDK 17)
- MySql Database
- Mysql JDBC driver
  
### Setup
 Open MySQL Workbench or MySQL Command Line Client and create the lab_management database and tables using the provided SQL script.
 ```sql script

CREATE DATABASE lab_management;

USE lab_management;

CREATE TABLE Researcher (
    researcher_id INT PRIMARY KEY AUTO_INCREMENT,
    researcher_name VARCHAR(255),
    specialization VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255)
);

CREATE TABLE Experiment (
    experiment_id INT PRIMARY KEY AUTO_INCREMENT,
    experiment_name VARCHAR(255),
    researcher_id INT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(255),
    FOREIGN KEY (researcher_id) REFERENCES Researcher(researcher_id)
);

CREATE TABLE Sample (
    sample_id INT PRIMARY KEY AUTO_INCREMENT,
    sample_name VARCHAR(255),
    experiment_id INT,
    collection_date DATE,
    description TEXT,
    FOREIGN KEY (experiment_id) REFERENCES Experiment(experiment_id)
);
```
## Clone the Repository and open the project in Eclipse . You can also choose different IDE's.
### Add MySQL Connector JAR
- For Eclipse Right-click the project .Select Build Path > Add External Archives.
- Navigate to the directory where you have downloaded the jar and select the MySQL Connector JAR file.
- For IntelliJ IDEA: Go to File -> Project Structure -> Libraries and add the JDBC driver jar file.
### Run the Application
- Right-click **LaboratoryManagementSystem.java** in Eclipse.
- Select **Run As > Java Application.**
- Sample Screenshot and You can also find the Output Explanation in the Repo.
