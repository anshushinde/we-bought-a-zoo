# Solo Quest: We Bought A Zoo
**`Quest giver: Prospector Yorgen`**
>Another day, another sighting.  We cannot keep track anymore.  It seems like each day there are ten new animal species found wandering around.  You would think people would not randomly engage an unknown species, but well... people.  We need to start keeping track of all the new animals.  I need you to get the list of new names and start ordering them alphabetically.  If you help I can name one of these species after you.

## Quest Overview
This lab has us pretending we are running an exotic animal database.  We need the ability to manipulate this database through various functions.  We also what to be able to easily access the database without having to run the program so we have it attached to a text-file.

**You may only use arrays with this lab.  No other Collections are allowed to be used at all.  This is the case even if they get converted back into an array after being used.  You are also not permitted to use any Collection methods to complete any of the tasks.**

## Objectives Required to complete
### Objective 1 - Designing a Plan
Our design plan should outline the steps being accomplished in each method of our program.  The [design example](designexample.txt) displays a design plan to base the one needed for this project.  When creating a design plan for this project it could be done in either a text file or as a comment on the top of a code file.  Remember design plans should be pushed and requested for approval before coding.

### Objective 2 - Cloning
- Clone this project into IntelliJ
  - remember to place it into a correct folder

### Objective 3 - Branching from the main
- Click on the word "Git" in the bottom left corner of IntelliJ
  - Right-click on the word "main"
  - Choose "New branch from selected"
- On the popup name the branch `dev`
  - Leave the checkmark in "Checkout Branch"
  - Click "Create"

Now we have our `dev` branch, we double-check the bottom right corner it should say `dev`, not main.

We can now work on this `dev` branch.

## Quest Requirements
The bullet points in this section are the bare minimum needed.  There are often times when exact details are missing as it would give away the answer.  Therefore, we should realize that some more work and logic are required with certain steps.

### Objective 1 - Class Requirements
We will need to create an appropriate class for this lab that will help us interact with the text-file and manage our database.

- Create a class called `OurZoo`
  - make the class in the following location (-> denotes going to a sub-folder)
    - `src` -> `main` -> `java`
- Create instance variables for this class
  - at a minimum, you will want an array of the animal names **and** an array of new animals being added
  - a logical size tracker for how many elements are in the array
- Create a constructor that does not take any parameters, but fills the collection
  - Text-file in the resources folder has a list of animals in it that need to manipulate
    - the file is named `zoo.txt` do not change or manipulate that name in any way
  - Build a String array from data in a text file
    - The array should only be created to fit the data from the file exactly, no larger or smaller
    - The animal names size cannot be hard-coded
      - You must determine how to make an array of appropriate size from the number of names in the text-file
    - The new animal names (your second array) size you can hard-code to 10
- Create a second constructor that takes a single parameter
  - a String array filled with animals
  - the instance field should get set to this passed array
  - this constructor will be helpful in your unit test to make sure your methods work correctly

Use try-catch blocks where appropriate (you will need to use them in at least 1 location when you are appending into the file, other locations may be needed as well).

You may need two different try-with-resources when reading from the text-file so that the reader is closed and reopened so it starts back at the top of the file.

If you are not using try/catch you will still need to close and reopen the readers so it starts back at the top of the file.  Yes, there are better ways to implement this with different classes, but we are not learning about those

- Create a main method in the `OurZoo` class
- Display the following options to the user
    ```
    0: Quit
    1: Get Value
    2: Set Value
    3: Insert Value
    4: Delete Value
    5: Print Out Array to Console
    6: Append New Elements to file
    7: Display Menu (optional)
    ```

You may decide to do this within a method or not.  This choice is yours, however, the numbering of the menu options must remain as outlined above.  The optional item at number 7 is not required.

When selecting the `get` or `delete` options from the menu the user should be presented with a second choice of doing these tasks with an index number or with a value only.  Once they make this selection you can call the appropriate method.

Building a menu method can be helpful if you foresee your code needing to call or show off the menu in multiple locations.  This menu method would have various potential implementations.  An idea for 1 of them would be what is outlined below.

- Create a method called `menuSelect` it takes two parameters
  - the `menuOption` the user selected
  - a `Scanner object` so the user can enter input when required
  - handle the various different menu options the user can select

This implementation would have this menu method call the various other methods instead of the main.  You could also have this menu method return the user selection back to the main method, which would then do the different options.  There are other potential implementations for this method.  No one solution is correct.  However, you need to document any logic that is not clear that you decide to use here.

Multiple methods need to be written and implemented for each of our menu options we are showing the user.  The method implementation minimums are described throughout this section.  I recommend you break this section down even further when it comes to the issues you are creating.

The methods are numbered so you can see them separated from each other.  They are not listed in any particular order and can be implemented in any order you wish.

