package com.jh.iam.utils.crypto.sm.sm4;

import com.jh.iam.utils.crypto.sm.utils.Util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;*/
import java.util.Base64;

public class SM4Utils {
    private String secretKey = "";
    private String iv = "";
    private boolean hexString = false;

	private static String hexStringstr = "0123456789ABCDEF";
	private static String ENC_PREFIX = "43383932443844452D463037382D413841362D393935302D393631303333313033463144";
	
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public boolean isHexString() {
        return hexString;
    }

    public void setHexString(boolean hexString) {
        this.hexString = hexString;
    }

    public SM4Utils() {
    }

    public String encryptData_ECB(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
            } else {
                keyBytes = secretKey.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes("UTF-8"));
            //String cipherText = new BASE64Encoder().encode(encrypted);
            String cipherText = Base64.getEncoder().encodeToString(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_ECB(String cipherText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
            } else {
                keyBytes = secretKey.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, Base64.getDecoder().decode(cipherText));
            //byte[] decrypted = sm4.sm4_crypt_ecb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encryptData_CBC(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("UTF-8"));
            //String cipherText = new BASE64Encoder().encode(encrypted);
            String cipherText = Base64.getEncoder().encodeToString(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_CBC(String cipherText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, Base64.getDecoder().decode(cipherText));
            //byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sm4CBCEncrypt(String data, String key, String iv) {
        if (data == null || data.trim().length() == 0 || key == null || iv == null) {
            return null;
        }

        SM4Utils sm4Utils = new SM4Utils();
        sm4Utils.setIv(iv);
        sm4Utils.setSecretKey(key);
        sm4Utils.setHexString(false);
        return sm4Utils.encryptData_CBC(data);
    }

    public static String sm4CBCDecrypt(String data, String key, String iv) {
        if (data == null || data.trim().length() < 1 || key == null || iv == null) {
            return null;
        }
        SM4Utils sm4Utils = new SM4Utils();
        sm4Utils.setIv(iv);
        sm4Utils.setSecretKey(key);
        sm4Utils.setHexString(false);
        return sm4Utils.decryptData_CBC(data);
    }

    public static String sm4ECBEncrypt(String data, String key) {
        if (data == null || data.trim().length() == 0) {
            return null;
        }

        SM4Utils sm4Utils = new SM4Utils();
        sm4Utils.setSecretKey(key);
        sm4Utils.setHexString(false);
		String sm4Str = sm4Utils.encryptData_ECB(data);
		sm4Str = encode(sm4Str);
        return sm4Str;
    }

    public static String sm4ECBDecrypt(String data, String key) {
        if (data == null || data.trim().length() < 1) {
            return null;
        }
        SM4Utils sm4Utils = new SM4Utils();
        sm4Utils.setSecretKey(key);
        sm4Utils.setHexString(false);
        return sm4Utils.decryptData_ECB(data);
    }
    
    
    
    
 
    
    /**
	 * 传入appkey,不足16位前方补0至16位
	 * @param appCode
	 * @return
	 */
	public static String getAppkey(String appkey){
		String appkey_result = "";
			

		int appkeyLength = appkey.length();
		//如果appkey不足16位则补充0至16位
		if(appkeyLength < 16){
			int lackLength = 16 - appkeyLength;
			String lackString = "";
			for(int i = 0; i < lackLength; i++){
				lackString += "0";
			}
			appkey_result = lackString + appkey;
		}
		
		return appkey_result;
	}
    
	
	/**
     * token加密方法
     * @param token 接收的token
     * @param appKey 12位秘钥 (例: k73yz8tJCW0=)
     * @return result 加密后的token(例: 0000k73yz8tJCW0=)
     */
    public static String sm4(String token, String appKey){
    	String result = "";
    	if(appKey == null || token == null || "".equals(token) || "".equals(appKey)){
    		return result;
    	}
    	String appkey_temp = getAppkey(appKey);
    	result = SM4Utils.sm4ECBEncrypt(token, appkey_temp);
    	
    	return result;
    }
	
	public static String encode(String str) {
		byte[] bytes = str.getBytes();
		return encode(bytes);
	}

	public static String encode(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexStringstr.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexStringstr.charAt((bytes[i] & 0x0f) >> 0));
		}
		return ENC_PREFIX + sb.toString();
	}

    public static void main(String[] args) throws IOException {
    	
    	
    	System.out.println(getAppkey("k73yz8tJCW0="));

        String ddd = "DBEeU3TJ3jNMG%2F2Q2g%2FKIArrevhwnVfOHUNIcJvG6o7eIX5a7o3eqssKWzaQ9vx%2Ft20F3k0cHuI1%0D%0AT1kaHvLo8z5%2BCAfBzd4CtFeoQYzigTXb6zQaWiXD2vQ5wH1D2LzxA3ERSyo0UPsdQ0hwm8bqjtP6%0D%0AakELAXIMQesBzwf5fXrzyGEuww%2BlzPXDzoUjCkPN";
        //对外提供方法
        String aaa = SM4Utils.sm4ECBEncrypt(ddd, "0000k73yz8tJCW0=");
        String bbb = SM4Utils.sm4ECBDecrypt(aaa, "0000k73yz8tJCW0=");
        System.out.println(aaa);
        System.out.println(bbb);


    }
}
