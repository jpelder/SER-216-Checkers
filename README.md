# SER-216-Checkers
Group 15 Final Project: Editing given Checkers game

CHANGES MADE BEFORE INITIAL PUSH TO GITHUB: Some of the image file names had to be edited

3/21/17: As of now you must copy all images to the class (bin) folder to run the game correctly. Working on a fix

3/28/17: Copied the image folder into bin and edited references. This makes it so no changes need to be done to start the game, but I still wish I could move the image folder up by one directory. Unexpectedly, this altered the Start Game screen by adding a lot of stuff. Edited a few text fields.

4/3/17: Moved Folders around and deleted duplicate folders/files. Changed references to sound files to make them work. Added a file (HowToPlay.is) to populate the Help Box.

4/5/17: Cleaned up the code - adding/moving brackets, spacing,  etc. Added our group names to the credits in the beginning, and some button tool tips.

4/6/17: Cleaned up code - spacing. Changed the bottom of the GUI (post 'start game'). Changed the help box. Working on showing which tile was previously used (most recently). Set up the framework of the junit testing.

4/7/17: Edited the Help Box. Added the Undo button - already in the code, just had to uncomment it. Added highlighting to show the previous spots of moved tiles (most recently moved tile).

4/9/17: Fixed the highlighting to always have the correct colors and work in single player - lets you more easily see where the computer moved from. Changed the formatting of the help box.

4/14/17: Fixed file structure so that gui and logic are separated into different packages. Fixed import statements in all class files so that they are not importing all classes from awt, and swing. Fixed HowToPlay folder as a text file, will try to work and make it formatted properly.

4/16/17: Created a resources source folder that holds both sounds and image files. Fixed PlaySound so that sound is being pulled from resource folder, removed File class declaration in played sound. CheckerMoveTest at 57% code completion.