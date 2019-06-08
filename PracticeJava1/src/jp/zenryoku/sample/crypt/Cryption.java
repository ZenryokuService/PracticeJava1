package jp.zenryoku.sample.crypt;

import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 暗号化処理を行います。
 * 
 * @author takunoji
 * 2019/06/08
 */
public class Cryption {
	private static SecretKeySpec secretKey;
    private static byte[] key;
    private static IvParameterSpec iv;
    private static KeySpec spec;
   
	public static void main(String[] args) {
		System.out.println("プログラム引数：" + args[0]);
		init(args[0]);
		Cipher cipher = null;
		String password = "パスワード";
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			byte[] cryp = cipher.doFinal(password.getBytes());
			System.out.println("暗号化：" + new String(cryp));
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			System.out.println("復号号化：" + new String(cipher.doFinal(cryp)));
		} catch(Exception e) {
			System.out.println("暗号化に失敗しました。" + args[0]);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private static void init(String passwd) {
		String keyStr = "0123456789012345";
		try {
			byte[] keyVal = new byte[16];
			keyVal = keyStr.getBytes("UTF-8");
			spec = new PBEKeySpec("password".toCharArray(), keyVal, 65536, 128); // AES-256
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] key = f.generateSecret(spec).getEncoded();
			secretKey = new SecretKeySpec(key, "AES");
			byte[] ivBytes = new byte[16];
			SecureRandom random = new SecureRandom();
			random.nextBytes(ivBytes);
			iv = new IvParameterSpec(ivBytes);
		} catch(Exception e) {
			System.out.println("キーの生成に失敗しました。" + key);
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
