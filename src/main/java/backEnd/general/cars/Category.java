package backEnd.general.cars;

/**
 *
 * @author jonathan
 */
public enum Category {
    N100("N100" , "01N100"),
    N200("N200", "02N200"),
    N300("N300", "03N300"),
    N400("N400", "04N400"),
    N500("N500", "05N500"),
    N600("N600", "06N600"),
    N700("N700", "07N700"),
    N800("N800", "08N800"),
    N900("N900", "09N900"),
    N1000("N1000", "10N1000"),
    GR4("GR.4", "11GR4"),
    GR3("GR.3", "12GR3"),
    GR1("GR.1", "13GR1"),
    GRB("GR.B", "14GRB"),
    GRX("GR.X", "15GRX"),
    EMPTY("", ""),
    MAX("ZZZZZ", "ZZZZZZ");
            
    private final String description;
    private final String dbValue;

    private Category(String description, String dbValue) {
        this.description = description;
        this.dbValue = dbValue;
    }

    public String getDescription() {
        return description;
    }
    
    public String getDBValue() {
        return dbValue;
    }
    
    public static Category toCategoryFromDescription(String description) {
        Category category = null;

        for (Category value : Category.values()) {
            if (description.equals(value.getDescription())) {
                category = value;
            }
        }

        return category;
    }    

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