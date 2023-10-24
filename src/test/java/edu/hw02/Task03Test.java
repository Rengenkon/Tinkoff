package edu.hw02;

import edu.hw02.task03.DefaultConnectionManager;
import edu.hw02.task03.FaultyConnectionManager;
import edu.hw02.task03.PopularCommandExecutor;
import edu.hw02.task03.StableConnection;
import org.junit.jupiter.api.Test;

public class Task03Test {
    @Test
    public void faulty() {
        //given
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(new FaultyConnectionManager(), 1);
        //when
        commandExecutor.updatePackages();
        //ConnectionException
        //израсходовано количетсво попыток

        //then
    }
    @Test
    public void stable() {
        //given
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(new DefaultConnectionManager(), 1);
        //when
        commandExecutor.updatePackages();
        //выполнится
    }
}
