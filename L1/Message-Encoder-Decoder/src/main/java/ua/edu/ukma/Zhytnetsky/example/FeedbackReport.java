package ua.edu.ukma.Zhytnetsky.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.ukma.Zhytnetsky.contract.Codable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class FeedbackReport implements Codable {

    private User user = new User();
    private Product product = new Product();

    @Override
    public int decode(final byte[] bytes, final int offset) {
        int nextOffset = this.user.decode(bytes, offset);
        nextOffset = this.product.decode(bytes, nextOffset);
        return nextOffset;
    }

    @Override
    public byte[] encode() {
        final ByteBuffer buffer = ByteBuffer.allocate(byteLength()).order(ByteOrder.BIG_ENDIAN);
        buffer.put(user.encode())
                .put(product.encode());
        return buffer.array();
    }

    @Override
    public int byteLength() {
        return user.byteLength() + product.byteLength();
    }
}
