package ua.edu.ukma.Zhytnetsky;

import ua.edu.ukma.Zhytnetsky.example.User;
import ua.edu.ukma.Zhytnetsky.model.Packet;
import ua.edu.ukma.Zhytnetsky.utils.DisplayUtils;

public final class Main {

    public static void main(String[] args) {
        final User someUser = new User("Mike Smith", "mike_smith@gmail.com");
        final Packet<User> packet = new Packet<>(someUser);
        final byte[] encodedMessage = packet.encode();
        System.out.println(DisplayUtils.bytesToHexString(encodedMessage));

        // Generic T type will be known from an additional packet header
        // (int mapped into type enum?)
        final Packet<User> decodedMessage = new Packet<>(new User());
        decodedMessage.decode(encodedMessage, 0);
        final User decodedUser = decodedMessage.getPayload();
        System.out.println("Decoded user: " + decodedUser.getName()
                + " (" + decodedUser.getEmail() + ')');
    }

}
