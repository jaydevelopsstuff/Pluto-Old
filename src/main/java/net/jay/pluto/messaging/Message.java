package net.jay.pluto.messaging;

import net.jay.pluto.entity.player.ManageablePlayer;

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

    public ManageablePlayer getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String[] getSpaceSplittedContent() {
        return spaceSplittedContent;
    }

    public String[] getCommaSplittedContent() {
        return commaSplittedContent;
    }
}
