package u.boot.start.utools.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author xiaou
 * @date 2024/2/22
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UtoolsSignUtils {

    public static String sign(Map<String, String> params, String secret) {
        String s = convertMapToString(params);
        try {
            return calculateHmac(s, secret);
        } catch (Exception e) {
            return "";
        }
    }


    public static String calculateHmac(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
        byte[] bytes = hmac.doFinal(data.getBytes());
        return bytesToHex(bytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static String convertMapToString(Map<String, String> params) {
        // 1. Sort the map by keys
        Map<String, String> sortedMap = new TreeMap<>(params);

        // 2. Build a query string
        return sortedMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }
}
