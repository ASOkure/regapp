package uk.ac.nesc.idsd.service;

import uk.ac.nesc.idsd.model.Gene;
import uk.ac.nesc.idsd.model.GeneCategory;

import java.util.List;

public interface GeneService {
    List<GeneCategory> getCategories();

    Gene getGeneByName(String geneName);

}
