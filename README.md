# Calculator API
Calculator API for getting net price based on country ISO

## Table of Contents
+ [About](#about)
+ [Getting Started](#getting_started)
+ [Usage](#usage)

## About <a name = "about"></a>
The purpose of this project is calculate net price for each country based on their VAT (value added tax). In order to get net price, there is a need to use gross price and country ISO code. In addition, it is allowed to add, retrieve, update and delete VAT information for each country. 
There are two APIs. First, /calculator for calculating net price. Second, /region for adding, retrieving, updating and deleting VAT information for each country based on its country ISO code.
It has swagger dependency which allows to test APIs after application is running either locally or in a server.

## Getting Started <a name = "getting_started"></a>
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them.

```
Java 8
Gradle 8.2.1
```

### Installing

A step by step series of examples that tell you how to get a development env running.

In order to run in development mode

```
./gradlew clean build
java -jar .\build\libs\calculator-0.0.1-SNAPSHOT.jar com.globalsavings.calculator.CalculatorApplication
```

## Usage <a name = "usage"></a>

There six APIs which split into two groups. These groups are <b>Calculator</b> and <b>Region</b>.

### Calculator

<details>

<summary><b>GET</b> /api/calculator/{grossPrice}/{countryIso}</summary>

<b>URL Params:</b>
```
curl -X GET "http://localhost:8080/api/calculator/100/DE" -H "accept: application/json"
```

<b>Required:</b>
<code>grossPrice </code>
<code>countryIso</code>

<b>Data Params:</b>
None

<b>Success Response:</b>
Code: 200
Content: 
<code>
{
  "grossPrice": 100,
  "netPrice": 85,
  "countryIso": "DE"
}
</code>

</details>

### Region

<details>

<summary><b>POST</b> /api/region</summary>

<b>URL Params:</b>
```
curl -X POST "http://localhost:8080/api/region" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"countryIso\": \"DE\", \"countryName\": \"Germany\", \"vat\": 0.15}"
```

<b>Required:</b>
All parameters required.

<b>Data Params:</b>
<code>
{
  "countryIso": "DE",
  "countryName": "Germany",
  "vat": 0.15
}
</code>

<b>Success Response:</b>
Code: 201
Content: 
<code>
{
  "countryIso": "DE",
  "countryName": "Germany",
  "vat": 0.15
}
</code>
</details>

<details>

<summary><b>PUT</b> /api/region</summary>

<b>URL Params:</b>
```
curl -X PUT "http://localhost:8080/api/region" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"countryIso\": \"DE\", \"countryName\": \"Germany\", \"vat\": 0.20}"
```

<b>Required:</b>
All parameters required.

<b>Data Params:</b>
<code>
{
  "countryIso": "DE",
  "countryName": "Germany",
  "vat": 0.20
}
</code>

<b>Success Response:</b>
Code: 200
Content: 
<code>
{
  "countryIso": "DE",
  "countryName": "Germany",
  "vat": 0.20
}
</code>
</details>

<details>

<summary><b>GET</b> /api/region/{countryIso}</summary>

<b>URL Params:</b>
```
curl -X GET "http://localhost:8080/api/region/DE" -H "accept: application/json"
```

<b>Required:</b>
<code>countryIso</code>

<b>Data Params:</b>
None

<b>Success Response:</b>
Code: 200
Content: 
<code>
{
  "countryIso": "DE",
  "countryName": "Germany",
  "vat": 0.20
}
</code>

</details>

<details>

<summary><b>DELETE</b> /api/region/{countryIso}</summary>

<b>URL Params:</b>
```
curl -X DELETE "http://localhost:8080/api/region/DE" -H "accept: application/json"
```

<b>Required:</b>
<code>countryIso</code>

<b>Data Params:</b>
None

<b>Success Response:</b>
Code: 204
Content: No Content
</details>

<details>

<summary><b>GET</b> /api/region/all</summary>

<b>URL Params:</b>
```
curl -X GET "http://localhost:8080/api/region/all" -H "accept: application/json"
```

<b>Required:</b>
None

<b>Data Params:</b>
None

<b>Success Response:</b>
Code: 200
Content: 
<code>
[
  {
    "countryIso": "DE",
    "countryName": "Germany",
    "vat": 0.2
  }
]
</code>
</details>
