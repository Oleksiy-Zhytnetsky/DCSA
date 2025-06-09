package ua.edu.ukma.Zhytnetsky;

import org.junit.jupiter.api.Test;
import ua.edu.ukma.Zhytnetsky.example.User;
import ua.edu.ukma.Zhytnetsky.model.Packet;
import ua.edu.ukma.Zhytnetsky.utils.EncryptionUtils;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void assertObjectEncodeEncryptDecryptDecodePreservesFields() {
        final User originalUser = new User("Oleksiy", "oleksiy@test.com");
        final Packet<User> originalPacket = new Packet<>(originalUser);
        final byte[] encodedMessage = originalPacket.encode();
        final byte[] encryptedMessage = EncryptionUtils.encrypt(encodedMessage);

        final byte[] decryptedMessage = EncryptionUtils.decrypt(encryptedMessage);
        final Packet<User> decodedPacket = new Packet<>(new User());
        decodedPacket.decode(decryptedMessage, 0);
        final User decodedUser = decodedPacket.getPayload();

        assertArrayEquals(encodedMessage, decryptedMessage);
        assertEquals(originalPacket, decodedPacket);
        assertEquals(originalUser, decodedUser);
    }

    @Test
    public void assertCannotDecryptDecodeDamagedEncodedEncryptedObjectBytes() {
        final User originalUser = new User("Eve Adams", "eve.adams@host");
        final Packet<User> originalPacket = new Packet<>(originalUser);
        final byte[] encodedMessage = originalPacket.encode();
        final byte[] encryptedMessage = EncryptionUtils.encrypt(encodedMessage);

        final int midIdx = Math.min(encryptedMessage.length / 2, 1);
        encryptedMessage[encryptedMessage.length - midIdx] ^= (byte)0xFF;
        assertThrows(IllegalStateException.class, () -> EncryptionUtils.decrypt(encryptedMessage));
    }

}
