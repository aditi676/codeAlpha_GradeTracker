# codeAlpha_GradeTracker
A simple Java-based Student Grade Tracker that allows users to manage student records, add grades, calculate averages, and display student information. This tool is perfect for educators, tutors, or anyone needing to track academic performance over time.

# Student Grading System Project

## Overview

The Student Grading System project is a comprehensive Java application designed to assist educational institutions in managing students' academic records efficiently. Developed with a focus on usability and functionality, this project offers a range of features to streamline the grading process and facilitate data analysis.

## Features

### 1. Add Student
- Allows administrators to add new students to the system.
- Captures essential student details such as name and ID.
- Ensures data integrity by preventing duplicate entries.

### 2. Insert Grades
- Provides functionality for entering grades for individual students.
- Supports multiple subjects, including Mathematics, Physics, Chemistry, Biology, and English.
- Validates input to ensure accuracy and consistency in grading.

### 3. Calculate Average
- Computes the average marks for each subject and overall average across all subjects.
- Offers insights into students' performance and academic progress.
- Facilitates data-driven decision-making for educators and administrators.

### 4. Generate Reports
- Generates comprehensive reports based on user specifications.
- Options include individual student reports and college-wide result summaries.
- Enhances transparency and accountability in academic assessment.

### 5. Dynamic Animations
- Incorporates interactive loading animations for a more engaging user experience.
- Provides visual feedback during data processing tasks.
- Enhances usability and aesthetics of the application.

## Technologies Used

- **Java**: Core programming language used for application development.
- **MySQL**: Relational database management system for storing and managing student data.
- **JDBC**: Java Database Connectivity for seamless interaction with the database.
- **Git**: Version control system for collaborative development and code management.


## How to Run

1. Clone the repository to your local machine using Git.
2. Install Java Development Kit (JDK) and MySQL on your system.
3. Import the project into your preferred Integrated Development Environment (IDE).
4. Set up the MySQL database using the provided SQL scripts to create tables and populate initial data.
5. Run the `Run.java` file to launch the application.
6. Follow on-screen prompts to add students, insert grades, calculate averages, and generate reports.

## Project Structure

- `Run.java`: Main class containing the entry point of the application.
- `Reports.java`: Class responsible for generating various types of reports based on user input.
- `Quries.java`: Contains SQL queries used for database operations.
- `insert_Grades.java`, `add_Student.java`, `Average.java`: Classes for adding students, inserting grades, and calculating averages respectively.
- `Animation.java`: Class for creating dynamic loading animations to enhance user experience.

## Database Configuration

The project requires setting up a MySQL database with the following tables and values:

### Tables
- **Grades**: Main table to store student grades and information.

### Values
Modify the following values according to your requirements:

**Subjects :-** MATHS,PHYSICS,CHEMISTRY,BIOLOGY,ENGLISH,etc. These are the Subjects that you can Modify according to your choice.


## Conclusion

The Student Grading System project represents a significant step towards modernizing academic record-keeping processes in educational institutions. By leveraging Java programming and database management technologies, this application provides a robust platform for managing student data effectively. Whether you're tracking individual student progress or analyzing college-wide performance trends, this project empowers users with the insights and tools they need to succeed in their academic endeavors.
