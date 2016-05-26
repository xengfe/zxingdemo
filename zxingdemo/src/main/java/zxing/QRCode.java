package zxing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {

	private static final int Marge = 10;// 边距
	private static final int E_Height = 10;// 额外高度

	public static void create(int width, int height, String fileType) {
		String myCodeText = GernateRandomNumber.create(10000000, 99999999,
				new Random()) + "";
		String filePath = "D:/zxing/" + myCodeText + "." + fileType;
		File myFile = new File(filePath);
		Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(
				EncodeHintType.class);
		hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		// Now with zxing version 3.2.1 you could change border size (white
		// border size to just 1)
		hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = null;
		try {
			byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE,
					width, height, hintMap);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		int imgWidth = byteMatrix.getWidth();
		int imgHeight = byteMatrix.getHeight();

		BufferedImage image = new BufferedImage(imgWidth, imgHeight + E_Height,
				BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, imgWidth, imgHeight + E_Height);
		graphics.drawRoundRect(0, 0, imgWidth, imgHeight + E_Height, Marge,
				Marge);
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < imgWidth; i++) {
			for (int j = 0; j < imgWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		
		for (int i = 0; i < imgWidth; i++) {
			graphics.fillRect(i, 0, 1, 1);
			graphics.fillRect(i, imgHeight + E_Height -1, 1, 1);
		}
		
		
		for (int i = 0; i < imgHeight + E_Height; i++) {
			graphics.fillRect(0, i, 1, 1);
			graphics.fillRect(imgWidth -1,i, 1, 1);
		}
		

		Font font = new Font("Arial Black", Font.PLAIN, 14);
		graphics.setFont(font);
		int textLenth = myCodeText.length();
		int startX = 0, startY = imgHeight + E_Height/2;
		int imageWidth=image.getWidth();
		if (textLenth >= imageWidth) {
			startX = 0;
		} else if (textLenth < imageWidth) {
			startX = textLenth / 2;
		}
		graphics.drawString(myCodeText, startX, startY);

		try {
			ImageIO.write(image, fileType, myFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n" + myCodeText + "." + fileType + ",二维码生成成功.");
	}

}
