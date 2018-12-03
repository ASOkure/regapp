package uk.ac.nesc.idsd.model;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import uk.ac.nesc.idsd.util.Utility;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "dsd_cah_visit", catalog = "idsd")
@BatchSize(size = 10)
public class DsdCahVisit implements java.io.Serializable, Comparable<DsdCahVisit> {

    private Long dsdCahVisitId;
    private DsdCah dsdCah;
    private Date date;
    private Double age;
    private Double weight;
    private Double height;
    private Double waistCircumference;
    private Double hipCircumference;
    private Double bmi;
    private Double bsa;
    private String cushingoid;
    private String virilisation;
    private Integer bpSystolic;
    private Integer bpDiastolic;
    private Integer sickDayEpisodes;
    private Integer sickDayTotal;
    private String emergencyAdrenalCrisis;
    private String dailyAdherenceToTherapy;
    private String treatmentChange;
    private String treatmentChangeReason;
    private String treatmentChangeReasonNote;
    private String glucocorticoid;
    private String glucocorticoidDetail;
    private String glucocorticoidNote;
    private String fludrocortisone;
    private Double fludrocortisoneDose;
    private String fludrocortisoneFrequency;
    private String fludrocortisoneNote;
    private String currentGcReplacement;
    private String saltReplacement;
    private Double saltReplacementDose;
    private String saltReplacementDoseUnit;
    private String saltReplacementFrequency;
    private String saltReplacementNote;
    private String oestrogen;
    private String oestrogenNote;
    private String testosterone;
    private String testosteroneNote;
    private String gnrhAnalogues;
    private String gnrhAnaloguesNote;
    private String antihypertensives;
    private String antihypertensivesNote;
    private String antidiabetic;
    private String antidiabeticNote;
    private String antidepressants;
    private String antidepressantsNote;
    private String otherDrugs;
    private String otherDrugsNote;
    private String eua;
    private String euaNote;
    private String genitaliaSurgery;
    private String genitaliaSurgeryNote;
    private String otherSurgery;
    private String otherSurgeryNote;
    private String otherCongenitalAbnormalities;
    private String otherCongenitalAbnormalitiesNote;
    private String diabetesMellitusType1;
    private String diabetesMellitusType1Note;
    private String diabetesMellitusType2;
    private String diabetesMellitusType2Note;
    private String hypertension;
    private String hypertensionNote;
    private String thyroidDisease;
    private String thyroidDiseaseNote;
    private String osteoporosis;
    private String osteoporosisNote;
    private String stroke;
    private String strokeNote;
    private String cardiovascularDisease;
    private String cardiovascularDiseaseNote;
    private String smoking;
    private String smokingNote;
    private String anaemia;
    private String anaemiaNote;
    private String depression;
    private String depressionNote;
    private String anxiety;
    private String anxietyNote;
    private String psychosis;
    private String psychosisNote;
    private String otherMentalHealthProblem;
    private String otherMentalHealthProblemNote;
    private String jointHypermobility;
    private String jointHypermobilityNote;
    private String otherComorbidCondition;
    private String otherComorbidConditionNote;
    private Date bloodTestDate;
    private String sodium;
    private Date sodiumDate;
    private Time sodiumTime;
    private String sodiumNote;
    private String potassium;
    private Date potassiumDate;
    private Time potassiumTime;
    private String potassiumNote;
    private String deoxycortisol11;
    private Date deoxycortisol11Date;
    private Time deoxycortisol11Time;
    private String deoxycortisol11Note;
    private String renin;
    private Date reninDate;
    private Time reninTime;
    private String reninNote;
    private String plasmaReninActivity;
    private Date plasmaReninActivityDate;
    private Time plasmaReninActivityTime;
    private String plasmaReninActivityNote;
    private String ohp17;
    private Date ohp17Date;
    private Time ohp17Time;
    private String ohp17Note;
    private String andostenedione;
    private Date andostenedioneDate;
    private Time andostenedioneTime;
    private String andostenedioneNote;
    private String totalTestosterone;
    private Date totalTestosteroneDate;
    private Time totalTestosteroneTime;
    private String totalTestosteroneNote;
    private String dihydrotestosterone;
    private Date dihydrotestosteroneDate;
    private Time dihydrotestosteroneTime;
    private String dihydrotestosteroneNote;
    private String lh;
    private Date lhDate;
    private Time lhTime;
    private String lhNote;
    private String fsh;
    private Date fshDate;
    private Time fshTime;
    private String fshNote;
    private String oestradiol;
    private Date oestradiolDate;
    private Time oestradiolTime;
    private String oestradiolNote;
    private String progesterone;
    private Date progesteroneDate;
    private Time progesteroneTime;
    private String progesteroneNote;
    private String inhibinB;
    private Date inhibinBDate;
    private Time inhibinBTime;
    private String inhibinBNote;
    private String haemoglobin;
    private Date haemoglobinDate;
    private Time haemoglobinTime;
    private String haemoglobinNote;
    private String haematocrit;
    private Date haematocritDate;
    private Time haematocritTime;
    private String haematocritNote;
    private String shbg;
    private Date shbgDate;
    private Time shbgTime;
    private String shbgNote;

    private String glucose;
    private Date glucoseDate;
    private Time glucoseTime;
    private String glucoseNote;
    private String acth;
    private Date acthDate;
    private Time acthTime;
    private String acthNote;
    private String cortisol;
    private Date cortisolDate;
    private Time cortisolTime;
    private String cortisolNote;
    private String dheas;
    private Date dheasDate;
    private Time dheasTime;
    private String dheasNote;
    private String urineSteroidsByGcms;
    private Date urineSteroidsByGcmsDate;
    private Time urineSteroidsByGcmsTime;
    private String urineSteroidsByGcmsNote;

    private Date boneAgeTestDate;
    private Double boneAgeResult;
    private String boneAgeTestMethod;
    private String boneMineralDensityDone;
    private Date boneMineralDensityTestDate;
    private String boneMineralDensityTestResult;
    private String boneMineralDensityTestResultNote;
    private String femaleBreastStage;
    private String femalePubicHairStage;
    private String femaleAxillaryHairStage;
    private String menarche;
    private Double ageAtMenarche;
    private String regularMenstrualCycle;
    private String femaleFertilityDesired;
    private Integer pastPregnancyNumber;
    private Integer liveBirthNumber;
    private String femaleAssistedConceptionHistory;
    private String femaleAssistedConceptionHistoryNote;
    private String maleGenitalStage;
    private String malePubicHairStage;
    private String maleAxillaryHairStage;
    private Double testicularVolumeLeft;
    private Double testicularVolumeRight;
    private String testicularTexture;
    private String evidenceOfTart;
    private String evidenceOfTartNote;
    private String maleFertilityDesired;
    private String maleFertilityAttempted;
    private String spermStorage;
    private Integer partnerPastPregnancyNumber;
    private String maleAssistedConceptionHistory;
    private String maleAssistedConceptionHistoryNote;
    private String cahVisitNote;
    private Set<DsdCahVisitMedDetail> dsdCahVisitMedDetails = new HashSet<>(0);
    private Set<DsdCahVisitEpisode> dsdCahVisitEpisodes = new HashSet<>(0);

    public DsdCahVisit() {
    }

    public DsdCahVisit(DsdCah dsdCah) {
        this.dsdCah = dsdCah;
    }

