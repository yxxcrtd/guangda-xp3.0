//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ultrapower.ams.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    public EncryptUtil() {
    }

    public static boolean ｖｅｒｉｆｙＰｗｄ(String ｌｄａｐＰｗｄ, String ｏｌｄＰｗｄ) throws NoSuchAlgorithmException {
        ＵｌｔｒａＤｅｇｉｓｔ ｕｍｄ = new ＵｌｔｒａＤｅｇｉｓｔ();
        MessageDigest ｍｄ = ｕｍｄ.ｇｅｔＩｎｔａｎｃｅ();
        ｌｄａｐＰｗｄ = ｕｍｄ.ｃｏｎｖｅｒｔ(ｌｄａｐＰｗｄ);
        byte[] ｌｄａｐＰｗｄＢｙｔｅ = ｕｍｄ.ｄｅｃｏｄｅＴｏＢａｓｅ６４(ｌｄａｐＰｗｄ);
        if (ｌｄａｐＰｗｄＢｙｔｅ == null) {
            return false;
        } else {
            byte[] ｓｈａＣｏｄｅ;
            byte[] ｓａｌｔ;
            if (ｌｄａｐＰｗｄＢｙｔｅ.length <= 20) {
                ｓｈａＣｏｄｅ = ｌｄａｐＰｗｄＢｙｔｅ;
                ｓａｌｔ = new byte[0];
            } else {
                ｓｈａＣｏｄｅ = new byte[20];
                ｓａｌｔ = new byte[ｌｄａｐＰｗｄＢｙｔｅ.length - 20];
                System.arraycopy(ｌｄａｐＰｗｄＢｙｔｅ, 0, ｓｈａＣｏｄｅ, 0, 20);
                System.arraycopy(ｌｄａｐＰｗｄＢｙｔｅ, 20, ｓａｌｔ, 0, ｓａｌｔ.length);
            }

            ｍｄ.update(ｏｌｄＰｗｄ.getBytes());
            ｍｄ.update(ｓａｌｔ);
            byte[] ｉｎｐｕｔｐｗｄ = ｍｄ.digest();
            ｉｎｐｕｔｐｗｄ = ｕｍｄ.ｇｅｎｅｒａｔｅＭＤ(ｌｄａｐＰｗｄ, ｏｌｄＰｗｄ);
            return ｕｍｄ.ｉｓＥｑｕａｌ(ｓｈａＣｏｄｅ, ｉｎｐｕｔｐｗｄ);
        }
    }

    public static boolean verifyPwd(String ｌｄａｐＰｗｄ, String ｏｌｄＰｗｄ) throws NoSuchAlgorithmException {
        return ｖｅｒｉｆｙＰｗｄ(ｌｄａｐＰｗｄ, ｏｌｄＰｗｄ);
    }

    public static void main(String[] args) {
    }
}
