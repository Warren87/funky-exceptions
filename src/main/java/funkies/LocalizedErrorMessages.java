package funkies;

import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

class LocalizedErrorMessages {

    private LocalizedErrorMessages() {
        throw new UnsupportedOperationException();
    }

    private static final Map<Lang, Properties> ERROR_MESSAGES;

    static {
        ERROR_MESSAGES = new EnumMap<>(Lang.class);

        for (Lang lang : Lang.values()) {
            ERROR_MESSAGES.put(lang, loadResource(lang));
        }
    }

    private static Properties loadResource(Lang lang) {
        try {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(
                            requireNonNull(getSystemResourceAsStream("error_" + lang.code() + ".properties")),
                            UTF_8
                    )
            );
            return properties;
        } catch (Exception e) {
            throw new CorruptedLocalizedMessageFileException(e);
        }
    }

    public static String message(Lang lang, String code, String defaultMessage) {
        return ERROR_MESSAGES.get(lang).getProperty(code, defaultMessage);
    }


    enum Lang {
        EN,
        PL;

        public String code() {
            return name().toLowerCase();
        }
    }
    
    static class CorruptedLocalizedMessageFileException extends RuntimeException {

        public CorruptedLocalizedMessageFileException(Throwable e) {
            super(e);
        }
    }
}
