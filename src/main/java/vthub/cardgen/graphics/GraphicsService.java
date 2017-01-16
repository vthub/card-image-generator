/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.graphics;

import com.google.inject.Singleton;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class GraphicsService {

    /**
     * List of fonds in the order of their priority
     */
    private static final String[] FONTS = {
            "Courier New",
            "Courier 10 Pitch",
            "FreeMono",
            "Monospaced"
    };

    public List<String> getAvailableFonts() {
        return Optional.ofNullable(GraphicsEnvironment.getLocalGraphicsEnvironment())
                .map(GraphicsEnvironment::getAvailableFontFamilyNames)
                .map(Arrays::stream).orElseGet(Stream::empty)
                .collect(Collectors.toList());
    }

    public String getFont() {
        List<String> availableFonts = getAvailableFonts();
        return Arrays.stream(FONTS).filter(availableFonts::contains).findFirst().orElse("Monospaced");
    }

}
