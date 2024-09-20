package study2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

//public class pg_메뉴리뉴얼 {
//	static HashMap<Integer, Integer> map;
//	static HashSet<Integer> set;
//
//	public static void main(String[] args) {
////		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
//		String[] orders = { "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD" };
//		int[] course = { 2, 3, 5 };
//
//		solution(orders, course);
//
////		System.out.println(solution(orders, course));
//	}
//
//	public static String[] solution(String[] orders, int[] course) {
//		map = new HashMap<>();
//		set = new HashSet<>();
//
//		for (int i : course) {
//			set.add(i);
//		}
//
//		for (String order : orders) {
//			Perm(0, 0, 0, order, 0);
//		}
//
//		List<String> result = new ArrayList<>();
//
//		for (int courseLength : course) {
//			int maxCount = 0;
//			List<String> maxCombinations = new ArrayList<>();
//
//			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//				if (Integer.bitCount(entry.getKey()) == courseLength && entry.getValue() >= 2) {
//					if (entry.getValue() > maxCount) {
//						maxCount = entry.getValue();
//						maxCombinations.clear();
//						maxCombinations.add(bitToString(entry.getKey()));
//					} else if (entry.getValue() == maxCount) {
//						maxCombinations.add(bitToString(entry.getKey()));
//					}
//				}
//			}
//
//			result.addAll(maxCombinations);
//		}
//
//		return result.stream().sorted().toArray(String[]::new);
//	}
//
//	public static void Perm(int put, int idx, int depth, String course, int bit) {
//		if (set.contains(Integer.bitCount(bit)) && put == 1 && depth >= 2) {
//			map.put(bit, map.getOrDefault(bit, 0) + 1);
//		}
//
//		if (idx == course.length())
//			return;
//
//		Perm(0, idx + 1, depth, course, bit);
//		Perm(1, idx + 1, depth + 1, course, (bit | (1 << (course.charAt(idx) - 'A'))));
//	}
//
//	private static String bitToString(int bit) {
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < 26; i++) {
//			if ((bit & (1 << i)) > 0) {
//				sb.append((char) ('A' + i));
//			}
//		}
//		return sb.toString();
//	}
//}

import java.util.*;

import java.util.*;

public class pg_메뉴리뉴얼 {
    static Map<Integer, Integer> map;
    static Set<Integer> courseSet;
    
    public static void main(String[] args) {
    	// String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
    	String[] orders = { "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD" };
    	int[] course = { 2, 3, 5 };

    	solution(orders, course);

    	// System.out.println(solution(orders, course));
    	}

    public static String[] solution(String[] orders, int[] course) {
        map = new HashMap<>();
        courseSet = new HashSet<>();
        for (int c : course) {
            courseSet.add(c);
        }

        for (String order : orders) {
            char[] orderChars = order.toCharArray();
            Arrays.sort(orderChars);  // 알파벳 순으로 정렬
            Perm(0, 0, new String(orderChars), 0);
        }

        List<String> result = new ArrayList<>();
        for (int length : course) {
            int maxCount = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (Integer.bitCount(entry.getKey()) == length) {
                    maxCount = Math.max(maxCount, entry.getValue());
                }
            }
            
            if (maxCount >= 2) {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (Integer.bitCount(entry.getKey()) == length && entry.getValue() == maxCount) {
                        result.add(bitToString(entry.getKey()));
                    }
                }
            }
        }

        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    public static void Perm(int idx, int depth, String order, int bit) {
        if (courseSet.contains(depth) && depth >= 2) {
            map.put(bit, map.getOrDefault(bit, 0) + 1);
        }

        if (idx == order.length()) return;

        Perm(idx + 1, depth + 1, order, bit | (1 << (order.charAt(idx) - 'A')));
        Perm(idx + 1, depth, order, bit);
    }

    private static String bitToString(int bit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if ((bit & (1 << i)) > 0) {
                sb.append((char) ('A' + i));
            }
        }
        return sb.toString();
    }
}

class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();
        
        for (int courseLength : course) {
            Map<String, Integer> combinations = new HashMap<>();
            
            for (String order : orders) {
                char[] orderChars = order.toCharArray();
                Arrays.sort(orderChars);
                combination("", new String(orderChars), courseLength, 0, combinations);
            }
            
            if (!combinations.isEmpty()) {
                int maxCount = Collections.max(combinations.values());
                if (maxCount >= 2) {
                    for (Map.Entry<String, Integer> entry : combinations.entrySet()) {
                        if (entry.getValue() == maxCount) {
                            result.add(entry.getKey());
                        }
                    }
                }
            }
        }
        
        Collections.sort(result);
        return result.toArray(new String[0]);
    }
    
    private void combination(String prefix, String str, int k, int start, Map<String, Integer> combinations) {
        if (k == 0) {
            combinations.put(prefix, combinations.getOrDefault(prefix, 0) + 1);
            return;
        }
        
        for (int i = start; i < str.length(); i++) {
            combination(prefix + str.charAt(i), str, k - 1, i + 1, combinations);
        }
    }
}