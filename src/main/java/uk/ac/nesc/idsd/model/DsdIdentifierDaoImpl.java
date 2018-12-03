package uk.ac.nesc.idsd.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DsdIdentifierDaoImpl extends AbstractDao<DsdIdentifier> implements DsdIdentifierDao {
    private static final Log log = LogFactory.getLog(DsdIdentifierDaoImpl.class);

    public static DsdIdentifierDao getFromApplicationContext(ApplicationContext ctx) {
        return (DsdIdentifierDao) ctx.getBean("dsdIdentifierDao");
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Transactional
    public List<DsdIdentifier> findByCriteria(DsdIdentifier instance) {
        log.debug("finding DsdIdentifier instance by criteria: " + instance);
        try {
            Criteria criteria = getCurrentSession().createCriteria(DsdIdentifier.class);
//            DetachedCriteria criteria = DetachedCriteria.forClass(DsdIdentifier.class);
            criteria.add(Example.create(instance));
            if (instance.getCentreName() != null && !instance.getCentreName().isEmpty()) {
                criteria.createCriteria("centre").add(Restrictions.eq("centreName", instance.getCentreName()));
            } else {
                if (instance.getCountryName() != null && !instance.getCountryName().isEmpty()) {
                    criteria.createCriteria("centre.country").add(Restrictions.eq("countryName", instance.getCountryName()));
                }
            }
            if (instance.getDsdCah() != null && instance.getDsdCah().isEmpty()) {
                //dsd criteria
                Map propertyNameValues = new HashMap();
                if (null != instance.getDsdCah().getPrenatalDiagnosis()) {
                    propertyNameValues.put("prenatalDiagnosis", instance.getDsdCah().getPrenatalDiagnosis());
                }
                if (null != instance.getDsdCah().getGestationalAge()) {
                    propertyNameValues.put("gestationalAge", instance.getDsdCah().getGestationalAge());
                }
                if (null != instance.getDsdCah().getPraderStageAtFirstPresentation()) {
                    propertyNameValues.put("praderStageAtFirstPresentation", instance.getDsdCah().getPraderStageAtFirstPresentation());
                }
                if (null != instance.getDsdCah().getSwCrisisAtPresentation()) {
                    propertyNameValues.put("swCrisisAtPresentation", instance.getDsdCah().getSwCrisisAtPresentation());
                }
                if (null != instance.getDsdCah().getAdrenalCrisesAfterFirstPresentation()) {
                    propertyNameValues.put("adrenalCrisesAfterFirstPresentation", instance.getDsdCah().getAdrenalCrisesAfterFirstPresentation());
                }
                if (null != instance.getDsdCah().getCurrentSaltReplacement()) {
                    propertyNameValues.put("currentSaltReplacement", instance.getDsdCah().getCurrentSaltReplacement());
                }
                log.info("DsdCah search criteria = " + propertyNameValues);
                criteria.createCriteria("dsdCah").add(Restrictions.allEq(propertyNameValues));
            }

            List<DsdIdentifier> results = (List<DsdIdentifier>) criteria.list();
            log.debug("find by criteria successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by criteria failed", re);
            throw re;
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List findDsdIdentifierStatistic() {
        log.debug("finding all DsdIdentifier instances with only fields for statistic purpose");
        try {
            String queryString = "select new DsdIdentifier(co.countryName, ce.centreName, d.dna, d.tissue, " +
                    "d.cellLine, d.urine, d.serum, d.sampleConsent, d.consent) from DsdIdentifier d, " +
                    "Centre ce, Country co where d.centre.centreId = ce.centreId and ce.country.countryId = co.countryId";

//			String queryString = "select new DsdIdentifier(co.countryName, ce.centreName, d.dna, d.tissue, " +
//					"d.cellLine, d.urine, d.serum, d.sampleConsent, d.consent) from DsdIdentifier d " +
//					"left join fetch d.centre ce left join fetch ce.country co";
            Query query = getCurrentSession().createQuery(queryString);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List findAllRegisterIds() {
        log.debug("finding all DsdIdentifier Ids");
        try {
            String queryString = "select distinct new DsdIdentifier(registerId) from DsdIdentifier order by registerId";
            Query query = getCurrentSession().createQuery(queryString);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    public List<DsdIdentifier> findByUploader(String username) {
        String sql = "select d from DsdIdentifier d where d.uploader.username = :username order by d.registerId";
        try {
            Query query = getCurrentSession().createQuery(sql).setParameter("username", username);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find by uploader username failed", re);
            throw re;
        }
    }

    @Override
    public List<DsdIdentifier> findByCentre(String centreName) {
        String sql = "select d from DsdIdentifier d  " +
                "where d.centre.centreName = :centreName order by d.registerId";
        try {
            Query query = getCurrentSession().createQuery(sql).setParameter("centreName", centreName);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find by uploader username failed", re);
            throw re;
        }
    }

    //return record number if consent level >=3
    public long getTotalContributionNumber() {
        String query = "select count(*) from DsdIdentifier d where d.consent >= 3";
        return ((Long) getCurrentSession().createQuery(query).uniqueResult()).intValue();
    }

    @Override
    public List<DsdIdentifier> findByCountry(Object country) {
        return findByProperty(COUNTRY, country);
    }

//    @Override
//    public List<DsdIdentifier> findByCentre(Object centre) {
//        return findByProperty(CENTRE, centre);
//    }

    @Override
    public List<DsdIdentifier> findByLocalId(Object localId) {
        return findByProperty(LOCAL_ID, localId);
    }

    @Override
    public List<DsdIdentifier> findByYob(Object yob) {
        return findByProperty(YOB, yob);
    }

    @Override
    public List<DsdIdentifier> findByBirthWeight(Object birthWeight) {
        return findByProperty(BIRTH_WEIGHT, birthWeight);
    }

    @Override
    public List<DsdIdentifier> findByAofp(Object aofp) {
        return findByProperty(AOFP, aofp);
    }

    @Override
    public List<DsdIdentifier> findByClinician(Object clinician) {
        return findByProperty(CLINICIAN, clinician);
    }

    @Override
    public List<DsdIdentifier> findByContact(Object contact) {
        return findByProperty(CONTACT, contact);
    }

    @Override
    public List<DsdIdentifier> findByKaryotype(Object karyotype) {
        return findByProperty(KARYOTYPE, karyotype);
    }

    @Override
    public List<DsdIdentifier> findByDisorderType(Object disorderType) {
        return findByProperty(DISORDER_TYPE, disorderType);
    }

    @Override
    public List<DsdIdentifier> findByActualDiagnosis(Object actualDiagnosis) {
        return findByProperty(ACTUAL_DIAGNOSIS, actualDiagnosis);
    }

    @Override
    public List<DsdIdentifier> findBySexAssigned(Object sexAssigned) {
        return findByProperty(SEX_ASSIGNED, sexAssigned);
    }

    @Override
    public List<DsdIdentifier> findByGeneticCertainty(Object geneticCertainty) {
        return findByProperty(GENETIC_CERTAINTY, geneticCertainty);
    }

    @Override
    public List<DsdIdentifier> findByAssocMalforms(Object assocMalforms) {
        return findByProperty(ASSOC_MALFORMS, assocMalforms);
    }

    @Override
    public List<DsdIdentifier> findByDnaAnalysis(Object dnaAnalysis) {
        return findByProperty(DNA_ANALYSIS, dnaAnalysis);
    }

    @Override
    public List<DsdIdentifier> findByClinicalInfo(Object clinicalInfo) {
        return findByProperty(CLINICAL_INFO, clinicalInfo);
    }

    @Override
    public List<DsdIdentifier> findByCaseNotes(Object caseNotes) {
        return findByProperty(CASE_NOTES, caseNotes);
    }

    @Override
    public List<DsdIdentifier> findByGrowthData(Object growthData) {
        return findByProperty(GROWTH_DATA, growthData);
    }

    @Override
    public List<DsdIdentifier> findByPubertyData(Object pubertyData) {
        return findByProperty(PUBERTY_DATA, pubertyData);
    }

    @Override
    public List<DsdIdentifier> findByDna(Object dna) {
        return findByProperty(DNA, dna);
    }

    @Override
    public List<DsdIdentifier> findByTissue(Object tissue) {
        return findByProperty(TISSUE, tissue);
    }

    @Override
    public List<DsdIdentifier> findByCellLine(Object cellLine) {
        return findByProperty(CELL_LINE, cellLine);
    }

    @Override
    public List<DsdIdentifier> findByCellLineInfo(Object cellLineInfo) {
        return findByProperty(CELL_LINE_INFO, cellLineInfo);
    }

    @Override
    public List<DsdIdentifier> findByUrine(Object urine) {
        return findByProperty(URINE, urine);
    }

    @Override
    public List<DsdIdentifier> findBySerum(Object serum) {
        return findByProperty(SERUM, serum);
    }

    @Override
    public List<DsdIdentifier> findBySampleShared(Object sampleShared) {
        return findByProperty(SAMPLE_SHARED, sampleShared);
    }

    @Override
    public List<DsdIdentifier> findBySampleSharedDetail(Object sampleSharedDetail) {
        return findByProperty(SAMPLE_SHARED_DETAIL, sampleSharedDetail);
    }

    @Override
    public List<DsdIdentifier> findByInfertilityHistory(Object infertilityHistory) {
        return findByProperty(INFERTILITY_HISTORY, infertilityHistory);
    }

    @Override
    public List<DsdIdentifier> findBySampleAvailability(Object sampleAvailability) {
        return findByProperty(SAMPLE_AVAILABILITY, sampleAvailability);
    }

    @Override
    public List<DsdIdentifier> findByClinicalFeatures(Object clinicalFeatures) {
        return findByProperty(CLINICAL_FEATURES, clinicalFeatures);
    }

    @Override
    public List<DsdIdentifier> findByBiochemistry(Object biochemistry) {
        return findByProperty(BIOCHEMISTRY, biochemistry);
    }

    @Override
    public List<DsdIdentifier> findByParentalConsanguinity(Object parentalConsanguinity) {
        return findByProperty(PARENTAL_CONSANGUINITY, parentalConsanguinity);
    }

    @Override
    public List<DsdIdentifier> findByConsent(Object consent) {
        return findByProperty(CONSENT, consent);
    }

    @Override
    public List<DsdIdentifier> findBySampleConsent(Object sampleConsent) {
        return findByProperty(SAMPLE_CONSENT, sampleConsent);
    }

    @Override
    public List<DsdIdentifier> findByFreeText(Object freeText) {
        return findByProperty(FREE_TEXT, freeText);
    }

    @Override
    public List<DsdIdentifier> findByUploader(Object uploader) {
        return findByProperty(UPLOADER, uploader);
    }

    @Override
    public List<DsdIdentifier> findByDsdHistory(Object dsdHistory) {
        return findByProperty(DSD_HISTORY, dsdHistory);
    }

    @Override
    public List<DsdIdentifier> findByCompleteness(Object completeness) {
        return findByProperty(COMPLETENESS, completeness);
    }

    @Override
    public List<DsdIdentifier> findByDeleteStatus(Object deleteStatus) {
        return findByProperty(DELETE_STATUS, deleteStatus);
    }

    @Override
    public List<DsdIdentifier> findByCoreFreeText(Object coreFreeText) {
        return findByProperty(CORE_FREE_TEXT, coreFreeText);
    }

    @Override
    public List<DsdIdentifier> findByBirthLength(Object birthLength) {
        return findByProperty(BIRTH_LENGTH, birthLength);
    }

    @Override
    public DsdIdentifier findById(Long registerId) {
        return super.findById(registerId);
    }

    @Override
    public List<DsdIdentifier> findByIds(Set<Long> registerIds) {
        try {
            String sql = "select d from DsdIdentifier d  " +
                    "where d.registerId in ( :registerIds ) order by d.registerId";
            Query query = getCurrentSession().createQuery(sql).setParameter("registerIds", registerIds);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

	
}