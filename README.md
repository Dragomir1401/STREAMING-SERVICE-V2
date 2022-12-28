______________________________
__________________Copyright__________________

                Dragomir Andrei 2022 
_______________________________________________________________
----------------------------------------------------------------------------
Name: Dragomir Andrei

Class: 322CA
_______________________________________________________________
----------------------------------------------------------------------------
--------------------------------_OOP TV_------------------------------------
----------------------------------------------------------------------------



----------------------------------------------------------------------------


____________Description____________


This project represents the back end part of a platform of streaming similar
to Netflix, HBO MAX or Disney+. It is a Java built design consisting of different
pages which together represent a close copy of Netflix functionalities. Input and
Output are made with JSON for easier readability and parsing, some tests consisting
of tens of thousands of lines.


    There are different pages in this backend part, such as: Login page, Register,
    first page movie list, Search, Filter results, Account upgrades(premium, buy currency),
    See details of a movie, watch/like/rate a movie, see rating and likes and Logout. They
    all have relations between them and permit/deny access to each other depending on the
    an easier to follow menu for the user.


    Commands are split in five different types:

    1-- change page commands which simulate the forward, backwards buttons of a
        website or a menu for picking what page to go to.

    2-- on page commands which simulate what actions can be done on that specific
        page for example:

    3-- back commands which simulate the back navigation button of a webpage

    4-- subscribe commands which simulate a system of subscriptions for the user
        and implements the possibilty of getting notified when movies are added/deleted
        or getting personal recommendations

    5-- database commands which are admin access operations of manipulating the database
        of the platform with adding/deleting/modifying movies



On page see details of the movie we are shown all the specifications of the movie:

- genres, actors, likes, ratings, duration, year publicised, countries where it is banned

  but we can action on that movie by watching it, liking it or rating it.



-----------------------------------------------------------------------------




------------------------------------------------------------------------------


____________Implementation____________

The project has a command parser as main entrance point in the program.
There are structures for current user and current page in the momentary
package and all the input classes are in the input package.


Commands package contains all the general commands of the project, the most
important ones being OnPage and ChangePage. The two further develop the
solution tree by splitting into commands.



     * DESIGN PATTERN 1 *
    The on page commands are implemented using factory design pattern by
    creating an abstract method command, which is extended by every type
    of command and using a switch by name an instance of the class command
    is created by the factory model. The general run method decides what
    child class it will use for every given command.



                                     * Command *
                                         ||
    /-----------------/-------------------\-------------------\------------\
    |                 |                   |                   |            |
    |                 |                   |                   |            |
    BuyPremium      BuyTokens             Filter               Like        |
                                                                           |                                                                                   
                                                                          /
    /---------------/------------/------------/------------/------------/-
    |               |            |            |            |            |
    Login          Purchase       Rate       Register       Search      Watch




     * DESIGN PATTERN 2 *
    As second design pattern, the project uses Singleton for the current user
    instance and for the tokens command class, user commands and movies commands.
    The reasoning behind is that these classes need a singular instantiation
    in the program and they get changed themselves, not instantiated again, but have
    to be reset from test to test.



     * DESIGN PATTERN 3 *
    As third design pattern, the implementation follows strategy design pattern
    for the filter command. There is an interface strategy which is implemented
    by three different classes:

    - GenresStrategy          - StartsWithStrategy          - ActorsStrategy

    The methods which use the strategies only know about the general interface
    strategy and then decides what subclass should be followed for the apply
    strategy criteria.



    * DESIGN PATTERN 4 *
    The last design pattern used is commander pattern. Its implementation helps with
    handling the navigation commands(commands of type change page). The strong point
    consists on having a receiver and an invoker which is capable of canceling the
    last action done with undo method.

    Implementation consists of an interface NavigateCommands which is implemented
    by each command:
    
    
                                 * NavigateCommand *
                                         ||
    /-----------------/-------------------\-------------------\------------\
    |                 |                   |                   |            |
    |                 |                   |                   |            |
    GoToLogin     GoToLogout          GoToMovies          GoToRegister     |
                                                                           |
               /-----------------------------------------------------------/
               |                            |
            GoToSeeDetails               GoToUpgrades


    The receiver is the one who parses the command, decides its type and creates an
    instance of Invoker which will then execute the action. The invoker has two
    stacks of executed and undone actions which uses to keep track. When an action
    has to be undone it simply pops the action from the exectued stack and calls
    its undo method.

    * Invoker - the commander
    - call actions on commands (invoke methods provided by objects of type Command)
    maintains a list of all orders applied to the ordered objects.

    * Receiver - the commanded
    - is the called class
    - it contains the actual implementation of what is intended to be executed
    
    * NavigationCommand
    - objects for representing commands implement this 
        interface/extend it if it is an abstract class

    * Concrete command - we mean its implementations/subclasses - ChangePage class
    - contains methods with suggestive names for executing the command action (next(), back())
    - their implementations contain the call to the Receiver class.



There are also sorters for lists of movies and lists of recommendations 
which implement the sortMovies and the RecommendationSort interface.

    -sortByDuration   -sortByRating  -sortByLikes   -sortRecommendationsByLikes


The platform also has implemented a system of subscriptions for users to 
be able to get notifications when there are added movies which they might be 
interested in or when there are movies deleted in order to get their money back.
At the end of every series of commands on the platform the current user gets
personalised recommendations based on what genres he and others like.



Input class has role of database for the project and contains the list of
users, the list of movies and the list of actions for the streaming platform.
Output is generated using output class and command output class to keep the JSON
format.


------------------------------------------------------------------------------





------------------------------------------------------------------------------

_____________Comments_____________


The implementation could have been done better based on the generality of
the code. Besides this I could have made a more modularized approach.

The system follows a system of abstract method implementation and factory or 
command pattern for each operation. Perhaps use of builder design pattern 
for pages or filter pattern for filters/sorters. Use of further abstract 
classes and interfaces could have made the implementation easier and more facile.

------------------------------------------------------------------------------





-----------------------------------------------------------------------------


Resources:

https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1
https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/design-patterns
https://refactoring.guru/design-patterns/factory-method
https://jsondiff.com/
https://ocw.cs.pub.ro/courses/poo-ca-cd/administrativ/barem_teme
https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2


-----------------------------------------------------------------------------