    public DsdCahVisit(DsdCahVisit that) {
        if (that == null) {
            return;
        }
        this.setDsdCahVisitId(that.getDsdCahVisitId());
        this.setDsdCah(that.getDsdCah());
        this.setDate(that.getDate());
        this.setAge(that.getAge());
        this.setWeight(that.getWeight());
        this.setHeight(that.getHeight());
        this.setWaistCircumference(that.getWaistCircumference());
        this.setHipCircumference(that.getHipCircumference());
        this.setBmi(that.getBmi());
        this.setBsa(that.getBsa());
        this.setCushingoid(Utility.trimValue(that.getCushingoid()));
        this.setVirilisation(Utility.trimValue(that.getVirilisation()));
        this.setBpSystolic(Utility.trimValue(that.getBpSystolic()));
        this.setBpDiastolic(Utility.trimValue(that.getBpDiastolic()));
        this.setSickDayEpisodes(Utility.trimValue(that.getSickDayEpisodes()));
        this.setSickDayTotal(Utility.trimValue(that.getSickDayTotal()));
        this.setDailyAdherenceToTherapy(Utility.trimValue(that.getDailyAdherenceToTherapy()));
        this.setTreatmentChange(Utility.trimValue(that.getTreatmentChange()));
        this.setTreatmentChangeReason(Utility.trimValue(that.getTreatmentChangeReason()));
        this.setGlucocorticoid(Utility.trimValue(that.getGlucocorticoid()));
        this.setGlucocorticoidDetail(Utility.trimValue(that.getGlucocorticoidDetail()));
        this.setGlucocorticoidNote(Utility.trimValue(that.getGlucocorticoidNote()));
        this.setFludrocortisone(Utility.trimValue(that.getFludrocortisone()));
        this.setFludrocortisoneDose(Utility.trimValue(that.getFludrocortisoneDose()));
        this.setFludrocortisoneFrequency(Utility.trimValue(that.getFludrocortisoneFrequency()));
        this.setFludrocortisoneNote(Utility.trimValue(that.getFludrocortisoneNote()));
        this.setCurrentGcReplacement(Utility.trimValue(that.getCurrentGcReplacement()));
        this.setSaltReplacement(Utility.trimValue(that.getSaltReplacement()));
        this.setSaltReplacementDose(Utility.trimValue(that.getSaltReplacementDose()));
        this.setSaltReplacementDoseUnit(Utility.trimValue(that.getSaltReplacementDoseUnit()));
        this.setSaltReplacementFrequency(Utility.trimValue(that.getSaltReplacementFrequency()));
        this.setSaltReplacementNote(Utility.trimValue(that.getSaltReplacementNote()));
        this.setOestrogen(Utility.trimValue(that.getOestrogen()));
        this.setOestrogenNote(Utility.trimValue(that.getOestrogenNote()));
        this.setTestosterone(Utility.trimValue(that.getTestosterone()));
        this.setTestosteroneNote(Utility.trimValue(that.getTestosteroneNote()));
        this.setGnrhAnalogues(Utility.trimValue(that.getGnrhAnalogues()));
        this.setGnrhAnaloguesNote(Utility.trimValue(that.getGnrhAnaloguesNote()));
        this.setAntihypertensives(Utility.trimValue(that.getAntihypertensives()));
        this.setAntihypertensivesNote(Utility.trimValue(that.getAntihypertensivesNote()));
        this.setAntidiabetic(Utility.trimValue(that.getAntidiabetic()));
        this.setAntidiabeticNote(Utility.trimValue(that.getAntidiabeticNote()));
        this.setAntidepressants(Utility.trimValue(that.getAntidepressants()));
        this.setAntidepressantsNote(Utility.trimValue(that.getAntidepressantsNote()));
        this.setOtherDrugs(Utility.trimValue(that.getOtherDrugs()));
        this.setOtherDrugsNote(Utility.trimValue(that.getOtherDrugsNote()));
        this.setEua(Utility.trimValue(that.getEua()));
        this.setEuaNote(Utility.trimValue(that.getEuaNote()));
        this.setGenitaliaSurgery(Utility.trimValue(that.getGenitaliaSurgery()));
        this.setGenitaliaSurgeryNote(Utility.trimValue(that.getGenitaliaSurgeryNote()));
        this.setOtherSurgery(Utility.trimValue(that.getOtherSurgery()));
        this.setOtherSurgeryNote(Utility.trimValue(that.getOtherSurgeryNote()));
        this.setOtherCongenitalAbnormalities(Utility.trimValue(that.getOtherCongenitalAbnormalities()));
        this.setOtherCongenitalAbnormalitiesNote(Utility.trimValue(that.getOtherCongenitalAbnormalitiesNote()));
        this.setDiabetesMellitusType1(Utility.trimValue(that.getDiabetesMellitusType1()));
        this.setDiabetesMellitusType1Note(Utility.trimValue(that.getDiabetesMellitusType1Note()));
        this.setDiabetesMellitusType2(Utility.trimValue(that.getDiabetesMellitusType2()));
        this.setDiabetesMellitusType2Note(Utility.trimValue(that.getDiabetesMellitusType2Note()));
        this.setHypertension(Utility.trimValue(that.getHypertension()));
        this.setHypertensionNote(Utility.trimValue(that.getHypertensionNote()));
        this.setThyroidDisease(Utility.trimValue(that.getThyroidDisease()));
        this.setThyroidDiseaseNote(Utility.trimValue(that.getThyroidDiseaseNote()));
        this.setOsteoporosis(Utility.trimValue(that.getOsteoporosis()));
        this.setOsteoporosisNote(Utility.trimValue(that.getOsteoporosisNote()));
        this.setStroke(Utility.trimValue(that.getStroke()));
        this.setStrokeNote(Utility.trimValue(that.getStrokeNote()));
        this.setCardiovascularDisease(Utility.trimValue(that.getCardiovascularDisease()));
        this.setCardiovascularDiseaseNote(Utility.trimValue(that.getCardiovascularDiseaseNote()));
        this.setSmoking(Utility.trimValue(that.getSmoking()));
        this.setSmokingNote(Utility.trimValue(that.getSmokingNote()));
        this.setAnaemia(Utility.trimValue(that.getAnaemia()));
        this.setAnaemiaNote(Utility.trimValue(that.getAnaemiaNote()));
        this.setDepression(Utility.trimValue(that.getDepression()));
        this.setDepressionNote(Utility.trimValue(that.getDepressionNote()));
        this.setAnxiety(Utility.trimValue(that.getAnxiety()));
        this.setAnxietyNote(Utility.trimValue(that.getAnxietyNote()));
        this.setPsychosis(Utility.trimValue(that.getPsychosis()));
        this.setPsychosisNote(Utility.trimValue(that.getPsychosisNote()));
        this.setOtherMentalHealthProblem(Utility.trimValue(that.getOtherMentalHealthProblem()));
        this.setOtherMentalHealthProblemNote(Utility.trimValue(that.getOtherMentalHealthProblemNote()));
        this.setJointHypermobility(Utility.trimValue(that.getJointHypermobility()));
        this.setJointHypermobilityNote(Utility.trimValue(that.getJointHypermobilityNote()));
        this.setOtherComorbidCondition(Utility.trimValue(that.getOtherComorbidCondition()));
        this.setOtherComorbidConditionNote(Utility.trimValue(that.getOtherComorbidConditionNote()));
        this.setSodium(Utility.trimValue(that.getSodium()));
        this.setSodiumDate(Utility.trimValue(that.getSodiumDate()));
        this.setSodiumTime(Utility.trimValue(that.getSodiumTime()));
        this.setSodiumNote(Utility.trimValue(that.getSodiumNote()));
        this.setPotassium(Utility.trimValue(that.getPotassium()));
        this.setPotassiumDate(Utility.trimValue(that.getPotassiumDate()));
        this.setPotassiumTime(Utility.trimValue(that.getPotassiumTime()));
        this.setPotassiumNote(Utility.trimValue(that.getPotassiumNote()));
        this.setDeoxycortisol11(Utility.trimValue(that.getDeoxycortisol11()));
        this.setDeoxycortisol11Date(Utility.trimValue(that.getDeoxycortisol11Date()));
        this.setDeoxycortisol11Time(Utility.trimValue(that.getDeoxycortisol11Time()));
        this.setDeoxycortisol11Note(Utility.trimValue(that.getDeoxycortisol11Note()));
        this.setRenin(Utility.trimValue(that.getRenin()));
        this.setReninDate(Utility.trimValue(that.getReninDate()));
        this.setReninTime(Utility.trimValue(that.getReninTime()));
        this.setReninNote(Utility.trimValue(that.getReninNote()));
        this.setPlasmaReninActivity(Utility.trimValue(that.getPlasmaReninActivity()));
        this.setPlasmaReninActivityDate(Utility.trimValue(that.getPlasmaReninActivityDate()));
        this.setPlasmaReninActivityTime(Utility.trimValue(that.getPlasmaReninActivityTime()));
        this.setPlasmaReninActivityNote(Utility.trimValue(that.getPlasmaReninActivityNote()));
        this.setOhp17(Utility.trimValue(that.getOhp17()));
        this.setOhp17Date(Utility.trimValue(that.getOhp17Date()));
        this.setOhp17Time(Utility.trimValue(that.getOhp17Time()));
        this.setOhp17Note(Utility.trimValue(that.getOhp17Note()));
        this.setAndostenedione(Utility.trimValue(that.getAndostenedione()));
        this.setAndostenedioneDate(Utility.trimValue(that.getAndostenedioneDate()));
        this.setAndostenedioneTime(Utility.trimValue(that.getAndostenedioneTime()));
        this.setAndostenedioneNote(Utility.trimValue(that.getAndostenedioneNote()));
        this.setTotalTestosterone(Utility.trimValue(that.getTotalTestosterone()));
        this.setTotalTestosteroneDate(Utility.trimValue(that.getTotalTestosteroneDate()));
        this.setTotalTestosteroneTime(Utility.trimValue(that.getTotalTestosteroneTime()));
        this.setTotalTestosteroneNote(Utility.trimValue(that.getTotalTestosteroneNote()));
        this.setDihydrotestosterone(Utility.trimValue(that.getDihydrotestosterone()));
        this.setDihydrotestosteroneDate(Utility.trimValue(that.getDihydrotestosteroneDate()));
        this.setDihydrotestosteroneTime(Utility.trimValue(that.getDihydrotestosteroneTime()));
        this.setDihydrotestosteroneNote(Utility.trimValue(that.getDihydrotestosteroneNote()));
        this.setLh(Utility.trimValue(that.getLh()));
        this.setLhDate(Utility.trimValue(that.getLhDate()));
        this.setLhTime(Utility.trimValue(that.getLhTime()));
        this.setLhNote(Utility.trimValue(that.getLhNote()));
        this.setFsh(Utility.trimValue(that.getFsh()));
        this.setFshDate(Utility.trimValue(that.getFshDate()));
        this.setFshTime(Utility.trimValue(that.getFshTime()));
        this.setFshNote(Utility.trimValue(that.getFshNote()));
        this.setOestradiol(Utility.trimValue(that.getOestradiol()));
        this.setOestradiolDate(Utility.trimValue(that.getOestradiolDate()));
        this.setOestradiolTime(Utility.trimValue(that.getOestradiolTime()));
        this.setOestradiolNote(Utility.trimValue(that.getOestradiolNote()));
        this.setProgesterone(Utility.trimValue(that.getProgesterone()));
        this.setProgesteroneDate(Utility.trimValue(that.getProgesteroneDate()));
        this.setProgesteroneTime(Utility.trimValue(that.getProgesteroneTime()));
        this.setProgesteroneNote(Utility.trimValue(that.getProgesteroneNote()));
        this.setInhibinB(Utility.trimValue(that.getInhibinB()));
        this.setInhibinBDate(Utility.trimValue(that.getInhibinBDate()));
        this.setInhibinBTime(Utility.trimValue(that.getInhibinBTime()));
        this.setInhibinBNote(Utility.trimValue(that.getInhibinBNote()));
        this.setHaemoglobin(Utility.trimValue(that.getHaemoglobin()));
        this.setHaemoglobinDate(Utility.trimValue(that.getHaemoglobinDate()));
        this.setHaemoglobinTime(Utility.trimValue(that.getHaemoglobinTime()));
        this.setHaemoglobinNote(Utility.trimValue(that.getHaemoglobinNote()));
        this.setHaematocrit(Utility.trimValue(that.getHaematocrit()));
        this.setHaematocritDate(Utility.trimValue(that.getHaematocritDate()));
        this.setHaematocritTime(Utility.trimValue(that.getHaematocritTime()));
        this.setHaematocritNote(Utility.trimValue(that.getHaematocritNote()));
        this.setShbg(Utility.trimValue(that.getShbg()));
        this.setShbgDate(Utility.trimValue(that.getShbgDate()));
        this.setShbgTime(Utility.trimValue(that.getShbgTime()));
        this.setShbgNote(Utility.trimValue(that.getShbgNote()));
        this.setGlucose(Utility.trimValue(that.getGlucose()));
        this.setGlucoseDate(Utility.trimValue(that.getGlucoseDate()));
        this.setGlucoseTime(Utility.trimValue(that.getGlucoseTime()));
        this.setGlucoseNote(Utility.trimValue(that.getGlucoseNote()));
        this.setActh(Utility.trimValue(that.getActh()));
        this.setActhDate(Utility.trimValue(that.getActhDate()));
        this.setActhTime(Utility.trimValue(that.getActhTime()));
        this.setActhNote(Utility.trimValue(that.getActhNote()));
        this.setCortisol(Utility.trimValue(that.getCortisol()));
        this.setCortisolDate(Utility.trimValue(that.getCortisolDate()));
        this.setCortisolTime(Utility.trimValue(that.getCortisolTime()));
        this.setCortisolNote(Utility.trimValue(that.getCortisolNote()));
        this.setDheas(Utility.trimValue(that.getDheas()));
        this.setDheasDate(Utility.trimValue(that.getDheasDate()));
        this.setDheasTime(Utility.trimValue(that.getDheasTime()));
        this.setDheasNote(Utility.trimValue(that.getDheasNote()));
        this.setUrineSteroidsByGcms(Utility.trimValue(that.getUrineSteroidsByGcms()));
        this.setUrineSteroidsByGcmsDate(Utility.trimValue(that.getUrineSteroidsByGcmsDate()));
        this.setUrineSteroidsByGcmsTime(Utility.trimValue(that.getUrineSteroidsByGcmsTime()));
        this.setUrineSteroidsByGcmsNote(Utility.trimValue(that.getUrineSteroidsByGcmsNote()));
        this.setBoneAgeTestDate(Utility.trimValue(that.getBoneAgeTestDate()));
        this.setBoneAgeResult(Utility.trimValue(that.getBoneAgeResult()));
        this.setBoneAgeTestMethod(Utility.trimValue(that.getBoneAgeTestMethod()));
        this.setBoneMineralDensityDone(Utility.trimValue(that.getBoneMineralDensityDone()));
        this.setBoneMineralDensityTestDate(Utility.trimValue(that.getBoneMineralDensityTestDate()));
        this.setBoneMineralDensityTestResult(Utility.trimValue(that.getBoneMineralDensityTestResult()));
        this.setBoneMineralDensityTestResultNote(Utility.trimValue(that.getBoneMineralDensityTestResultNote()));
        this.setMaleGenitalStage(Utility.trimValue(that.getMaleGenitalStage()));
        this.setMalePubicHairStage(Utility.trimValue(that.getMalePubicHairStage()));
        this.setMaleAxillaryHairStage(Utility.trimValue(that.getMaleAxillaryHairStage()));
        this.setTesticularVolumeLeft(Utility.trimValue(that.getTesticularVolumeLeft()));
        this.setTesticularVolumeRight(Utility.trimValue(that.getTesticularVolumeRight()));
        this.setTesticularTexture(Utility.trimValue(that.getTesticularTexture()));
        this.setEvidenceOfTart(Utility.trimValue(that.getEvidenceOfTart()));
        this.setEvidenceOfTartNote(Utility.trimValue(that.getEvidenceOfTartNote()));
        this.setMaleFertilityDesired(Utility.trimValue(that.getMaleFertilityDesired()));
        this.setSpermStorage(Utility.trimValue(that.getSpermStorage()));
        this.setPartnerPastPregnancyNumber(Utility.trimValue(that.getPartnerPastPregnancyNumber()));
        this.setMaleAssistedConceptionHistory(Utility.trimValue(that.getMaleAssistedConceptionHistory()));
        this.setMaleAssistedConceptionHistoryNote(Utility.trimValue(that.getMaleAssistedConceptionHistoryNote()));
        this.setFemaleBreastStage(Utility.trimValue(that.getFemaleBreastStage()));
        this.setFemalePubicHairStage(Utility.trimValue(that.getFemalePubicHairStage()));
        this.setFemaleAxillaryHairStage(Utility.trimValue(that.getFemaleAxillaryHairStage()));
        this.setMenarche(Utility.trimValue(that.getMenarche()));
        this.setAgeAtMenarche(Utility.trimValue(that.getAgeAtMenarche()));
        this.setRegularMenstrualCycle(Utility.trimValue(that.getRegularMenstrualCycle()));
        this.setFemaleFertilityDesired(Utility.trimValue(that.getFemaleFertilityDesired()));
        this.setPastPregnancyNumber(Utility.trimValue(that.getPastPregnancyNumber()));
        this.setLiveBirthNumber(Utility.trimValue(that.getLiveBirthNumber()));
        this.setFemaleAssistedConceptionHistory(Utility.trimValue(that.getFemaleAssistedConceptionHistory()));
        this.setFemaleAssistedConceptionHistoryNote(Utility.trimValue(that.getFemaleAssistedConceptionHistoryNote()));
        this.setCahVisitNote(that.getCahVisitNote());
        if (CollectionUtils.isNotEmpty(that.getDsdCahVisitEpisodes())) {
            Set<DsdCahVisitEpisode> dsdCahVisitEpisodeSet = new TreeSet<>();
            for (DsdCahVisitEpisode episode : that.getDsdCahVisitEpisodes()) {
                dsdCahVisitEpisodeSet.add(new DsdCahVisitEpisode(episode));
            }
            this.setDsdCahVisitEpisodes(dsdCahVisitEpisodeSet);
        }
        if (CollectionUtils.isNotEmpty(that.getDsdCahVisitMedDetails())) {
            Set<DsdCahVisitMedDetail> dsdCahVisitMedDetailSet = new TreeSet<>();
            for (DsdCahVisitMedDetail detail : that.getDsdCahVisitMedDetails()) {
                dsdCahVisitMedDetailSet.add(new DsdCahVisitMedDetail(detail));
            }
            this.setDsdCahVisitMedDetails(dsdCahVisitMedDetailSet);
        }
    }

