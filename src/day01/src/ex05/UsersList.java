package ex05;

public interface UsersList {
    void addUser(User user);

    User getUserByID(int id);

    User getUserByIndex(int index);

    int getSizeUsers();
}
