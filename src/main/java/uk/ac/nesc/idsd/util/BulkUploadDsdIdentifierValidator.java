package uk.ac.nesc.idsd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.service.CentreService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.exception.NoneCheckedServiceException;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.util.List;

@Component
public class BulkUploadDsdIdentifierValidator {
    @Autowired
    private CentreService centreService;
    @Autowired
    private ParameterService parameterService;

    private DsdIdentifier dsdIdentifier;

    public void validate(DsdIdentifier dsdIdentifier) throws ServiceException {
        this.dsdIdentifier = dsdIdentifier;
        enrichCentre();
        enrichKaryotype();
        enrichDisorderType();
        enrichActualDiagnosis();
    }

    private void enrichActualDiagnosis() {
        List<String> actualDiagnosisValues = parameterService.getActualDiagnosisValues(dsdIdentifier.getDisorderType());
        String foundValue = findMatchedDatabaseValue(actualDiagnosisValues, dsdIdentifier.getActualDiagnosis());

        if (foundValue != null) {
            dsdIdentifier.setActualDiagnosis(foundValue);
        } else {
            throw new NoneCheckedServiceException("Value used in Actual Diagnosis field does not exist in registry. Please adjust. The available values are: " + actualDiagnosisValues);
        }
    }

    private void enrichDisorderType() {
        List<String> disorderType = parameterService.getDisorderTypeValues();
        String foundValue = findMatchedDatabaseValue(disorderType, dsdIdentifier.getDisorderType());

        if (foundValue != null) {
            dsdIdentifier.setDisorderType(foundValue);
        } else {
            throw new NoneCheckedServiceException("Value used in Disorder Type field does not exist in registry. Please adjust. The available values are: " + disorderType);
        }
    }

    private void enrichKaryotype() {
        List<String> karyotypes = parameterService.getKaryotypesValues();
        String foundValue = findMatchedDatabaseValue(karyotypes, dsdIdentifier.getKaryotype());

        if (foundValue != null) {
            dsdIdentifier.setKaryotype(foundValue);
        } else {
            throw new NoneCheckedServiceException("Value used in Karyotype field does not exist in registry. Please adjust. The available values are: " + karyotypes);
        }
    }

    private String findMatchedDatabaseValue(List<String> databaseValues, String valueToCheck) {
        for (String s : databaseValues) {
            if (s.equalsIgnoreCase(valueToCheck)) {
                return s;
            }
        }
        return null;
    }

    private void enrichCentre() {
        //search centre by name:
        centreService.getCentreByCentreName(dsdIdentifier.getCentreName());
    }

}