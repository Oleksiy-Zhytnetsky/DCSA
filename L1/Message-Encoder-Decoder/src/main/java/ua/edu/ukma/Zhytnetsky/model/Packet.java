package ua.edu.ukma.Zhytnetsky.model;

import lombok.Data;
import ua.edu.ukma.Zhytnetsky.config.AppConfig;

@Data
public final class Packet {

    private static long currentPktId = -1;

    private final byte magic = AppConfig.MAGIC_BYTE_VALUE;
    private byte src;
    private long pktId;
    private int len;
    private Short headerCrc16; // null until encoded/decoded
    private Message msg;
    private Short msgCrc16; // null until encoded/decoded

    public Packet(final String payload) {
        this.msg = new Message(payload);

        this.src = AppConfig.APP_CLIENT_ID;
        this.pktId = ++Packet.currentPktId;
        this.len = this.msg.byteLength();
    }

    public int byteLength() {
        return 1 + 1 + 8 + 4 + 2 + 2 + this.msg.byteLength();
    }

    public String getPayload() {
        return this.msg.getContent();
    }

}
