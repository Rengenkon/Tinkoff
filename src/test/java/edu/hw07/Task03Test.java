package edu.hw07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task03Test {
    @Test
    @DisplayName("Add")
    void f1() {
        //given
        Task03RWL bd = new Task03RWL();
        int i = 1;
        String n = "Name";
        Task03RWL.Person p = new Task03RWL.Person(i, n, "", "");
        //when
        bd.add(p);
        //then
        assertThat(bd.findByName(n).toArray(new Task03RWL.Person[0])).hasSize(1).contains(p);
    }

    @Test
    @DisplayName("Remove")
    void f2() {
        //given
        Task03RWL bd = new Task03RWL();
        int i = 1;
        String n = "Name";
        Task03RWL.Person p = new Task03RWL.Person(i, n, "", "");
        //when
        bd.add(p);
        bd.delete(i);
        //then
        assertThat(bd.findByName(n).toArray(new Task03RWL.Person[0])).hasSize(0);
    }





    @Test
    @DisplayName("Add")
    void f11() {
        //given
        Task03Sync bd = new Task03Sync();
        int i = 1;
        String n = "Name";
        Task03Sync.Person p = new Task03Sync.Person(i, n, "", "");
        //when
        bd.add(p);
        //then
        assertThat(bd.findByName(n).toArray(new Task03Sync.Person[0])).hasSize(1).contains(p);
    }

    @Test
    @DisplayName("Remove")
    void f12() {
        //given
        Task03Sync bd = new Task03Sync();
        int i = 1;
        String n = "Name";
        Task03Sync.Person p = new Task03Sync.Person(i, n, "", "");
        //when
        bd.add(p);
        bd.delete(i);
        //then
        assertThat(bd.findByName(n).toArray(new Task03Sync.Person[0])).hasSize(0);
    }
}