### 1
```Java
public String getValue (int index) {

}
```
- Return the animal located at the passed index
- The index should not be larger than the logical size of the array
- Determine how to

### 2
```Java
public String getValue (String firstLetter) {

}
```
- Return the first animal you find whose name begins with the passed letter
- If no name is found return `No animal found`

### 3
```Java
public String getValue (String firstLetter, int n) {

}
```
- Return the nth animal you find whose name begins with the passed letter
- If no name is found return `No animal found`

### 4
```Java
public boolean setValue (String animalName, int index) {

}
```
- Overwrite the value at the index location with the new passed animal name
- Return if the overwrite was successful or not
  - Think about why or when you would not be able to overwrite a value

### 5
```Java
public void insertValue (String animalName, boolean inAlaphbetical) {

}
```
- Insert the passed value into the array
- If the inAlaphabetical is true you should insert it in the correct location based on alphabetical ordering
- If the inAlaphabetical is false you should then ask the user what index location they wish to insert at
- Inserting should **not** overwrite the current value at the location, it should make space at that location by shifting everything else down
- You will need to increase the array size if the array is completely full

### 6
```Java
public String deleteValue (int index) {

}
```
- Delete an animal at the given index location
- Everything else should shift down to condense the array
- Return the animal that was deleted from the array

### 7
```Java
public String deleteValue (String name) {

}
```
- Delete the animal with the given name
- Everything should shift down to condense the array
- If no animal is found matching the name passed you should return `No animal with that name in the list`
- Return the animal name that is removed if the animal is found

### 8
```Java
public String deleteValue (String letter, int n) {

}
```
- Delete the nth animal starting with the letter
- Everything should shift down to condense the array
- If no animal is found matching the name passed you should return `No animal with that first letter in the list`
- Return the animal name that is removed if the animal is found

### 9
```Java
public void printAnaimals () {

}
```
- Print out the list of animals that exist in the array
- Ask the user what order would they like the animals printed
  - alphabetical
  - reverse alphabetical
  - by length
  - alternating alphabetical (this means that the first thing printed is alphabetically first, the second thing printed is alphabetically last, etc.)
- You may not use any prebuilt Java methods to do the sorting it must be done with your own algorithms.

### 10
```Java
public boolean saveList () {

}
```
- Write only the new elements added back to the text file
- These elements should be appended to the end of the text file
- You should not overwrite the text-file with the entire array
- Return if the list was able to be saved
  - Think about why the new animals would not be able to be appended to the text-file

### 11
```Java
public int getAnimalsLogical () {

}
```
- Return the logical size of the entire animal array

### 12
```Java
public int getNewAnimalsLogical () {

}
```
- Return the logical size of the new animal array

There are more methods than menu options as some methods have the potential to be implemented with at least two different parameters.  Some methods will help with a Unit Test file if you make one.

When trying to determine the alphabetical order of words use the compareTo method, based on what is returned you can place words in the correct order.
```
    "bear".compareTo("lion"); returns a -10
    "lion".compareTo("bear"); returns a 10
```
Keep everything lowercase when making comparisons so the numbers are correct

### Objective 2 - Unit Tests
Create a Unit Test class in the correct location.

Write at least two test cases for each of the methods you need to implement in the `OurZoo` source code

### Objective 3 - Merging to the main branch and Pushing
- Click on the word "Git" in the bottom left corner of IntelliJ
- Select "main" (it should only say main)
  - Right-click and select "Checkout"
- It should now say "main" in the bottom right corner
- main should also have the checkout tag on it in this "Git" window
- Select the "dev" branch in this Git window
  - Right-click and select "Merge Selected into Current"
- Put a checkmark in the `dev` option
  - The checkmark might not be an option, if it is not that is acceptable
  - Only select the `dev` branch with no other words as this is our local version
- Click the merge button

### Objective 4 - Pushing back to GitHub
- Push all your commits to GitHub on both the main, dev, and any other branches you make
  - It does not hurt to do a final "add and commit" before pushing to verify the latest version is up on GitHub

The committing and pushing process does not change for different branches it is always the same.  If you are confused or struggling with this process please reach out and let me know.

**Note:** Only the code on the main branch will be graded.  It is important to make sure you are happy with your main branch.  You can check if your code is only GitHub by logging in, finding your repo based on the assignment, and checking the commit messages on the page that loads.  These are in the middle column of the little box at the top.  You should see your most recent commit message next to whatever files/folder you pushed.

#### Grading
|   Points     |   Requirement                               |
| :----------: |:------------------------------------------- |
| 5            | documentation                               |
| 2            | quit the program with correct option        |
| 7            | get methods                                 |
| 3            | both set methods                            |
| 15           | insert method                               |
| 10           | delete methods                              |
| 2            | print to console                            |
| 5            | append into file                            |
| 10           | good UI and programming conventions         |
| 5            | unit tests implemented correctly            |
