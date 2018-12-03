package uk.ac.nesc.idsd.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.ac.nesc.idsd.bean.ResearchResultBean;
import uk.ac.nesc.idsd.model.ResearchResult;
import uk.ac.nesc.idsd.model.ResearchResultCategory;
import uk.ac.nesc.idsd.model.ResearchResultCategoryDao;
import uk.ac.nesc.idsd.model.ResearchResultDao;
import uk.ac.nesc.idsd.security.Authorization;
import uk.ac.nesc.idsd.service.ResearchResultManager;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.DeleteDiskFileException;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResearchResultManagerImpl implements ResearchResultManager, Serializable {
    private static final Log log = LogFactory.getLog(ResearchResultManagerImpl.class);
    private ResearchResultDao researchResultDao;
    private ResearchResultCategoryDao researchResultCategoryDao;
    private UserService userService;

    public void saveResultPDF(ResearchResult researchResult) {
        researchResultDao.save(researchResult);
    }

    public void updateResultPDF(ResearchResult researchResult) {
        researchResultDao.attachDirty(researchResult);
    }

    public ResearchResult getResearchResult(Long id) {
        return researchResultDao.findById(id);
    }

    public ResearchResult getResult() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResultBean> viewOthersResultPDF(String username) {
        List<ResearchResultBean> otherPdfs = new ArrayList<ResearchResultBean>();
        for (ResearchResult rr : (List<ResearchResult>) this.researchResultDao.findAllSharedByOthers(username)) {
            if (Authorization.authorizeFileDownload(username, rr.getAccess(), rr.getUploader(), rr.getDsdIdentifier().getUploader().getUsername())
                    && !rr.getUploader().equalsIgnoreCase(username)) {
                ResearchResultBean resultBean = this.getResultBean(rr);
                if (resultBean != null) {
                    otherPdfs.add(resultBean);
                }
            }
        }
        return otherPdfs;
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResultBean> viewMyUploads(String username) {
        List<ResearchResultBean> myPdfs = new ArrayList<ResearchResultBean>();
        List<ResearchResult> researchResults = (List<ResearchResult>) this.researchResultDao.findAllByUsername(username);
        ResearchResultBean resultBean;
        for (ResearchResult rr : researchResults) {
            resultBean = this.getResultBean(rr);
            if (rr.getUploader() != null
                    && null != resultBean) {
                myPdfs.add(resultBean);
            }
        }
        return myPdfs;
    }

    public ResearchResult getResult(long pdfId) {
        return this.researchResultDao.findById(pdfId);
    }

    public List<String> getAllCategoryNames() {
        List<String> categoryNames = new ArrayList<String>();
        @SuppressWarnings("unchecked")
        List<ResearchResultCategory> categories = this.researchResultCategoryDao.findAll();
        if (categories == null || categories.isEmpty()) {
            log.warn("Research Result Category DAO returns null/empty list");
            return null;
        }
        for (ResearchResultCategory r : categories) {
            categoryNames.add(r.getName());
        }
        return categoryNames;
    }

    public void setResearchResultDao(ResearchResultDao researchResultDao) {
        this.researchResultDao = researchResultDao;
    }

    public void setResearchResultCategoryDao(
            ResearchResultCategoryDao researchResultCategoryDao) {
        this.researchResultCategoryDao = researchResultCategoryDao;
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResultCategory> getAllCategories() {
        return this.researchResultCategoryDao.findAll();
    }

    public ResearchResultBean getResultBean(ResearchResult result) {
        //ResearchResult result = this.getResult(pdfId);
        if (null == result) {
            return null;
        }
        List<String> access = new ArrayList<String>(0);
        if (null != result.getAccess() && !result.getAccess().isEmpty()) {
            access = this.userService.getPortalUsersByUsernames(result.getAccess());
        }
        return new ResearchResultBean(result, access);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void deleteResultPDF(ResearchResult researchResult) throws DeleteDiskFileException {
        this.researchResultDao.delete(researchResult);
        deleteFile(researchResult.getPath() + File.separator + researchResult.getDiskFileName());

    }

    public ResearchResultCategory getCategory(Integer categoryId) {
        return this.researchResultCategoryDao.findById(categoryId);
    }

    private void deleteFile(String fileName) throws DeleteDiskFileException {
        // A File object to represent the filename
        File f = new File(fileName);

        // Make sure the file or directory exists and isn't write protected
        if (!f.exists()) {
            throw new DeleteDiskFileException("Delete: no such file or directory: " + fileName);
        }
        if (!f.canWrite()) {
            throw new DeleteDiskFileException("Delete: write protected: " + fileName);
        }
        // If it is a directory, make sure it is empty
        if (f.isDirectory()) {
            String[] files = f.list();
            if (files.length > 0) {
                throw new DeleteDiskFileException("Delete: directory not empty: " + fileName);
            }
        }

        // Attempt to delete it
        boolean success = f.delete();

        if (!success) {
            throw new DeleteDiskFileException("Delete: deletion failed");
        }
    }
}
