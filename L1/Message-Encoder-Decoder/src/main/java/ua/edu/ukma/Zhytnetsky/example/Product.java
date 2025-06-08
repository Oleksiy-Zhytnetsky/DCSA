package ua.edu.ukma.Zhytnetsky.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.edu.ukma.Zhytnetsky.contract.Codable;

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
        return new byte[0];
    }

    @Override
    public int byteLength() {
        return 0;
    }
}
