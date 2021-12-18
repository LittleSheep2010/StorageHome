package club.smartsheep.storagehome.Utils;

import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;

public class EncryptionUtils {

    @SneakyThrows
    public static String md5encrypt(String str) {
        return DigestUtils.md5Hex(str).toUpperCase();
    }
}
