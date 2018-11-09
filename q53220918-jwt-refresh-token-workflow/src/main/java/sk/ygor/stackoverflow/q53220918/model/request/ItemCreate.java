package sk.ygor.stackoverflow.q53220918.model.request;

import javax.validation.constraints.*;

public class ItemCreate {

    @NotBlank
    @Size(max=255)
    private final String content;

    @NotNull
    @Min(1)
    @Max(10)
    private final Integer impact;

    @NotNull
    @Min(1)
    @Max(10)
    private final Integer ease;

    @NotNull
    @Min(1)
    @Max(10)
    private final Integer confidence;

    public ItemCreate(String content, Integer impact, Integer ease, Integer confidence) {
        this.content = content;
        this.impact = impact;
        this.ease = ease;
        this.confidence = confidence;
    }

    public String getContent() {
        return content;
    }

    public Integer getImpact() {
        return impact;
    }

    public Integer getEase() {
        return ease;
    }

    public Integer getConfidence() {
        return confidence;
    }

}
