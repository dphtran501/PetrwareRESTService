# PetrwareRESTService
RESTful web service for the [Petrware](https://github.com/dphtran501/inf124-project1) e-commerce site.

## Installation 

To connect to the MySQL database, the source code must include a `Credentials.java` file containing the user's database 
credentials. The file is structured as so:

```java
public class Credentials {
    public static final String DB_USERNAME = "YOUR_USERNAME";
    public static final String DB_PASSWORD = "YOUR_PASSWORD";
    public static final String DATABASE = "petrware_db";
    public static final String HOSTNAME = "YOUR_HOST_NAME";
    public static final int PORT_NUMBER = "YOUR_PORT_NUMBER";
}
```
`PORT_NUMBER` is the port number used for MySQL. `Credentials.java` must be added to the [`db`](src/main/java/edu/uci/inf124/petrwarerestservice/db) folder.

## Requirements Checklist

- [ ] **Using JSP reimplement the product list page. This is the page that contains the list of your products.**

  Refer to the [Petrware](https://github.com/dphtran501/inf124-project1) web client for this requirement.

- [x] **Create REST services to allow for interaction with the order and product resources stored in your application 
database.
  
    - [`getAllProducts()`](https://github.com/dphtran501/PetrwareRESTService/blob/ecc785bc2cbc62549e23e5ea5bcc400d14632855/src/main/java/edu/uci/inf124/petrwarerestservice/resource/ProductResource.java#L15)
      - GET
      - Request URL: <SERVER_URL>/v1/api/products
      - Sample Response:
        ```json
        {
          "productCPUList": [
            {
                "id": 1,
                "model": "BX80684I99900K",
                "brand": "Intel",
                "name": "Core i9-9900K",
                "series": "Core i9 9th Gen",
                "displayName": "Intel Core i9-9900K",
                "category": "cpu",
                "price": 504.99,
                "description": "Introducing the NEW 9th Gen Intel Core desktop processors the first unlocked mainstream desktop processor. When paired with Intel Optane memory, accelerates the loading and launching of the games you play. With up to 5GHz and 16-way multitasking, take your creativity to the next level with the power you need to create, edit, and share.",
                "imgSrc": "core_i9-9900K.png",
                "processorsType": "Desktop",
                "socketType": "LGA 1151 (300 series)",
                "coreName": "Coffee Lake",
                "numOfCores": 8,
                "numOfThreads": 16,
                "operatingFrequency": 3.6,
                "maxTurboFrequency": 5.0
            },
            ...
            ],
            "productRAMList": [...],
            "productVCList": [...]
        }
        ```
  
    **Ensure that proper REST principles and conventions are followed while creating your REST services. For example: A 
    GET method should be used only for retrieving an existing resource, A POST method should be used for creating a new 
    resource, etc. Do not forget to perform validation for certain methods. For instance, when implementing methods like
     GET, PUT and DELETE to interact with existing resources, you will need to verify if the resource being queried 
     actually exists. If the resource is not found. You will need to throw a 404 - Not Found response.**
  
    **You are required to create your new REST service application in Java. It is mandatory that you use the Jersey REST 
    framework.  For the scope of this assignment, it is sufficient that your REST services communicate in JSON. (You are 
    free to implement support for other media types, but you will have to make sure that your e-commerce web application 
    communicates successfully with the REST service.)**
    
- [ ] **You will now need to replace all the database interactions in your web application with REST calls. Your web 
application will now act as a REST client and retrieve the MySQL data indirectly through the new RESTful web service. 
That is, you will have two applications: (1) a backend application that provides RESTful APIs that essentially exposes 
the available operations in your database, and (2) an application that is the client of the RESTful APIs, generates the 
HTML pages, and handles requests from the user.  While in this assignment you are developing both applications yourself, 
in practice, each application may be developed by a separate company. For example, companies such as Google, Amazon, and 
PayPal may develop the RESTful APIs that allow others to leverage their services in building their web applications.** 

    **In the readme file, provide proper documentation highlighting the details for each RESTful service method that you 
implement. Your documentation should include the following at the very least**
    - **Method Type**
    - **Request URL**
    - **Sample Response**
    - **Sample Request (if applicable)**

## Authors
Group 21: Jevford Barro (72114221), Peter Tang (45193375), Derek Tran (18491795)

## Project Status
- [x] [PA 1: Building a dynamic website using HTML, JavaScript, and CSS](https://github.com/dphtran501/inf124-project1/blob/master/docs/pa1.md)
- [x] [PA 2: Building a web application using PHP, Ajax, and MySQL](https://github.com/dphtran501/inf124-project1/blob/master/docs/pa2.md) 
- [X] [PA 3: Building a web application using Java Servlets](https://github.com/dphtran501/inf124-project1/blob/master/docs/pa3.md)
- [ ] PA 4: Building a web application using JSP and RESTful web services 
