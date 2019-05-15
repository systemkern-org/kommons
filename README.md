# Kommons

[![Build Status](https://travis-ci.com/systemkern/kommons.svg?branch=master)](https://travis-ci.com/systemkern/kommons)

Kommons is a library offering useful extensions for handling recurring problems in a convenient and Kotlin idiomatic way.
The main features are:
* Extension properties for simple conversion between all number types
* Number Format, Date and String Conversion Extensions
* Extended collection lambdas
* [XML collection Api](#XML collection Api)
* [Spring Data Extensions](#Spring Data Extensions)

Kommons itself has no mandatory dependencies, tough it provides some extensions to Spring Data which are backed 
by optional dependencies. Please look at the pom.xml for further details regarding the exact versions. 


### Spring Data Extensions
The KtCrudRepository Interface is a extension for the Spring `org.springframework.data.repository.CrudRepository`.
Making the handing of the CrudRepository more Kotlin idiomatic and also providing a generic getById() function
for all repository and entity types. 

### XML Collection Api
Kommons provides an Api for `org.w3c.dom.Node` and `org.w3c.dom.NodeList`
which mimics the functionality and ease of use to the Kotlin Collection Api

### Extended Collection Api
Kommons provides compfortable union functions to combine (multiple) single items and (multiple) collections of the same type
 

## Maven Coordinates
```
<dependency>
    <groupId>com.systemkern</groupId>
    <artifactId>kommons</artifactId>
    <version>${kommons.version}</version>
</dependency>
```

[Maven Central Repository](https://repo.maven.apache.org/maven2/com/systemkern/kommons/)

[Sonatype Snapshot Repository](https://oss.sonatype.org/content/repositories/snapshots/com/systemkern/kommons/)

## Experimental
### InMemoryRepository
The InMemoryRepository is a basic for the base `org.springframework.data.repository.CrudRepository`.
It is intended mainly for testing purposes.
It emulates the basic CRUD functionality of a SpringData Repository but completely in memory.
Also findById(), getById() and delteById() work as expected
