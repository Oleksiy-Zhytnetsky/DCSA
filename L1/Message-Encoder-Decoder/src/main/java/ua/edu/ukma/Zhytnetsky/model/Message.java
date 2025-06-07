package ua.edu.ukma.Zhytnetsky.model;

import lombok.Data;
import ua.edu.ukma.Zhytnetsky.config.AppConfig;

import java.nio.charset.StandardCharsets;

@Data
public final class Message {

    private int type;
    private int userId;
    private String content;

    public Message(final String payload) {
        this.type = AppConfig.TEAM_CODE;
        this.userId = AppConfig.APP_USER_ID;
        this.content = payload;
    }

    public int byteLength() {
        return 4 + 4 + this.content.getBytes(StandardCharsets.UTF_8).length;
    }

}
