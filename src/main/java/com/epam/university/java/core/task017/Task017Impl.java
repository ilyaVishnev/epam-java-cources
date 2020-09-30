package com.epam.university.java.core.task017;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

public class Task017Impl implements Task017 {

    /**
     * Output objects as formatted string.
     *
     * @param args objects to output
     * @return formatted string
     */
    public String formatString(Object... args) {
        if (args.length == 0 || Arrays.asList(args).contains(null)) {
            throw new IllegalArgumentException();
        }
        return String.format("You know %s, %s!", args[0], args[1]);
    }

    /**
     * Output objects as formatted string.
     *
     * @param args objects to output
     * @return formatted string
     */
    public String formatNumbers(Object... args) {
        if (args.length == 0 || Arrays.asList(args).contains(null)) {
            throw new IllegalArgumentException();
        }
        String format = "";
        String str = "";
        if (args[0] instanceof Double) {
            format = "f";
            double dS = (double) args[0];
            str = Double.toHexString(dS);
        } else if (args[0] instanceof Long) {
            format = "d";
            long lS = (long) args[0];
            str = Long.toHexString(lS);
        } else {
            format = "d";
            int li = (int) args[0];
            str = Integer.toHexString(li);
        }
        return String.format(Locale.ROOT, "%.1" + format + ", " + "%.2" + format + ", "
                + "%+.2" + format + ", " + "%s", args[0], args[0], args[0], str);
    }

    /**
     * Output objects as formatted string.
     *
     * @param args objects to output
     * @return formatted string
     */
    public String formatDates(Object... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.dd.MM");
        return dateFormat.format(args[0]);
    }
}
