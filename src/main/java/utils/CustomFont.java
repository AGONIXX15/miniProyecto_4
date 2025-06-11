package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
// clase para cargar la fuente personalizada
public class CustomFont {
    public static Font loadfont(float size) {
        try {
            InputStream is = CustomFont.class.getClassLoader().getResourceAsStream("fonts/PocketMonk-15ze.ttf");
            assert is != null;
            Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) size);
        }
    }
}
