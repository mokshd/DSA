package DSA.StreamApi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

//        String s="Mississipi";
////        Map<Character, Long> res = s.chars().mapToObj(x->(char)x).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
////        System.out.println(res);

//        String multiLine = """
//    This is a
//    multi-line
//    string in Java!
//    """;
//        System.out.println(multiLine);
//
//        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().toList();
//
//        list.parallelStream()
//                .forEachOrdered(n -> System.out.println(n + " " + Thread.currentThread().getName()));
//
//    }
//        Optional<String> opt = Optional.of("java");
//        Optional<String> upper = opt.map(String::toUpperCase);
//        System.out.println(upper.get()); // JAVA
//
//    }

        //filter

//        List<Integer> nums = List.of(1, 2, 3, 3, 4, 5, 6);
//        nums.stream().filter(x -> x > 5).forEach(System.out::println);

        //Map
//        List<String> list=Arrays.asList("apple", "bannan","orange");
//        list.stream().map(x->x.toUpperCase()).forEach(System.out::println);

        //Flamap
//        List<List<String>> orders=Arrays.asList(Arrays.asList("macbok","mipad"),Arrays.asList("macbok","mipad"));
//        Map<String, Long> numOfItems=orders.stream().flatMap(List::stream).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
////        numOfItems.forEach((a,b)->System.out.println(a+ " "+ b));
//        System.out.println(numOfItems);

        List<Integer> nums = List.of(1, 2, 3, 3, 4, 5, 6);
        Optional<Integer> res=nums.stream().collect(Collectors.maxBy(Integer::compare));
        res.ifPresent(x->System.out.println(x));
//        System.out.println();
//        Optional<Integer> max = nums.stream().max(Integer::compareTo);

    }

}
