package ua.edu.ukma.Zhytnetsky;

import org.junit.jupiter.api.Test;
import ua.edu.ukma.Zhytnetsky.utils.EncryptionUtils;

import static org.junit.jupiter.api.Assertions.*;

public final class EncryptionUtilsTest {

    @Test
    public void assertEncryptDecryptPreservesOriginal() {
        final byte[] original = "Hello, world!".getBytes();
        final byte[] cipher = EncryptionUtils.encrypt(original);
        assertNotNull(cipher);
        assertTrue(cipher.length > original.length);

        final byte[] plain = EncryptionUtils.decrypt(cipher);
        assertArrayEquals(original, plain);
    }

    @Test
    public void assertInvalidCipherThrows() {
        byte[] original = "Secret".getBytes();
        byte[] cipher = EncryptionUtils.encrypt(original);
        cipher[cipher.length - 1] ^= (byte)0xFF;
        assertThrows(IllegalStateException.class, () -> EncryptionUtils.decrypt(cipher));
    }

}
