package ua.edu.ukma.Zhytnetsky.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.edu.ukma.Zhytnetsky.contract.Codable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
public final class User implements Codable {

    private String name;
    private String email;

    @Override
    public int decode(final byte[] bytes, final int offset) {
        // ...
        return 0;
    }

    @Override
    public byte[] encode() {
        final byte[] nameBytes = this.name.getBytes(StandardCharsets.UTF_8);
        final byte[] emailBytes = this.email.getBytes(StandardCharsets.UTF_8);

        ByteBuffer buffer = ByteBuffer.allocate(byteLength()).order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(nameBytes.length)
                .put(nameBytes)
                .putInt(emailBytes.length)
                .put(emailBytes);
        return buffer.array();
    }

    @Override
    public int byteLength() {
        return this.name.getBytes(StandardCharsets.UTF_8).length
                + this.email.getBytes(StandardCharsets.UTF_8).length;
    }
}
