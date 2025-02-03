package dev.kielblock.movieflix.mapper;

import dev.kielblock.movieflix.controller.request.StreamingRequest;
import dev.kielblock.movieflix.controller.response.StreamingResponse;
import dev.kielblock.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming (StreamingRequest streamingRequest) {
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse (Streaming streaming) {
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
