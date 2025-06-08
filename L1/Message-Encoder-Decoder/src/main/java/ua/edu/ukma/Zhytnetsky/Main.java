package ua.edu.ukma.Zhytnetsky;

import ua.edu.ukma.Zhytnetsky.example.User;
import ua.edu.ukma.Zhytnetsky.model.Packet;
import ua.edu.ukma.Zhytnetsky.utils.DisplayUtils;

public final class Main {

    public static void main(String[] args) {
        final User someUser = new User("Mike Smith", "mike_smith@gmail.com");
        final Packet<User> packet = new Packet<>(someUser);
        final String encodedMessage = DisplayUtils.bytesToHexString(packet.encode());

        System.out.println(encodedMessage);
    }

}
