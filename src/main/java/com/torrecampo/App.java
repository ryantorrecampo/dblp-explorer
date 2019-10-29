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
            String keyword = args[1];
            int num = Integer.parseInt(args[2]);

            System.out.println("Filename: " + file);
            System.out.println("Keyword: " + keyword);
            System.out.println("n: " + num);

            Path path = Paths.get(file);
            Stream<String> lines = Files.lines(path);
            CharSequence charSequence = new StringBuilder(keyword);
            // return the lines where the keyword is present throughout all titles
            List<String> filtered = lines.filter(string -> string.startsWith("#*"))
                    .filter(string -> string.contains(charSequence)).collect(Collectors.toList());

            for (int i = 0; i < filtered.size(); i++) {
                System.out.println(filtered.get(i));
            }

            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
