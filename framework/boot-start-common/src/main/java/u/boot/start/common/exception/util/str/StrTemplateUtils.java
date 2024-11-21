package u.boot.start.common.exception.util.str;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.PropertyPlaceholderHelper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 使用 ${} 占位符的字符串模板工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StrTemplateUtils {
    private static final PropertyPlaceholderHelper HELPER = new PropertyPlaceholderHelper("${", "}");
    private static final Pattern PATTERN = Pattern.compile("\\$\\{[^\\}]+\\}");

    /**
     * 使用 ${} 占位符的字符串模板
     * @param template 模板
     * @param paramMap 参数
     * @return 模板替换后的字符串
     */
    public static String getString(String template, Map<String, String> paramMap) {
        if (PATTERN.matcher(template).find()) {
            return HELPER.replacePlaceholders(template,
                    propertyName -> paramMap.getOrDefault(propertyName, ""));
        } else {
            return template;
        }
    }

    /**
     * 使用 ${} 占位符的字符串模板
     * @param template 模板
     * @param o 参数对象
     * @return 模板替换后的字符串
     */
    public static String getString(String template, Object o) {
        Map<String, String> paramMap = new HashMap<>();
        for (Field declaredField : o.getClass().getDeclaredFields()) {
            try {
                String value = o.getClass().getDeclaredField(declaredField.getName()).get(o).toString();
                paramMap.put(declaredField.getName(), value);
            } catch (Exception e) {
                paramMap.put(declaredField.getName(), "");
            }
        }
        return getString(template, paramMap);
    }
}
