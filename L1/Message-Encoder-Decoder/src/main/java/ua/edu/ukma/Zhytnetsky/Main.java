package ua.edu.ukma.Zhytnetsky;

import ua.edu.ukma.Zhytnetsky.model.Packet;
import ua.edu.ukma.Zhytnetsky.utils.CodingUtils;
import ua.edu.ukma.Zhytnetsky.utils.DisplayUtils;

public final class Main {

    public static void main(String[] args) {
        final String testMessage = "Hello from the application!";
        final Packet packet = new Packet(testMessage);
        final String encodedMessage = DisplayUtils.bytesToHexString(CodingUtils.encodePacket(packet));

        System.out.println(encodedMessage);
    }

}
