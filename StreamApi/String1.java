package DSA.StreamApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class String1 {

    public static void main(String[] args) {
//        String s="i am leanring stram api";
//        String maxLen= Arrays.stream(s.split(" ")).max(Comparator.comparing(String::length)).get();
//        System.out.println(maxLen.length());

//        String s1="uigvcudvuvucvhdv";
//        s1.chars().distinct().mapToObj(x->(char)x).forEach(System.out::print);
//        Arrays.stream(s1.split("")).distinct().forEach(System.out::print);

//        String s="i am leanrink leanring stram api";
//        int res;
//        res = Arrays.stream(s.split(" ")).sorted(Comparator.comparing(String::length).reversed()).mapToInt(String::length).skip(1).findFirst().orElse(-1);
//        System.out.println(res);

        String s="Mississipi";
        Map<Character, Long> res = s.chars().mapToObj(x->(char)x).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(res);

    }
}
