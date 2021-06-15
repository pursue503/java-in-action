package chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * java-in-action p52  , 1.3.2 챕터
 *
 */

public class FilteringApples {

    public static void main(String[] args) {

        //활용
        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);

        //자바 8 에서 람다를 사용하여 위의 코드를 간략하게 바꿈
        List<Apple> greenApples2 = filterApples(inventory, (Apple apple) -> "green".equals(apple.getColor()));
        System.out.println(greenApples2);

        List<Apple> heavyApples2 = filterApples(inventory, (Apple apple) -> apple.getWeight() > 150);
        System.out.println(heavyApples2);

        //  응용  weight 가 150보다크고 color 가 green
        List<Apple> appleList = filterApples(inventory, (Apple apple) -> apple.getWeight() > 150 &&  "green".equals(apple.getColor()));
        System.out.println(appleList);

        //스트림으로 위와 동일한 결과를 리턴해줌.
        List<Apple> appleList1 = inventory.stream().filter(apple -> apple.getWeight() > 150 && "green".equals(apple.getColor())).collect(Collectors.toList());;
        System.out.println(appleList1);

    }

    // 예시에 사용할 Apple 클래스 작성
    public static class Apple {

        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color='%s', weight=%d}", color, weight);
        }

    }


    //자바 8 이상 방식

    public static boolean isGreenApple(Apple apple) { return "green".equals(apple.getColor()); }

    public static boolean isHeavyApple(Apple apple) { return apple.getWeight() > 150; };

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) { // 메서드가 p라는 이름의 프레디케이트 파라미터로 전달됨 , 위에서 isGreenApple 가 전달됨
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) { // 메소드 p -> isGreenApple
                result.add(apple);
            }
        }
        return result;
    }

    /////////////////////////////////////////////////////////////////

    // 자바 7 이하 방식 , List 에서 green 이라는 컬러를 가진 값만 뽑아야할때
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    // 자바 7 이하 방식 , List 에서 Weight 가 150보다 큰경우만 뽑아야할댸 구현해야함
    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

}
