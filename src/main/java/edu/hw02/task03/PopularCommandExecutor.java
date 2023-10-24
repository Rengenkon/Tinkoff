package edu.hw02.task03;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        int trys = 0;
        try (Connection connection = manager.getConnection()) {
            while (trys != maxAttempts) {
                try {
                    connection.execute(command);
                    return;
                } catch (ConnectionException connectionException) {
                    trys++;
                }
            }
            throw new ConnectionException();
        } catch (Exception e) {
            // не выкинется, так как в close() нет trow
            throw new RuntimeException(e);
        }
    }
}
