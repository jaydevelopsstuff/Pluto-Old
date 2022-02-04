package net.jay.pluto.world;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class WorldMetadata {
    private boolean favorited;
    private long revision;

    private LocalDateTime creationTime;
}
