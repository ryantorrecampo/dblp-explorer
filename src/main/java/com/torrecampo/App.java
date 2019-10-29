package com.torrecampo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class App {

    public static void main(String[] args) {
        try {
            String file = args[0];
            Path path = Paths.get(file);
            Stream<String> lines = Files.lines(path);
            String keyword = "engineering in industry";
            CharSequence charSequence = new StringBuilder(keyword);
            // return how many times the keyword is present throughout all titles
            long count = lines.filter(string -> string.startsWith("#*")).filter(string -> string.contains(charSequence))
                    .count();
            System.out.println(count);
            // Files.write(path, replaced);
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
