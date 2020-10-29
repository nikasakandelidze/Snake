# **Snake**
### Well known game Snake, implemented fully in Java language.
# **Used technologies :**
- MVC 
- Swing(for front of the application)
- Java 8 features( Functional interfaces, Lambdas ... etc )

# **Main classes**:
- Main.java - creates and connects Model View and Controller, sets up Key Listeners for game.
- Model.java - encapsulates logic of the game(Inside we have helper classes like Logic.java,State.java..etc)
- View.java - encapsulates front of the application using Swing lib.Draw board,Snake on each iteration.
- Controller.java - Listens to model, whenever model is changed notifies view to update board/snake locations.
        
