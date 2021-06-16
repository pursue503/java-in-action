package chapter01;

import java.util.Optional;

public class OptionalTest {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        // Optional 기억나는대로 미리 예습

        //객체를 가져올 클래스 선언
        GetOptionalTest getOptionalTest = new GetOptionalTest();

        OptionalTest optionalTest = getOptionalTest.optionalTestNull().orElseThrow(NullPointerException::new);

        OptionalTest optionalTest1 = getOptionalTest.optionalTestNull().orElse(new OptionalTest());

    }

}

class GetOptionalTest {

    //NULL 을 리턴
    public Optional<OptionalTest> optionalTestNull() {
        return null;
    }

}