package zxing;

import java.io.File;

public class CaculateTest {

	public static void main(String[] args) {
		File file = new File("d:/zxing");
		int size = file.list().length;
		System.out.println("文件总数：" + size );

	}

}
