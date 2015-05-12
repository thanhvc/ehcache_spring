####EhCache EhCache How to
##Recipe 14 (Spring Cache Abstraction)

In this recipe you will be shown how to use built-in EhCache support in recent cache abstraction API in Spring 3.1 and higher. 
We will be using an annotation-based approach but will follow the traditional EhCache XML configuration. 

Build Dependencies
-------

| Requirement      |  Version   |
|------------------|:----------:|
|  Apache Maven    |    3.x     |
|  Java JDK        |    >= 6    |
|  Eclipse         | >= Helios  |


Building The Recipe
-------
1. Compile: mvn clean jetty:run
2. goto http://localhost:8080/recipe14/
3. Insert Employee: Clicks on Insert button
4. Gets the Employee by Id: Fill Id in textbox and Clicks on Load button


Implemented:

1. Creates: CachedEmployeeDao class what invokes the Dao service to populate data from DB.
