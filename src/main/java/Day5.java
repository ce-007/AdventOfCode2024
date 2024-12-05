import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    public static int totalIncorrect = 0;

    public static void main(String[] args) throws FileNotFoundException {
        boolean firstHalf = true;
        int totalCorrect = 0;

        File file = new File("inputDay5");
        Scanner scanner = new Scanner(file);
        Map<Integer, List<Integer>> data = new HashMap<>();
        List<List<Integer>> updatesList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String temp = scanner.nextLine();
            if (temp.isEmpty()) {
                firstHalf = false;
                temp = scanner.nextLine();
            }
            if (firstHalf) {
                if (data.containsKey(Integer.parseInt(temp.substring(0, 2)))) {
                    List<Integer> list = data.get(Integer.parseInt(temp.substring(0, 2)));
                    list.add(Integer.parseInt(temp.substring(3)));
                    data.put(Integer.parseInt(temp.substring(0, 2)), list);
                } else {
                    List<Integer> babyList = new ArrayList<>();
                    babyList.add((Integer.parseInt(temp.substring(3))));
                    data.put(Integer.parseInt(temp.substring(0, 2)), babyList);
                }
            } else {
                List<Integer> list = new ArrayList<>();
                String[] rawList = temp.split(",");
                for (int i = 0; i < rawList.length; i++) {
                    list.add(Integer.parseInt(rawList[i]));
                }
                updatesList.add(list);
            }
        }

        for (int i = 0; i < updatesList.size(); i++) {
            totalCorrect += checkIfCorrect(updatesList.get(i), data);
        }
        System.out.println(totalCorrect);
        System.out.println(totalIncorrect);
    }

    private static int checkIfCorrect(List<Integer> updateList, Map<Integer, List<Integer>> map) {
        for (int i = 0; i < updateList.size(); i++) {
            Integer update = updateList.get(i);
            List<Integer> data = map.get(update);

            for (int k = i + 1; k < updateList.size(); k++) {
                boolean a;
                try {
                    a = data.contains(updateList.get(k));
                } catch (Exception e) {
                    fixIncorrect(updateList.toArray(new Integer[0]), map);
                    return 0;
                }
                if (!a) {
                    fixIncorrect(updateList.toArray(new Integer[0]), map);
                    return 0;
                }
            }
        }
        return updateList.get((updateList.size() / 2));
    }

    private static void fixIncorrect(Integer[] array, Map<Integer, List<Integer>> map) {
        for (int i = 0; i < array.length; i++) {
            boolean fixMe = false;
            int count = 0;

            for (int k = i + 1; k < array.length; k++) {
                try {
                    fixMe = map.get(array[i]).contains(array[k]);
                } catch (Exception ignored) {
                }
                if (!fixMe) {
                    count++;
                }
            }
            int temp = array[i];
            array[i] = array[i + count];
            array[i + count] = temp;
            if (count != 0) {
                i--;
            }
        }
        System.out.println(List.of(array));
        totalIncorrect += array[(array.length / 2)];
    }
}
