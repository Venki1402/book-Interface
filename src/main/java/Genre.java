/**
 * Genre enum representing book genres
 * Demonstrates encapsulation through enum constants
 */
public enum Genre {
    FICTION("Fiction"),
    NON_FICTION("Non Fiction");

    private final String displayName;

    // Constructor for enum (private by default)
    Genre(String displayName) {
        this.displayName = displayName;
    }

    // Getter method for display name
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Static method to parse genre from string
     * @param genreStr string representation of genre
     * @return Genre enum value
     */
    public static Genre fromString(String genreStr) {
        if (genreStr == null) {
            return null;
        }

        String normalized = genreStr.trim().toLowerCase();
        switch (normalized) {
            case "fiction":
                return FICTION;
            case "non fiction":
            case "nonfiction":
                return NON_FICTION;
            default:
                throw new IllegalArgumentException("Unknown genre: " + genreStr);
        }
    }
}