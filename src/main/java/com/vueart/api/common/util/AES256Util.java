package com.vueart.api.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@Slf4j
public class AES256Util {
    @Value("${aes.key}")
    private String secretKey;

    //암호화
    public String encode(String str) {
        if (str == null) return null;

        try {
            byte[] keyData = secretKey.getBytes();
            SecretKey secureKey = new SecretKeySpec(keyData, "AES");

            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(secretKey.substring(0, 16).getBytes()));

            byte[] encrypted = c.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.encodeBase64(encrypted));
        } catch (IllegalArgumentException | InvalidAlgorithmParameterException | NoSuchPaddingException
                 | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e){
            log.warn("encoding error");
        }
        return str;
    }

    //복호화
    public String decode(String str) {
        if (str == null) return null;
        try {
            byte[] keyData = secretKey.getBytes();
            SecretKey secureKey = new SecretKeySpec(keyData, "AES");

            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(secretKey.substring(0, 16).getBytes(StandardCharsets.UTF_8)));

            byte[] byteStr = Base64.decodeBase64(str.getBytes());
            return new String(c.doFinal(byteStr), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException | InvalidAlgorithmParameterException | NoSuchPaddingException
                 | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            log.warn("encoding error");
        }
        return str;

    }
}
