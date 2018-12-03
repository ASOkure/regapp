package uk.ac.nesc.idsd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.model.Gene;
import uk.ac.nesc.idsd.model.GeneCategory;
import uk.ac.nesc.idsd.model.GeneCategoryDao;
import uk.ac.nesc.idsd.model.GeneDao;
import uk.ac.nesc.idsd.service.GeneService;

import java.io.Serializable;
import java.util.List;

public class GeneServiceImpl implements GeneService, Serializable {

    @Autowired
    private GeneCategoryDao geneCategoryDao;
    @Autowired
    private GeneDao geneDao;

    @SuppressWarnings("unchecked")
    public List<GeneCategory> getCategories() {
        return this.geneCategoryDao.findAll();
    }

    public Gene getGeneByName(String geneName) {
        return this.geneDao.findByGeneName(geneName).get(0);
    }

}
