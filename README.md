# MCINO Group Details:

## Group Details
* Kamil Cierpisz - **Team Leader**
* Nikolaj Jasenko
* Temitope Akinwale

## Assignment Tasks
- [x] Users can View School Statistics: % of Male/Female, Nationalities
- [x] Students able to register to WebApp
- [x] Students can login and logout from the WebApp
- [x] Students can enroll to modules if capacity not reached, status is Active and fees paid
- [x] Students can cancel enrollment to Active modules
- [x] Students can cancel their registration (Removes entire student record)
- [x] Students can pay fees
- [x] My Profile section with Active Modules and Terminated/Graded Modules
- [x] My Profile section for coordinators to access their modules quickly
- [x] Module page implemented with basic module details
- [x] Module page displays grade distributions for all editions of a given module to date
- [x] Coordinator can update some module information, terminate the module, and grade students
- [x] Coordinator can terminate a module
- [x] Coordinator can grade students upon module termination


## Distribution of Tasks
###### Kamil Cierpisz
* Initialized the repository
* Worked on setting up mySQL DB w/ Nikolaj and Temitope
* Set up the models of the SpringBoot application to resemble database
* Worked on getting the JPA functionality using the model
* Modified models to reflect changes during the development
* Fixed up the frontend look and feel to be more responsive
* Implemented My Profile page for students displaying modules w/ enrollment, paying fees and cancelling registration functionality
* Implemented Modules page displaying Module list w/ a filter search bar
* Added Module pages for each module with basic info and linked to them from Modules and My Profile pages
* Wrote up this beautiful README

###### Nikolaj Jasenko
* Worked on setting up MySQL DB w/ Kamil and Temitope
* Set up the Frontend of the application using JSPs
* Found and edited a suitable website template
* Implemented login and registration functionalities
* Added database intialization instruction on WebApp start-up and modified as the model changed
* Helped Temitope with d3 visuals for Male/Female % and Nationality %
* Worked on visualizing grade distributions for modules

###### Temitope Akinwale
* Worked on setting up MySQL DB w/ Kamil and Nikolaj
* Performed refactorings to optimize front-end changes by taking out repetitive parts of each jsp into separate files
* Performed other front-end changes to reflect assignment brief
* Removed My Staff Portal and instead implemented a Staff specific My Profile page
* Worked on adding d3 visuals with Nikolaj to display Male/Female % and Nationality % in Statistic page
* Modified model to add gender for the above statistics