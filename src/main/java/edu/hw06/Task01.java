package edu.hw06;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Task01 implements Map<String, String> {
    private static final int BUFFER_SIZE = 128;
    private static final char LINE_SEPARATOR = '\n';
    private static final char ARG_SEPARATOR = ':';

    Path file;
    ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

    Task01(Path path) {
        file = path;
        System.out.println(file);
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int size() {
        int size = 0;
        try (var in = Files.newInputStream(file)){
            while (!readLine(in).isEmpty()) {
                size++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        String skey = (String)key;
        try (var in = Files.newInputStream(file)){
            String line = readLine(in);
            while (!line.isEmpty()) {
                String v = line.split(":")[0];
                if (v.equals(skey)) {
                    return true;
                }
                line = readLine(in);
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        String svalue = (String)value;
        try (var in = Files.newInputStream(file)){
            String line = readLine(in);
            while (!line.isEmpty()) {
                String v = line.split(":")[1];
                if (v.equals(svalue)) {
                    return true;
                }
                line = readLine(in);
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(Object key) {
        String skey = (String)key;
        try (var in = Files.newInputStream(file)){
            String[] kv;
            do {
                String line = readLine(in);
                if (line.isEmpty()) {
                    return null;
                }
                kv = line.split(":");
            } while (!kv[0].equals(skey));
            return kv[1];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        HashSet<String> set = new HashSet<>();
        try (var in = Files.newInputStream(file)){
            String line = readLine(in);
            while (!line.isEmpty()) {
                set.add(line.split(":")[0]);
                line = readLine(in);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return set;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        ArrayList<String> list = new ArrayList<>();
        try (var in = Files.newInputStream(file)){
            String line = readLine(in);
            while (!line.isEmpty()) {
                list.add(line.split(":")[1]);
                line = readLine(in);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        HashSet<Entry<String, String>> set = new HashSet<>();
        try (var in = Files.newInputStream(file)){
            String line = readLine(in);
            while (!line.isEmpty()) {
                String[] kv = line.split(":");
                set.add(new AbstractMap.SimpleEntry<String, String>(kv[0], kv[1]));
                line = readLine(in);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return set;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (this.containsKey(key)){
            var k = new HashSet<String>();
            k.add((String) key);
            removeN(k);
        }
        return putNC(key, value);
    }

    private String putNC(String key, String value) {//------------------------------------------nio
        try (var out = FileChannel.open(file, StandardOpenOption.APPEND)){
            var b = (key + ARG_SEPARATOR + value + LINE_SEPARATOR).getBytes();
            ByteBuffer obuf = ByteBuffer.allocate(b.length);
            obuf.put(b);
            obuf.flip();
            out.write(obuf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map m) {
        var k = m.keySet().iterator().next();
        if (k instanceof String && m.get(k) instanceof String) {
            HashSet<String> keyRemove = new HashSet<>();
            var keys = this.keySet();
            for (var key : m.keySet()) {
                if (keys.contains((String) key)) {
                    keyRemove.add((String) key);
                }
                this.put((String) key, (String) m.get(key));
            }
            removeN(keyRemove);
        }else {
            throw new RuntimeException("Type of key or value is not String");
        }
    }

    @Override
    public String remove(Object key) {
        if (this.containsKey(key)) {
            var k = new HashSet<String>();
            k.add((String) key);
            return removeN(k).iterator().next();
        }
        return null;
    }

    private Set<String> removeN (Set<String> setKey) {
        HashSet<String> ans = new HashSet<>();
        Path copy = Task02.cloneFile(file);
        this.clear();
        try (var in = Files.newInputStream(copy)){
            String[] kv;
            String line = readLine(in);
            while (!line.isEmpty()) {
                kv = line.split(":");
                if (setKey.contains(kv[0])){
                    ans.add(kv[1]);
                    setKey.remove(kv[0]);
                } else {
                    this.putNC(kv[0], kv[1]);
                }
                line = readLine(in);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.delete(copy);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ans;
    }

    @Override
    public void clear() {
        try {
            Files.delete(file);
            Files.createFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String readLine (InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        int c = stream.read();
        while (c != -1 && c != (int)LINE_SEPARATOR) {
            builder.append((char) c);
            c = stream.read();
        }
        return builder.toString();
    }
}
