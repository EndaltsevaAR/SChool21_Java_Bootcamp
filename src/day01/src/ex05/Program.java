package ex05;

/**
 * As a result, you should create a functioning application with a console menu.
 * Menu functionality must be implemented in the respective class with a link
 * field to TransactionsService.
 * Each menu item must be accompanied by the number of the command entered by a
 * user to call an action.
 * The application shall support two launch modes—production (standard mode) and
 * dev (where transfer information for a specific user can be removed by user
 * ID, and a function that checks the validity of all transfers can be run).
 * If an exception is thrown, a message containing information about the error
 * shall appear, and user shall be provided an ability to enter valid data.
 */

public class Program {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.startApp();
    }
}

/*
 * 1
 * Jonh 777
 * 1
 * Mike 100
 * 3
 * 1 2 100
 * 3
 * 1 2 150
 * 3
 * 1 2 50
 * 2
 * 2
 * 4
 * 1
 * 5
 * 1 нужно скопировать ид из ответа выше для 150
 * 6
 * 7
 */
