public class TextToBeast {
    private static final char[] bd = {'嗷', '呜', '啊', '~'};

    public static void main(String[] args){
        String str = "this is a test";
        String enc = ToBeast(str);
        System.out.println(FromBeast(enc));
    }

    private static String ToBeast(String str){
        return "" + bd[3] + bd[1] + bd[0] + HexToBeast(ToHex(str)) + bd[2];
    }

    private static String FromBeast(String str){
        str = str.substring(3, str.length()-1);
        return FromHex(BeastToHex(str));
    }

    private static String ToHex(String str){  // 字符串十六进制，不足4位补零
        char[] UBytes = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char c : UBytes){
            StringBuilder hexB = new StringBuilder(Integer.toHexString(c));
            while (hexB.length() < 4){
                hexB.insert(0, "0");
            }
            stringBuilder.append(hexB);
        }
        return stringBuilder.toString();
    }

    private static String FromHex(String dataStr){  // 十六进制字符串转字符串
        StringBuffer stringBuffer = new StringBuffer();
        int start = 0;

        for(int end = 4;end <= dataStr.length();end += 4){
            stringBuffer.append(Character.toString((char) Integer.parseInt(dataStr.substring(start, end), 16)));
            start += 4;
        }
        return stringBuffer.toString();
    }

    private static String HexToBeast(String tf){  // 十六进制转兽语
        char[] tfArr = tf.toCharArray();
        StringBuffer beast = new StringBuffer();

        for(int i = 0;i < tfArr.length;i++){
            int k = Integer.valueOf(String.valueOf(tfArr[i]), 16) + (i % 16);
            if(k >= 16){
                k -= 16;
            }
            beast.append("" + bd[k / 4] + bd[k % 4]);
        }
        return beast.toString();
    }

    private static String BeastToHex(String encode){
        char[] bfArr = encode.toCharArray();
        StringBuffer bf = new StringBuffer();

        for(int i = 0;i <= bfArr.length - 2;i += 2){
            int pos1 = 0;
            int pos2 = 0;
            char c = bfArr[i];
            while (pos1 <= 3 && c != bd[pos1]){
                pos1++;
            }
            char c2 = bfArr[i+1];
            while (pos2 <= 3 && c2 != bd[pos2]){
                pos2++;
            }
            int k = ((pos1 * 4) + pos2) - ((i / 2) % 16);
            if(k < 0){
                k += 16;
            }
            bf.append(Integer.toHexString(k));
        }
        return bf.toString();
    }
}
