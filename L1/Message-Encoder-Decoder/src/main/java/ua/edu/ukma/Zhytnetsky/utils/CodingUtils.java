package ua.edu.ukma.Zhytnetsky.utils;

import ua.edu.ukma.Zhytnetsky.model.Message;
import ua.edu.ukma.Zhytnetsky.model.Packet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public final class CodingUtils {

    private CodingUtils() {}

    // Encoding
    public static byte[] encodePacket(final Packet packet) {
        final byte[] msgBytes = CodingUtils.encodeMessage(packet.getMsg());
        final ByteBuffer buffer = ByteBuffer.allocate(packet.byteLength()).order(ByteOrder.BIG_ENDIAN);

        buffer.put(packet.getMagic())
                .put(packet.getSrc())
                .putLong(packet.getPktId())
                .putInt(msgBytes.length);

        final short headerCrc = ValidationUtils.calculateCrc(buffer.array(), 0, 14);
        packet.setHeaderCrc16(headerCrc);
        buffer.putShort(headerCrc)
                .put(msgBytes);

        final short msgCrc = ValidationUtils.calculateCrc(buffer.array(), 16, msgBytes.length);
        packet.setMsgCrc16(msgCrc);
        buffer.putShort(msgCrc);

        return buffer.array();
    }

    public static byte[] encodeMessage(final Message message) {
        final ByteBuffer buffer = ByteBuffer.allocate(message.byteLength()).order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(message.getType())
                .putInt(message.getUserId())
                .put(message.getContent().getBytes(StandardCharsets.UTF_8));
        return buffer.array();
    }

    // Decoding
    public static Packet decodePacket(final byte[] bytes) {
        // ...
        return new Packet("");
    }

    public static Message decodeMessage(final byte[] bytes) {
        // ...
        return new Message("");
    }

}
