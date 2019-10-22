## 1. Abstract Document Pattern (Structural)

An object-oriented structural design pattern for organizing objects in loosely typed key-value stores and exposing the data using typed views. The purpose of the pattern is to achieve a high degree of flexibility between components in a strongly typed language where new properties can be added to the object-tree on the fly, without losing the support of type-safety. 

[Code](https://github.com/iluwatar/java-design-patterns/tree/master/abstract-document)

#### Advantages 
The document structure is easy to expand and build upon as your system grows. Different subsystems can expose different data through the trait-interfaces. The same map can be viewed as different types depending on which constructor was used to generate the view. Another advantage is that the whole object hierarchy exists in one single Map which means that it is easy to serialize and deserialize using existing libraries, for example Google’s gson tool.

#### Disadvantage

Wiki : The abstract factory pattern provides a way to encapsulate a group of individual factories that have a common theme without specifying their concrete classes

One disadvantage is that it is less secure than a regular beans-structure. A component can be modified from multiple places through multiple interfaces which might make the code less testable
-----------------------------
## Abstract-Factory (Creational)
[src](https://refactoring.guru/design-patterns/abstract-factory/java/example)


Abstract Factory is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.

The Abstract Factory pattern is pretty common in Java code. Many frameworks and libraries use it to provide a way to extend and customize their standard components.

[Code](https://github.com/iluwatar/java-design-patterns/tree/master/abstract-factory)

! The client shouldn’t care about the concrete class of the factory it works with.

##### Benefits 
[src](https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java)

1. Abstract Factory design pattern provides approach to code for interface rather than implementation.

2. Abstract Factory pattern is “factory of factories” and can be easily extended to accommodate more products, for example we can add another sub-class Laptop and a factory LaptopFactory.

3. Abstract Factory pattern is robust and avoid conditional logic of Factory pattern.

Real world examples  

javax.xml.parsers.DocumentBuilderFactory#newInstance()
javax.xml.transform.TransformerFactory#newInstance()
javax.xml.xpath.XPathFactory#newInstance()


-----------------------------

## Acyclic-Visitor (Behavioral)
[src](https://condor.depaul.edu/dmumaugh/OOT/Design-Principles/acv.pdf)
[code](https://github.com/iluwatar/java-design-patterns/tree/master/acyclic-visitor)

Allow new functions to be added to existing class hierarchies without affecting those hierarchies, and without creating
the troublesome dependency cycles that are inherent to the GOF VISITOR Pattern.

Procedural software can be written in such a way that new functions can be added to existing data structures without
affecting those data structures. Object oriented software can be written such that new data structures can be used by
existing functions without affecting those functions

#### This pattern can be used:

When you need to add a new function to an existing hierarchy without the need to alter or affect that hierarchy.
When there are functions that operate upon a hierarchy, but which do not belong in the hierarchy itself. e.g. the ConfigureForDOS / ConfigureForUnix / ConfigureForX issue.
When you need to perform very different operations on an object depending upon its type.
When the visited class hierarchy will be frequently extended with new derivatives of the Element class.
When the recompilation, relinking, retesting or redistribution of the derivatives of Element is very expensive.