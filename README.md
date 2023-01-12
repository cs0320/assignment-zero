# Coming to CSCI 0320 Directly from 0190

Students who complete CSCI 0190 may move directly to intermediate and upper-level courses without taking CSCI 0200. However, as the [intro course guide](https://cs.brown.edu/degrees/undergrad/whatcourse/) says:

>Many students go on to take 0200 after 0190, especially if they did not have a strong grounding in object-oriented programming prior to starting at Brown.

If you plan to move directly from 0190 to 0320, you should be aware that 0320 assumes a baseline familiarity with object-oriented programming in Java. While we do review _some_ OO material, there are many vital ideas that we can't cover, either in class or in gear-up sessions. There is no one assignment, or collection of readings, that can substitute for a full-semester experience in 0200---but for those of you who plan to skip 0200 regardless, this assignment is meant to provide some guidance. 

**This document focuses entirely on Java and OO, not setup of the tools 0320 uses. (The first sprint in 0320, CSV, focuses heavily on setup.)**

## Readings

We strongly suggest making use of the support material that 0320 provides, especially access to Joshua Bloch's _Effective Java (3e)_, which is available online via Brown's library. _Effective Java_ is not a "Java textbook"---you might consult 0200's lecture notes for that purpose. Rather, it's a series of focused OO design tips written by one of Java's authors; some are Java-specific, but often they even contain useful wisdom for OO programming outside Java. 

You should be able to access the book in two steps. 
* Navigate [here](https://www.oreilly.com/library/view/temporary-access/) to setup your Brown-affiliated account. Select "Brown University" and log in---note this login is Brown-managed.
* Access the online reading [here](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/). Either click "Continue" or use the table-of-contents menu in the upper-right corner of the screen to begin.

Here is a non-exhaustive list of readings in _Effective Java_ that will be useful in preparing for 0320. Some of these will be assigned reading for everyone early in the semester, but we suggest not waiting---develop an appreciation for the subtleties of OO _before_ you're under pressure to resolve them.

* Encapsulation 
  * Item 15: Minimize the accessibility of classes and members
  * Item 16: In public classes, use accessor methods, not public fields
  * Item 17: Minimize mutability
  * Item 18: Favor composition over inheritance

* The power and dangers of inheritance
  * Item 10: Obey the general contract when overriding equals
  * Item 11: Always override hashCode when you override equals
  * Item 12: Always override toString

* Generic types
  * Item 26: Don't use raw types

* Exceptions
  * Item 73: Throw exceptions appropriate to the abstraction

## Anticipated Questions

* Q: Will working in Java mean that we can't program functionally?
  * A: No. The lessons you learned about functional programming will still be useful. E.g., Java has supported lambdas for quite some time. (See Item 42 in _Effective Java_.) The difference is that you'll need to learn _more_ concepts to program with objects and do it well.

* Q: Will you provide us with solutions to this (and other) assignments?
  * A: 0320 is a different sort of course. You're moving into a larger world, where there's almost always no perfect solution, especially when it comes to OO design. We'll review candidate sprint solutions in class, but the code reviewed will almost always have _both_ weaknesses and strengths. We won't be releasing a "solution" to this specific assignment prompt, but we'll have a discussion about it.

## The Assignment


* read through the code. what do you notice? questions?
* why do you think (fill)

* What would you do if you wanted to support a new kind of game---Go, for instance?
* 


* which tests? I only wrote one? 
empowered to change whatever

* 


