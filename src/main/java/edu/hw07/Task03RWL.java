package edu.hw07;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task03 {
    public record Person(int id, String name, String address, String phoneNumber) {}
    private final ArrayList<Person> bd = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    public void add(Person person) {
        w.lock();
        try {
            bd.add(person);
        } finally {
            w.unlock();
        }
    }

    public void delete(int id) {
        w.lock();
        try {
            bd.remove(id);
        } finally {
            w.unlock();
        }
    }

    public List<Person> findByName(String name) {
        r.lock();
        try {
            ArrayList<Person> ans = new ArrayList<>(0);
            for (Person person : bd) {
                if (Objects.equals(person.name, name)) {
                    ans.add(person);
                }
            }
            return ans;
        } finally {
            r.unlock();
        }
    }

    public List<Person> findByAddress(String address) {
        r.lock();
        try {
            ArrayList<Person> ans = new ArrayList<>(0);
            for (Person person : bd) {
                if (person.address.equals(address)) {
                    ans.add(person);
                }
            }
            return ans;
        } finally {
            r.unlock();
        }
    }

    public List<Person> findByPhone(String phone) {
        r.lock();
        try {
            ArrayList<Person> ans = new ArrayList<>(0);
            for (Person person : bd) {
                if (person.phoneNumber.equals(phone)) {
                    ans.add(person);
                }
            }
            return ans;
        } finally {
            r.unlock();
        }
    }
}
