//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ultrapower.ams.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.MessageDigest;

public class ＵｌｔｒａＤｅｇｉｓｔ {
    public ＵｌｔｒａＤｅｇｉｓｔ() {
    }

    public boolean ｉｓＥｑｕａｌ(byte[] ａ, byte[] ｂ) {
        return MessageDigest.isEqual(ａ, ｂ);
    }

    public MessageDigest ｇｅｔＩｎｔａｎｃｅ() {
        try {
            return MessageDigest.getInstance("SHA-1");
        } catch (Exception var2) {
            return null;
        }
    }

    public String ｃｏｎｖｅｒｔ(String ｌｄａｐＰｗｄ) {
        try {
            if (ｌｄａｐＰｗｄ.startsWith("{SSHA}")) {
                ｌｄａｐＰｗｄ = ｌｄａｐＰｗｄ.substring(6);
            } else if (ｌｄａｐＰｗｄ.startsWith("{SHA}")) {
                ｌｄａｐＰｗｄ = ｌｄａｐＰｗｄ.substring(5);
            }

            return ｌｄａｐＰｗｄ;
        } catch (Exception var3) {
            return null;
        }
    }

    public byte[] ｇｅｎｅｒａｔｅＭＤ(String ｌｄａｐＰｗｄ, String ｏｌｄＰｗｄ) {
        try {
            MessageDigest ｍｄ = this.ｇｅｔＩｎｔａｎｃｅ();
            if (ｌｄａｐＰｗｄ.startsWith("{SSHA}")) {
                ｌｄａｐＰｗｄ = ｌｄａｐＰｗｄ.substring(6);
            } else if (ｌｄａｐＰｗｄ.startsWith("{SHA}")) {
                ｌｄａｐＰｗｄ = ｌｄａｐＰｗｄ.substring(5);
            }

            byte[] ｌｄａｐＰｗｄＢｙｔｅ = Base64.decode(ｌｄａｐＰｗｄ);
            byte[] ｓａｌｔ;
            if (ｌｄａｐＰｗｄＢｙｔｅ.length <= 20) {
                ｓａｌｔ = new byte[0];
            } else {
                byte[] ｓｈａＣｏｄｅ = new byte[20];
                ｓａｌｔ = new byte[ｌｄａｐＰｗｄＢｙｔｅ.length - 20];
                System.arraycopy(ｌｄａｐＰｗｄＢｙｔｅ, 0, ｓｈａＣｏｄｅ, 0, 20);
                System.arraycopy(ｌｄａｐＰｗｄＢｙｔｅ, 20, ｓａｌｔ, 0, ｓａｌｔ.length);
            }

            ｍｄ.update(ｏｌｄＰｗｄ.getBytes());
            ｍｄ.update(ｓａｌｔ);
            byte[] ｉｎｐｕｔＰｗｄＢｙｔｅ = ｍｄ.digest();
            return ｉｎｐｕｔＰｗｄＢｙｔｅ;
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    public byte[] ｄｅｃｏｄｅＴｏＢａｓｅ６４(String ｔｍｐＳｔｒ) {
        return Base64.decode(ｔｍｐＳｔｒ);
    }
}
