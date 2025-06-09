package ua.edu.ukma.Zhytnetsky;

import org.junit.jupiter.api.Test;
import ua.edu.ukma.Zhytnetsky.config.AppConfig;
import ua.edu.ukma.Zhytnetsky.utils.ValidationUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ValidationUtilsTest {

    @Test
    public void validateCrcCorrectDoesNotThrow() {
        final byte[] data = new byte[] {0x00, 0x1, 0x2};
        final short correctCrc = ValidationUtils.calculateCrc(data, 0, data.length);
        assertDoesNotThrow(() -> ValidationUtils.validateCrc(correctCrc, data, 0, data.length));
    }

    @Test
    public void validateCrcIncorrectThrows() {
        final byte[] data = new byte[] {0x00, 0x1, 0x2};
        final short wrongCrc = (short)(ValidationUtils.calculateCrc(data, 0, data.length) + 1);
        assertThrows(
                IllegalStateException.class,
                () -> ValidationUtils.validateCrc(wrongCrc, data, 0, data.length)
        );
    }

    @Test
    public void validateMagicByteCorrectDoesNotThrow() {
        final byte correctByte = AppConfig.MAGIC_BYTE_VALUE;
        assertDoesNotThrow(() -> ValidationUtils.validateMagicByte(correctByte));
    }

    @Test
    public void validateMagicByteIncorrectThrows() {
        final byte wrongByte = (byte)(AppConfig.MAGIC_BYTE_VALUE + 12);
        assertThrows(IllegalStateException.class, () -> ValidationUtils.validateMagicByte(wrongByte));
    }
}
