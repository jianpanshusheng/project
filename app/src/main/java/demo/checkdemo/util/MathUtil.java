package demo.checkdemo.util;


/**
 *
 * @作者 键盘书生
 * @类描述: 得到所有数据校验后的 ，添加到所有数据后面一个字节
 * 校验 从命令头到数据域最后一字节的逐字节异或值
 * @创建日期 2018/9/4 9:55
 */


public class MathUtil {

	public static String getCheck(String command) {

		if (command != null && command.length() >= 4) {
			command = command.replace(" ", "");
			String result = "00";
			for (int i = 0; i < command.length(); i += 2) {
				String str1 = command.substring(i, i + 2);
				result = xor(result, str1);
			}

			return result;

		} else {
			return null;
		}

	}

	private static String xor(String strHex_X, String strHex_Y) {
		// 将x、y转成二进制形式
		String anotherBinary = Integer.toBinaryString(Integer.valueOf(strHex_X,
				16));
		String thisBinary = Integer.toBinaryString(Integer
				.valueOf(strHex_Y, 16));
		String result = "";
		// 判断是否为8位二进制，否则左补零
		if (anotherBinary.length() != 8) {
			for (int i = anotherBinary.length(); i < 8; i++) {
				anotherBinary = "0" + anotherBinary;
			}
		}
		if (thisBinary.length() != 8) {
			for (int i = thisBinary.length(); i < 8; i++) {
				thisBinary = "0" + thisBinary;
			}
		}
		// 异或运算
		for (int i = 0; i < anotherBinary.length(); i++) {
			// 如果相同位置数相同，则补0，否则补1
			if (thisBinary.charAt(i) == anotherBinary.charAt(i))
				result += "0";
			else {
				result += "1";
			}
		}

		return Integer.toHexString(Integer.parseInt(result, 2));
	}
}
