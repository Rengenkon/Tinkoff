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

    private void tryExecute(String command) {
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
        } catch (ConnectionException e){
            //ConnectionException при превышении количества попыток
            throw new RuntimeException(e);
        } catch(Exception e) {
            //Exception при Conection.close
            throw new RuntimeException(e);
        }
    }
}
