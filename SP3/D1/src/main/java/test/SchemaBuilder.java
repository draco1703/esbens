
package test;

import javax.persistence.Persistence;

/**
 *
 * @author Esben
 */
public class SchemaBuilder {


    public static void main(String[] args) {
        Persistence.generateSchema("day1", null);
    }
    
}
