package vthub.cardgen.graphics;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FontUtils {

    /**
     * List of fonds in the order of their priority
     */
    private static final String[] FONTS = {
            "Courier New",
            "Courier 10 Pitch",
            "Cursor",
            "Monospaced"
    };

    public static List<String> getAvailableFonts() {
        return Optional.ofNullable(GraphicsEnvironment.getLocalGraphicsEnvironment())
                .map(GraphicsEnvironment::getAvailableFontFamilyNames)
                .map(Arrays::stream).orElseGet(Stream::empty)
                .collect(Collectors.toList());
    }

    public static String getFont() {
        List<String> availableFonts = getAvailableFonts();
        return Arrays.stream(FONTS).filter(availableFonts::contains).findFirst().orElse("SansSerif");
    }

}
