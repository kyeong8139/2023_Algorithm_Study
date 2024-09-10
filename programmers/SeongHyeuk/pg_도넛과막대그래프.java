import java.util.*;

public static class pg_도넛과막대그래프 {
    static Map<Integer, Set<Integer>> map;
    static Map<Integer, Set<Integer>> reverseMap;
    static Set<Integer> eight;
    static Set<Integer> cycle;
    static Set<Integer> rod;
    static int[] answer;

    public static int[] solution(int[][] edges) {
        answer = new int[4];
        map = new HashMap<Integer, Set<Integer>>();
        reverseMap = new HashMap<Integer, Set<Integer>>();
        List<Integer> list = new ArrayList<>();
        for(int[] edge : edges){
            if(map.containsKey(edge[0])){
                Set<Integer> set = map.get(edge[0]);
                set.add(edge[1]);
                map.put(edge[0], set);
                if(reverseMap.containsKey(edge[1])){
                    Set<Integer> reverseSet = reverseMap.get(edge[1]);
                    reverseSet.add(edge[0]);
                    reverseMap.put(edge[1], reverseSet);
                } else {
                    Set<Integer> reverseSet = new HashSet<>();
                    reverseSet.add(edge[0]);
                    reverseMap.put(edge[1], reverseSet);
                }
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(edge[1]);
                map.put(edge[0], set);
                if(reverseMap.containsKey(edge[1])){
                    Set<Integer> reverseSet = reverseMap.get(edge[1]);
                    reverseSet.add(edge[0]);
                    reverseMap.put(edge[1], reverseSet);
                } else {
                    Set<Integer> reverseSet = new HashSet<>();
                    reverseSet.add(edge[0]);
                    reverseMap.put(edge[1], reverseSet);
                }
            }
            if(map.get(edge[0]).size()>2){
                answer[0] = edge[0];
            } else if(map.get(edge[0]).size()==2){
                list.add(edge[0]);
            }
        }
        eight = new HashSet<>();
        for(int node :list){
            if(isEight(node)){
                ++answer[3];
            } else {
                answer[0] = node;
            }
        }
        cycle = new HashSet<>();
        for(int node : map.get(answer[0])){
            if(!eight.contains(node)){
                if(isCycle(node)){
                    ++answer[1];
                }
            }
        }
        rod = new HashSet<>();
        for(int node : map.get(answer[0])){
            if(!eight.contains(node)&&!cycle.contains(node)){
                if(isRod(node)){
                    ++answer[2];
                }
            }
        }
        System.out.println("팔자");
        System.out.println(eight.toString());
        System.out.println("도넛");
        System.out.println(cycle.toString());
        System.out.println("막대");
        System.out.println(rod.toString());
        return answer;
    }

    public static boolean isEight(int node){
        boolean A = false;
        boolean B = false;
        Set<Integer> route = new HashSet<>();
        for(int i : map.get(node)){
            route.add(node);
            int now = i;
            boolean isTrue = false;
            while(true){
                route.add(now);
                int next = 0;
                try{
                    for(int j : map.get(now)) {
                        next = j;
                    }
                } catch (Exception e) {
                    return B;
                }
                if(route.contains(next)){
                    isTrue = true;
                    break;
                } else {
                    now = next;
                }
            }
            if(isTrue){
                if(A){
                    eight.addAll(route);
                    return A;
                } else {
                    A = true;
                }
            }
        }
        return B;
    }

    public static boolean isCycle(int node){
        int now = node;
        Set<Integer> route = new HashSet<>();
        while(true){
            route.add(now);
            int next = 0;
            try{
                if(map.get(now).size() == 1){
                    for(int j : map.get(now)) {
                        next = j;
                    }
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            if(route.contains(next)){
                cycle.addAll(route);
                return true;
            } else {
                now = next;
            }
        }
    }

    public static boolean isRod(int node){
        Map<Integer,Set<Integer>> isgo = new HashMap<>();
        Map<Integer,Set<Integer>> isreverse = new HashMap<>();
        Set<Integer> route = new HashSet<>();
        if(map.containsKey(node)&&reverseMap.containsKey(node)){
            return false;
        }
        if(map.containsKey(node)){
            isgo = map;
            isreverse = reverseMap;
        } else{
            isgo = reverseMap;
            isreverse = map;
        }
        boolean first = true;
        int now = node;
        while(true){
            route.add(now);
            int next = 0;
            if(first){
                try{
                    if(isgo.get(now).size() == 1){
                        for(int j : isgo.get(now)) {
                            if(j!= answer[0]){
                                next = j;
                            }
                        }
                    } else {
                        if(isreverse.get(now).size() != 1){
                            if(now==node&&isreverse.get(now).size() == 2){
                                for(int j : isgo.get(now)) {
                                    if(j!= answer[0]){
                                        next = j;
                                    }
                                }
                            } else {
                                return false;
                            }
                        } else {
                            for(int j : isgo.get(now)) {
                                next = j;
                            }
                        }
                    }
                    now = next;
                } catch (Exception e) {
                    rod.addAll(route);
                    return true;
                }
            } else {
                try{
                    if(isgo.get(now).size() == 1){
                        for(int j : isgo.get(now)) {
                            if(j!= answer[0]){
                                next = j;
                            }
                        }
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    rod.addAll(route);
                    return true;
                }
                now = next;
            }
        }
    }
}