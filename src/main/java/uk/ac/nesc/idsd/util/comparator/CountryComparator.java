package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.Country;

import java.util.Comparator;

public class CountryComparator implements Comparator<Country> {

    public int compare(Country c1, Country c2) {
        if (c1.equals(c2)) {
            return 0;
        } else if (c1.getCountryName().compareTo(c2.getCountryName()) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

}