package ua.edu.ukma.Zhytnetsky.model;

import lombok.Data;
import ua.edu.ukma.Zhytnetsky.config.AppConfig;
import ua.edu.ukma.Zhytnetsky.contract.Codable;
import ua.edu.ukma.Zhytnetsky.utils.ValidationUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Data
public final class Packet<T extends Codable> implements Codable {

    private static long currentPktId = -1;

    private final byte magic = AppConfig.MAGIC_BYTE_VALUE;
    private byte src;
    private long pktId;
    private int len;
    private Short headerCrc16; // null until encoded/decoded
    private Message<T> msg;
    private Short msgCrc16; // null until encoded/decoded

    public Packet(final T payload) {
        this.msg = new Message<>(payload);

        this.src = AppConfig.APP_CLIENT_ID;
        this.pktId = ++Packet.currentPktId;
        this.len = this.msg.byteLength();
    }

    public T getPayload() {
        return this.msg.getContent();
    }

    @Override
    public int decode(final byte[] bytes, final int offset) {
        // ...
        return 0;
    }

    @Override
    public byte[] encode() {
        final byte[] msgBytes = this.getMsg().encode();
        final ByteBuffer buffer = ByteBuffer.allocate(byteLength()).order(ByteOrder.BIG_ENDIAN);

        buffer.put(this.magic)
                .put(this.src)
                .putLong(this.pktId)
                .putInt(msgBytes.length);

        this.headerCrc16 = ValidationUtils.calculateCrc(buffer.array(), 0, 14);;
        buffer.putShort(this.headerCrc16)
                .put(msgBytes);

        this.msgCrc16 = ValidationUtils.calculateCrc(buffer.array(), 16, msgBytes.length);
        buffer.putShort(this.headerCrc16);

        return buffer.array();
    }

    @Override
    public int byteLength() {
        return 1 + 1 + 8 + 4 + 2 + 2 + this.msg.byteLength();
    }

}
