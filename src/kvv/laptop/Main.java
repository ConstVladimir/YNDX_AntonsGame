package kvv.laptop;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        int n = 11;
        String str = "1 5 1 6 2 3 4 2 3 2 4";

        AtomicInteger i = new AtomicInteger(0);

        Map<Integer,Integer> enemy = Arrays.stream(str.split(" "))
                .collect(Collectors
                        .toMap(t->i.incrementAndGet(),t->Integer.parseInt(t)))
                .entrySet().stream()
                .sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));


        Integer level = 1;
        Integer points = 0;
        boolean status = true;

        for (Iterator<Integer> it = enemy.values().iterator(); it.hasNext(); ) {
            Integer next_enemy_level = it.next();
            if (level < next_enemy_level){
                status = false;
                break;
            }
            else if (level < 2*next_enemy_level) ++points;
            if (points == 3) {
                points = 0;
                --level;
            }
            ++level;
        }

        if (!status)
        {
            System.out.println ("Impossible");
        }
        else {
            System.out.println ("Possible");
            System.out.println (enemy.keySet().stream().map(t->t.toString()).reduce("",(e1,e2)->e1+e2+" "));
        }
    }
}
