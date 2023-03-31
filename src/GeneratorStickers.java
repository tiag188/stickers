import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class GeneratorStickers {

    public void create(InputStream inputStream, String newFile, String texto) throws Exception {
        /*
         * 1 - ler imagem
         */
        //InputStream inputStream = new FileInputStream(new File("images/filme.jpg"));
        //URL inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_2.jpg");
        BufferedImage imageOriginal = ImageIO.read(inputStream);

        int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();
        int newHeight = height + 200;

        /*
         * 2 - criar nova imagem em memoria com transparência
         */
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        /*
         * 3 - copiar imagem original para nova imagem em memória
         */
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imageOriginal, 0, 0, null);

        // configurar fonte
        var newFont = new Font(Font.SANS_SERIF, Font.BOLD, 80);
        graphics.setFont(newFont);
        graphics.setColor(Color.YELLOW);

        /*
         * 4 - escrever nova frase
         */

        int x = 50;
        int y = 100;

        graphics.drawString(texto, x, newHeight - y);

        // desenha a borda
        graphics.setPaint(Color.BLACK);
        graphics.drawString(texto, x - 1, newHeight - y - 1);
        graphics.drawString(texto, x + 1, newHeight - y - 1);
        graphics.drawString(texto, x - 1, newHeight - y + 1);
        graphics.drawString(texto, x + 1, newHeight - y + 1);
        // desenha o texto
        graphics.setPaint(Color.WHITE);
        graphics.drawString(texto, x, newHeight - y);
        /*
         * 5 - escrever nova imagem em um arquivo
         */
        ImageIO.write(newImage, "png", new File("stickers/" + newFile));
    }
}
