
Java 11 and Spring Boot is used for this project 
dependencies used as follows 

![img_3.png](img_3.png)

for the logging Sl4J is used and logs copied to the logs folder with below properties 
![img_6.png](img_6.png)
![img_2.png](img_2.png)

The postman test results screens as follows

![img_1.png](img_1.png)
![img.png](img.png)

Here are the logged requests and responses with the correlation id which is 
embedded by using the interceptors. correlation id is important when working with the
multiple threads with high range of requests.

![img_4.png](img_4.png)
![img_5.png](img_5.png)