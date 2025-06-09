package ua.edu.ukma.Zhytnetsky;

import org.junit.jupiter.api.Test;
import ua.edu.ukma.Zhytnetsky.example.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class UserTest {

    @Test
    public void assertEncodeDecodePreservesFields() {
        final User user = new User("Oleksiy", "oleksiy@test.com");
        final byte[] encodedUser = user.encode();

        final User copy = new User();
        final int decodeOffset = copy.decode(encodedUser, 0);

        assertEquals(encodedUser.length, decodeOffset);
        assertEquals(user.getName(), copy.getName());
        assertEquals(user.getEmail(), copy.getEmail());
    }

}
