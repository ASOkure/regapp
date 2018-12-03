package uk.ac.nesc.idsd.service;

import uk.ac.nesc.idsd.bean.ResearchResultBean;
import uk.ac.nesc.idsd.model.ResearchResult;
import uk.ac.nesc.idsd.model.ResearchResultCategory;

import java.util.List;

public interface ResearchResultManager {

    void saveResultPDF(ResearchResult resultPdf);

    void updateResultPDF(ResearchResult resultPdf);

    ResearchResult getResult(long pdfId);

    ResearchResultBean getResultBean(ResearchResult result);

    List<ResearchResultBean> viewOthersResultPDF(String username);

    List<ResearchResultBean> viewMyUploads(String username);

    List<String> getAllCategoryNames();

    List<ResearchResultCategory> getAllCategories();

    void deleteResultPDF(ResearchResult researchResult);

    ResearchResultCategory getCategory(Integer categoryId);

}
