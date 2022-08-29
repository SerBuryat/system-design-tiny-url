package com.systemdesign.tinyurl;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HashCoder {

    static final String HASH_SYMBOLS = "asdfghjklzxcvbnmqwertyuiopQWEASDZXCRTYFGHVBNUIOJKLMOPL";

    public static String intHashToStringHash(int hash) {
        var strHash =  String.valueOf(hash).replace("-", "1");
        var result = new StringBuilder();
        var hashSymbolsSize = HASH_SYMBOLS.length();

        for(int i = 0; i < strHash.length(); i++) {
            var curr = String.valueOf(strHash.charAt(i));
            var next = i + 1 <= strHash.length() - 1 ? String.valueOf(strHash.charAt(i + 1)) : "";
            var nextCurrDigit = Integer.parseInt(curr + next);
            if(nextCurrDigit > hashSymbolsSize - 1 || Integer.parseInt(curr) == 0) {
                result.append(HASH_SYMBOLS.charAt(Integer.parseInt(curr)));
            } else {
                result.append(HASH_SYMBOLS.charAt(nextCurrDigit));
                i++;
            }
        }
        return result.toString();
    }
}
