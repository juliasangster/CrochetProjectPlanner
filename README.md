# Graphghan Pattern Creator
***

Crochet is an ancient fibre art, in which yarn is weaved into complex patterns using a hook. 
Crochet artists refer to their work as "projects".

One of the most notorious crochet projects is the afghan, otherwise known as the throw blanket.
And of the afghans, one of the most notoriously difficult afghans to create is the graphghan, an afghan that you stitch
a pixelated pattern into. These blankets often take weeks of pre-planning, collecting materials, and then months
of crocheting to complete.

> Example Graphghan Square Pattern by E'Claire Makery --
> [Click Here](https://eclairemakery.com/wp-content/uploads/2021/09/Watermarked-Fall-Leaf-Right-Leaf-Graph.pdf)

The goal of this project is to develop an application that can assist in planning a graphghan project.
The application will open with an option to add projects or to view generated projects. 
If adding a new project, the program will prompt you to generate one on a grid-style interface. You will be able
to select the number of squares in the pattern, with length and width being independent. 
You will be able to select colors to fill the grids with. You can save this pattern to use while making your graphghan.
The program will have many tools to design your graphghan with, and will allow you to store an arbitrary
number of projects.


This project is of particular interest to me, as I spend a lot of my free time crocheting.
There is also a lot of interesting extensions I can add to my user stories, with sufficient time. Such as inputting
a JPEG image and having a graphghan pattern automatically generate, having a yarn-list for purchasing be generated,
and more. I believe this program will provide a useful
framework for future, more extensive projects. As well, many people who crochet professionally 
produce patterns as their product, and this application could be used to streamline pattern-making workflows. 

***

### User Stories Implemented
* As a user, I want to add new templates for crochet graphghan patterns to my collection of patterns by adding
  dimensions of length and width. I want to be able to give these patterns a name to find them easily.
* As a user, I want to be able to remove the projects from the collection by providing the name of the project to remove
* As a user, I want to be able to change the colours on the graphghan template, in order to design my own patterns.
  I should be able to pick multiple different colours on the same graphghan.
* As a user, I want to be able to visualize the graphghan pattern I'm designing in the GUI
  * I should be able to select any of my graphghans to edit by name 
  * The GUI should update during editing to show me what changes I'm making in real-time
  * The changes I make to the blanket should persist when I close the editing window and reopen it 
  * I would like to pick any color (using RGB codes) to use on my graphghan, and I would like to be able to see what color is currently selected
  * While editing, I would like to be able to use different tools to change entire rows, columns or blankets easily 
* As a user, I want to be able to save the entire state of the crochet application
  to a file. I want to be able to select to do so at any time while in the app.
* As a user, I want the option to load from file a previous state of the crochet
  application. I want to be able to select to do so at any time while in the app.
* As a user, I want to see a display of all current projects loaded in the application. This should update as 
  I add or delete projects from the project collection.
* As a user, I want to be able to clear all projects from the project collection in a single action

***
### Instructions for Use

1. How to add a new graphghan to project collection
   * When the application is running a main menu should appear on the left-hand side
   * Press the button that says `New Graphghan`
   * A pop-up window should appear asking you to enter a name, rows, and columns
     * Set `columns` to be 1 - 100
     * Set `rows` to be 1 - 100
     * Set `name` to be any string that is not empty. "Test" will work 
   * Hit `OK`
   * Your graphghan should now appear in the list under the heading `Projects`
   
   
2. How to remove a project from the project collection 
   * Make sure there is a project in the project collection to remove
     * NOTE: can follow procedure from (1) or (6) - there are preloaded projects for you to see
   * Hit `Delete Project` button
   * A pop-up window should appear asking you to select the name of the graphghan to delete
   * Use the JComboBox to select the name of the graphghan to delete
   * Hit `OK`
   * The graphghan selected to be removed from the list should now not appear under the headings `Projects`


3. How to remove all projects from project collection
    * Hit `Clear Projects` button
    * A pop-up window should appear asking you to confirm you would like to delete all projects
    * Hit `OK`
    * The projects shown under the headings `Projects` should now be empty


4. How to save state of application
    * On the main menu press `Save`
    * A pop-up window will appear asking you to confirm the save
    * Hit `OK`
    * Your data will now be saved to the JSON file 
 
  
5. How to load state 
   * On the main menu hit `Load`
   * A pop-up window will appear asking you to confirm the load
   * Hit `OK`
   * Your data will now be loaded, and the loaded data should be reflected in the app


6. How to edit a graphghan 
   * Make sure you have at least one project in the project collection
   * On the main menu hit `Edit Project`
   * A window such as the one below will open for the selected graphghan
   ![](data/app_editing.png)
   * The top row is the tool panel
     * `One Square` will change the color of the square pressed
     * `Fill` will change the color of the entire graphghan
     * `Fill Column` will change the color of the entire column selected
     * `Fill Row` will change the color of the entire row selected
     * The colored bar shows your current color
     * `Change Color` will prompt the user through a changing color process
   * You can begin editing by clicking on the squares you'd like to change the color of
   * You can tell which tool is selected as it will be colored a light-teal color
   * Closing the window will dispose of the editing window and re-display the projects
     panel, but will save the changes you made to the graphghan
     * See this quickly by closing and re-opening a project you've edited 
***
## Event Log 

Events that are logged
* Adding projects to project collection (`addProject()`)
  * Logs failures to add blankets: "Failed to add blanket: <<name>>"
  * Logs successful additions of blankets: "Added blanket: <<name>>"
* Removing projects from project collection (`removeProject()`)
  * Logs failures to remove blankets: "Failed to remove blanket: <<name>>"
  * Logs successful removals of blankets: "Removed blanket: <<name>>"

**Sample Event Log**
> Sun Apr 09 13:18:52 PDT 2023
> 
> Added blanket: Test 1
> 
> Sun Apr 09 13:18:56 PDT 2023
> 
> Failed to add blanket: Test 1
>
> Sun Apr 09 13:19:00 PDT 2023
> 
> Added blanket: Test 2
>
> Sun Apr 09 13:19:03 PDT 2023
> 
> Added blanket: Test 3
>
> Sun Apr 09 13:19:08 PDT 2023
> 
> Added blanket: Test 4
>
> Sun Apr 09 13:19:10 PDT 2023
> 
> Removed blanket: Test 1
>
> Sun Apr 09 13:19:11 PDT 2023
> 
> Removed blanket: Test 2
>
> Sun Apr 09 13:19:12 PDT 2023
> 
> Removed blanket: Test 3
>
>
> Process finished with exit code 0

### Task 3 - UML Diagram and Refactoring

The UML Diagram has been saved in the root folder as UML_Design_Diagram.png

![](UML_Design_Diagram.png)

Notes:
* Included ArrayList<Graphghan> as ProjectCollection extends this built-in Java class
* Interfaces denoted by red boxes and annotations
* Abstract classes denoted by yellow boxes and annotations
* Built-in Java classes denoted by blue boxes
* Enums are denoted by green boxes and annotations

#### Refactoring

A bidirectional relationship was implemented for many aspects of the GUI. 
This was done so that the sub element can always reference the parent element, and vice versa. 
For example, `EditingFrame` and `CrochetApp2` have this relationship, which allows `EditingFrame`
to call a method on `CrochetApp2` when closed to redisplay `ProjectsPanel` in `CrochetApp2`. 

However, I neglected to implement this type of relationship between `ProjectsPanel` and `CrochetApp2`. 
As a result, there were difficulties in getting `ProjectsPanel` to redisplay properly after events, as it could not 
directly call methods on `CrochetApp2`. With more time I would implement a one-to-one bidirectional relationship
between `CrochetApp2` and `ProjectsPanel`. 

Further, when initially designing the application I chose to have `ProjectCollection`
extend `ArrayList<Graphghan>`. I believed I had made an excellent choice, as I could use
the built-in functions for `ArrayList<E>`. However, this caused issues in the visibility of 
`ProjectCollection` and what objects were able to call add/remove on the list of graphghans.
Java's `ArrayList` methods `add(Element E)` and `remove(Element E)` both have the `public` access modifier.
As a result, calls to `add()` and `remove()` from the ui package was possible, but not desirable, 
as methods `addProject()` and `removeProject()` were implemented to facilitate this and event logging. 
As a result, situations occurred when updating the code in which the wrong method was called 
from the UI package and the associated logging was not completed. 

To improve on this design choice, I would instead make `ProjectCollection` have a private field
which is of type `ArrayList<Graphghan>`. I would then re-implement the methods to add/remove
graphghans from the project collection in `ProjectCollection`. This would prevent someone maintaining the code from
wrongly using the `add()` and `remove()` functions on the `ProjectCollection` from outside the 
 class.

Lastly, the GUI has a top-level container (`CrochetApp2`) and three sub-containers (`MainMenu`, `ProjectsPanel` and
`EditingFrame`). I decided initially to make `ProjectsPanel` and `EditingFrame` their own classes, and left `MainMenu`
as a `JPanel` field in `CrochetApp2`. With more time, I would create an interface  called `CrochetAppPanel`. This 
interface would have methods `show()`, `repaint()`, `hide()` and others as common GUI panel behaviours are discovered. 
`CrochetApp2` would thus have a collection of `CrochetAppPanel` that it could iterate over to change all sub-elements. 
I believe this would greatly improve the maintainability of the code. 



