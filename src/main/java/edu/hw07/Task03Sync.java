package edu.hw07;

import java.util.ArrayList;
import java.util.List;

public class Task03Sync {
    private final ArrayList<Person> bd;

    public Task03Sync() {
        bd = new ArrayList<>();
    }

    public synchronized void add(Person person) {
        bd.add(person);
    }

    public synchronized void delete(int id) {
        bd.removeIf(person -> person.id == id);
    }

    public List<Person> findByName(String name) {
        ArrayList<Person> ans = new ArrayList<>(0);
        for (Person person : bd) {
            if (person.name.equals(name)) {
                ans.add(person);
            }
        }
        return ans;
    }

    public List<Person> findByAddress(String address) {
        ArrayList<Person> ans = new ArrayList<>(0);
        for (Person person : bd) {
            if (person.address.equals(address)) {
                ans.add(person);
            }
        }
        return ans;
    }

    public List<Person> findByPhone(String phone) {
        ArrayList<Person> ans = new ArrayList<>(0);
        for (Person person : bd) {
            if (person.phoneNumber.equals(phone)) {
                ans.add(person);
            }
        }
        return ans;
    }

    public record Person(int id, String name, String address, String phoneNumber) {}
}
