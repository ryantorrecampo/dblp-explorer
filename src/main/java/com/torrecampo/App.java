package com.torrecampo;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        String file = args[0];
        String keyword = args[1];
        int n = Integer.parseInt(args[2]);
        Path path = Paths.get(file);

        // Ability to reuse the stream
        Supplier<Stream<String>> streamSupplier = () -> {
            try {
                return Files.lines(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };

        // Parse through JSON file
        JSONParser parser = new JSONParser();
        List<JSONObject> titles = streamSupplier.get().map(line -> {
            try {
                return (JSONObject) parser.parse(line);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        // Finds titles containing the keyword
        Map<Integer, List<JSONObject>> matches = new HashMap<>();
        List<JSONObject> first = titles.stream()
                .filter(item -> item.get("title").toString().toUpperCase().contains(keyword.toUpperCase()))
                .collect(Collectors.toList());
        matches.put(0, first);

        // Check n-tiers
        for (int i = 1; i < n; i++) {
            List<JSONObject> prevTier = matches.get(i - 1);
            List<JSONObject> temp = first.stream().filter(item -> item.containsKey("references"))
                    .filter(ref -> prevTier.contains(ref)).collect(Collectors.toList());
            for (JSONObject j : prevTier) {
                String id = j.get("id").toString();
                first.stream().filter(item -> item.containsKey("references")).filter(ref -> ref.containsValue(id))
                        .collect(Collectors.toList());

            }
            matches.put(i, temp);
        }
        System.out.println(matches.get(0));
        streamSupplier.get().close();
    }
}
