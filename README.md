# PetrwareRESTService
RESTful web service for the [Petrware](https://github.com/dphtran501/PetrwareRESTClient) e-commerce site.

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

- [x] **Using JSP reimplement the product list page. This is the page that contains the list of your products.**

  Refer to the [Petrware](https://github.com/dphtran501/PetrwareRESTClient) web client for this requirement.

- [x] **Create REST services to allow for interaction with the order and product resources stored in your application 
database.**

    The REST service is created using the Jersey REST framework. The REST services communicate mainly in JSON, though some services may produce TEXT/PLAIN responses. The implemented RESTful service methods are as follows:
  
    - `getAllProducts()`
      - GET
      - Request URL: {SERVER_URL}/v1/api/products
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
                    "description": "Introducing the NEW 9th Gen Intel Core desktop processors ...",
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
        
    - `getProduct(int id)`
      - GET
      - Request URL: {SERVER_URL}/v1/api/products/{ID}
      - Sample Response (if ID is 3):
        ```json
        {
            "id": 3,
            "model": "100-100000163WOF",
            "brand": "AMD",
            "name": "Ryzen Threadripper 3990X",
            "series": "Ryzen Threadripper 3rd Gen",
            "displayName": "AMD Ryzen Threadripper 3990X",
            "category": "cpu",
            "price": 4063.31,
            "description": "Announced at CES 2020, AMD's 3990X Threadripper processor ...",
            "imgSrc": "ryzen_threadripper_3990x.png",
            "processorsType": "Desktop",
            "socketType": "Socket sTRX4",
            "coreName": null,
            "numOfCores": 64,
            "numOfThreads": 128,
            "operatingFrequency": 2.9,
            "maxTurboFrequency": 4.3
        }
        ```
        
    - `getSearchProducts(String query)`
      - GET
      - Request URL: {SERVER_URL}/v1/api/products/search?query={QUERY}
      - Sample Response (if QUERY is CORSAIR):
        ```json
        {
            "productCPUList": [],
            "productRAMList": [
                {
                    "id": 4,
                    "model": "CMW16GX4M2C3200C16",
                    "brand": "CORSAIR",
                    "name": null,
                    "series": "Vengeance RGB Pro",
                    "displayName": "CORSAIR Vengeance RGB Pro",
                    "category": "ram",
                    "price": 99.99,
                    "description": "CORSAIR VENGEANCE RGB PRO Series DDR4 memory lights up your PC ...",
                    "imgSrc": "vengeance_rgb_pro.png",
                    "capacity": "16GB (2 x 8GB)",
                    "speed": "DDR4 3200 (PC4 25600)",
                    "latency": 16,
                    "timing": "16-18-18-36",
                    "color": "Black",
                    "colorLED": "RGB"
                }
            ],
            "productVCList": []
        }
        ```
        
    - `getCustomer(int id)`
      - GET
      - Request URL: {SERVER_URL}/v1/api/customers/{ID}
      - Sample Response (if ID is 98):
        ```json
        {
            "id": 98,
            "firstName": "Gucci",
            "lastName": "Rich",
            "phone": "777-123-4567",
            "country": "Canada",
            "streetAddress": "777 Gucci Circle",
            "city": "Beverly Hills",
            "state": "CA",
            "zipcode": "90210",
            "shipping": "7 Day Shipping",
            "email": "rich@gucci.com"
        }
        ```
        
    - `postCustomer()`
      - POST
      - Request URL: {SERVER_URL}/v1/api/customers
      - Sample Response:
        ```json
        {
            "id": 101,
            "firstName": null,
            "lastName": null,
            "phone": null,
            "country": null,
            "streetAddress": null,
            "city": null,
            "state": null,
            "zipcode": null,
            "shipping": null,
            "email": null
        }
        ```
        
    - `putCustomer(Customer customer)`
      - PUT
      - Request URL: {SERVER_URL}/v1/api/customers
      - Sample Response:
        `Customer updated successfully`
      - Sample Request Body:
        ```json
        {
            "id": 101,
            "firstName": "Test",
            "lastName": "TestLast",
            "phone": "1111111111",
            "country": "United States",
            "streetAddress": "111 1st Ave",
            "city": "Tustin",
            "state": "CA",
            "zipcode": "92780",
            "shipping": "Anteater Express",
            "email": "test@uci.edu"
        }
        ```
        
    - `getCreditCard(int id)`
      - GET
      - Request URL: {SERVER_URL}/v1/api/creditcards/{ID}
      - Sample Response (if ID is 98):
        ```json
        {
            "id": 98,
            "cardNumber": "7777777777777",
            "expiration": "9/2020",
            "securityCode": "007"
        }
        ```
        
    - `postCreditCard(CreditCard card)`
      - POST
      - Request URL: {SERVER_URL}/v1/api/creditcards
      - Sample Response:
        `Credit card added successfully`
      - Sample Request Body:
        ```json
        {
            "id": 101,
            "cardNumber": "12345678912340",
            "expiration": "01/2021",
            "securityCode": "123"
        }
        ```
        
    - `getZipcodeData(String zipcode)`
      - GET
      - Request URL: {SERVER_URL}/v1/api/checkout/zipcode?zipcode={ZIPCODE}
      - Sample Response (if ZIPCODE is 10003):
        ```json
        {
            "zipcode": "10003",
            "city": "New York",
            "state": "NY",
            "combinedRate": 0.08875
        }
        ```
    
- [x] **You will now need to replace all the database interactions in your web application with REST calls. Your web 
application will now act as a REST client and retrieve the MySQL data indirectly through the new RESTful web service. 
That is, you will have two applications:** 

    **(1) a backend application that provides RESTful APIs that essentially exposes the available operations in your database**
    
    PetrwareRESTService is the backend application providing RESTful APIs.
    
    **(2) an application that is the client of the RESTful APIs, generates the HTML pages, and handles requests from the user.**
    
    [PetrwareRESTClient](https://github.com/dphtran501/PetrwareRESTClient) is the client of PetrwareRESTService.

## Authors
Group 21: Jevford Barro (72114221), Peter Tang (45193375), Derek Tran (18491795)