    @Id
    @GeneratedValue
    @Column(name = "dsd_cah_visit_id", unique = true, nullable = false)
    public Long getDsdCahVisitId() {
        return this.dsdCahVisitId;
    }

    public void setDsdCahVisitId(Long dsdCahVisitId) {
        this.dsdCahVisitId = dsdCahVisitId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cah_id", nullable = false)
    public DsdCah getDsdCah() {
        return this.dsdCah;
    }

    public void setDsdCah(DsdCah dsdCah) {
        this.dsdCah = dsdCah;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", length = 10)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "age", precision = 22, scale = 2)
    public Double getAge() {
        return this.age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    @Column(name = "weight", precision = 22, scale = 2)
    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Column(name = "height", precision = 22, scale = 2)
    public Double getHeight() {
        return this.height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Column(name = "waist_circumference", precision = 22, scale = 2)
    public Double getWaistCircumference() {
        return this.waistCircumference;
    }

    public void setWaistCircumference(Double waistCircumference) {
        this.waistCircumference = waistCircumference;
    }

    @Column(name = "hip_circumference", precision = 22, scale = 2)
    public Double getHipCircumference() {
        return this.hipCircumference;
    }

    public void setHipCircumference(Double hipCircumference) {
        this.hipCircumference = hipCircumference;
    }

    @Column(name = "bmi", precision = 22, scale = 2)
    public Double getBmi() {
        return this.bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    @Column(name = "bsa", precision = 22, scale = 2)
    public Double getBsa() {
        return this.bsa;
    }

    public void setBsa(Double bsa) {
        this.bsa = bsa;
    }

    @Column(name = "cushingoid", length = 45)
    public String getCushingoid() {
        return this.cushingoid;
    }

    public void setCushingoid(String cushingoid) {
        this.cushingoid = cushingoid;
    }

    @Column(name = "virilisation", length = 45)
    public String getVirilisation() {
        return this.virilisation;
    }

    public void setVirilisation(String virilisation) {
        this.virilisation = virilisation;
    }

    @Column(name = "bp_systolic")
    public Integer getBpSystolic() {
        return this.bpSystolic;
    }

    public void setBpSystolic(Integer bpSystolic) {
        this.bpSystolic = bpSystolic;
    }

    @Column(name = "bp_diastolic")
    public Integer getBpDiastolic() {
        return this.bpDiastolic;
    }

    public void setBpDiastolic(Integer bpDiastolic) {
        this.bpDiastolic = bpDiastolic;
    }

    @Column(name = "sick_day_episodes")
    public Integer getSickDayEpisodes() {
        return this.sickDayEpisodes;
    }

    public void setSickDayEpisodes(Integer sickDayEpisodes) {
        this.sickDayEpisodes = sickDayEpisodes;
    }

    @Column(name = "sick_day_total")
    public Integer getSickDayTotal() {
        return this.sickDayTotal;
    }

    public void setSickDayTotal(Integer sickDayTotal) {
        this.sickDayTotal = sickDayTotal;
    }

    @Column(name = "emergency_adrenal_crisis", length = 45)
    public String getEmergencyAdrenalCrisis() {
        return this.emergencyAdrenalCrisis;
    }

    public void setEmergencyAdrenalCrisis(String emergencyAdrenalCrisis) {
        this.emergencyAdrenalCrisis = emergencyAdrenalCrisis;
    }

    @Column(name = "daily_adherence_to_therapy", length = 45)
    public String getDailyAdherenceToTherapy() {
        return dailyAdherenceToTherapy;
    }

    public void setDailyAdherenceToTherapy(String dailyAdherenceToTherapy) {
        this.dailyAdherenceToTherapy = dailyAdherenceToTherapy;
    }

    @Column(name = "treatment_change", length = 45)
    public String getTreatmentChange() {
        return this.treatmentChange;
    }

    public void setTreatmentChange(String treatmentChange) {
        this.treatmentChange = treatmentChange;
    }

    @Column(name = "treatment_change_reason", length = 200)
    public String getTreatmentChangeReason() {
        return this.treatmentChangeReason;
    }

    public void setTreatmentChangeReason(String treatmentChangeReason) {
        this.treatmentChangeReason = treatmentChangeReason;
    }

    @Column(name = "treatment_change_reason_note", length = 65535)
    public String getTreatmentChangeReasonNote() {
        return treatmentChangeReasonNote;
    }

    public void setTreatmentChangeReasonNote(String treatmentChangeReasonNote) {
        this.treatmentChangeReasonNote = treatmentChangeReasonNote;
    }

    @Column(name = "glucocorticoid", length = 45)
    public String getGlucocorticoid() {
        return this.glucocorticoid;
    }

    public void setGlucocorticoid(String glucocorticoid) {
        this.glucocorticoid = glucocorticoid;
    }

    @Column(name = "glucocorticoid_detail", length = 65535)
    public String getGlucocorticoidDetail() {
        return this.glucocorticoidDetail;
    }

    public void setGlucocorticoidDetail(String glucocorticoidDetail) {
        this.glucocorticoidDetail = glucocorticoidDetail;
    }

    @Column(name = "glucocorticoid_note", length = 65535)
    public String getGlucocorticoidNote() {
        return this.glucocorticoidNote;
    }

    public void setGlucocorticoidNote(String glucocorticoidNote) {
        this.glucocorticoidNote = glucocorticoidNote;
    }

    @Column(name = "fludrocortisone", length = 45)
    public String getFludrocortisone() {
        return this.fludrocortisone;
    }

    public void setFludrocortisone(String fludrocortisone) {
        this.fludrocortisone = fludrocortisone;
    }

    @Column(name = "fludrocortisone_dose", precision = 22, scale = 0)
    public Double getFludrocortisoneDose() {
        return this.fludrocortisoneDose;
    }

    public void setFludrocortisoneDose(Double fludrocortisoneDose) {
        this.fludrocortisoneDose = fludrocortisoneDose;
    }

    @Column(name = "fludrocortisone_frequency", length = 45)
    public String getFludrocortisoneFrequency() {
        return this.fludrocortisoneFrequency;
    }

    public void setFludrocortisoneFrequency(String fludrocortisoneFrequency) {
        this.fludrocortisoneFrequency = fludrocortisoneFrequency;
    }

    @Column(name = "fludrocortisone_note", length = 65535)
    public String getFludrocortisoneNote() {
        return this.fludrocortisoneNote;
    }

    public void setFludrocortisoneNote(String fludrocortisoneNote) {
        this.fludrocortisoneNote = fludrocortisoneNote;
    }

    @Column(name = "current_gc_replacement", length = 45)
    public String getCurrentGcReplacement() {
        return this.currentGcReplacement;
    }

    public void setCurrentGcReplacement(String currentGcReplacement) {
        this.currentGcReplacement = currentGcReplacement;
    }

    @Column(name = "salt_replacement", length = 45)
    public String getSaltReplacement() {
        return this.saltReplacement;
    }

    public void setSaltReplacement(String saltReplacement) {
        this.saltReplacement = saltReplacement;
    }

    @Column(name = "salt_replacement_dose", precision = 22, scale = 0)
    public Double getSaltReplacementDose() {
        return this.saltReplacementDose;
    }

    public void setSaltReplacementDose(Double saltReplacementDose) {
        this.saltReplacementDose = saltReplacementDose;
    }

    @Column(name = "salt_replacement_dose_unit", length = 45)
    public String getSaltReplacementDoseUnit() {
        return this.saltReplacementDoseUnit;
    }

    public void setSaltReplacementDoseUnit(String saltReplacementDoseUnit) {
        this.saltReplacementDoseUnit = saltReplacementDoseUnit;
    }

    @Column(name = "salt_replacement_frequency", length = 45)
    public String getSaltReplacementFrequency() {
        return this.saltReplacementFrequency;
    }

    public void setSaltReplacementFrequency(String saltReplacementFrequency) {
        this.saltReplacementFrequency = saltReplacementFrequency;
    }

    @Column(name = "salt_replacement_note", length = 65535)
    public String getSaltReplacementNote() {
        return this.saltReplacementNote;
    }

    public void setSaltReplacementNote(String saltReplacementNote) {
        this.saltReplacementNote = saltReplacementNote;
    }

    @Column(name = "oestrogen", length = 45)
    public String getOestrogen() {
        return this.oestrogen;
    }

    public void setOestrogen(String oestrogen) {
        this.oestrogen = oestrogen;
    }

    @Column(name = "oestrogen_note", length = 65535)
    public String getOestrogenNote() {
        return this.oestrogenNote;
    }

    public void setOestrogenNote(String oestrogenNote) {
        this.oestrogenNote = oestrogenNote;
    }

    @Column(name = "testosterone", length = 45)
    public String getTestosterone() {
        return this.testosterone;
    }

    public void setTestosterone(String testosterone) {
        this.testosterone = testosterone;
    }

    @Column(name = "testosterone_note", length = 65535)
    public String getTestosteroneNote() {
        return this.testosteroneNote;
    }

    public void setTestosteroneNote(String testosteroneNote) {
        this.testosteroneNote = testosteroneNote;
    }

    @Column(name = "gnrh_analogues", length = 45)
    public String getGnrhAnalogues() {
        return this.gnrhAnalogues;
    }

    public void setGnrhAnalogues(String gnrhAnalogues) {
        this.gnrhAnalogues = gnrhAnalogues;
    }

    @Column(name = "gnrh_analogues_note", length = 65535)
    public String getGnrhAnaloguesNote() {
        return this.gnrhAnaloguesNote;
    }

    public void setGnrhAnaloguesNote(String gnrhAnaloguesNote) {
        this.gnrhAnaloguesNote = gnrhAnaloguesNote;
    }

    @Column(name = "antihypertensives", length = 45)
    public String getAntihypertensives() {
        return this.antihypertensives;
    }

    public void setAntihypertensives(String antihypertensives) {
        this.antihypertensives = antihypertensives;
    }

    @Column(name = "antihypertensives_note", length = 65535)
    public String getAntihypertensivesNote() {
        return this.antihypertensivesNote;
    }

    public void setAntihypertensivesNote(String antihypertensivesNote) {
        this.antihypertensivesNote = antihypertensivesNote;
    }

    @Column(name = "antidiabetic", length = 45)
    public String getAntidiabetic() {
        return this.antidiabetic;
    }

    public void setAntidiabetic(String antidiabetic) {
        this.antidiabetic = antidiabetic;
    }

    @Column(name = "antidiabetic_note", length = 65535)
    public String getAntidiabeticNote() {
        return this.antidiabeticNote;
    }

    public void setAntidiabeticNote(String antidiabeticNote) {
        this.antidiabeticNote = antidiabeticNote;
    }

    @Column(name = "antidepressants", length = 45)
    public String getAntidepressants() {
        return this.antidepressants;
    }

    public void setAntidepressants(String antidepressants) {
        this.antidepressants = antidepressants;
    }

    @Column(name = "antidepressants_note", length = 65535)
    public String getAntidepressantsNote() {
        return this.antidepressantsNote;
    }

    public void setAntidepressantsNote(String antidepressantsNote) {
        this.antidepressantsNote = antidepressantsNote;
    }

    @Column(name = "other_drugs", length = 45)
    public String getOtherDrugs() {
        return this.otherDrugs;
    }

    public void setOtherDrugs(String otherDrugs) {
        this.otherDrugs = otherDrugs;
    }

    @Column(name = "other_drugs_note", length = 65535)
    public String getOtherDrugsNote() {
        return this.otherDrugsNote;
    }

    public void setOtherDrugsNote(String otherDrugsNote) {
        this.otherDrugsNote = otherDrugsNote;
    }

    @Column(name = "eua_note", length = 65535)
    public String getEuaNote() {
        return euaNote;
    }

    public void setEuaNote(String euaNote) {
        this.euaNote = euaNote;
    }

    @Column(name = "eua", length = 45)
    public String getEua() {
        return eua;
    }

    public void setEua(String eua) {
        this.eua = eua;
    }

    @Column(name = "genitalia_surgery", length = 45)
    public String getGenitaliaSurgery() {
        return this.genitaliaSurgery;
    }

    public void setGenitaliaSurgery(String genitaliaSurgery) {
        this.genitaliaSurgery = genitaliaSurgery;
    }

    @Column(name = "genitalia_surgery_note", length = 65535)
    public String getGenitaliaSurgeryNote() {
        return this.genitaliaSurgeryNote;
    }

    public void setGenitaliaSurgeryNote(String genitaliaSurgeryNote) {
        this.genitaliaSurgeryNote = genitaliaSurgeryNote;
    }

    @Column(name = "other_surgery", length = 45)
    public String getOtherSurgery() {
        return this.otherSurgery;
    }

    public void setOtherSurgery(String otherSurgery) {
        this.otherSurgery = otherSurgery;
    }

    @Column(name = "other_surgery_note", length = 65535)
    public String getOtherSurgeryNote() {
        return this.otherSurgeryNote;
    }

    public void setOtherSurgeryNote(String otherSurgeryNote) {
        this.otherSurgeryNote = otherSurgeryNote;
    }

    @Column(name = "other_congenital_abnormalities", length = 45)
    public String getOtherCongenitalAbnormalities() {
        return this.otherCongenitalAbnormalities;
    }

    public void setOtherCongenitalAbnormalities(
            String otherCongenitalAbnormalities) {
        this.otherCongenitalAbnormalities = otherCongenitalAbnormalities;
    }

    @Column(name = "other_congenital_abnormalities_note", length = 65535)
    public String getOtherCongenitalAbnormalitiesNote() {
        return this.otherCongenitalAbnormalitiesNote;
    }

    public void setOtherCongenitalAbnormalitiesNote(
            String otherCongenitalAbnormalitiesNote) {
        this.otherCongenitalAbnormalitiesNote = otherCongenitalAbnormalitiesNote;
    }

    @Column(name = "diabetes_mellitus_type1", length = 45)
    public String getDiabetesMellitusType1() {
        return this.diabetesMellitusType1;
    }

    public void setDiabetesMellitusType1(String diabetesMellitusType1) {
        this.diabetesMellitusType1 = diabetesMellitusType1;
    }

    @Column(name = "diabetes_mellitus_type1_note", length = 65535)
    public String getDiabetesMellitusType1Note() {
        return this.diabetesMellitusType1Note;
    }

    public void setDiabetesMellitusType1Note(String diabetesMellitusType1Note) {
        this.diabetesMellitusType1Note = diabetesMellitusType1Note;
    }

    @Column(name = "diabetes_mellitus_type2", length = 45)
    public String getDiabetesMellitusType2() {
        return this.diabetesMellitusType2;
    }

    public void setDiabetesMellitusType2(String diabetesMellitusType2) {
        this.diabetesMellitusType2 = diabetesMellitusType2;
    }

    @Column(name = "diabetes_mellitus_type2_note", length = 65535)
    public String getDiabetesMellitusType2Note() {
        return this.diabetesMellitusType2Note;
    }

    public void setDiabetesMellitusType2Note(String diabetesMellitusType2Note) {
        this.diabetesMellitusType2Note = diabetesMellitusType2Note;
    }

    @Column(name = "hypertension", length = 45)
    public String getHypertension() {
        return this.hypertension;
    }

    public void setHypertension(String hypertension) {
        this.hypertension = hypertension;
    }

    @Column(name = "hypertension_note", length = 65535)
    public String getHypertensionNote() {
        return this.hypertensionNote;
    }

    public void setHypertensionNote(String hypertensionNote) {
        this.hypertensionNote = hypertensionNote;
    }

    @Column(name = "thyroid_disease", length = 45)
    public String getThyroidDisease() {
        return this.thyroidDisease;
    }

    public void setThyroidDisease(String thyroidDisease) {
        this.thyroidDisease = thyroidDisease;
    }

    @Column(name = "thyroid_disease_note", length = 65535)
    public String getThyroidDiseaseNote() {
        return this.thyroidDiseaseNote;
    }

    public void setThyroidDiseaseNote(String thyroidDiseaseNote) {
        this.thyroidDiseaseNote = thyroidDiseaseNote;
    }

    @Column(name = "osteoporosis", length = 45)
    public String getOsteoporosis() {
        return this.osteoporosis;
    }

    public void setOsteoporosis(String osteoporosis) {
        this.osteoporosis = osteoporosis;
    }

    @Column(name = "osteoporosis_note", length = 65535)
    public String getOsteoporosisNote() {
        return this.osteoporosisNote;
    }

    public void setOsteoporosisNote(String osteoporosisNote) {
        this.osteoporosisNote = osteoporosisNote;
    }

    @Column(name = "stroke", length = 45)
    public String getStroke() {
        return this.stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    @Column(name = "stroke_note", length = 65535)
    public String getStrokeNote() {
        return this.strokeNote;
    }

    public void setStrokeNote(String strokeNote) {
        this.strokeNote = strokeNote;
    }

    @Column(name = "cardiovascular_disease", length = 45)
    public String getCardiovascularDisease() {
        return this.cardiovascularDisease;
    }

    public void setCardiovascularDisease(String cardiovascularDisease) {
        this.cardiovascularDisease = cardiovascularDisease;
    }

    @Column(name = "cardiovascular_disease_note", length = 65535)
    public String getCardiovascularDiseaseNote() {
        return this.cardiovascularDiseaseNote;
    }

    public void setCardiovascularDiseaseNote(String cardiovascularDiseaseNote) {
        this.cardiovascularDiseaseNote = cardiovascularDiseaseNote;
    }

    @Column(name = "smoking", length = 45)
    public String getSmoking() {
        return this.smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    @Column(name = "smoking_note", length = 65535)
    public String getSmokingNote() {
        return this.smokingNote;
    }

    public void setSmokingNote(String smokingNote) {
        this.smokingNote = smokingNote;
    }

    @Column(name = "anaemia", length = 45)
    public String getAnaemia() {
        return this.anaemia;
    }

    public void setAnaemia(String anaemia) {
        this.anaemia = anaemia;
    }

    @Column(name = "anaemia_note", length = 65535)
    public String getAnaemiaNote() {
        return this.anaemiaNote;
    }

    public void setAnaemiaNote(String anaemiaNote) {
        this.anaemiaNote = anaemiaNote;
    }

    @Column(name = "depression", length = 45)
    public String getDepression() {
        return this.depression;
    }

    public void setDepression(String depression) {
        this.depression = depression;
    }

    @Column(name = "depression_note", length = 65535)
    public String getDepressionNote() {
        return this.depressionNote;
    }

    public void setDepressionNote(String depressionNote) {
        this.depressionNote = depressionNote;
    }

    @Column(name = "anxiety", length = 45)
    public String getAnxiety() {
        return this.anxiety;
    }

    public void setAnxiety(String anxiety) {
        this.anxiety = anxiety;
    }

    @Column(name = "anxiety_note", length = 65535)
    public String getAnxietyNote() {
        return this.anxietyNote;
    }

    public void setAnxietyNote(String anxietyNote) {
        this.anxietyNote = anxietyNote;
    }

    @Column(name = "psychosis", length = 45)
    public String getPsychosis() {
        return this.psychosis;
    }

    public void setPsychosis(String psychosis) {
        this.psychosis = psychosis;
    }

    @Column(name = "psychosis_note", length = 65535)
    public String getPsychosisNote() {
        return this.psychosisNote;
    }

    public void setPsychosisNote(String psychosisNote) {
        this.psychosisNote = psychosisNote;
    }

    @Column(name = "other_mental_health_problem", length = 45)
    public String getOtherMentalHealthProblem() {
        return this.otherMentalHealthProblem;
    }

    public void setOtherMentalHealthProblem(String otherMentalHealthProblem) {
        this.otherMentalHealthProblem = otherMentalHealthProblem;
    }

    @Column(name = "other_mental_health_problem_note", length = 65535)
    public String getOtherMentalHealthProblemNote() {
        return this.otherMentalHealthProblemNote;
    }

    public void setOtherMentalHealthProblemNote(
            String otherMentalHealthProblemNote) {
        this.otherMentalHealthProblemNote = otherMentalHealthProblemNote;
    }

    @Column(name = "joint_hypermobility", length = 45)
    public String getJointHypermobility() {
        return this.jointHypermobility;
    }

    public void setJointHypermobility(String jointHypermobility) {
        this.jointHypermobility = jointHypermobility;
    }

    @Column(name = "joint_hypermobility_note", length = 65535)
    public String getJointHypermobilityNote() {
        return this.jointHypermobilityNote;
    }

    public void setJointHypermobilityNote(String jointHypermobilityNote) {
        this.jointHypermobilityNote = jointHypermobilityNote;
    }

    @Column(name = "other_comorbid_condition", length = 45)
    public String getOtherComorbidCondition() {
        return this.otherComorbidCondition;
    }

    public void setOtherComorbidCondition(String otherComorbidCondition) {
        this.otherComorbidCondition = otherComorbidCondition;
    }

    @Column(name = "other_comorbid_condition_note", length = 65535)
    public String getOtherComorbidConditionNote() {
        return this.otherComorbidConditionNote;
    }

    public void setOtherComorbidConditionNote(String otherComorbidConditionNote) {
        this.otherComorbidConditionNote = otherComorbidConditionNote;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "blood_test_date", length = 10)
    public Date getBloodTestDate() {
        return this.bloodTestDate;
    }

    public void setBloodTestDate(Date bloodTestDate) {
        this.bloodTestDate = bloodTestDate;
    }

    @Column(name = "sodium", length = 45)
    public String getSodium() {
        return this.sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "sodium_date", length = 10)
    public Date getSodiumDate() {
        return this.sodiumDate;
    }

    public void setSodiumDate(Date sodiumDate) {
        this.sodiumDate = sodiumDate;
    }

    @Column(name = "sodium_time", length = 8)
    public Time getSodiumTime() {
        return sodiumTime;
    }

    public void setSodiumTime(Time sodiumTime) {
        this.sodiumTime = sodiumTime;
    }

    @Column(name = "sodium_note", length = 65535)
    public String getSodiumNote() {
        return this.sodiumNote;
    }

    public void setSodiumNote(String sodiumNote) {
        this.sodiumNote = sodiumNote;
    }

    @Column(name = "potassium", length = 45)
    public String getPotassium() {
        return this.potassium;
    }

    public void setPotassium(String potassium) {
        this.potassium = potassium;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "potassium_date", length = 10)
    public Date getPotassiumDate() {
        return this.potassiumDate;
    }

    public void setPotassiumDate(Date potassiumDate) {
        this.potassiumDate = potassiumDate;
    }

    @Column(name = "potassium_time", length = 8)
    public Time getPotassiumTime() {
        return potassiumTime;
    }

    public void setPotassiumTime(Time potassiumTime) {
        this.potassiumTime = potassiumTime;
    }

    @Column(name = "potassium_note", length = 65535)
    public String getPotassiumNote() {
        return this.potassiumNote;
    }

    public void setPotassiumNote(String potassiumNote) {
        this.potassiumNote = potassiumNote;
    }

    @Column(name = "deoxycortisol11", length = 45)
    public String getDeoxycortisol11() {
        return this.deoxycortisol11;
    }

    public void setDeoxycortisol11(String deoxycortisol11) {
        this.deoxycortisol11 = deoxycortisol11;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "deoxycortisol11_date", length = 10)
    public Date getDeoxycortisol11Date() {
        return this.deoxycortisol11Date;
    }

    public void setDeoxycortisol11Date(Date deoxycortisol11Date) {
        this.deoxycortisol11Date = deoxycortisol11Date;
    }

    @Column(name = "deoxycortisol11_time", length = 8)
    public Time getDeoxycortisol11Time() {
        return deoxycortisol11Time;
    }

    public void setDeoxycortisol11Time(Time deoxycortisol11Time) {
        this.deoxycortisol11Time = deoxycortisol11Time;
    }

    @Column(name = "deoxycortisol11_note", length = 65535)
    public String getDeoxycortisol11Note() {
        return this.deoxycortisol11Note;
    }

    public void setDeoxycortisol11Note(String deoxycortisol11Note) {
        this.deoxycortisol11Note = deoxycortisol11Note;
    }

    @Column(name = "renin", length = 45)
    public String getRenin() {
        return this.renin;
    }

    public void setRenin(String renin) {
        this.renin = renin;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "renin_date", length = 10)
    public Date getReninDate() {
        return this.reninDate;
    }

    public void setReninDate(Date reninDate) {
        this.reninDate = reninDate;
    }

    @Column(name = "renin_time", length = 8)
    public Time getReninTime() {
        return reninTime;
    }

    public void setReninTime(Time reninTime) {
        this.reninTime = reninTime;
    }

    @Column(name = "renin_note", length = 65535)
    public String getReninNote() {
        return this.reninNote;
    }

    public void setReninNote(String reninNote) {
        this.reninNote = reninNote;
    }

    @Column(name = "plasma_renin_activity", length = 45)
    public String getPlasmaReninActivity() {
        return this.plasmaReninActivity;
    }

    public void setPlasmaReninActivity(String plasmaReninActivity) {
        this.plasmaReninActivity = plasmaReninActivity;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "plasma_renin_activity_date", length = 10)
    public Date getPlasmaReninActivityDate() {
        return this.plasmaReninActivityDate;
    }

    public void setPlasmaReninActivityDate(Date plasmaReninActivityDate) {
        this.plasmaReninActivityDate = plasmaReninActivityDate;
    }

    @Column(name = "plasma_renin_activity_time", length = 8)
    public Time getPlasmaReninActivityTime() {
        return plasmaReninActivityTime;
    }

    public void setPlasmaReninActivityTime(Time plasmaReninActivityTime) {
        this.plasmaReninActivityTime = plasmaReninActivityTime;
    }

    @Column(name = "plasma_renin_activity_note", length = 65535)
    public String getPlasmaReninActivityNote() {
        return this.plasmaReninActivityNote;
    }

    public void setPlasmaReninActivityNote(String plasmaReninActivityNote) {
        this.plasmaReninActivityNote = plasmaReninActivityNote;
    }

    @Column(name = "ohp17", length = 45)
    public String getOhp17() {
        return this.ohp17;
    }

    public void setOhp17(String ohp17) {
        this.ohp17 = ohp17;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ohp17_date", length = 10)
    public Date getOhp17Date() {
        return this.ohp17Date;
    }

    public void setOhp17Date(Date ohp17Date) {
        this.ohp17Date = ohp17Date;
    }

    @Column(name = "ohp17_time", length = 8)
    public Time getOhp17Time() {
        return ohp17Time;
    }

    public void setOhp17Time(Time ohp17Time) {
        this.ohp17Time = ohp17Time;
    }

    @Column(name = "ohp17_note", length = 65535)
    public String getOhp17Note() {
        return this.ohp17Note;
    }

    public void setOhp17Note(String ohp17Note) {
        this.ohp17Note = ohp17Note;
    }

    @Column(name = "andostenedione", length = 45)
    public String getAndostenedione() {
        return this.andostenedione;
    }

    public void setAndostenedione(String andostenedione) {
        this.andostenedione = andostenedione;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "andostenedione_date", length = 10)
    public Date getAndostenedioneDate() {
        return this.andostenedioneDate;
    }

    public void setAndostenedioneDate(Date andostenedioneDate) {
        this.andostenedioneDate = andostenedioneDate;
    }

    @Column(name = "andostenedione_time", length = 8)
    public Time getAndostenedioneTime() {
        return andostenedioneTime;
    }

    public void setAndostenedioneTime(Time andostenedioneTime) {
        this.andostenedioneTime = andostenedioneTime;
    }

    @Column(name = "andostenedione_note", length = 65535)
    public String getAndostenedioneNote() {
        return this.andostenedioneNote;
    }

    public void setAndostenedioneNote(String andostenedioneNote) {
        this.andostenedioneNote = andostenedioneNote;
    }

    @Column(name = "total_testosterone", length = 45)
    public String getTotalTestosterone() {
        return this.totalTestosterone;
    }

    public void setTotalTestosterone(String totalTestosterone) {
        this.totalTestosterone = totalTestosterone;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "total_testosterone_date", length = 10)
    public Date getTotalTestosteroneDate() {
        return this.totalTestosteroneDate;
    }

    public void setTotalTestosteroneDate(Date totalTestosteroneDate) {
        this.totalTestosteroneDate = totalTestosteroneDate;
    }

    @Column(name = "total_testosterone_time", length = 8)
    public Time getTotalTestosteroneTime() {
        return totalTestosteroneTime;
    }

    public void setTotalTestosteroneTime(Time totalTestosteroneTime) {
        this.totalTestosteroneTime = totalTestosteroneTime;
    }

    @Column(name = "total_testosterone_note", length = 65535)
    public String getTotalTestosteroneNote() {
        return this.totalTestosteroneNote;
    }

    public void setTotalTestosteroneNote(String totalTestosteroneNote) {
        this.totalTestosteroneNote = totalTestosteroneNote;
    }

    @Column(name = "dihydrotestosterone", length = 45)
    public String getDihydrotestosterone() {
        return this.dihydrotestosterone;
    }

    public void setDihydrotestosterone(String dihydrotestosterone) {
        this.dihydrotestosterone = dihydrotestosterone;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dihydrotestosterone_date", length = 10)
    public Date getDihydrotestosteroneDate() {
        return this.dihydrotestosteroneDate;
    }

    public void setDihydrotestosteroneDate(Date dihydrotestosteroneDate) {
        this.dihydrotestosteroneDate = dihydrotestosteroneDate;
    }

    @Column(name = "dihydrotestosterone_time", length = 8)
    public Time getDihydrotestosteroneTime() {
        return dihydrotestosteroneTime;
    }

    public void setDihydrotestosteroneTime(Time dihydrotestosteroneTime) {
        this.dihydrotestosteroneTime = dihydrotestosteroneTime;
    }

    @Column(name = "dihydrotestosterone_note", length = 65535)
    public String getDihydrotestosteroneNote() {
        return this.dihydrotestosteroneNote;
    }

    public void setDihydrotestosteroneNote(String dihydrotestosteroneNote) {
        this.dihydrotestosteroneNote = dihydrotestosteroneNote;
    }

    @Column(name = "lh", length = 45)
    public String getLh() {
        return this.lh;
    }

    public void setLh(String lh) {
        this.lh = lh;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "lh_date", length = 10)
    public Date getLhDate() {
        return this.lhDate;
    }

    public void setLhDate(Date lhDate) {
        this.lhDate = lhDate;
    }

    @Column(name = "lh_time", length = 8)
    public Time getLhTime() {
        return lhTime;
    }

    public void setLhTime(Time lhTime) {
        this.lhTime = lhTime;
    }

    @Column(name = "lh_note", length = 65535)
    public String getLhNote() {
        return this.lhNote;
    }

    public void setLhNote(String lhNote) {
        this.lhNote = lhNote;
    }

    @Column(name = "fsh", length = 45)
    public String getFsh() {
        return this.fsh;
    }

    public void setFsh(String fsh) {
        this.fsh = fsh;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fsh_date", length = 10)
    public Date getFshDate() {
        return this.fshDate;
    }

    public void setFshDate(Date fshDate) {
        this.fshDate = fshDate;
    }

    @Column(name = "fsh_time", length = 8)
    public Time getFshTime() {
        return fshTime;
    }

    public void setFshTime(Time fshTime) {
        this.fshTime = fshTime;
    }

    @Column(name = "fsh_note", length = 65535)
    public String getFshNote() {
        return this.fshNote;
    }

    public void setFshNote(String fshNote) {
        this.fshNote = fshNote;
    }

    @Column(name = "oestradiol", length = 45)
    public String getOestradiol() {
        return this.oestradiol;
    }

    public void setOestradiol(String oestradiol) {
        this.oestradiol = oestradiol;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "oestradiol_date", length = 10)
    public Date getOestradiolDate() {
        return this.oestradiolDate;
    }

    public void setOestradiolDate(Date oestradiolDate) {
        this.oestradiolDate = oestradiolDate;
    }

    @Column(name = "oestradiol_time", length = 8)
    public Time getOestradiolTime() {
        return oestradiolTime;
    }

    public void setOestradiolTime(Time oestradiolTime) {
        this.oestradiolTime = oestradiolTime;
    }

    @Column(name = "oestradiol_note", length = 65535)
    public String getOestradiolNote() {
        return this.oestradiolNote;
    }

    public void setOestradiolNote(String oestradiolNote) {
        this.oestradiolNote = oestradiolNote;
    }

    @Column(name = "progesterone", length = 45)
    public String getProgesterone() {
        return this.progesterone;
    }

    public void setProgesterone(String progesterone) {
        this.progesterone = progesterone;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "progesterone_date", length = 10)
    public Date getProgesteroneDate() {
        return this.progesteroneDate;
    }

    public void setProgesteroneDate(Date progesteroneDate) {
        this.progesteroneDate = progesteroneDate;
    }

    @Column(name = "progesterone_time", length = 8)
    public Time getProgesteroneTime() {
        return progesteroneTime;
    }

    public void setProgesteroneTime(Time progesteroneTime) {
        this.progesteroneTime = progesteroneTime;
    }

    @Column(name = "progesterone_note", length = 65535)
    public String getProgesteroneNote() {
        return this.progesteroneNote;
    }

    public void setProgesteroneNote(String progesteroneNote) {
        this.progesteroneNote = progesteroneNote;
    }

    @Column(name = "inhibin_b", length = 45)
    public String getInhibinB() {
        return this.inhibinB;
    }

    public void setInhibinB(String inhibinB) {
        this.inhibinB = inhibinB;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "inhibin_b_date", length = 10)
    public Date getInhibinBDate() {
        return this.inhibinBDate;
    }

    public void setInhibinBDate(Date inhibinBDate) {
        this.inhibinBDate = inhibinBDate;
    }

    @Column(name = "inhibin_b_time", length = 8)
    public Time getInhibinBTime() {
        return inhibinBTime;
    }

    public void setInhibinBTime(Time inhibinBTime) {
        this.inhibinBTime = inhibinBTime;
    }

    @Column(name = "inhibin_b_note", length = 65535)
    public String getInhibinBNote() {
        return this.inhibinBNote;
    }

    public void setInhibinBNote(String inhibinBNote) {
        this.inhibinBNote = inhibinBNote;
    }

    @Column(name = "haemoglobin", length = 45)
    public String getHaemoglobin() {
        return this.haemoglobin;
    }

    public void setHaemoglobin(String haemoglobin) {
        this.haemoglobin = haemoglobin;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "haemoglobin_date", length = 10)
    public Date getHaemoglobinDate() {
        return this.haemoglobinDate;
    }

    public void setHaemoglobinDate(Date haemoglobinDate) {
        this.haemoglobinDate = haemoglobinDate;
    }

    @Column(name = "haemoglobin_time", length = 8)
    public Time getHaemoglobinTime() {
        return haemoglobinTime;
    }

    public void setHaemoglobinTime(Time haemoglobinTime) {
        this.haemoglobinTime = haemoglobinTime;
    }

    @Column(name = "haemoglobin_note", length = 65535)
    public String getHaemoglobinNote() {
        return this.haemoglobinNote;
    }

    public void setHaemoglobinNote(String haemoglobinNote) {
        this.haemoglobinNote = haemoglobinNote;
    }

    @Column(name = "haematocrit", length = 45)
    public String getHaematocrit() {
        return this.haematocrit;
    }

    public void setHaematocrit(String haematocrit) {
        this.haematocrit = haematocrit;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "haematocrit_date", length = 10)
    public Date getHaematocritDate() {
        return this.haematocritDate;
    }

    public void setHaematocritDate(Date haematocritDate) {
        this.haematocritDate = haematocritDate;
    }

    @Column(name = "haematocrit_time", length = 8)
    public Time getHaematocritTime() {
        return haematocritTime;
    }

    public void setHaematocritTime(Time haematocritTime) {
        this.haematocritTime = haematocritTime;
    }

    @Column(name = "haematocrit_note", length = 65535)
    public String getHaematocritNote() {
        return this.haematocritNote;
    }

    public void setHaematocritNote(String haematocritNote) {
        this.haematocritNote = haematocritNote;
    }

    @Column(name = "shbg", length = 45)
    public String getShbg() {
        return this.shbg;
    }

    public void setShbg(String shbg) {
        this.shbg = shbg;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "shbg_date", length = 10)
    public Date getShbgDate() {
        return this.shbgDate;
    }

    public void setShbgDate(Date shbgDate) {
        this.shbgDate = shbgDate;
    }

    @Column(name = "shbg_time", length = 8)
    public Time getShbgTime() {
        return shbgTime;
    }

    public void setShbgTime(Time shbgTime) {
        this.shbgTime = shbgTime;
    }

    @Column(name = "shbg_note", length = 65535)
    public String getShbgNote() {
        return this.shbgNote;
    }

    public void setShbgNote(String shbgNote) {
        this.shbgNote = shbgNote;
    }

    @Column(name = "glucose", length = 45)
    public String getGlucose() {
        return glucose;
    }

    public void setGlucose(String glucose) {
        this.glucose = glucose;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "glucose_date", length = 10)
    public Date getGlucoseDate() {
        return glucoseDate;
    }

    public void setGlucoseDate(Date glucoseDate) {
        this.glucoseDate = glucoseDate;
    }

    @Column(name = "glucose_time", length = 8)
    public Time getGlucoseTime() {
        return glucoseTime;
    }

    public void setGlucoseTime(Time glucoseTime) {
        this.glucoseTime = glucoseTime;
    }

    @Column(name = "glucose_note", length = 65535)
    public String getGlucoseNote() {
        return glucoseNote;
    }

    public void setGlucoseNote(String glucoseNote) {
        this.glucoseNote = glucoseNote;
    }

    @Column(name = "acth", length = 45)
    public String getActh() {
        return acth;
    }

    public void setActh(String acth) {
        this.acth = acth;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "acth_date", length = 10)
    public Date getActhDate() {
        return acthDate;
    }

    public void setActhDate(Date acthDate) {
        this.acthDate = acthDate;
    }

    @Column(name = "acth_time", length = 8)
    public Time getActhTime() {
        return acthTime;
    }

    public void setActhTime(Time acthTime) {
        this.acthTime = acthTime;
    }

    @Column(name = "acth_note", length = 65535)
    public String getActhNote() {
        return acthNote;
    }

    public void setActhNote(String acthNote) {
        this.acthNote = acthNote;
    }

    @Column(name = "cortisol", length = 45)
    public String getCortisol() {
        return cortisol;
    }

    public void setCortisol(String cortisol) {
        this.cortisol = cortisol;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "cortisol_date", length = 10)
    public Date getCortisolDate() {
        return cortisolDate;
    }

    public void setCortisolDate(Date cortisolDate) {
        this.cortisolDate = cortisolDate;
    }

    @Column(name = "cortisol_time", length = 8)
    public Time getCortisolTime() {
        return cortisolTime;
    }

    public void setCortisolTime(Time cortisolTime) {
        this.cortisolTime = cortisolTime;
    }

    @Column(name = "cortisol_note", length = 65535)
    public String getCortisolNote() {
        return cortisolNote;
    }

    public void setCortisolNote(String cortisolNote) {
        this.cortisolNote = cortisolNote;
    }

    @Column(name = "dheas", length = 45)
    public String getDheas() {
        return dheas;
    }

    public void setDheas(String dheas) {
        this.dheas = dheas;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dheas_date", length = 10)
    public Date getDheasDate() {
        return dheasDate;
    }

    public void setDheasDate(Date dheasDate) {
        this.dheasDate = dheasDate;
    }

    @Column(name = "dheas_time", length = 8)
    public Time getDheasTime() {
        return dheasTime;
    }

    public void setDheasTime(Time dheasTime) {
        this.dheasTime = dheasTime;
    }

    @Column(name = "dheas_note", length = 65535)
    public String getDheasNote() {
        return dheasNote;
    }

    public void setDheasNote(String dheasNote) {
        this.dheasNote = dheasNote;
    }

    @Column(name = "urine_steroids_by_gcms", length = 45)
    public String getUrineSteroidsByGcms() {
        return urineSteroidsByGcms;
    }

    public void setUrineSteroidsByGcms(String urineSteroidsByGcms) {
        this.urineSteroidsByGcms = urineSteroidsByGcms;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "urine_steroids_by_gcms_date", length = 10)
    public Date getUrineSteroidsByGcmsDate() {
        return urineSteroidsByGcmsDate;
    }

    public void setUrineSteroidsByGcmsDate(Date urineSteroidsByGcmsDate) {
        this.urineSteroidsByGcmsDate = urineSteroidsByGcmsDate;
    }

    @Column(name = "urine_steroids_by_gcms_time", length = 8)
    public Time getUrineSteroidsByGcmsTime() {
        return urineSteroidsByGcmsTime;
    }

    public void setUrineSteroidsByGcmsTime(Time urineSteroidsByGcmsTime) {
        this.urineSteroidsByGcmsTime = urineSteroidsByGcmsTime;
    }

    @Column(name = "urine_steroids_by_gcms_note", length = 65535)
    public String getUrineSteroidsByGcmsNote() {
        return urineSteroidsByGcmsNote;
    }

    public void setUrineSteroidsByGcmsNote(String urineSteroidsByGcmsNote) {
        this.urineSteroidsByGcmsNote = urineSteroidsByGcmsNote;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "bone_age_test_date", length = 10)
    public Date getBoneAgeTestDate() {
        return this.boneAgeTestDate;
    }

    public void setBoneAgeTestDate(Date boneAgeTestDate) {
        this.boneAgeTestDate = boneAgeTestDate;
    }

    @Column(name = "bone_age_result", precision = 22, scale = 0)
    public Double getBoneAgeResult() {
        return this.boneAgeResult;
    }

    public void setBoneAgeResult(Double boneAgeResult) {
        this.boneAgeResult = boneAgeResult;
    }

    @Column(name = "bone_age_test_method", length = 45)
    public String getBoneAgeTestMethod() {
        return this.boneAgeTestMethod;
    }

    public void setBoneAgeTestMethod(String boneAgeTestMethod) {
        this.boneAgeTestMethod = boneAgeTestMethod;
    }

    @Column(name = "bone_mineral_density_done", length = 45)
    public String getBoneMineralDensityDone() {
        return this.boneMineralDensityDone;
    }

    public void setBoneMineralDensityDone(String boneMineralDensityDone) {
        this.boneMineralDensityDone = boneMineralDensityDone;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "bone_mineral_density_test_date", length = 10)
    public Date getBoneMineralDensityTestDate() {
        return this.boneMineralDensityTestDate;
    }

    public void setBoneMineralDensityTestDate(Date boneMineralDensityTestDate) {
        this.boneMineralDensityTestDate = boneMineralDensityTestDate;
    }

    @Column(name = "bone_mineral_density_test_result", length = 45)
    public String getBoneMineralDensityTestResult() {
        return this.boneMineralDensityTestResult;
    }

    public void setBoneMineralDensityTestResult(String boneMineralDensityTestResult) {
        this.boneMineralDensityTestResult = boneMineralDensityTestResult;
    }

    @Column(name = "bone_mineral_density_test_result_note", length = 65535)
    public String getBoneMineralDensityTestResultNote() {
        return boneMineralDensityTestResultNote;
    }

    public void setBoneMineralDensityTestResultNote(String boneMineralDensityTestResultNote) {
        this.boneMineralDensityTestResultNote = boneMineralDensityTestResultNote;
    }

    @Column(name = "menarche", length = 45)
    public String getMenarche() {
        return menarche;
    }

    public void setMenarche(String menarche) {
        this.menarche = menarche;
    }

    @Column(name = "female_breast_stage", length = 45)
    public String getFemaleBreastStage() {
        return this.femaleBreastStage;
    }

    public void setFemaleBreastStage(String femaleBreastStage) {
        this.femaleBreastStage = femaleBreastStage;
    }

    @Column(name = "female_pubic_hair_stage", length = 45)
    public String getFemalePubicHairStage() {
        return this.femalePubicHairStage;
    }

    public void setFemalePubicHairStage(String femalePubicHairStage) {
        this.femalePubicHairStage = femalePubicHairStage;
    }

    @Column(name = "female_axillary_hair_stage", length = 45)
    public String getFemaleAxillaryHairStage() {
        return this.femaleAxillaryHairStage;
    }

    public void setFemaleAxillaryHairStage(String femaleAxillaryHairStage) {
        this.femaleAxillaryHairStage = femaleAxillaryHairStage;
    }

    @Column(name = "age_at_menarche", precision = 22, scale = 0)
    public Double getAgeAtMenarche() {
        return this.ageAtMenarche;
    }

    public void setAgeAtMenarche(Double ageAtMenarche) {
        this.ageAtMenarche = ageAtMenarche;
    }

    @Column(name = "regular_menstrual_cycle", length = 45)
    public String getRegularMenstrualCycle() {
        return this.regularMenstrualCycle;
    }

    public void setRegularMenstrualCycle(String regularMenstrualCycle) {
        this.regularMenstrualCycle = regularMenstrualCycle;
    }

    @Column(name = "female_fertility_desired", length = 45)
    public String getFemaleFertilityDesired() {
        return this.femaleFertilityDesired;
    }

    public void setFemaleFertilityDesired(String femaleFertilityDesired) {
        this.femaleFertilityDesired = femaleFertilityDesired;
    }

    @Column(name = "past_pregnancy_number")
    public Integer getPastPregnancyNumber() {
        return this.pastPregnancyNumber;
    }

    public void setPastPregnancyNumber(Integer pastPregnancyNumber) {
        this.pastPregnancyNumber = pastPregnancyNumber;
    }

    @Column(name = "live_birth_number")
    public Integer getLiveBirthNumber() {
        return this.liveBirthNumber;
    }

    public void setLiveBirthNumber(Integer liveBirthNumber) {
        this.liveBirthNumber = liveBirthNumber;
    }

    @Column(name = "female_assisted_conception_history", length = 45)
    public String getFemaleAssistedConceptionHistory() {
        return this.femaleAssistedConceptionHistory;
    }

    public void setFemaleAssistedConceptionHistory(
            String femaleAssistedConceptionHistory) {
        this.femaleAssistedConceptionHistory = femaleAssistedConceptionHistory;
    }

    @Column(name = "female_assisted_conception_history_note", length = 65535)
    public String getFemaleAssistedConceptionHistoryNote() {
        return this.femaleAssistedConceptionHistoryNote;
    }

    public void setFemaleAssistedConceptionHistoryNote(
            String femaleAssistedConceptionHistoryNote) {
        this.femaleAssistedConceptionHistoryNote = femaleAssistedConceptionHistoryNote;
    }

    @Column(name = "male_genital_stage", length = 45)
    public String getMaleGenitalStage() {
        return this.maleGenitalStage;
    }

    public void setMaleGenitalStage(String maleGenitalStage) {
        this.maleGenitalStage = maleGenitalStage;
    }

    @Column(name = "male_pubic_hair_stage", length = 45)
    public String getMalePubicHairStage() {
        return this.malePubicHairStage;
    }

    public void setMalePubicHairStage(String malePubicHairStage) {
        this.malePubicHairStage = malePubicHairStage;
    }

    @Column(name = "male_axillary_hair_stage", length = 45)
    public String getMaleAxillaryHairStage() {
        return this.maleAxillaryHairStage;
    }

    public void setMaleAxillaryHairStage(String maleAxillaryHairStage) {
        this.maleAxillaryHairStage = maleAxillaryHairStage;
    }

    @Column(name = "testicular_volume_left", precision = 22, scale = 0)
    public Double getTesticularVolumeLeft() {
        return this.testicularVolumeLeft;
    }

    public void setTesticularVolumeLeft(Double testicularVolumeLeft) {
        this.testicularVolumeLeft = testicularVolumeLeft;
    }

    @Column(name = "testicular_volume_right", precision = 22, scale = 0)
    public Double getTesticularVolumeRight() {
        return this.testicularVolumeRight;
    }

    public void setTesticularVolumeRight(Double testicularVolumeRight) {
        this.testicularVolumeRight = testicularVolumeRight;
    }

    @Column(name = "testicular_texture", length = 45)
    public String getTesticularTexture() {
        return this.testicularTexture;
    }

    public void setTesticularTexture(String testicularTexture) {
        this.testicularTexture = testicularTexture;
    }

    @Column(name = "evidence_of_tart", length = 45)
    public String getEvidenceOfTart() {
        return this.evidenceOfTart;
    }

    public void setEvidenceOfTart(String evidenceOfTart) {
        this.evidenceOfTart = evidenceOfTart;
    }

    @Column(name = "evidence_of_tart_note", length = 65535)
    public String getEvidenceOfTartNote() {
        return this.evidenceOfTartNote;
    }

    public void setEvidenceOfTartNote(String evidenceOfTartNote) {
        this.evidenceOfTartNote = evidenceOfTartNote;
    }

    @Column(name = "male_fertility_desired", length = 45)
    public String getMaleFertilityDesired() {
        return this.maleFertilityDesired;
    }

    public void setMaleFertilityDesired(String maleFertilityDesired) {
        this.maleFertilityDesired = maleFertilityDesired;
    }

    @Column(name = "male_fertility_attempted", length = 45)
    public String getMaleFertilityAttempted() {
        return this.maleFertilityAttempted;
    }

    public void setMaleFertilityAttempted(String maleFertilityAttempted) {
        this.maleFertilityAttempted = maleFertilityAttempted;
    }

    @Column(name = "sperm_storage", length = 45)
    public String getSpermStorage() {
        return this.spermStorage;
    }

    public void setSpermStorage(String spermStorage) {
        this.spermStorage = spermStorage;
    }

    @Column(name = "partner_past_pregnancy_number")
    public Integer getPartnerPastPregnancyNumber() {
        return this.partnerPastPregnancyNumber;
    }

    public void setPartnerPastPregnancyNumber(Integer partnerPastPregnancyNumber) {
        this.partnerPastPregnancyNumber = partnerPastPregnancyNumber;
    }

    @Column(name = "male_assisted_conception_history", length = 45)
    public String getMaleAssistedConceptionHistory() {
        return this.maleAssistedConceptionHistory;
    }

    public void setMaleAssistedConceptionHistory(String maleAssistedConceptionHistory) {
        this.maleAssistedConceptionHistory = maleAssistedConceptionHistory;
    }

    @Column(name = "male_assisted_conception_history_note", length = 65535)
    public String getMaleAssistedConceptionHistoryNote() {
        return this.maleAssistedConceptionHistoryNote;
    }

    public void setMaleAssistedConceptionHistoryNote(String maleAssistedConceptionHistoryNote) {
        this.maleAssistedConceptionHistoryNote = maleAssistedConceptionHistoryNote;
    }

    @Column(name = "cah_visit_note", length = 65535)
    public String getCahVisitNote() {
        return cahVisitNote;
    }

    public void setCahVisitNote(String cahVisitNote) {
        this.cahVisitNote = cahVisitNote;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dsdCahVisit")
    @OrderBy("dsdCahVisitMedDetailId ASC")
    @BatchSize(size = 100)
//    @Sort(type = SortType.NATURAL)
    public Set<DsdCahVisitMedDetail> getDsdCahVisitMedDetails() {
        return this.dsdCahVisitMedDetails;
    }

    public void setDsdCahVisitMedDetails(Set<DsdCahVisitMedDetail> dsdCahVisitMedDetails) {
        this.dsdCahVisitMedDetails = dsdCahVisitMedDetails;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dsdCahVisit")
    @OrderBy("dsdCahVisitEpisodeId ASC")
    @BatchSize(size = 100)
//    @Sort(type = SortType.NATURAL)
    public Set<DsdCahVisitEpisode> getDsdCahVisitEpisodes() {
        return this.dsdCahVisitEpisodes;
    }

    public void setDsdCahVisitEpisodes(Set<DsdCahVisitEpisode> dsdCahVisitEpisodes) {
        this.dsdCahVisitEpisodes = dsdCahVisitEpisodes;
    }

    @Override
    public String toString() {
        if (this == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (this.getDsdCahVisitId() != null) {
            stringBuilder.append("dsdCahVisitId=").append(this.getDsdCahVisitId()).append(";");
        }
        if (this.getDsdCah() != null) {
            stringBuilder.append("cahId=").append(getDsdCah().getCahId()).append(";");
        }
        if (getDate() != null) {
            stringBuilder.append("date=").append(getDate()).append(";");
        }
        if (getAge() != null) {
            stringBuilder.append("age=").append(getAge()).append(";");
        }
        if (getWeight() != null) {
            stringBuilder.append("weight=").append(getWeight()).append(";");
        }
        if (getHeight() != null) {
            stringBuilder.append("height=").append(getHeight()).append(";");
        }
        if (getWaistCircumference() != null) {
            stringBuilder.append("waistCircumference=").append(getWaistCircumference()).append(";");
        }
        if (getHipCircumference() != null) {
            stringBuilder.append("hipCircumference=").append(getHipCircumference()).append(";");
        }
        if (getBmi() != null) {
            stringBuilder.append("bmi=").append(getBmi()).append(";");
        }
        if (getBsa() != null) {
            stringBuilder.append("bsa=").append(getBsa()).append(";");
        }
        if (StringUtils.isNotBlank(getCushingoid())) {
            stringBuilder.append("cushingoid=").append(getCushingoid()).append(";");
        }
        if (StringUtils.isNotBlank(getVirilisation())) {
            stringBuilder.append("virilisation=").append(getVirilisation()).append(";");
        }
        if (getBpSystolic() != null) {
            stringBuilder.append("bpSystolic=").append(getBpSystolic()).append(";");
        }
        if (getBpDiastolic() != null) {
            stringBuilder.append("bpDiastolic=").append(getBpDiastolic()).append(";");
        }
        if (getSickDayEpisodes() != null) {
            stringBuilder.append("sickDayEpisodes=").append(getSickDayEpisodes()).append(";");
        }
        if (getSickDayTotal() != null) {
            stringBuilder.append("sickDayTotal=").append(getSickDayTotal()).append(";");
        }

//        private String emergencyAdrenalCrisis;
//        private String dailyAdherenceToTherapy;
//        private String treatmentChange;
//        private String treatmentChangeReason;
//        private String treatmentChangeReasonNote;
//        private String glucocorticoid;
//        private String glucocorticoidDetail;
//        private String glucocorticoidNote;
//        private String fludrocortisone;
//        private Double fludrocortisoneDose;
//        private String fludrocortisoneFrequency;
//        private String fludrocortisoneNote;
//        private String currentGcReplacement;
//        private String saltReplacement;
//        private Double saltReplacementDose;
//        private String saltReplacementDoseUnit;
//        private String saltReplacementFrequency;
//        private String saltReplacementNote;
//        private String oestrogen;
//        private String oestrogenNote;
//        private String testosterone;
//        private String testosteroneNote;
//        private String gnrhAnalogues;
//        private String gnrhAnaloguesNote;
//        private String antihypertensives;
//        private String antihypertensivesNote;
//        private String antidiabetic;
//        private String antidiabeticNote;
//        private String antidepressants;
//        private String antidepressantsNote;
//        private String otherDrugs;
//        private String otherDrugsNote;
//        private String eua;
//        private String euaNote;
//        private String genitaliaSurgery;
//        private String genitaliaSurgeryNote;
//        private String otherSurgery;
//        private String otherSurgeryNote;
//        private String otherCongenitalAbnormalities;
//        private String otherCongenitalAbnormalitiesNote;
//        private String diabetesMellitusType1;
//        private String diabetesMellitusType1Note;
//        private String diabetesMellitusType2;
//        private String diabetesMellitusType2Note;
//        private String hypertension;
//        private String hypertensionNote;
//        private String thyroidDisease;
//        private String thyroidDiseaseNote;
//        private String osteoporosis;
//        private String osteoporosisNote;
//        private String stroke;
//        private String strokeNote;
//        private String cardiovascularDisease;
//        private String cardiovascularDiseaseNote;
//        private String smoking;
//        private String smokingNote;
//        private String anaemia;
//        private String anaemiaNote;
//        private String depression;
//        private String depressionNote;
//        private String anxiety;
//        private String anxietyNote;
//        private String psychosis;
//        private String psychosisNote;
//        private String otherMentalHealthProblem;
//        private String otherMentalHealthProblemNote;
//        private String jointHypermobility;
//        private String jointHypermobilityNote;
//        private String otherComorbidCondition;
//        private String otherComorbidConditionNote;
//        private Date bloodTestDate;
//        private String sodium;
//        private Date sodiumDate;
//        private Time sodiumTime;
//        private String sodiumNote;
//        private String potassium;
//        private Date potassiumDate;
//        private Time potassiumTime;
//        private String potassiumNote;
//        private String deoxycortisol11;
//        private Date deoxycortisol11Date;
//        private Time deoxycortisol11Time;
//        private String deoxycortisol11Note;
//        private String renin;
//        private Date reninDate;
//        private Time reninTime;
//        private String reninNote;
//        private String plasmaReninActivity;
//        private Date plasmaReninActivityDate;
//        private Time plasmaReninActivityTime;
//        private String plasmaReninActivityNote;
//        private String ohp17;
//        private Date ohp17Date;
//        private Time ohp17Time;
//        private String ohp17Note;
//        private String andostenedione;
//        private Date andostenedioneDate;
//        private Time andostenedioneTime;
//        private String andostenedioneNote;
//        private String totalTestosterone;
//        private Date totalTestosteroneDate;
//        private Time totalTestosteroneTime;
//        private String totalTestosteroneNote;
//        private String dihydrotestosterone;
//        private Date dihydrotestosteroneDate;
//        private Time dihydrotestosteroneTime;
//        private String dihydrotestosteroneNote;
//        private String lh;
//        private Date lhDate;
//        private Time lhTime;
//        private String lhNote;
//        private String fsh;
//        private Date fshDate;
//        private Time fshTime;
//        private String fshNote;
//        private String oestradiol;
//        private Date oestradiolDate;
//        private Time oestradiolTime;
//        private String oestradiolNote;
//        private String progesterone;
//        private Date progesteroneDate;
//        private Time progesteroneTime;
//        private String progesteroneNote;
//        private String inhibinB;
//        private Date inhibinBDate;
//        private Time inhibinBTime;
//        private String inhibinBNote;
//        private String haemoglobin;
//        private Date haemoglobinDate;
//        private Time haemoglobinTime;
//        private String haemoglobinNote;
//        private String haematocrit;
//        private Date haematocritDate;
//        private Time haematocritTime;
//        private String haematocritNote;
//        private String shbg;
//        private Date shbgDate;
//        private Time shbgTime;
//        private String shbgNote;
//
//        private String glucose;
//        private Date glucoseDate;
//        private Time glucoseTime;
//        private String glucoseNote;
//        private String acth;
//        private Date acthDate;
//        private Time acthTime;
//        private String acthNote;
//        private String cortisol;
//        private Date cortisolDate;
//        private Time cortisolTime;
//        private String cortisolNote;
//        private String dheas;
//        private Date dheasDate;
//        private Time dheasTime;
//        private String dheasNote;
//        private String urineSteroidsByGcms;
//        private Date urineSteroidsByGcmsDate;
//        private Time urineSteroidsByGcmsTime;
//        private String urineSteroidsByGcmsNote;
//
//        private Date boneAgeTestDate;
//        private Double boneAgeResult;
//        private String boneAgeTestMethod;
//        private String boneMineralDensityDone;
//        private Date boneMineralDensityTestDate;
//        private String boneMineralDensityTestResult;
//        private String boneMineralDensityTestResultNote;
//        private String femaleBreastStage;
//        private String femalePubicHairStage;
//        private String femaleAxillaryHairStage;
//        private String menarche;
//        private Double ageAtMenarche;
//        private String regularMenstrualCycle;
//        private String femaleFertilityDesired;
//        private Integer pastPregnancyNumber;
//        private Integer liveBirthNumber;
//        private String femaleAssistedConceptionHistory;
//        private String femaleAssistedConceptionHistoryNote;
//        private String maleGenitalStage;
//        private String malePubicHairStage;
//        private String maleAxillaryHairStage;
//        private Double testicularVolumeLeft;
//        private Double testicularVolumeRight;
//        private String testicularTexture;
//        private String evidenceOfTart;
//        private String evidenceOfTartNote;
//        private String maleFertilityDesired;
//        private String maleFertilityAttempted;
//        private String spermStorage;
//        private Integer partnerPastPregnancyNumber;
//        private String maleAssistedConceptionHistory;
//        private String maleAssistedConceptionHistoryNote;
//        private String cahVisitNote;
//        private Set<DsdCahVisitMedDetail> dsdCahVisitMedDetails = new HashSet<>(0);
//        private Set<DsdCahVisitEpisode> dsdCahVisitEpisodes = new HashSet<>(0);

        return stringBuilder.toString();
    }

    public String toLongString() {
        return "DsdCahVisit{" +
                "dsdCahVisitId=" + dsdCahVisitId +
                ", dsdCahId=" + (dsdCah == null ? "" : dsdCah.getCahId()) +
                ", date=" + date +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", waistCircumference=" + waistCircumference +
                ", hipCircumference=" + hipCircumference +
                ", bmi=" + bmi +
                ", bsa=" + bsa +
                ", cushingoid='" + cushingoid + '\'' +
                ", virilisation='" + virilisation + '\'' +
                ", bpSystolic=" + bpSystolic +
                ", bpDiastolic=" + bpDiastolic +
                ", sickDayEpisodes=" + sickDayEpisodes +
                ", sickDayTotal=" + sickDayTotal +
                ", emergencyAdrenalCrisis='" + emergencyAdrenalCrisis + '\'' +
                ", dailyAdherenceToTherapy='" + dailyAdherenceToTherapy + '\'' +
                ", treatmentChange='" + treatmentChange + '\'' +
                ", treatmentChangeReason='" + treatmentChangeReason + '\'' +
                ", treatmentChangeReasonNote='" + treatmentChangeReasonNote + '\'' +
                ", glucocorticoid='" + glucocorticoid + '\'' +
                ", glucocorticoidDetail='" + glucocorticoidDetail + '\'' +
                ", glucocorticoidNote='" + glucocorticoidNote + '\'' +
                ", fludrocortisone='" + fludrocortisone + '\'' +
                ", fludrocortisoneDose=" + fludrocortisoneDose +
                ", fludrocortisoneFrequency='" + fludrocortisoneFrequency + '\'' +
                ", fludrocortisoneNote='" + fludrocortisoneNote + '\'' +
                ", currentGcReplacement='" + currentGcReplacement + '\'' +
                ", saltReplacement='" + saltReplacement + '\'' +
                ", saltReplacementDose=" + saltReplacementDose +
                ", saltReplacementDoseUnit='" + saltReplacementDoseUnit + '\'' +
                ", saltReplacementFrequency='" + saltReplacementFrequency + '\'' +
                ", saltReplacementNote='" + saltReplacementNote + '\'' +
                ", oestrogen='" + oestrogen + '\'' +
                ", oestrogenNote='" + oestrogenNote + '\'' +
                ", testosterone='" + testosterone + '\'' +
                ", testosteroneNote='" + testosteroneNote + '\'' +
                ", gnrhAnalogues='" + gnrhAnalogues + '\'' +
                ", gnrhAnaloguesNote='" + gnrhAnaloguesNote + '\'' +
                ", antihypertensives='" + antihypertensives + '\'' +
                ", antihypertensivesNote='" + antihypertensivesNote + '\'' +
                ", antidiabetic='" + antidiabetic + '\'' +
                ", antidiabeticNote='" + antidiabeticNote + '\'' +
                ", antidepressants='" + antidepressants + '\'' +
                ", antidepressantsNote='" + antidepressantsNote + '\'' +
                ", otherDrugs='" + otherDrugs + '\'' +
                ", otherDrugsNote='" + otherDrugsNote + '\'' +
                ", eua='" + eua + '\'' +
                ", euaNote='" + euaNote + '\'' +
                ", genitaliaSurgery='" + genitaliaSurgery + '\'' +
                ", genitaliaSurgeryNote='" + genitaliaSurgeryNote + '\'' +
                ", otherSurgery='" + otherSurgery + '\'' +
                ", otherSurgeryNote='" + otherSurgeryNote + '\'' +
                ", otherCongenitalAbnormalities='" + otherCongenitalAbnormalities + '\'' +
                ", otherCongenitalAbnormalitiesNote='" + otherCongenitalAbnormalitiesNote + '\'' +
                ", diabetesMellitusType1='" + diabetesMellitusType1 + '\'' +
                ", diabetesMellitusType1Note='" + diabetesMellitusType1Note + '\'' +
                ", diabetesMellitusType2='" + diabetesMellitusType2 + '\'' +
                ", diabetesMellitusType2Note='" + diabetesMellitusType2Note + '\'' +
                ", hypertension='" + hypertension + '\'' +
                ", hypertensionNote='" + hypertensionNote + '\'' +
                ", thyroidDisease='" + thyroidDisease + '\'' +
                ", thyroidDiseaseNote='" + thyroidDiseaseNote + '\'' +
                ", osteoporosis='" + osteoporosis + '\'' +
                ", osteoporosisNote='" + osteoporosisNote + '\'' +
                ", stroke='" + stroke + '\'' +
                ", strokeNote='" + strokeNote + '\'' +
                ", cardiovascularDisease='" + cardiovascularDisease + '\'' +
                ", cardiovascularDiseaseNote='" + cardiovascularDiseaseNote + '\'' +
                ", smoking='" + smoking + '\'' +
                ", smokingNote='" + smokingNote + '\'' +
                ", anaemia='" + anaemia + '\'' +
                ", anaemiaNote='" + anaemiaNote + '\'' +
                ", depression='" + depression + '\'' +
                ", depressionNote='" + depressionNote + '\'' +
                ", anxiety='" + anxiety + '\'' +
                ", anxietyNote='" + anxietyNote + '\'' +
                ", psychosis='" + psychosis + '\'' +
                ", psychosisNote='" + psychosisNote + '\'' +
                ", otherMentalHealthProblem='" + otherMentalHealthProblem + '\'' +
                ", otherMentalHealthProblemNote='" + otherMentalHealthProblemNote + '\'' +
                ", jointHypermobility='" + jointHypermobility + '\'' +
                ", jointHypermobilityNote='" + jointHypermobilityNote + '\'' +
                ", otherComorbidCondition='" + otherComorbidCondition + '\'' +
                ", otherComorbidConditionNote='" + otherComorbidConditionNote + '\'' +
                ", bloodTestDate=" + bloodTestDate +
                ", sodium='" + sodium + '\'' +
                ", sodiumDate=" + sodiumDate +
                ", sodiumTime=" + sodiumTime +
                ", sodiumNote='" + sodiumNote + '\'' +
                ", potassium='" + potassium + '\'' +
                ", potassiumDate=" + potassiumDate +
                ", potassiumTime=" + potassiumTime +
                ", potassiumNote='" + potassiumNote + '\'' +
                ", deoxycortisol11='" + deoxycortisol11 + '\'' +
                ", deoxycortisol11Date=" + deoxycortisol11Date +
                ", deoxycortisol11Time=" + deoxycortisol11Time +
                ", deoxycortisol11Note='" + deoxycortisol11Note + '\'' +
                ", renin='" + renin + '\'' +
                ", reninDate=" + reninDate +
                ", reninTime=" + reninTime +
                ", reninNote='" + reninNote + '\'' +
                ", plasmaReninActivity='" + plasmaReninActivity + '\'' +
                ", plasmaReninActivityDate=" + plasmaReninActivityDate +
                ", plasmaReninActivityTime=" + plasmaReninActivityTime +
                ", plasmaReninActivityNote='" + plasmaReninActivityNote + '\'' +
                ", ohp17='" + ohp17 + '\'' +
                ", ohp17Date=" + ohp17Date +
                ", ohp17Time=" + ohp17Time +
                ", ohp17Note='" + ohp17Note + '\'' +
                ", andostenedione='" + andostenedione + '\'' +
                ", andostenedioneDate=" + andostenedioneDate +
                ", andostenedioneTime=" + andostenedioneTime +
                ", andostenedioneNote='" + andostenedioneNote + '\'' +
                ", totalTestosterone='" + totalTestosterone + '\'' +
                ", totalTestosteroneDate=" + totalTestosteroneDate +
                ", totalTestosteroneTime=" + totalTestosteroneTime +
                ", totalTestosteroneNote='" + totalTestosteroneNote + '\'' +
                ", dihydrotestosterone='" + dihydrotestosterone + '\'' +
                ", dihydrotestosteroneDate=" + dihydrotestosteroneDate +
                ", dihydrotestosteroneTime=" + dihydrotestosteroneTime +
                ", dihydrotestosteroneNote='" + dihydrotestosteroneNote + '\'' +
                ", lh='" + lh + '\'' +
                ", lhDate=" + lhDate +
                ", lhTime=" + lhTime +
                ", lhNote='" + lhNote + '\'' +
                ", fsh='" + fsh + '\'' +
                ", fshDate=" + fshDate +
                ", fshTime=" + fshTime +
                ", fshNote='" + fshNote + '\'' +
                ", oestradiol='" + oestradiol + '\'' +
                ", oestradiolDate=" + oestradiolDate +
                ", oestradiolTime=" + oestradiolTime +
                ", oestradiolNote='" + oestradiolNote + '\'' +
                ", progesterone='" + progesterone + '\'' +
                ", progesteroneDate=" + progesteroneDate +
                ", progesteroneTime=" + progesteroneTime +
                ", progesteroneNote='" + progesteroneNote + '\'' +
                ", inhibinB='" + inhibinB + '\'' +
                ", inhibinBDate=" + inhibinBDate +
                ", inhibinBTime=" + inhibinBTime +
                ", inhibinBNote='" + inhibinBNote + '\'' +
                ", haemoglobin='" + haemoglobin + '\'' +
                ", haemoglobinDate=" + haemoglobinDate +
                ", haemoglobinTime=" + haemoglobinTime +
                ", haemoglobinNote='" + haemoglobinNote + '\'' +
                ", haematocrit='" + haematocrit + '\'' +
                ", haematocritDate=" + haematocritDate +
                ", haematocritTime=" + haematocritTime +
                ", haematocritNote='" + haematocritNote + '\'' +
                ", shbg='" + shbg + '\'' +
                ", shbgDate=" + shbgDate +
                ", shbgTime=" + shbgTime +
                ", shbgNote='" + shbgNote + '\'' +
                ", boneAgeTestDate=" + boneAgeTestDate +
                ", boneAgeResult=" + boneAgeResult +
                ", boneAgeTestMethod='" + boneAgeTestMethod + '\'' +
                ", boneMineralDensityDone='" + boneMineralDensityDone + '\'' +
                ", boneMineralDensityTestDate=" + boneMineralDensityTestDate +
                ", boneMineralDensityTestResult='" + boneMineralDensityTestResult + '\'' +
                ", boneMineralDensityTestResultNote='" + boneMineralDensityTestResultNote + '\'' +
                ", femaleBreastStage='" + femaleBreastStage + '\'' +
                ", femalePubicHairStage='" + femalePubicHairStage + '\'' +
                ", femaleAxillaryHairStage='" + femaleAxillaryHairStage + '\'' +
                ", menarche='" + menarche + '\'' +
                ", ageAtMenarche=" + ageAtMenarche +
                ", regularMenstrualCycle='" + regularMenstrualCycle + '\'' +
                ", femaleFertilityDesired='" + femaleFertilityDesired + '\'' +
                ", pastPregnancyNumber=" + pastPregnancyNumber +
                ", liveBirthNumber=" + liveBirthNumber +
                ", femaleAssistedConceptionHistory='" + femaleAssistedConceptionHistory + '\'' +
                ", femaleAssistedConceptionHistoryNote='" + femaleAssistedConceptionHistoryNote + '\'' +
                ", maleGenitalStage='" + maleGenitalStage + '\'' +
                ", malePubicHairStage='" + malePubicHairStage + '\'' +
                ", maleAxillaryHairStage='" + maleAxillaryHairStage + '\'' +
                ", testicularVolumeLeft=" + testicularVolumeLeft +
                ", testicularVolumeRight=" + testicularVolumeRight +
                ", testicularTexture='" + testicularTexture + '\'' +
                ", evidenceOfTart='" + evidenceOfTart + '\'' +
                ", evidenceOfTartNote='" + evidenceOfTartNote + '\'' +
                ", maleFertilityDesired='" + maleFertilityDesired + '\'' +
                ", maleFertilityAttempted='" + maleFertilityAttempted + '\'' +
                ", spermStorage='" + spermStorage + '\'' +
                ", partnerPastPregnancyNumber=" + partnerPastPregnancyNumber +
                ", maleAssistedConceptionHistory='" + maleAssistedConceptionHistory + '\'' +
                ", maleAssistedConceptionHistoryNote='" + maleAssistedConceptionHistoryNote + '\'' +
                ", dsdCahVisitMedDetails=" + dsdCahVisitMedDetails +
                ", dsdCahVisitEpisodes=" + dsdCahVisitEpisodes +
                '}';
    }

    @Override
    public int compareTo(DsdCahVisit o) {
        if(getDate() != null && o != null && o.getDate() != null && getDate().compareTo(o.getDate()) != 0) {
            return getDate().compareTo(o.getDate());
        } else {
            return getDsdCahVisitId().compareTo(o.getDsdCahVisitId());
        }
    }
}