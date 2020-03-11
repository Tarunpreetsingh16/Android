Basic/elementary calculator app built using android studio and java as a college project.

Calculator with one to one manipulation (one operation at a time, it cannot compute an equation with operator chaining). It supports undo, redo and backspace functionality.

Some basic functionalities explained -
1. Undo - it gives the last computed result to the user on which the user can continue working on. It can undo till the first ever computed result.
2. Redo - It can redo the undone operation. It can redo till the last ever computed result.
3. Backspace - It deletes the last digit in the number that is entered by the user
4. Clear - It clear everything from the memory.

Followed Singleton pattern for calculator class and Bridge design pattern for functionality of the calculator.

For Undo and Redo I used Stack to store the results of the operations.

Screenshots of the application - 
Figure 1
Landing screen of the application
![Landing screen of the application](https://github.com/Tarunpreetsingh16/Android/blob/master/Calculator/images/Screenshot_20200306_163855_com.example.calculator.jpg)

Figure 2
When user enters the input, it updates the screen
![When user enters the input, it updates the screen](https://github.com/Tarunpreetsingh16/Android/blob/master/Calculator/images/Screenshot_20200306_163907_com.example.calculator.jpg)

