# A First Taste of CSCI 0320

This readme document gives some initial, non-exhaustive advice to those who want to do a bit of preparation for 0320 in the week before the semester starts. This document focuses entirely on Java and OO, not setup of the tools 0320 uses for Java, which is covered in class and at hours in the first week of the course. You can find our setup guide [here](https://docs.google.com/document/d/1gp96MAm-hyR6Is3EBY_KU8x9EH6p8drkL4NlTvbBTj4). 

## If you're coming to CSCI 0320 directly from 0190

Students who complete CSCI 0190 may move directly to intermediate and upper-level courses without taking CSCI 0200. However, as the [intro course guide](https://cs.brown.edu/degrees/undergrad/whatcourse/) says:

>Many students go on to take 0200 after 0190, especially if they did not have a strong grounding in object-oriented programming prior to starting at Brown.

We suggest taking 0200 before taking 0320. If you plan to move directly from 0190 to 0320, you should be aware that 0320 assumes a baseline familiarity with object-oriented programming in Java. While we do review _some_ OO material, there are many vital ideas that we can't cover, either in class or in gear-up sessions. There is no one assignment, or collection of readings, that can substitute for a full-semester experience in 0200---but for those of you who plan to skip 0200 regardless, the material below can provide some guidance. 

## Readings

We strongly suggest making use of the support material that 0320 provides, especially access to Joshua Bloch's _Effective Java (3e)_, which is available online via Brown's library. _Effective Java_ is not a "Java textbook"---you might consult 0200's lecture notes for that purpose. Rather, it's a series of focused OO design tips written by one of Java's authors; some are Java-specific, but often they even contain useful wisdom for OO programming outside Java. 

You should be able to access the book in two steps. 
* Navigate [here](https://www.oreilly.com/library/view/temporary-access/) to setup your Brown-affiliated account. Select "Brown University" and log in---note this login is Brown-managed.
* Access the online reading [here](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/). Either click "Continue" or use the table-of-contents menu in the upper-right corner of the screen to begin.

Here is a non-exhaustive list of readings in _Effective Java_ that will be useful in adopting the "0320 mindset" when it comes to OO programming early in the semester. Some of these will be assigned reading for everyone, but we suggest getting an understanding of the core point of all of these.

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

## Anticipated questions about assignments

* Q: Will working in Java mean that we can't program functionally?
  * A: No. The lessons you learned about functional programming in 0170, 0190 or 0200 will still be useful. E.g., Java has supported lambdas for quite some time. (See Item 42 in _Effective Java_.) The difference is that you'll need to learn _more_ concepts to program with objects and do it well.

* Q: Will you provide us with solutions to this (and other) assignments?
  * A: 0320 is a different sort of course. You're moving into a larger world, where there's almost always no perfect solution, especially when it comes to OO design. We'll review candidate sprint solutions in class, but the code reviewed will almost always have _both_ weaknesses and strengths. We won't be releasing a "solution" to the assignment zero prompt below.
 
* Q: What's the deal with grading, mandatory S/NC, and collaboration? 
  * A: While the 2023 syllabus isn't finalized, you can see some of 0320's philosophy in [last semester's syllabus](https://docs.google.com/document/d/1EkgvdBmDfqNhp4gPwG2PVys8lqqsrEmQp-9U8IX-DUc). Yes, you'll be free to share your code (indeed, required to do so at times.) Yes, we'll be requiring you to follow professional standards about doing so.

## Assignment Zero

[This public repository](https://github.com/cs0320/assignment-zero) contains a starter codebase in the `chessish` directory. The codebase is a Java project, and uses Maven to resolve dependencies---you can recognize Maven projects by the `pom.xml` file in the project root, which contains configuration information for Maven. 

This existing code is somewhat more complex than you'll write for the first assignment (CSV), and roughly the same complexity as the code you'll write for the third assignment (Server). It's an intermediate stage in my (Tim's) effort to author a chess program. Actually, that's not quite right; if it was only meant to play chess, it wouldn't need to be this complex. Instead, we're trying to make a codebase that can handle _many_ similar, but different, games. 

In the past, you've probably focused mostly on writing code that is _fit for a purpose_. It satisfies the assignment spec, or does what you need at the moment. You might have also tried to write code that is _easy to understand_ or _free from bugs_. In 0320, we'll write code that is also _safe from external bugs_ and _ready for change or extension_. When I (Tim) wrote this code, I was thinking mostly about extensibility. If I shared my code with someone, could they easily extend it to play Go as well? 

### Baseline Requirements

You are allowed to modify this starter code in any way you wish. You may change the classes I wrote, add new classes, new `main` methods as alternative starting points, etc. 

### Requirements

There are three parts to this assignment. 

#### Code Walk

First, run a game of (pawns-only) chess without changing the code. (Run `ChessGameMain.java`.) Then look through the code. What do you notice that might be new or different? What questions do you have? (It's OK not to understand everything. Part of this assignment is about noticing that and identifying questions.) **Keep a record of these questions.**

#### Testing

Next, look over the testing class (it's in the `src/test` directory, not `src/main/...`). I wrote only one test; surely more testing would be good. But what kind of tests should I write? **Take notes on what tests you think are important, but missing**. Then **write at least two tests**: one that tests the outcome of a complete game (a "system test" for the overall game-playing program), and one that tests some lower-level functionality (a "unit test" for a class or method). 

#### Extending 

Finally, extend the codebase in some way. Here are two suggested paths:
* You might add another chess piece---I only coded the logic for pawns. This would probably be another class, just like `Pawn`, that describes how that new piece moves.
* You might leave chess behind, and see if you can use this codebase to run a game of Go instead. There, you'd also need a new piece type, but a `Move` is probably not what you want---once placed, Go pieces can be captured but not moved. What assumptions am I making that could interfere with playing Go? Is there another `Action` type that would work better? 

### Handing In

_This semester_, we do not have the TA capacity to grade your solutions to assignment zero. However, to prevent overlap of effort with the first "real" assignment in 0320, we consider this "due" on January 26th (the first day of 0320).
