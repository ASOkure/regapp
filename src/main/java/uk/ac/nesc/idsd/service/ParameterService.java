package uk.ac.nesc.idsd.service;

import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.Option;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Section;

import java.util.List;

public interface ParameterService {

    List<Section> getCahVisitSections(String sexAssigned);

    List<Section> getCreateDiagnosisSections();

    List<Section> getCoreAndDiagnosisSectionsForCreateView();

    List<Section> getCoreAndDiagnosisSectionsForUpdateView();

    List<Section> getReadDsdSectionsForNoneOwner();

    List<Parameter> getParametersForPatientUser();

    List<Section> getViewDsdCoreSections();

    Section getViewDsdCahSection();

    Section getDsdAssessmentSection();

    Section getDsdGeneAnalysisSection();

    List<Parameter> getDsdCahSection();

    List<Option> getYesNoNKOptions();

    List<Option> getGlucocorticoidsMedicineOptions();

    List<Option> getYesNoOptions();

    List<Section> getAllSearchSections(PortalUser user);

    List<Section> getMyCentreSearchSections(PortalUser user);

    List<Section> getCoreSearchSections(PortalUser user);

    List<Parameter> getCoreSearchParameters(PortalUser portalUser);

    Parameter getParameterById(int parameterId);

    List<Country> getCountries();

    List<Country> getCountriesForCurrentUser();

    List<Country> getCountriesForSearch();

    List<Country> getUserCountryForSearch(String centreName);

    List<Option> getMedUnitOptions();

    List<String> getKaryotypesValues();

    List<String> getDisorderTypeValues();

    List<String> getActualDiagnosisValues(String disorderType);

    List<Option> getOralSteroidsOptions();

    List<Option> getEmergencyAttendanceOptions();

    List<Parameter> getMyContributionParameters();

    List<Parameter> getMyCentreContributionParameters();

    List<Option> getPredisposingConditionOptions();

}
