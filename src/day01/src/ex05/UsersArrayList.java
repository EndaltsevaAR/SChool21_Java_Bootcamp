package ex05;

import java.util.Arrays;

public class UsersArrayList implements UsersList {
    private int capacity;
    private User[] usersArray;
    private int occupiedCells;

    public UsersArrayList() {
        capacity = 10;
        usersArray = new User[capacity];
        occupiedCells = 0;
    }

    @Override
    public void addUser(User user) {
        if (occupiedCells == capacity) {
            capacity = (int) (capacity * 1.5);
            User[] newArray = new User[capacity];
            System.arraycopy(usersArray, 0, newArray, 0, occupiedCells - 1);
            usersArray = newArray;
        }
        usersArray[occupiedCells++] = user;
    }

    @Override
    public User getUserByID(int ID) {
        for (User user : usersArray) {
            if (user.getIdentifier() == ID) {
                return user;
            }
        }
        throw new UserNotFoundException("There is no user with such id");
    }

    @Override
    public User getUserByIndex(int index) {
        if (index < 0 || index >= occupiedCells) {
            throw new UserNotFoundException("Array has less users than index");
        }
        return usersArray[index];
    }

    @Override
    public int getSizeUsers() {
        return occupiedCells;
    }

    @Override
    public String toString() {
        return "UsersArrayList{" +
                "capacity=" + capacity +
                ", occupiedCells=" + occupiedCells +
                ", usersArray=" + Arrays.toString(usersArray) +
                '}' + "\n";
    }
}