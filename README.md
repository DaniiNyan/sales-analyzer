# Sales Analyzer
- [Challenge](https://github.com/DaniiNyan/sales-analyzer#challenge)
- [Architecture](https://github.com/DaniiNyan/sales-analyzer#architecture)
- [Getting Started](https://github.com/DaniiNyan/sales-analyzer#getting-started)

## Challenge

#### Description
Build a data analyzer system which produces a report based on imported batches
of files with sales data. There are 3 data types inside the imported files. It's
possible to distinguish them by the first column's ID of each line.
The letter "ç" is used as the column's delimiter.

#### Data Example

- Seller data: ID 001
```
001çCPFçNameçSalary
```

- Customer data: ID 002
```
002çCNPJçNameçBusiness Area
```

- Sale data: ID 003
```
003çSale IDç[Item ID-Item Quantity-ItemPrice]çSaller name
```

- There's a file example:
```
001ç1234567891234çPedroç50000
001ç3245678865434çPauloç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardoPereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
```

#### Business Requirements
The system must continuously read all files inside the default folder `HOMEPATH/data/in`
and write the final report in `HOMEPATH/data/out`. The report file must have the following data:  
- Quantity of customers in data files;
- Quantity of sellers in data files;
- ID from most expensive sale;
- The worst seller;

#### Technical Requirements
- The system must continuously run and catch all new files when inserted in default folder;
- You can use any external library if necessary; 

## Architecture
`FolderWatcher` is responsible for listen to the default folder and call 
`DataService` whenever it has any updates. `DataService` analyzes all data, 
it uses `FileDAO` to read all files and write the final report. `DataMapper` is 
used to map lines into objects to be analyzed.  

It's possible to define the default folder to read files and write the report 
through the `application.properties` file. The `PropertiesHelper` is responsible 
for globally manage the values set in `application.properties`, granting to use the same value everywhere it is needed.  

Since this is a data analyzer, I'm assuming that all files received are following 
the correct data pattern, so the system won't accept wrong files.

## Getting Started
To run this application you will need [Java 8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html).

- Clone this repo:
```
git clone https://github.com/DaniiNyan/sales-analyzer.git
```

- Navigate to its folder and build the project:  
```
./gradlew build  
```

- Run the application: 
```
java -jar build/libs/analyzer-1.0-SNAPSHOT.jar
```

**Done!**  
Now you just need to add some data files, so the report will automatically update.
There are sample data in the default folder, feel free to delete or modify it. 

