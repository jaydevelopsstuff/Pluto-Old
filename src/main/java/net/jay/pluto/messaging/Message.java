package net.jay.pluto.messaging;

import lombok.Getter;
import net.jay.pluto.entity.player.ManageablePlayer;

@Getter
public class Message {
    private final ManageablePlayer sender;
    private final String content;
    private final String[] spaceSplittedContent;
    private final String[] commaSplittedContent;

    public Message(ManageablePlayer sender, String content) {
        this.sender = sender;
        this.content = content;
        this.spaceSplittedContent = content.split(" ");
        this.commaSplittedContent = content.split(",");
    }
}
