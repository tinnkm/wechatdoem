package com.tinnkm.application.util.encode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author tinnkm
 */
public class EncodeUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncodeUtils.class);

    private EncodeUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取sha1加密的结果
     *
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String sha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * 获取md5加密的字符串
     *
     * @param str 带加密字符串
     * @return 加密后的字符串
     */
    public static String md5(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("NoSuchAlgorithmException caught!");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getLocalizedMessage());
        }

        if (null == messageDigest) {
            LOGGER.info("get messageDigest filed!");
            return null;
        }
        byte[] byteArray = messageDigest.digest();

        StringBuilder md5StrBuff = new StringBuilder();

        for (byte aByteArray : byteArray) {
            if (Integer.toHexString(0xFF & aByteArray).length() == 1) {
                md5StrBuff.append("0").append(String.format("%02X",0xFF & aByteArray));
            } else {
                md5StrBuff.append(String.format("%02X",0xFF & aByteArray));
            }
        }

        return md5StrBuff.toString();
    }
}
