/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.regions;

/**
 * The error exceptions for the region.
 *
 * @author jonathan
 */
public class RegionException extends Exception {

    /**
     * The error message for trying to save a region without filling the
     * description.
     */
    public static final String REGION_DESCRIPTION_NOT_SET = "The region description was not set.";

    /**
     * The error message for trying to save a region where the region
     * description exists already.
     */
    public static final String REGION_DESCRIPTION_ALREADY_EXISTS = "The region description already exsits: ";

    /**
     * The error message for not finding a region primary key when trying to
     * delete a region.
     */
    public static final String REGION_KEY_NOT_FOUND_TO_DELETE = "The region key was not found to be deleted: ";

    /**
     * The error message for looking up a region by the primary key and not
     * finding a region.
     */
    public static final String REGION_NOT_FOUND_BY_KEY = "Could not find the region with the key: ";

    /**
     * The error message for looking up a region by the description and not
     * finding a region.
     */
    public static final String REGION_NOT_FOUND_BY_DESCRIPTION = "Could not find the region with the description: ";

    /**
     * Creates a new instance of <code>RegionException</code> without detail
     * message.
     */
    public RegionException() {
    }

    /**
     * Constructs an instance of <code>RegionException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public RegionException(String msg) {
        super(msg);
    }
}
