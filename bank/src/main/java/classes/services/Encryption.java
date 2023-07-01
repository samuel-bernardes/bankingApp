import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryption {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "mysecretkey12345"; // Chave secreta para criptografia AES

    public static String encrypt(String input) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedInput) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedInput));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            String originalString = "Hello, world!";
            String encryptedString = encrypt(originalString);
            String decryptedString = decrypt(encryptedString);

            System.out.println("Original String: " + originalString);
            System.out.println("Encrypted String: " + encryptedString);
            System.out.println("Decrypted String: " + decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
