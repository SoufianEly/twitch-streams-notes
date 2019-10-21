
[src](https://www.geeksforgeeks.org/polymorphism-in-java/)


#### In practice, inheritance and polymorphism are used together in java to achieve fast performance and readability of code.


## Polymorphism :
 A person at the same time can have different characteristic. Like a man at the same time is a father, a husband, an employee. So the same person posses different behaviour in different situations. This is called polymorphism. The word “poly” means many and “morphs” means forms, So it means many forms.

### Compile time Polymorphism 


It is also known as static polymorphism. This type of polymorphism is achieved by function overloading or operator overloading.

#### 1. Method Overloading

When there are multiple functions with same name but different parameters then these functions are said to be overloaded

static double Multiply(double a, double b) 
static int Multiply(int a, int b) 

static int Multiply(int a, int b) 
static int Multiply(int a, int b, int c) 

#### 2. Operator Overloading 

Java also provide option to overload operators

void operator(String str1, String str2)
void operator(int a, int b) 

### Runtime Polymorphism

Runtime polymorphism: It is also known as Dynamic Method Dispatch. It is a process in which a function call to the overridden method is resolved at Runtime. This type of polymorphism is achieved by Method Overriding.

Method overriding, on the other hand, occurs when a derived class has a definition for one of the member functions of the base class. That base function is said to be overridden.


## Why & when we use ? 

1.  If you have several classes that seem to be doing the same thing a lot, you can "generalize" those duplicate methods into a new base class
2.  Inheritance is a very strong form of coupling. That is, the extended class has a strong dependency on the base class. Changes in the base class can break the extended class.


-------------------

[src](https://www.geeksforgeeks.org/inheritance-in-java/)

### Problems with Polymorphism 
[src](https://www.baeldung.com/java-polymorphism)

#### Type Identification During Downcasting
#### Fragile Base Class Problem

## Inheritance 

Important terminology:

Super Class | Sub Class | Reusability

In below example of inheritance, class Bicycle is a base class, class MountainBike is a derived class which extends Bicycle class and class Test is a driver class to run program.

#### 1. Single 

In single inheritance, subclasses inherit the features of one superclass. In image below, the class A serves as a base class for the derived class B.

#### 2. Multilevel  

In Multilevel Inheritance, a derived class will be inheriting a base class and as well as the derived class also act as the base class to other class A -> B -> C

#### 3. Hierarchical

In Hierarchical Inheritance, one class serves as a superclass (base class) for more than one sub class.In below image, the class A serves as a base class for the derived class B,C and D. A -> B | A -> C | A -> D

#### 4. Multiple 
In Multiple inheritance ,one class can have more than one superclass and inherit features from all parent classes. 
!!! Please note that Java does not support multiple inheritance with classes (Just interface).Java doesn’t allow multiple inheritance to avoid the ambiguity caused by it. One of the example of such problem is the diamond problem that occurs in multiple inheritance.
interface three extends one,two A -> C | B -> C
class child implements three 

#### 5. Hybrid Inheritance 

It is a mix of two or more of the above types of inheritance. Since java doesn’t support multiple inheritance with classes, the hybrid inheritance is also not possible with classes. In java, we can achieve hybrid inheritance only through Interfaces.

Default superclass : Object

### Inheriting Constructors:

 A subclass inherits all the members (fields, methods, and nested classes) from its superclass. Constructors are not members, so they are not inherited by subclasses, but the constructor of the superclass can be invoked from the subclass.

### Private member inheritance:

 A subclass does not inherit the private members of its parent class. However, if the superclass has public or protected methods(like getters and setters) for accessing its private fields, these can also be used by the subclass.

---------------------------------

## Encapsulation 
[src](https://www.geeksforgeeks.org/encapsulation-in-java/)

Encapsulation is defined as the wrapping up of data under a single unit. It is the mechanism that binds together code and the data it manipulates.Other way to think about encapsulation is, it is a protective shield that prevents the data from being **accessed by the code outside this shield**.

#### Technically in encapsulation, the variables or data of a class is hidden from any other class and can be accessed only through any member function of own class in which they are declared.

#### As in encapsulation, the data in a class is hidden from other classes, so it is also known as **data-hiding**.

#### Encapsulation can be achieved by: Declaring all the variables in the class as private and writing public methods in the class to set and get the values of variables.

### Why we use ? 
[src](https://www.quora.com/What-is-the-need-of-encapsulation)

For example I develop a website to dispaly bank account by using your library. Now I do not access the bank database directly but I just use your classes to fetch the details. Now, as you made the variables public I got power of replacing the account balance variable although I should not have that permission but developers mistake gave me that permission!

Encapsulation helps us in binding the data(instance variables) and the member functions(that work on the instance variables) of a class.
Encapsulation is also useful in hiding the data(instance variables) of a class from an illegal direct access.
Encapsulation also helps us to make a flexible code which is easy to change and maintain.

Look at that [src](https://dzone.com/articles/why-encapsulation-matters) [src](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.36.8552&rep=rep1&type=pdf)

private double latitude;
private double longitude;

public void setLongitude( double longitude ){
    // Ensure -180 < longitude <= 180 using modulo arithmetic. ! 
    // Code not shown.
    // Then set instance variable.
    this.longitude = longitude;
}

Position myHouse = new Position( 36.538611, -121.797500 );


##### Defensive programming is a form of defensive design intended to ensure the continuing function of a piece of software under unforeseen circumstances. Defensive programming practices are often used where high availability, safety or security is needed.
[src](https://en.wikipedia.org/wiki/Defensive_programming) 
##### Offensive programming is a category of defensive programming, with the added emphasis that certain errors should not be handled defensively. In this practice, only errors from outside the program's control are to be handled (such as user input); the software itself,as well as data from within the program's line of defense, are to be trusted in this methodology.


----------------

## Abstraction 
[src](https://www.geeksforgeeks.org/abstraction-in-java-2/)

Data Abstraction is the property by virtue of which only the essential details are displayed to the user.The trivial or the non-essentials units are not displayed to the user. Ex: A car is viewed as a car rather than its individual components.

### Abstract classes and Abstract methods :

##### An abstract class is a class that is declared with abstract keyword.
##### An abstract method is a method that is declared without an implementation.
##### An abstract class may or may not have all abstract methods. Some of them can be concrete methods
##### A method defined abstract must always be redefined in the subclass,thus making overriding compulsory OR either make subclass itself abstract.
##### Any class that contains one or more abstract methods must also be declared with abstract keyword.
##### There can be no object of an abstract class.That is, an abstract class can not be directly instantiated with the new operator.
##### An abstract class can have parametrized constructors and default constructor is always present in an abstract class.

### Encapsulation vs Data Abstraction

Encapsulation is data hiding(information hiding) while Abstraction is detail hiding(implementation hiding).
While encapsulation groups together data and methods that act upon the data, data abstraction deals with exposing the interface to the user and hiding the details of implementation.

### Advantages of Abstraction

It reduces the complexity of viewing the things.
Avoids code duplication and increases reusability.
Helps to increase security of an application or program as only important details are provided to the user.

### Important rules for abstract methods:

##### Any class that contains one or more abstract methods must also be declared abstract
##### The following are various illegal combinations of other modifiers for methods with respect to abstract modifier

final
abstract native
abstract synchronized
abstract static
abstract private
abstract strictfp

-------------

## Interfaces 

Like a class, an interface can have methods and variables, but the methods declared in interface are by default abstract (only method signature, no body).  

##### Interfaces specify what a class must do and not how. It is the blueprint of the class.
##### An Interface is about capabilities like a Player may be an interface and any class implementing Player must be able to (or must implement) move(). So it specifies a set of methods that the class has to implement.
##### If a class implements an interface and does not provide method bodies for all functions specified in the interface, then class must be declared abstract.
##### A Java library example is, Comparator Interface. If a class implements this interface, then it can be used to sort a collection.

### Why do we use interface ?

It is used to achieve total abstraction.
Since java does not support multiple inheritance in case of class, but by using interface it can achieve multiple inheritance .
It is also used to achieve loose coupling.
Interfaces are used to implement abstraction. So the question arises why use interfaces when we have abstract classes?
The reason is, abstract classes may contain non-final variables, whereas variables in interface are final, public and static.

### Abstract class vs Interface
[src](https://www.geeksforgeeks.org/difference-between-abstract-class-and-interface-in-java/)

##### Type of methods: 
Interface can have only abstract methods. Abstract class can have abstract and non-abstract methods. From Java 8, it can have default and static methods also.

##### Final Variables: 
Variables declared in a Java interface are by default final. An abstract class may contain non-final variables.
Type of variables: Abstract class can have final, non-final, static and non-static variables. Interface has only static and final variables.

##### Implementation: 
Abstract class can provide the implementation of interface. Interface can’t provide the implementation of abstract class.
Inheritance vs Abstraction: A Java interface can be implemented using keyword “implements” and abstract class can be extended using keyword “extends”.

##### Multiple implementation:
An interface can extend another Java interface only, an abstract class can extend another Java class and implement multiple Java interfaces.

##### Accessibility of Data Members:
Members of a Java interface are public by default. A Java abstract class can have class members like private, protected, etc.

### When to use what?

##### Abstract

In java application, there are some related classes that need to share some lines of code then you can put these lines of code within abstract class and this abstract class should be extended by all these related classes.

You can define non-static or non-final field(s) in abstract class, so that via a method you can access and modify the state of Object to which they belong.

You can expect that the classes that extend an abstract class have many common methods or fields, or require access modifiers other than public (such as protected and private).

##### Interface

It is total abstraction, All methods declared within an interface must be implemented by the class(es) that implements this interface.

A class can implement more than one interface. It is called multiple inheritance.

You want to specify the behavior of a particular data type, but not concerned about who implements its behavior.