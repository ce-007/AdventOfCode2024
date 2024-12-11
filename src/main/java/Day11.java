import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay11");
        Scanner scanner = new Scanner(file);
        List<String> rawInput = List.of(scanner.nextLine().split(" "));
        List<Long> input = new ArrayList<>();
        for (int i = 0; i < rawInput.size(); i++) {
            input.add(Long.parseLong(rawInput.get(i)));
        }

        scanner.close();
        Map<Long, List<Long>> calculated = new HashMap<>();
        LinkedHashMap<Long, Long> amountOfEach = new LinkedHashMap<>();
        List<Long> first = new ArrayList<>();
        first.add(1L);
        calculated.put(0L, first);

        for (int i = 0; i < input.size(); i++) {
            amountOfEach.put(input.get(i), 1L);
        }
        System.out.println("Part One: " + calculate(amountOfEach, calculated, 25));
        System.out.println("Part Two: " + calculate(amountOfEach, calculated, 50));
    }

    private static long calculate(LinkedHashMap<Long, Long> input, Map<Long, List<Long>> calculated, int part) {
        for (int i = 0; i < part; i++) {
            Map<Long, Long> keysToModify = new HashMap<>();
            List<Map.Entry<Long, List<Long>>> calculatedEntries = new ArrayList<>();
            Map<Long, Long> keysToDecrement = new HashMap<>();

            for (Map.Entry<Long, Long> entry : input.entrySet()) {
                if (calculated.containsKey(entry.getKey())) {
                    List<Long> temp = calculated.get(entry.getKey());

                    if (keysToModify.containsKey(temp.get(0))) {
                        keysToModify.put(temp.getFirst(), keysToModify.get(temp.getFirst()) + entry.getValue());
                    } else {
                        keysToModify.put(temp.getFirst(), entry.getValue());
                    }
                    keysToDecrement.put(entry.getKey(), entry.getValue());
                    if (temp.size() > 1) {
                        if (keysToModify.containsKey(temp.get(1))) {
                            keysToModify.put(temp.get(1), keysToModify.get(temp.get(1)) + entry.getValue());
                        } else {
                            keysToModify.put(temp.get(1), entry.getValue());
                        }
                    }

                } else if (entry.toString().length() % 2 == 0) {
                    int size = entry.getKey().toString().length();
                    String temp = entry.getKey().toString();
                    Long a = Long.parseLong(temp.substring(0, size / 2));
                    Long b = Long.parseLong(temp.substring(size / 2));
                    List<Long> tempList = new ArrayList<>();
                    tempList.add(a);
                    tempList.add(b);
                    calculatedEntries.add(Map.entry(entry.getKey(), tempList));
                    keysToDecrement.put(entry.getKey(), entry.getValue());

                    if (keysToModify.containsKey(a)) {
                        keysToModify.put(a, keysToModify.get(a) + entry.getValue());
                    } else {
                        keysToModify.put(a, entry.getValue());
                    }
                    if (keysToModify.containsKey(b)) {
                        keysToModify.put(b, keysToModify.get(b) + entry.getValue());
                    } else {
                        keysToModify.put(b, entry.getValue());
                    }
                } else {
                    List<Long> temp = new ArrayList<>();
                    temp.add(entry.getKey() * 2024L);
                    keysToDecrement.put(entry.getKey(), entry.getValue());
                    calculatedEntries.add(Map.entry(entry.getKey(), temp));

                    if (keysToModify.containsKey(temp.getFirst())) {
                        keysToModify.put(temp.getFirst(), keysToModify.get(temp.getFirst()) + entry.getValue());
                    } else {
                        keysToModify.put(temp.getFirst(), entry.getValue());
                    }

                }
            }

            for (Long key : keysToDecrement.keySet()) {
                if (input.containsKey(key)) {
                    input.remove(key, keysToDecrement.get(key));
                }
            }
            for (Long key : keysToModify.keySet()) {
                input.putIfAbsent(key, 0L);
                input.replace(key, keysToModify.get(key));
            }
            for (Map.Entry<Long, List<Long>> entry : calculatedEntries) {
                calculated.put(entry.getKey(), entry.getValue());
            }
        }
        Long out = 0L;
        for (Map.Entry<Long, Long> entry : input.entrySet()) {
            out += entry.getValue();
        }
        return out;
    }
}