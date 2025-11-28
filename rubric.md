# Project Assessment Rubric

**Project:** Pet Adoption System
**Section:** C2A
**Course:** Object Oriented Programming

## Grading Breakdown

### 1. Class Diagram Completeness (25%)
**Score:** 5/5
**Notes:** Complete UML class diagram provided in both PNG format (UML diagram.png) and via Figma link (https://www.figma.com/board/rxqSN5dzyKFtmuO4DkBJ4g/UMLOOP). The diagram clearly shows all classes (Person, Adopter, Pet, Staff, Adoption System) with proper notation for inheritance relationships, attributes, methods, visibility modifiers, and associations. Well-structured and professional diagram that accurately represents the implementation.

### 2. Java Program - OOP Concepts (50%)
**Score:** 5/5
**Notes:**
- **Encapsulation (Excellent):** Strong encapsulation demonstrated with private fields in Pet class (petName, petType, petBreed, petGender, isAvailable) and protected fields in Person class. Public getter methods provide controlled access to private data. Adopter class properly encapsulates contactNumber as private.
- **Inheritance (Excellent):** Clear inheritance hierarchy with Person as base class and Adopter extending Person. Protected fields (name, address, gender) properly inherited from parent class. Demonstrates proper use of inheritance to model real-world relationships.
- **Polymorphism (Strong):** Method overriding demonstrated with Adopter class overriding setPersonInfo() method to include additional contactNumber parameter. Also displays proper use of super class methods through displayInfo() call in displayAdopterInfo(). Polymorphic behavior with method overloading.
- **Abstraction (Good):** Clean separation of concerns with distinct classes for different entities (Person, Adopter, Pet). Main class handles business logic while entity classes focus on data representation. Methods like getAdoptionFee() abstract pricing logic based on pet type.

Excellent implementation of all four OOP concepts with practical real-world application. The code demonstrates mature understanding of object-oriented principles with file I/O for receipt generation and proper data validation throughout.

### 3. Git Usage & Collaboration (15%)
**Score:** 2/5
**Notes:** Minimal git usage with 8 total commits from 2 contributors (Kartzz1: 6 commits, Michael Ong: 2 commits). Appears to be primarily a solo project by John Carlo Escartin with external/instructor collaboration. Limited commit count and no evidence of peer collaboration among students. Basic version control demonstrated but minimal collaborative development.

### 4. Base Grade (10%)
**Score:** 5/5

---

### 5. Extra Points (up to 6)

**Features:** 3/5
- File saving
- Console experience

**Code Quality:** 1.0/1.0
- Variable naming: 0.5/0.5
- Code organization: 0.5/0.5

**Extra Points Total:** +4.0

---

## Final Grade: 85 + 4.0 = **89/100**

*Assessment generated based on project analysis.*
