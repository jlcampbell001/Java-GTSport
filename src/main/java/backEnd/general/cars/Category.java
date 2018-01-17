package backEnd.general.cars;

/**
 * The car's category level. The dbvalue is the value stored in the database and
 * is setup so the order of the data will put the N*** first the the GR*.
 *
 * @author jonathan
 */
public enum Category {

    /**
     * The N100 category.
     */
    N100("N100", "01N100"),
    /**
     * The N200 category.
     */
    N200("N200", "02N200"),
    /**
     * The N300 category.
     */
    N300("N300", "03N300"),
    /**
     * The N400 category.
     */
    N400("N400", "04N400"),
    /**
     * The N500 category.
     */
    N500("N500", "05N500"),
    /**
     * The N600 category.
     */
    N600("N600", "06N600"),
    /**
     * The N700 category.
     */
    N700("N700", "07N700"),
    /**
     * The N800 category.
     */
    N800("N800", "08N800"),
    /**
     * The N900 category.
     */
    N900("N900", "09N900"),
    /**
     * The N1000 category.
     */
    N1000("N1000", "10N1000"),
    /**
     * The GR4 category.
     */
    GR4("GR.4", "11GR4"),
    /**
     * The GR3 category.
     */
    GR3("GR.3", "12GR3"),
    /**
     * The GR1 category.
     */
    GR1("GR.1", "13GR1"),
    /**
     * The GRB category. Rally cars.
     */
    GRB("GR.B", "14GRB"),
    /**
     * The GRX category. Concept cars..
     */
    GRX("GR.X", "15GRX"),
    /**
     * The no category.
     */
    EMPTY("", ""),
    /**
     * The category value used for filtering the max value.
     */
    MAX("ZZZZZ", "ZZZZZZ");

    private final String description;
    private final String dbValue;

    private Category(String description, String dbValue) {
        this.description = description;
        this.dbValue = dbValue;
    }

    /**
     * Get the category description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the database value.
     *
     * @return the database value
     */
    public String getDBValue() {
        return dbValue;
    }

    /**
     * Looks for a category based on the description passed.
     *
     * @param description the description to look up
     * @return the found category or null if not found
     */
    public static Category toCategoryFromDescription(String description) {
        Category category = null;

        for (Category value : Category.values()) {
            if (description.equals(value.getDescription())) {
                category = value;
            }
        }

        return category;
    }

    /**
     * Looks up the category based on the database value.
     *
     * @param dbValue the database value
     * @return the category found of null is not found
     */
    public static Category toCategoryFromDBValue(String dbValue) {
        Category category = null;

        for (Category value : Category.values()) {
            if (dbValue.equals(value.getDBValue())) {
                category = value;
            }
        }

        return category;
    }
}
