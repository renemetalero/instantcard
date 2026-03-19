package com.orchestration.instantcard.validate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.text.Normalizer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplaceNoASCII {

    public static String normalizarString(String theString) {
        if (!StringUtils.hasText(theString)) return "";
        char[] out = new char[theString.length()];
        theString = Normalizer.normalize(theString, Normalizer.Form.NFD);
        int j = 0;
        for (int i = 0, n = theString.length(); i < n; ++i) {
            char c = theString.charAt(i);
            if (c <= '\u007F') {
                out[j++] = c;
            }
        }
        return new String(out).trim();
    }

}