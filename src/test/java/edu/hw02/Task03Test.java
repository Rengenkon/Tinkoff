package edu.hw02;

import edu.hw02.task03.ConnectionException;
import edu.hw02.task03.DefaultConnectionManager;
import edu.hw02.task03.FaultyConnectionManager;
import edu.hw02.task03.PopularCommandExecutor;
import org.junit.jupiter.api.Test;

public class Task03Test {
    @Test
    public void faulty() {
        //given
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(new FaultyConnectionManager(), 1);
        //when
        try {
            commandExecutor.updatePackages();
        }catch (RuntimeException e){
            //израсходовано количетсво попыток или ошибка при Conection.close()
            int a;
        }

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
