# SlimMsgSys
****
## Intro:
I wanted to practice writing a full java server side web application using **Spring Boot**.  
In my past projects I've used: 
* **Jersey** - as a JAX-RS provider for developing RESTful Web Services in Java.
* **Hibernate** - as a JPA provider to takes care of the mapping from Java classes to database tables (and from Java data types to SQL data types), 
but also provides data query and retrieval facilities.  
* **Spring IoC Container** - for dependency injection.
* **JUnit and Mockito** - for testing of course.  

Now I could use them all in one.  
Spring's 'opinionated' default configuration fits most (but not all) application's needs.  

I also used:
**Lombok** - that prevented me from writing boilerplate code with no real value to project business side. 
For example I didn't needed to implement constructors, getters/setters, equals/hashCode or even implement the Builder pattern for my DTOs.  

This project has a very simple and straightforward business logic. My focus was at the technological POV.
****
## How to use this repository:
1. Clone this github repository to you local repo/FS
2. Import it as a maven project to you STS
3. In you MySQL DBMS create a DB named "messgae_sys_schema" (set userName and password 'root')
4. Run the App as 'Spring Boot App'
5. Open Postman and use the URLs from the list below.
****
## App business overview:
There are three main entities: a user, message and comment.  
 
1. **Use cases**: Create/get/update/delete User  
   **URL**: http://localhost:8080/api/users/{id}
2. **Use cases**: Create/get/update/delete Message  
   **URL**: http://localhost:8080/api/messagess/{id}
3. **Use cases**: Create a Comment for specific Message  
   **URL**: http://localhost:8080/api/comments
4. **Use cases**: Get all Users  
   **URL**: http://localhost:8080/api/users
5. **Use cases**: Get all Messages  
   **URL**: http://localhost:8080/api/messagess
6. **Use cases**: Get all Comments of specific Message  
   **URL**: http://localhost:8080/api/comments/message/{id}

****
## Project structure:
* **com.mycomp.mymessagesys.controller**:  
RestControllerInterface.java  
UsersController.java  
MessageController.java  
CommentController.java  

* **com.mycomp.mymessagesys.service**:  
UserService.java  
UserServiceImpl.java    
MessageService.java  
MessageServiceImpl.java  
CommentService.java  
CommentServiceImpl.java


* **com.mycomp.mymessagesys.repository**:  
UserDAO.java  
MessageDAO.java  
CommentDAO.java  

* **com.mycomp.mymessagesys.model**:  
UserDTO.java
MessageDTO.java  
CommentDTO.java

* **com.mycomp.mymessagesys.service.exceptions**:   
InvalidMessageIdException.java  
InvalidUserIdException.java

****

## Tests:
1. Unit tests  
1.1. Per Controllers:   
com.mycomp.mymessagesys.controller.unit_tests.**UsersControllerTest.java**  
com.mycomp.mymessagesys.controller.unit_tests.**MessageControllerTest.java**  
com.mycomp.mymessagesys.controller.unit_tests.**CommentControllerTest.java**  
1.2. Per Services:  
com.mycomp.mymessagesys.service.unit_tests.**UserServiceImplTest.java**  
com.mycomp.mymessagesys.service.unit_tests.**MessageServiceImplTest.java**  
com.mycomp.mymessagesys.service.unit_tests.**CommentServiceImplTest.java**  
2. Component Integration Tests  
2.1 Per Entity repository:  
com.mycomp.mymessagesys.repository.component_integration_tests.**TestUserRepo.java**  
com.mycomp.mymessagesys.repository.component_integration_tests.**TestMessageRepo.java** (TBD)  
com.mycomp.mymessagesys.repository.component_integration_tests.**TestCommentRepo.java** (TBD)  
2. System Integration Tests  
2.1 Per Entity repository:  
com.mycomp.mymessagesys.repository.system_integration_tests.**TestUserRepo.java** (TBD)  
com.mycomp.mymessagesys.repository.system_integration_tests.**TestMessageRepo.java** (TBD)  
com.mycomp.mymessagesys.repository.system_integration_tests.**TestCommentRepo.java** (TBD)  

****

## Pending technical issues:
1. Complete all the (TBD) tests  
2. Add loggings
3. Solve the open bugs
****

## Open Bugs:
1. 'Get all Messages' call retrieved also all Comments (could be due to the fact that  Message is super class to Comment).(TBD)
