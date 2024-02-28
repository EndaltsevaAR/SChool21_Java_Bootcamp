package ex01;

public class UserIdsGenerator {
    private static UserIdsGenerator userIdsGenerator;
    private int maxId;

    private UserIdsGenerator() {
        this.maxId = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (userIdsGenerator == null) {
            userIdsGenerator = new UserIdsGenerator();
        }
        return userIdsGenerator;
    }

    public int generateId() {
        return ++maxId;
    }
}
