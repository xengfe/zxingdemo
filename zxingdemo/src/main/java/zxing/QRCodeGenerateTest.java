package zxing;


public class QRCodeGenerateTest {
	// Tutorial: http://zxing.github.io/zxing/apidocs/index.html
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			QRCode.create(80, 80, "png");
			System.out.println("第"+i+"个文件");
		}
	}

}
