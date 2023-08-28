package passion3.BackEnd.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import passion3.BackEnd.dto.FoodOrderDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static String refineInvalidJson(String input) {
        // 정규 표현식을 사용하여 필드와 값을 찾아 큰따옴표로 감쌈
        String refined = input.replaceAll("(\\w+)(\\s*:\\s*)([\\w가-힣]+)", "\"$1\"$2\"$3\"");
        refined = refined.replaceAll("(\\w+)(\\s*:\\s*)(\\d+)", "\"$1\"$2$3");
        return refined;
    }

    public static <T> T parseRefinedJson(String input, Class<T> clazz) throws Exception {
        String refinedJson = refineInvalidJson(input);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(refinedJson, clazz);
    }
}