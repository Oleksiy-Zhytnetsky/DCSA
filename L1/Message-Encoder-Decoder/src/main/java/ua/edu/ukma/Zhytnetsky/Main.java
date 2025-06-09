package ua.edu.ukma.Zhytnetsky;

import ua.edu.ukma.Zhytnetsky.example.User;
import ua.edu.ukma.Zhytnetsky.model.Packet;
import ua.edu.ukma.Zhytnetsky.utils.DisplayUtils;
import ua.edu.ukma.Zhytnetsky.utils.EncryptionUtils;

public final class Main {

    public static void main(String[] args) {
        final User someUser = new User("Mike Smith", "mike_smith@gmail.com");
        final Packet<User> packet = new Packet<>(someUser);
        final byte[] encodedMessage = packet.encode();
        System.out.println("Encoded msg:   " + DisplayUtils.bytesToHexString(encodedMessage));

        final byte[] encryptedMessage = EncryptionUtils.encrypt(encodedMessage);
        System.out.println("Encrypted msg: " + DisplayUtils.bytesToHexString(encryptedMessage));

        final byte[] decryptedMessage = EncryptionUtils.decrypt(encryptedMessage);
        System.out.println("Decrypted msg: " + DisplayUtils.bytesToHexString(decryptedMessage));

        // Generic T type will be known from an additional packet header
        // (int mapped into type enum?)
        final Packet<User> decodedMessage = new Packet<>(new User());
        decodedMessage.decode(encodedMessage, 0);
        final User decodedUser = decodedMessage.getPayload();
        System.out.println("Decoded user:  " + decodedUser.getName()
                + " (" + decodedUser.getEmail() + ')');
    }

}
