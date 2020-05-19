Snake Game(+Tests)

Implemented on Java.

Used technologies : MVC,Swing(for front of the application),Functional interfaces,Threads .. etc.

Main classes:
            Main.java - creates and connects Model View and Controller, sets up Key Listeners for game.
            Model.java - encapsulates logic of the game(Inside we have helper classes like Logic.java,State.java..etc)
            View.java - encapsulates front of the application using Swing lib.Draw board,Snake on each iteration.
            Controller.java - Listens to model, whenever model is changed notifies view to update board/snake locations.
            
            
Details: Heavily used MVC technology. Used threads-->sleep to make clock tick simulation.
