package edu.hw06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task02 {
    public static Path cloneFile(Path file) {
        if (Files.notExists(file)) {
            throw new RuntimeException("File not exist");
        }
        Path newFile = file;
        do {
            newFile = Paths.get(file.getParent().toString(), nextName(newFile.getFileName().toString()));
        } while(Files.exists(newFile));
        try {
            Files.copy(file, newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newFile;
    }

    private static String nextName(String file) {
        StringBuilder builder = new StringBuilder();
        Matcher matcher = Pattern.compile("(.* - копия)(.*)\\.(.*)$").matcher(file);
        if (matcher.find()) {
            builder.append(matcher.group(1));
            var num = matcher.group(2);
            if (!num.isEmpty()) {
                int i = Integer.parseInt(num.substring(2, num.length() - 1));
                i++;
                builder.append(" (").append(i).append(").");
            } else {
                builder.append(" (1).");
            }
            builder.append(matcher.group(3));
        } else {
            int point = file.lastIndexOf('.');
            builder.append(file, 0, point).append(" - копия").append(file.substring(point));
        }
        return builder.toString();
    }
}
