package ex04;

public interface UsersList {
    void AddUser(User user);

    User getUserByID(int ID);

    User getUserByIndex(int index);

    int getSizeUsers();
}
