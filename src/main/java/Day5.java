import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        boolean firstHalf = true;
        int total = 0;
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
            total += checkIfCorrect(updatesList.get(i), data);
        }
        System.out.println(total);
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
                    return 0;
                }
                if (!a) {
                    return 0;
                }

            }
        }
        return updateList.get((updateList.size() / 2));
    }
}
