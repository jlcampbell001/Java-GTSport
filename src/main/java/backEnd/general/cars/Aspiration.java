package backEnd.general.cars;

/**
 * The aspiration values for a car. How the car sucks air into the engine.
 *
 * @author jonathan
 */
public enum Aspiration {

    /**
     * Naturally Aspirated. Uses negative pressure produced when the pistons
     * come down to suck in air
     */
    NA("NA"),
    /**
     * Turbo. Uses a turbine to suck the air in.
     */
    TB("TB"),
    /**
     * Supercharged. Uses a turbine to suck the air in.
     */
    SC("SC"),
    /**
     * Electric Motor.
     */
    EV("EV"),
    /**
     * No aspiration set.
     */
    EMPTY("");

    private final String description;

    private Aspiration(String description) {
        this.description = description;
    }

    /**
     * Get the description of the aspiration. This is the value used in the
     * database.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts a string value to an aspiration value. If no value matches it
     * returns a null.
     *
     * @param description the string to convert.
     * @return The aspiration.
     */
    public static Aspiration toAspiration(String description) {
        Aspiration aspiration = null;

        for (Aspiration value : Aspiration.values()) {
            if (description.equals(value.getDescription())) {
                aspiration = value;
            }
        }

        return aspiration;
    }
}
