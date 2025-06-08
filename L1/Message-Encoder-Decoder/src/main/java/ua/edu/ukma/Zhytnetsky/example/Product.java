package ua.edu.ukma.Zhytnetsky.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.edu.ukma.Zhytnetsky.contract.Codable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
public final class Product implements Codable {

    private String title;
    private String description;
    private int qtyAvailable;
    private double price;

    @Override
    public int decode(final byte[] bytes, final int offset) {
        // ...
        return 0;
    }

    @Override
    public byte[] encode() {
        final byte[] titleBytes = this.title.getBytes(StandardCharsets.UTF_8);
        final byte[] descriptionBytes = this.description.getBytes(StandardCharsets.UTF_8);

        final ByteBuffer buffer = ByteBuffer.allocate(byteLength()).order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(titleBytes.length)
                .put(titleBytes)
                .putInt(descriptionBytes.length)
                .put(descriptionBytes)
                .putInt(this.qtyAvailable)
                .putDouble(this.price);
        return buffer.array();
    }

    @Override
    public int byteLength() {
        return 4 + 4 + 4 + 8 + this.title.getBytes(StandardCharsets.UTF_8).length
                + this.description.getBytes(StandardCharsets.UTF_8).length;
    }
}
