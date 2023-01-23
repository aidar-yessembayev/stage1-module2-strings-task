package com.epam.mjc;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> results = new ArrayList<String>();

        String part = "";
        boolean shouldInclude;

        for (int i = 0; i < source.length(); i++) {
            shouldInclude = true;

            for (String delim : delimiters) {
                if (delim.equals(String.valueOf(source.charAt(i)))) {
                    if (!part.isEmpty()) {
                        results.add(part);
                    }

                    part = "";
                    shouldInclude = false;

                    break;
                }
            }

            if (shouldInclude) {
                part += source.charAt(i);
            }
        }

        if (!part.isEmpty()) {
            results.add(part);
        }

        return results;
    }
}
