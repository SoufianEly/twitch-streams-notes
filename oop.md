
[src](https://www.geeksforgeeks.org/polymorphism-in-java/)


#### In practice, inheritance and polymorphism are used together in java to achieve fast performance and readability of code.


## Polymorphism :
 A person at the same time can have different characteristic. Like a man at the same time is a father, a husband, an employee. So the same person posses different behaviour in different situations. This is called polymorphism. The word “poly” means many and “morphs” means forms, So it means many forms.

### Compile time Polymorphism 

#### It is also known as static polymorphism. This type of polymorphism is achieved by function overloading or operator overloading.

### 1. Method Overloading

When there are multiple functions with same name but different parameters then these functions are said to be overloaded

static double Multiply(double a, double b) 
static int Multiply(int a, int b) 

static int Multiply(int a, int b) 
static int Multiply(int a, int b, int c) 

### 2. Operator Overloading 

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

## Inheritance 

Important terminology:

Super Class | Sub Class | Reusability

In below example of inheritance, class Bicycle is a base class, class MountainBike is a derived class which extends Bicycle class and class Test is a driver class to run program.

1. Single : In single inheritance, subclasses inherit the features of one superclass. In image below, the class A serves as a base class for the derived class B.
2. Multilevel :  In Multilevel Inheritance, a derived class will be inheriting a base class and as well as the derived class also act as the base class to other class A -> B -> C 
3. Hierarchical : In Hierarchical Inheritance, one class serves as a superclass (base class) for more than one sub class.In below image, the class A serves as a base class for the derived class B,C and D. A -> B / A -> C / A -> D
4. Multiple :  In Multiple inheritance ,one class can have more than one superclass and inherit features from all parent classes. 
!!! Please note that Java does not support multiple inheritance with classes (Just interface) 
interface three extends one,two
class child implements three 
5. Hybrid Inheritance
