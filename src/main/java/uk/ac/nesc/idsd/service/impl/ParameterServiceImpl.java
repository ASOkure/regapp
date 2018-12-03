package uk.ac.nesc.idsd.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.Constants;
import uk.ac.nesc.idsd.exception.NoCentreFoundException;
import uk.ac.nesc.idsd.exception.UserException;
import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.CentreDao;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.CountryDao;
import uk.ac.nesc.idsd.model.Menu;
import uk.ac.nesc.idsd.model.MenuDao;
import uk.ac.nesc.idsd.model.Option;
import uk.ac.nesc.idsd.model.OptionDao;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.ParameterDao;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.model.SectionDao;
import uk.ac.nesc.idsd.model.TertiaryDao;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.comparator.CentreComparator;
import uk.ac.nesc.idsd.util.comparator.ParameterComparator;
import uk.ac.nesc.idsd.util.comparator.SectionComparator;
import uk.ac.nesc.idsd.util.filters.DsdParameterFilter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ParameterServiceImpl implements ParameterService, Serializable {
    private static final Log log = LogFactory.getLog(ParameterServiceImpl.class);

    @Autowired
    private SectionDao sectionDao;
    @Autowired
    private ParameterDao parameterDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private OptionDao optionDao;
    @Autowired
    private TertiaryDao tertiaryDao;
    @Autowired
    private CentreDao centreDao;
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private UserService userService;

    @Override
    public List<Section> getCahVisitSections(String sexAssigned) {
        List<Section> cahViewSectionList = new ArrayList<>();

        Parameter date = parameterDao.findById(991);
        Parameter age = parameterDao.findById(992);

        Section cahSection = sectionDao.findById(9);

        Set<Parameter> parameters = new TreeSet<Parameter>(new ParameterComparator());
        parameters.add(date);
        parameters.add(age);

        cahSection = new Section(cahSection);
        cahSection.setParameters(parameters);
        cahViewSectionList.add(cahSection);

        for (int i = 10; i <= 16; i++) {
            cahViewSectionList.add(new Section(sectionDao.findById(i)));
        }

        if (StringUtils.isNotBlank(sexAssigned) && "M".equalsIgnoreCase(sexAssigned)) {
            cahViewSectionList.add(new Section(sectionDao.findById(18)));
        } else if (StringUtils.isNotBlank(sexAssigned) && "F".equalsIgnoreCase(sexAssigned)) {
            cahViewSectionList.add(new Section(sectionDao.findById(17)));
        } else {
            cahViewSectionList.add(new Section(sectionDao.findById(17)));
            cahViewSectionList.add(new Section(sectionDao.findById(18)));
        }
        //add cah visit note section
        cahViewSectionList.add(new Section(sectionDao.findById(19)));
        return cahViewSectionList;
    }

    public List<Section> getCreateDiagnosisSections() {
        Section section2 = this.sectionDao.findById(2);
        Section section3 = this.sectionDao.findById(3);
        Section section5 = this.sectionDao.findById(5);
        Section section7 = this.sectionDao.findById(7);
        Section section8 = this.sectionDao.findById(8);
        List<Section> sections = new ArrayList<Section>();

        Section clone = new Section(section2);
        Set<Parameter> pSet = new TreeSet<Parameter>(new ParameterComparator());
        for (Parameter p : section2.getParameters()) {
            if (p.getParamId().longValue() == 12
                    || p.getParamId().longValue() == 14
                    || p.getParamId().longValue() == 13) {
                pSet.add(p);
            }
        }
        clone.setParameters(pSet);
        sections.add(clone);
        sections.add(section3);
        sections.add(section5);
        sections.add(section7);
        sections.add(section8);
        Collections.sort(sections, new SectionComparator());
        return sections;
    }

    public List<Section> getReadDsdSectionsForNoneOwner() {
        Section coreInfo = this.sectionDao.findById(1);
        Section dsdClassification = this.sectionDao.findById(2);
        List<Section> sections = new ArrayList<Section>(2);
        sections.add(coreInfo);
        sections.add(dsdClassification);
        return sections;
    }

    public List<Parameter> getParametersForPatientUser() {
        List<Parameter> parameters = new ArrayList<Parameter>();

        Section coreInfo = this.sectionDao.findById(1);
        for (Parameter p : coreInfo.getParameters()) {
            if (p.getParamId().longValue() != 3L) {
                parameters.add(p);
            }
        }

        Section dsdClassification = this.sectionDao.findById(2);
        for (Parameter p : dsdClassification.getParameters()) {
            long pid = p.getParamId().longValue();
            if (pid != 11L && pid != 12L && pid != 59) {
                parameters.add(p);
            }
        }

        return parameters;
    }

    public List<Section> getViewDsdCoreSections() {
        List<Section> allSections = this.sectionDao.findAll();
        List<Section> sections = new ArrayList<Section>();
        for (Section s : allSections) {
            if (s.getSectionId() < 9) {
                sections.add(s);
            }
        }
        return DsdParameterFilter.viewDsdIdentifierFilter(sections);
    }

    public Section getViewDsdCahSection() {
        Section dbCahSection = sectionDao.findById(9);
        Set<Parameter> parameters = new TreeSet<Parameter>(new ParameterComparator());
        for (Parameter p : dbCahSection.getParameters()) {
            if (p.getParamId() != 74 && p.getParamId() <= 900) {
                parameters.add(p);
            }
        }
        Section cahSection = new Section(dbCahSection);
        cahSection.setParameters(parameters);
        return cahSection;
    }

    @Override
    public List<Section> getCoreAndDiagnosisSectionsForCreateView() {

        List<Section> sections = new ArrayList<>();

        sections.add(getCoreSectionForCreateView());
        sections.add(getDiagnosisSection());

        Collections.sort(sections, new SectionComparator());
        return sections;
    }

    @Override
    public List<Section> getCoreAndDiagnosisSectionsForUpdateView() {

        List<Section> sections = new ArrayList<>();

        sections.add(getCoreSectionForUpdateView());
        sections.add(getDiagnosisSection());

        Collections.sort(sections, new SectionComparator());
        return sections;
    }

    private Section getCoreSectionForCreateView() {
        Section section1 = this.sectionDao.findById(1);
        Section clone1 = new Section(section1);
        Set<Parameter> pSet1 = new TreeSet<>(new ParameterComparator());
        for (Parameter p : section1.getParameters()) {
            if (p.getParamId().longValue() != 1) {
                pSet1.add(p);
            }
        }

        clone1.setParameters(pSet1);
        return clone1;
    }

    private Section getCoreSectionForUpdateView() {
        Section section1 = this.sectionDao.findById(1);
        Section clone1 = new Section(section1);
        Set<Parameter> pSet1 = new TreeSet<>(new ParameterComparator());
        pSet1.addAll(section1.getParameters());

        clone1.setParameters(pSet1);
        return clone1;
    }

    private Section getDiagnosisSection() {
        Section section2 = this.sectionDao.findById(2);
        Section clone2 = new Section(section2);
        Set<Parameter> pSet2 = new TreeSet<>(new ParameterComparator());
        for (Parameter p : section2.getParameters()) {
            if (p.getParamId().longValue() != 12
                    && p.getParamId().longValue() != 14
                    && p.getParamId().longValue() != 13) {
                pSet2.add(p);
            }
        }
        clone2.setParameters(pSet2);
        return clone2;
    }

    public Section getDsdAssessmentSection() {
        return this.sectionDao.findById(6);
    }

    public Section getDsdGeneAnalysisSection() {
        return this.sectionDao.findById(4);
    }

    // todo - review this method, replace it with some more sound logic
    public List<Parameter> getDsdCahSection() {
        List<Parameter> cahParameterList = new ArrayList<Parameter>();
        Parameter[] cahParameterArray = new TreeSet<>(this.sectionDao.findById(9).getParameters()).toArray(new Parameter[0]);

        cahParameterList.addAll(Arrays.asList(cahParameterArray).subList(0, cahParameterArray.length - 3));
        cahParameterList.add(cahParameterArray[cahParameterArray.length - 3]);
        return cahParameterList;
    }

    /**
     * return list of options for Yes,No,Not Known
     */
    public List<Option> getYesNoNKOptions() {
        int menuId = 8;
        Menu menu = this.menuDao.findById(menuId);
        return new ArrayList<Option>(menu.getOptions());
    }

    @Override
    public List<Option> getGlucocorticoidsMedicineOptions() {
        Menu menu = this.menuDao.findById(22);
        return new ArrayList<Option>(menu.getOptions());
    }

    public List<Option> getYesNoOptions() {
        int menuId = 7;
        Menu menu = this.menuDao.findById(menuId);
        return new ArrayList<Option>(menu.getOptions());
    }

    public List<Section> getAllSearchSections(PortalUser portalUser) {
        List<Section> allSections = this.sectionDao.findAll();
        try {
            return DsdParameterFilter
                    .searchSectionDuplicateAssessmentFilter(DsdParameterFilter
                            .searchSectionCentreFilter(DsdParameterFilter
                                    .searchSectionActualDiagnosisFilter(DsdParameterFilter
                                            .centresFilter(DsdParameterFilter
                                                    .searchSectionSearchableFilter(allSections, portalUser), portalUser))));
        } catch (UserException e) {
            log.error("User exception", e);
            throw new ServiceException(e.getMessage());
        } catch (NoCentreFoundException e) {
            log.error("No centre found exception", e);
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Section> getMyCentreSearchSections(PortalUser portalUser) {
        List<Section> allSections = this.sectionDao.findAll();
        try {
            return DsdParameterFilter
                    .removeIdAndCountry(DsdParameterFilter
                            .searchSectionDuplicateAssessmentFilter(DsdParameterFilter
                                    .searchSectionActualDiagnosisFilter(DsdParameterFilter
                                            .searchSectionSearchableFilter(allSections, portalUser))));
        } catch (UserException e) {
            log.error("User exception", e);
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Section> getCoreSearchSections(PortalUser portalUser) {
        Section core = this.sectionDao.findById(1);
        Section classification = this.sectionDao.findById(2);
        List<Section> allSections = new ArrayList<Section>(2);
        allSections.add(core);
        allSections.add(classification);
        try {
            return DsdParameterFilter.searchSectionCentreFilter(DsdParameterFilter
                    .searchSectionActualDiagnosisFilter(DsdParameterFilter
                            .centresFilter(DsdParameterFilter
                                    .searchSectionSearchableFilter(allSections,
                                            portalUser), portalUser)));
        } catch (UserException e) {
            log.error("User exception", e);
            throw new ServiceException(e.getMessage());
        } catch (NoCentreFoundException e) {
            log.error("No centre found exception", e);
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Parameter> getCoreSearchParameters(PortalUser portalUser) {
        List<Section> sections = getCoreSearchSections(portalUser);
        List<Parameter> parameters = new ArrayList<Parameter>();
        for (Section s : sections) {
            parameters.addAll(s.getParameters());
        }
        return parameters;
    }

    public Section getSectionById(int sectionId) {
        return this.sectionDao.findById(sectionId);
    }

    @Override
    public List<Country> getCountriesForSearch() {
        List<Country> countries = getCountries();
        if (countries != null) {
            for (Country co : countries) {
                co.getCentres().add(new Centre(Constants.ALL_CENTRES));
            }
        }
        return countries;
    }

    @Override
    public List<Country> getUserCountryForSearch(String centreName) {
        Centre centre = centreDao.findUniqueByCentreName(centreName);
        Country country = new Country(centre.getCountry().getCountryName(), Collections.singleton(centre));
        return Arrays.asList(country);
    }

    @Override
    public List<Option> getMedUnitOptions() {
        return new ArrayList<Option>(menuDao.findById(30).getOptions());
    }

    @Override
    public List<Country> getCountries() {
        List<Centre> centres = this.centreDao.findCountriesCentres();
        Map<Country, Set<Centre>> countryMap = new TreeMap<Country, Set<Centre>>(
                new Comparator<Country>() {
                    @Override
                    public int compare(Country o1, Country o2) {
                        return o1.getCountryName().compareTo(o2.getCountryName());
                    }
                });
        for (Centre c : centres) {
            if (countryMap.containsKey(c.getCountry())) {
                countryMap.get(c.getCountry()).add(c);
            } else {
                Set<Centre> cs = new TreeSet<Centre>(new CentreComparator());
                cs.add(c);
                countryMap.put(c.getCountry(), cs);
            }
        }
        for (Country co : countryMap.keySet()) {
            co.setCentres(countryMap.get(co));
        }
        return new ArrayList<Country>(countryMap.keySet());
    }

    @Override
    public List<Country> getCountriesForCurrentUser() {
        List<Country> countries = new ArrayList<Country>();
        String username;
        try {
            username = Utility.getLoginUserName();
        } catch (ServiceException e) {
            log.error("Exception when retrieving username from security session", e);
            return null;
        }

        PortalUser portalUser;
        try {
            portalUser = userService.getPortalUserByUsername(username);
        } catch (ServiceException e) {
            log.error("Exception when retrieving user from username: " + username, e);
            return null;
        }

        List<Centre> centres = this.centreDao.findByCentreName(portalUser.getCentre());

        if (null != centres && !centres.isEmpty()) {
            log.debug("looking for centres by centre name.");
            Centre dbCentre = centres.get(0);
            Centre centre = new Centre();
            centre.setCentreName(dbCentre.getCentreName());
            Country dbCountry = dbCentre.getCountry();
            Country country = new Country();
            country.setCountryName(dbCountry.getCountryName());
            centre.setCountry(country);
            country.getCentres().add(centre);
            countries.add(country);
            return countries;
        } else {
            // if user's center does not match anything, looking for user's
            // country
            List<Country> countryList = this.countryDao
                    .findByCountryName(portalUser.getCountry());
            if (null != countryList && !countryList.isEmpty()) {
                log.debug("looking for centres by country name");
                return countries;
            } else {// even country does not match.
                log.debug("returning all country and centre as none matches with user's country and centre");
                return getCountries();
            }

        }
    }

    public Parameter getParameterById(int parameterId) {
        return this.parameterDao.findById(parameterId);
    }

    @Override
    public List<String> getKaryotypesValues() {
        return optionDao.findAllOptionValuesByMenuId(3);
    }

    @Override
    public List<String> getDisorderTypeValues() {
        return optionDao.findAllOptionValuesByMenuId(4);
    }

    @Override
    public List<String> getActualDiagnosisValues(String disorderType) {
        return tertiaryDao.findAllTertiaryValuesByOptionValue(disorderType);
    }

    @Override
    public List<Option> getOralSteroidsOptions() {
        int menuId = 36;
        Menu menu = this.menuDao.findById(menuId);
        return new ArrayList<Option>(menu.getOptions());
    }

    @Override
    public List<Option> getEmergencyAttendanceOptions() {
        int menuId = 37;
        Menu menu = this.menuDao.findById(menuId);
        return new ArrayList<Option>(menu.getOptions());
    }

    @Override
    public List<Parameter> getMyContributionParameters() {
        List<Integer> parameterIds = Arrays.asList(1, 2, 3, 4, 5, 8, 9, 10, 11);
        return parameterDao.findByIds(parameterIds);
    }

    @Override
    public List<Parameter> getMyCentreContributionParameters() {
        List<Integer> parameterIds = Arrays.asList(1, 2, 3, 4, 5, 8, 9, 10, 11);
        return parameterDao.findByIds(parameterIds);
    }

    @Override
    public List<Option> getPredisposingConditionOptions() {
        int menuId = 39;
        Menu menu = this.menuDao.findById(menuId);
        return new ArrayList<Option>(menu.getOptions());
    }

}
