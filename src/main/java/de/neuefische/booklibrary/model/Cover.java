package de.neuefische.booklibrary.model;

public enum Cover {
    SOFT_COVER("Soft-Cover"),
    E_BOOK("E-Book"),
    HARD_COVER("Hard-Cover"),
    AUDIO_BOOK("Hörbuch");

    private final String coverType;

    Cover(String coverType) {
        this.coverType = coverType;
    }

    public String getCoverType() {
        return coverType;
    }
}
