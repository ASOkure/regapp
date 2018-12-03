package uk.ac.nesc.idsd.util.filters;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.ac.nesc.idsd.Constants;
import uk.ac.nesc.idsd.exception.ErrorCodes;
import uk.ac.nesc.idsd.exception.NoCentreFoundException;
import uk.ac.nesc.idsd.exception.UserException;
import uk.ac.nesc.idsd.model.Menu;
import uk.ac.nesc.idsd.model.Option;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.model.Tertiary;
import uk.ac.nesc.idsd.security.Authorization;
import uk.ac.nesc.idsd.util.comparator.OptionComparator;
import uk.ac.nesc.idsd.util.comparator.ParameterComparator;
import uk.ac.nesc.idsd.util.comparator.TertiaryComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class DsdParameterFilter {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(DsdParameterFilter.class);

    /**
     * take out section 4 and 6, and take out paramId 1 (register ID)
     *
     * @param _sections
     * @return
     * @throws NoCentreFoundException
     * @throws Exception
     */
    public static List<Section> createDsdIdentifierFilter(List<Section> _sections, PortalUser portalUser) throws UserException, NoCentreFoundException {
        List<Section> sections = new ArrayList<Section>();
        for (Section s : _sections) {
            if (s.getSectionId() != 4 && s.getSectionId() != 6) {
                if (s.getSectionId() == 1) {
                    Set<Parameter> pSet = new TreeSet<Parameter>(new ParameterComparator());
                    for (Parameter p : s.getParameters()) {
                        if (p.getParamId() != 1) {
                            pSet.add(p);
                        }
                    }
                    s.setParameters(pSet);
                }
                sections.add(s);
            }
        }
        return centresFilter(sections, portalUser);
    }

    /**
     * take out only section 4 and 6, prepare for view page of specific record.
     *
     * @param _sections
     * @return
     * @throws Exception
     */
    public static List<Section> viewDsdIdentifierFilter(List<Section> _sections) {
        List<Section> sections = new ArrayList<Section>();
        for (Section s : _sections) {
            if (s.getSectionId() != 4 && s.getSectionId() != 6) {
                sections.add(s);
            }
        }
        return sections;
    }

    /**
     * filter out non-searchable parameters (searchable column in Parameter
     * table), also add "dsdIdentifier." prefix to all parameters except section
     * 6 - Assessment
     *
     * @param _sections
     * @return
     * @throws Exception
     */
    public static List<Section> searchSectionSearchableFilter(List<Section> _sections, PortalUser portalUser) {
        List<Section> sections = new ArrayList<Section>();
        for (Section s : _sections) {
            Set<Parameter> parameters = new TreeSet<Parameter>(new ParameterComparator());
            for (Parameter p : s.getParameters()) {
                if (p.getSearchable()) {
                    parameters.add(p);
                }
            }
            if (parameters.size() != 0) {
                s.setParameters(parameters);
                sections.add(s);
            }
        }
        return sections;
    }

    /**
     * add new option "All Centres" to centre double list
     *
     * @param sections
     * @return
     * @throws Exception
     */
    public static List<Section> searchSectionCentreFilter(List<Section> sections) {
        List<Section> newSectionSet = new ArrayList<Section>();
        for (Section s : sections) {
            if (s.getSectionId() == 1) {
                Section coreSection = new Section(s);
                for (Parameter p : coreSection.getParameters()) {
                    if (null != p.getMenu() && p.getMenu().getMenuId() == 1) {
                        for (Option option : p.getMenu().getOptions()) {
                            option.getTertiaries().add(new Tertiary(0, Constants.ALL_CENTRES));
                        }
                        break;
                    }
                    newSectionSet.add(coreSection);
                }
            } else {
                newSectionSet.add(s);
            }
        }
        return newSectionSet;
    }

    /**
     * add new options "All of the Above" to actual diagnosis double list
     *
     * @param _sections
     * @return
     * @throws Exception
     */
    public static List<Section> searchSectionActualDiagnosisFilter(List<Section> _sections) {
        List<Section> sections = new ArrayList<Section>();
        for (Section s : _sections) {
            if (s.getSectionId() == 2) {
                for (Parameter p : s.getParameters()) {
                    if (null != p.getMenu() && p.getMenu().getMenuId() == 4) {
                        //add new option ANY
                        SortedSet<Option> enhancedOptions = new TreeSet<>(new OptionComparator());
                        Option anyOption = new Option(0, "Any");
                        enhancedOptions.add(anyOption);

                        //loop through tertiaries, add all together for ANY
                        Map<String, Tertiary> allTertiariesMap = new HashMap<>();
                        for (Option option : p.getMenu().getOptions()) {
                            Set<Tertiary> newTertiarySet = new TreeSet<Tertiary>(new TertiaryComparator());
                            for (Tertiary t : option.getTertiaries()) {
                                newTertiarySet.add(t);
                                allTertiariesMap.put(t.getValue(), t);
                            }
                            newTertiarySet.add(new Tertiary(0, "All of the Above"));
                            option.setTertiaries(newTertiarySet);
                            enhancedOptions.add(option);
                        }

                        // get all Tertiary, and sort by name
                        SortedSet<Tertiary> sortedTertiaries = new TreeSet<>(new Comparator<Tertiary>() {
                            @Override
                            public int compare(Tertiary o1, Tertiary o2) {
                                return o1.getValue().compareTo(o2.getValue());
                            }
                        });
                        for (Tertiary t : allTertiariesMap.values()) {
                            sortedTertiaries.add(t);
                        }

                        //add all aggregated tertiaries to ANY option
                        anyOption.setTertiaries(sortedTertiaries);

                        //replace existing options on menu
                        p.getMenu().setOptions(enhancedOptions);
                        break;
                    }
                }
            }
            sections.add(s);
        }
        return sections;
    }

    public static List<Section> removeIdAndCountry(List<Section> _sections) {
        List<Section> sections = new ArrayList<Section>();
        for (Section s : _sections) {
            if (s.getSectionId() == 1) {
                Section section = new Section(s);
                Set<Parameter> parameters = new TreeSet<>(new ParameterComparator());
                for (Parameter p : s.getParameters()) {
                    if (p.getParamId() != 1 && p.getParamId() != 2 && p.getParamId() != 6 && p.getParamId() != 7) {
                        parameters.add(p);
                    }
                }
                section.setParameters(parameters);
                sections.add(section);
            } else {
                sections.add(s);
            }
        }
        return sections;
    }

    /**
     * duplicate assessment section, add first and latest assessment
     *
     * @param _sections
     * @return
     * @throws Exception
     */
    public static List<Section> searchSectionDuplicateAssessmentFilter(List<Section> _sections) {
        List<Section> sections = new ArrayList<Section>();
        for (Section s : _sections) {
            if (s.getSectionId() == 6) {
                String originalName = s.getName();
                Section firstSection = new Section(s);
                firstSection.setName("First " + originalName);
                for (Parameter p : firstSection.getParameters()) {
//                    p.setParamName("firstAssessment." + p.getParamName());
                    p.setParamName("first" + p.getParamName().substring(0, 1).toUpperCase() + p.getParamName().substring(1));
                }
                sections.add(firstSection);

                Section latestSection = new Section(s);
                latestSection.setName("Latest " + originalName);
                for (Parameter p : latestSection.getParameters()) {
//                    p.setParamName("latestAssessment." + p.getParamName());
                    p.setParamName("latest" + p.getParamName().substring(0, 1).toUpperCase() + p.getParamName().substring(1));
                }
                sections.add(latestSection);
            } else {
                sections.add(s);
            }
        }
        return sections;
    }

    public static List<Section> centresFilter(List<Section> _sections, PortalUser portalUser) throws UserException, NoCentreFoundException {
        if (portalUser.getCentre() == null || portalUser.getCentre().isEmpty()
                || portalUser.getRoles() == null || portalUser.getRoles().isEmpty()) {
            throw new UserException(ErrorCodes.USER_CENTRE_ROLE_EXCEPTION);
        }

        for (Section section : _sections) {
            if (section.getSectionId() == 1) {
                for (Parameter parameter : section.getParameters()) {
                    if (null != parameter.getMenu() && parameter.getMenu().getMenuId() == 1) {
                        parameter.setMenu(getCentreMenu(portalUser, parameter.getMenu()));
                    }
                }
            }
        }
        return _sections;
    }

    private static String getCentresString(Set<Option> countries) {
        StringBuilder sb = new StringBuilder();
        for (Option country : countries) {
            for (Tertiary centre : country.getTertiaries()) {
                sb.append(centre.getValue());
                sb.append(",");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static Menu getCentreMenu(PortalUser portalUser, Menu _menu) throws NoCentreFoundException {
        if (Authorization.isLocalContributor(portalUser)) {
            Set<Option> newOptionSet = new TreeSet<Option>(new OptionComparator());
            for (Option option : _menu.getOptions()) {
                Set<Tertiary> newTertiarySet = new TreeSet<Tertiary>(new TertiaryComparator());
                if (option.getValue().equalsIgnoreCase(portalUser.getCountry())) {
                    for (Tertiary t : option.getTertiaries()) {
                        if (t.getValue().equalsIgnoreCase(portalUser.getCentre())) {
                            newTertiarySet.add(t);
                        }
                    }
                    if (CollectionUtils.isEmpty(newTertiarySet)) {
                        throw new NoCentreFoundException("The centre registered in your user account cannot match to any of existing ones ("
                                + getCentresString(_menu.getOptions()) + "). If your centre is one of them but with a different name, " +
                                "please change your centre to be the same as the one in the list. If your centre does not exist in the list, " +
                                "please contact Jillian Bryce (jillian.bryce@glasgow.ac.uk) to add your centre to the I-DSD registry");
                    }
                    option.setTertiaries(newTertiarySet);
                    newOptionSet.add(option);
                }
            }
            if (newOptionSet.size() == 0) {
                Set<Tertiary> localCentreSet = new TreeSet<Tertiary>(new TertiaryComparator());
                Tertiary localCentre = new Tertiary(portalUser.getCentre(), false);
                localCentreSet.add(localCentre);
                Option localCountry = new Option(portalUser.getCountry(), (float) 0, localCentreSet);
                newOptionSet.add(localCountry);
            } else if (newOptionSet.size() == 1) {
                Option localCountry = newOptionSet.iterator().next();
                if (localCountry.getTertiaries().size() == 0) {
                    Set<Tertiary> localCentreSet = new TreeSet<Tertiary>(new TertiaryComparator());
                    Tertiary localCentre = new Tertiary(portalUser.getCentre(), false);
                    localCentreSet.add(localCentre);
                    localCountry.setTertiaries(localCentreSet);
                }
            }
            _menu.setOptions(newOptionSet);
        } else {
            Set<Option> newOptionSet = new TreeSet<Option>(new OptionComparator());
            for (Option option : _menu.getOptions()) {
                Set<Tertiary> newTertiarySet = new TreeSet<Tertiary>(new TertiaryComparator());
                for (Tertiary t : option.getTertiaries()) {
                    if (t.getIdsdPartner()) {
                        newTertiarySet.add(t);
                    }
                }
                option.setTertiaries(newTertiarySet);
                if (option.getTertiaries().size() > 0) {
                    newOptionSet.add(option);
                }
            }
            _menu.setOptions(newOptionSet);
        }
        return _menu;
    }

}
