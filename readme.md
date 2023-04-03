> Hi, First of all thank you for considering me for this role.
> A short introduction about me,
> I am Cyrelle, Originally from Philippines, Currently working as an IT consultant.
> I have been working in Japanese IT Industry for about 5 years.
> Experince in both front-end and back-end development, mostly on JAVA.
> I am not new in Spring framework. I have experience Spring framework in one of my client, NTT Data.

Below are the list of what I did to the program.

- EmployeeController.java
  - added constructor for easy testing
  - added "final" to the EmployeeService
  - added javadoc for every method
  - added response format
  - deleted System.out.println() functions, reason: better to use logger
  - fixing bug when updating employee, bug: updating existing record, while adding new record
  - deleted setEmployeeService() function. reason: unnecessary
  - added "final" to the EmployeeService
- EmployeeService.java
  - Deleted UpdateEmployee method and updated the name saveEmployee method to saveAndUpdateEmployee. reason: doing same thing with saveEmployee method
- EmployeeServiceImpl.java
  - Added Constructor
  - Fixed bug on getEmployee method when fetching non existing employee.
- Added TestCases on some endpoints

If I have more time, Below are the list that I wanted to do.

- Add test cases on all end points. covering OK and BAD results.
- Add security to the endpoints like JWT.
- Add security that can consume and produce only JSON
- Add Logger to start and end of the method. for easy debugging
- Add constants.java for hardcoded strings
- Add more information on the api for swagger docs
