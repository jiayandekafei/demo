package hoperun.pagoda.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

/**
 * String utils.
 *
 * @author zhangxiqin
 *
 */
@Component
public final class StringUtils {

    /**
     * no parameter Construtor.
     */
    private StringUtils() {
    }

    /**
     * convert String to list.
     * @param input  input
     * @return List<Integer>
     */
    public static List<Integer> convertStringIntList(final String input) {
        List<Integer> list = new ArrayList<>();
        Optional.ofNullable(input).map(str -> str.split(",")).ifPresent(str -> {
            for (int i = 0; i < str.length; i++) {
                if (!"".equals(str[i])) {
                    list.add(Integer.parseInt(str[i]));
                }
            }

        });
        return list;
    }
}
