package com.epam.university.java.core.task025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task025Impl implements Task025 {

    /**
     * Martian exploration ship has been broken and sent several SOS messages back to Earth.
     * Some letters of the SOS message are altered by cosmic radiation during transmission.
     * Given the signal received by Earth as a string, determine how many letters of SOS
     * has been changed by radiation.
     *
     * <p>
     * Example: source SOSOASOB, result is 2
     * </p>
     *
     * @param sourceMessage received message
     * @return amount of altered letters
     */
    @Override
    public int getAmountOfAlteredLetters(String sourceMessage) {
        if (sourceMessage.equals("")) {
            return 0;
        }
        String[] sourceArray = sourceMessage.split("");
        int mistake = 0;
        List<String> sos = Arrays.asList("S", "O", "S");
        int index = 0;
        for (int i = 0; i < sourceArray.length; i++) {
            if (!sos.get(index).equals(sourceArray[i])) {
                mistake++;
            }
            if (index++ == 2) {
                index = 0;
            }
        }
        return mistake;
    }
}
