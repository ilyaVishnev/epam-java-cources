package com.epam.university.java.core.task026;

public class Task026Impl implements Task026 {

    @Override
    public String encrypt(String sourceString, int shift) {
        char[] sourceArray = sourceString.toCharArray();
        for (int i = 0; i < sourceArray.length; i++) {
            if (String.valueOf(sourceArray[i]).matches("[^_\\s\\p{Punct}]")) {
                int index = (int) sourceArray[i];
                if (index >= 97) {
                    sourceArray[i] = (char) (97 + (sourceArray[i] - 97 + shift) % 26);
                } else {
                    sourceArray[i] = (char) (65 + (sourceArray[i] - 65 + shift) % 26);
                }
            }
        }
        return String.valueOf(sourceArray);
    }

    @Override
    public String decrypt(String encryptedString, int shift) {
        char[] sourceArray = encryptedString.toCharArray();
        for (int i = 0; i < sourceArray.length; i++) {
            if (String.valueOf(sourceArray[i]).matches("[^_\\s\\p{Punct}]")) {
                int index = (int) sourceArray[i];
                if (index >= 97) {
                    sourceArray[i] = (char) (122 - (122 - sourceArray[i] + shift) % 26);
                } else {
                    sourceArray[i] = (char) (90 - (90 - sourceArray[i] + shift) % 26);
                }
            }
        }
        return String.valueOf(sourceArray);
    }
}
