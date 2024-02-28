package ex01;

/**
 * Make sure that each user ID is unique. To do so, create UserIdsGenerator
 * class. Behavior of the object of this class defines the functionality for
 * generating user IDs.
 * State-of-the-art database management systems support autoincrement principle
 * where each new ID is the value of the previously generated ID +1.
 * So, UserIdsGenerator class contains the last generated ID as its state.
 * UserIdsGenerator behavior is defined by int generateId() method that returns
 * a newly generated ID each time it is called.
 * An example of use of such classes shall be contained in Program file
 * (creation, initialization, printing object content on a console).
 * Notes:
 * 
 * 
 * Make sure only one UserIdsGenerator object exists (see the Singleton
 * pattern). It is required because existence of several objects of this class
 * cannot guarantee that all user identifiers are unique.
 * 
 * 
 * User identifier must be read-only since it is initialized only once (when the
 * object is created) and cannot be modified later during the program execution.
 * 
 * 
 * Temporary logic for identifier initialization should be added to User class
 * constructor:
 * 
 * 
 * 
 * public User(...) {
 * this.id = UserIdsGenerator.getInstance().generateId();
 * }
 */

public class Program {
    public static void main(String[] args) {
        User harry = new User("Harry", 10000);
        User hermione = new User("Hermione", 3200);
        User ronald = new User("Ronald", 123);

        System.out.println(harry);
        System.out.println(hermione);
        System.out.println(ronald);
    }
}