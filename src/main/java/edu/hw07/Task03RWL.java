package edu.hw07;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task03RWL {
    private final ArrayList<Person> bd;
    private final Lock r;
    private final Lock w;

    public Task03RWL() {
        bd = new ArrayList<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        r = lock.readLock();
        w = lock.writeLock();
    }

    public void add(Person person) {
        w.lock();
        try {
            bd.add(person);
        } finally {
            w.unlock();
        }
    }

    public void delete(int id) {
        r.lock();
        for (Person person : bd) {
            if (person.id == id) {
                r.unlock();
                w.lock();
                try {
                    bd.remove(person);
                } finally {
                    w.unlock();
                }
                break;
            }
        }
    }

    public List<Person> findByName(String name) {
        r.lock();
        try {
            ArrayList<Person> ans = new ArrayList<>(0);
            for (Person person : bd) {
                if (person.name.equals(name)) {
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

    public record Person(int id, String name, String address, String phoneNumber) {}
}
