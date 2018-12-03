package uk.ac.nesc.idsd.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DsdCahVisitDaoImpl extends AbstractDao<DsdCahVisit> implements DsdCahVisitDao {
    public static final String AGE = "age";
    public static final String WEIGHT = "weight";
    public static final String HEIGHT = "height";
    public static final String WAIST_CIRCUMFERENCE = "waistCircumference";
    public static final String HIP_CIRCUMFERENCE = "hipCircumference";
    public static final String BMI = "bmi";
    public static final String BSA = "bsa";
    public static final String CUSHINGOID = "cushingoid";
    public static final String VIRILISATION = "virilisation";
    public static final String BP_SYSTOLIC = "bpSystolic";
    public static final String BP_DIASTOLIC = "bpDiastolic";
    public static final String HYPERTENSIVE = "hypertensive";
    public static final String SICK_DAY_EPISODES = "sickDayEpisodes";
    public static final String SICK_DAY_TOTAL = "sickDayTotal";
    public static final String EPISODE_NUMBER = "episodeNumber";
    public static final String EMERGENCY_ADRENAL_CRISIS = "emergencyAdrenalCrisis";
    public static final String TREATMENT_CHANGE = "treatmentChange";
    public static final String TREATMENT_CHANGE_REASON = "treatmentChangeReason";
    public static final String GLUCOCORTICOID = "glucocorticoid";
    public static final String GLUCOCORTICOID_DETAIL = "glucocorticoidDetail";
    public static final String GLUCOCORTICOID_NOTE = "glucocorticoidNote";
    public static final String FLUDROCORTISONE = "fludrocortisone";
    public static final String FLUDROCORTISONE_DOSE = "fludrocortisoneDose";
    public static final String FLUDROCORTISONE_DOSE_UNIT = "fludrocortisoneDoseUnit";
    public static final String FLUDROCORTISONE_FREQUENCY = "fludrocortisoneFrequency";
    public static final String FLUDROCORTISONE_NOTE = "fludrocortisoneNote";
    public static final String CURRENT_GC_REPLACEMENT = "currentGcReplacement";
    public static final String CURRENT_FC_TREATMENT = "currentFcTreatment";
    public static final String SALT_REPLACEMENT = "saltReplacement";
    public static final String SALT_REPLACEMENT_DOSE = "saltReplacementDose";
    public static final String SALT_REPLACEMENT_DOSE_UNIT = "saltReplacementDoseUnit";
    public static final String SALT_REPLACEMENT_FREQUENCY = "saltReplacementFrequency";
    public static final String SALT_REPLACEMENT_NOTE = "saltReplacementNote";
    public static final String OESTROGEN = "oestrogen";
    public static final String OESTROGEN_NOTE = "oestrogenNote";
    public static final String TESTOSTERONE = "testosterone";
    public static final String TESTOSTERONE_NOTE = "testosteroneNote";
    public static final String GNRH_ANALOGUES = "gnrhAnalogues";
    public static final String GNRH_ANALOGUES_NOTE = "gnrhAnaloguesNote";
    public static final String ANTIHYPERTENSIVES = "antihypertensives";
    public static final String ANTIHYPERTENSIVES_NOTE = "antihypertensivesNote";
    public static final String ANTIDIABETIC = "antidiabetic";
    public static final String ANTIDIABETIC_NOTE = "antidiabeticNote";
    public static final String ANTIDEPRESSANTS = "antidepressants";
    public static final String ANTIDEPRESSANTS_NOTE = "antidepressantsNote";
    public static final String OTHER_DRUGS = "otherDrugs";
    public static final String OTHER_DRUGS_NOTE = "otherDrugsNote";
    public static final String GENITALIA_SURGERY = "genitaliaSurgery";
    public static final String GENITALIA_SURGERY_NOTE = "genitaliaSurgeryNote";
    public static final String OTHER_SURGERY = "otherSurgery";
    public static final String OTHER_SURGERY_NOTE = "otherSurgeryNote";
    public static final String OTHER_CONGENITAL_ABNORMALITIES = "otherCongenitalAbnormalities";
    public static final String OTHER_CONGENITAL_ABNORMALITIES_NOTE = "otherCongenitalAbnormalitiesNote";
    public static final String DIABETES_MELLITUS_TYPE1 = "diabetesMellitusType1";
    public static final String DIABETES_MELLITUS_TYPE1_NOTE = "diabetesMellitusType1Note";
    public static final String DIABETES_MELLITUS_TYPE2 = "diabetesMellitusType2";
    public static final String DIABETES_MELLITUS_TYPE2_NOTE = "diabetesMellitusType2Note";
    public static final String HYPERTENSION = "hypertension";
    public static final String HYPERTENSION_NOTE = "hypertensionNote";
    public static final String THYROID_DISEASE = "thyroidDisease";
    public static final String THYROID_DISEASE_NOTE = "thyroidDiseaseNote";
    public static final String OSTEOPOROSIS = "osteoporosis";
    public static final String OSTEOPOROSIS_NOTE = "osteoporosisNote";
    public static final String STROKE = "stroke";
    public static final String STROKE_NOTE = "strokeNote";
    public static final String CARDIOVASCULAR_DISEASE = "cardiovascularDisease";
    public static final String CARDIOVASCULAR_DISEASE_NOTE = "cardiovascularDiseaseNote";
    public static final String SMOKING = "smoking";
    public static final String SMOKING_NOTE = "smokingNote";
    public static final String ANAEMIA = "anaemia";
    public static final String ANAEMIA_NOTE = "anaemiaNote";
    public static final String DEPRESSION = "depression";
    public static final String DEPRESSION_NOTE = "depressionNote";
    public static final String ANXIETY = "anxiety";
    public static final String ANXIETY_NOTE = "anxietyNote";
    public static final String PSYCHOSIS = "psychosis";
    public static final String PSYCHOSIS_NOTE = "psychosisNote";
    public static final String OTHER_MENTAL_HEALTH_PROBLEM = "otherMentalHealthProblem";
    public static final String OTHER_MENTAL_HEALTH_PROBLEM_NOTE = "otherMentalHealthProblemNote";
    public static final String JOINT_HYPERMOBILITY = "jointHypermobility";
    public static final String JOINT_HYPERMOBILITY_NOTE = "jointHypermobilityNote";
    public static final String OTHER_COMORBID_CONDITION = "otherComorbidCondition";
    public static final String OTHER_COMORBID_CONDITION_NOTE = "otherComorbidConditionNote";
    public static final String SODIUM = "sodium";
    public static final String SODIUM_NOTE = "sodiumNote";
    public static final String POTASSIUM = "potassium";
    public static final String POTASSIUM_NOTE = "potassiumNote";
    public static final String DEOXYCORTISOL11 = "deoxycortisol11";
    public static final String DEOXYCORTISOL11_NOTE = "deoxycortisol11Note";
    public static final String RENIN = "renin";
    public static final String RENIN_NOTE = "reninNote";
    public static final String PLASMA_RENIN_ACTIVITY = "plasmaReninActivity";
    public static final String PLASMA_RENIN_ACTIVITY_NOTE = "plasmaReninActivityNote";
    public static final String OHP17 = "ohp17";
    public static final String OHP17_NOTE = "ohp17Note";
    public static final String ANDOSTENEDIONE = "andostenedione";
    public static final String ANDOSTENEDIONE_NOTE = "andostenedioneNote";
    public static final String TOTAL_TESTOSTERONE = "totalTestosterone";
    public static final String TOTAL_TESTOSTERONE_NOTE = "totalTestosteroneNote";
    public static final String DIHYDROTESTOSTERONE = "dihydrotestosterone";
    public static final String DIHYDROTESTOSTERONE_NOTE = "dihydrotestosteroneNote";
    public static final String LH = "lh";
    public static final String LH_NOTE = "lhNote";
    public static final String FSH = "fsh";
    public static final String FSH_NOTE = "fshNote";
    public static final String OESTRADIOL = "oestradiol";
    public static final String OESTRADIOL_NOTE = "oestradiolNote";
    public static final String PROGESTERONE = "progesterone";
    public static final String PROGESTERONE_NOTE = "progesteroneNote";
    public static final String INHIBIN_B = "inhibinB";
    public static final String INHIBIN_BNOTE = "inhibinBNote";
    public static final String HAEMOGLOBIN = "haemoglobin";
    public static final String HAEMOGLOBIN_NOTE = "haemoglobinNote";
    public static final String HAEMATOCRIT = "haematocrit";
    public static final String HAEMATOCRIT_NOTE = "haematocritNote";
    public static final String SHBG = "shbg";
    public static final String SHBG_NOTE = "shbgNote";
    public static final String BONE_AGE_RESULT = "boneAgeResult";
    public static final String BONE_AGE_TEST_METHOD = "boneAgeTestMethod";
    public static final String BONE_MINERAL_DENSITY_DONE = "boneMineralDensityDone";
    public static final String BONE_MINERAL_DENSITY_TEST_RESULT = "boneMineralDensityTestResult";
    public static final String PREMENARCHEAL = "premenarcheal";
    public static final String FEMALE_BREAST_STAGE = "femaleBreastStage";
    public static final String FEMALE_PUBIC_HAIR_STAGE = "femalePubicHairStage";
    public static final String FEMALE_AXILLARY_HAIR_STAGE = "femaleAxillaryHairStage";
    public static final String AGE_AT_MENARCHE = "ageAtMenarche";
    public static final String REGULAR_MENSTRUAL_CYCLE = "regularMenstrualCycle";
    public static final String FEMALE_FERTILITY_DESIRED = "femaleFertilityDesired";
    public static final String PAST_PREGNANCY_NUMBER = "pastPregnancyNumber";
    public static final String LIVE_BIRTH_NUMBER = "liveBirthNumber";
    public static final String FEMALE_ASSISTED_CONCEPTION_HISTORY = "femaleAssistedConceptionHistory";
    public static final String FEMALE_ASSISTED_CONCEPTION_HISTORY_NOTE = "femaleAssistedConceptionHistoryNote";
    public static final String MALE_GENITAL_STAGE = "maleGenitalStage";
    public static final String MALE_PUBIC_HAIR_STAGE = "malePubicHairStage";
    public static final String MALE_AXILLARY_HAIR_STAGE = "maleAxillaryHairStage";
    public static final String TESTICULAR_VOLUME_LEFT = "testicularVolumeLeft";
    public static final String TESTICULAR_VOLUME_RIGHT = "testicularVolumeRight";
    public static final String TESTICULAR_TEXTURE = "testicularTexture";
    public static final String EVIDENCE_OF_TART = "evidenceOfTart";
    public static final String EVIDENCE_OF_TART_NOTE = "evidenceOfTartNote";
    public static final String MALE_FERTILITY_DESIRED = "maleFertilityDesired";
    public static final String MALE_FERTILITY_ATTEMPTED = "maleFertilityAttempted";
    public static final String SPERM_STORAGE = "spermStorage";
    public static final String PARTNER_PAST_PREGNANCY_NUMBER = "partnerPastPregnancyNumber";
    public static final String MALE_ASSISTED_CONCEPTION_HISTORY = "maleAssistedConceptionHistory";
    public static final String MALE_ASSISTED_CONCEPTION_HISTORY_NOTE = "maleAssistedConceptionHistoryNote";
    private static final Logger log = LoggerFactory.getLogger(DsdCahVisitDaoImpl.class);

    public static DsdCahVisitDaoImpl getFromApplicationContext(ApplicationContext ctx) {
        return (DsdCahVisitDaoImpl) ctx.getBean("dsdCahVisitDao");
    }

    @Override
    public List<DsdCahVisit> findByAge(Object age) {
        return findByProperty(AGE, age);
    }

    @Override
    public List<DsdCahVisit> findByWeight(Object weight) {
        return findByProperty(WEIGHT, weight);
    }

    @Override
    public List<DsdCahVisit> findByHeight(Object height) {
        return findByProperty(HEIGHT, height);
    }

    @Override
    public List<DsdCahVisit> findByWaistCircumference(Object waistCircumference) {
        return findByProperty(WAIST_CIRCUMFERENCE, waistCircumference);
    }

    @Override
    public List<DsdCahVisit> findByHipCircumference(Object hipCircumference) {
        return findByProperty(HIP_CIRCUMFERENCE, hipCircumference);
    }

    @Override
    public List<DsdCahVisit> findByBmi(Object bmi) {
        return findByProperty(BMI, bmi);
    }

    @Override
    public List<DsdCahVisit> findByBsa(Object bsa) {
        return findByProperty(BSA, bsa);
    }

    @Override
    public List<DsdCahVisit> findByCushingoid(Object cushingoid) {
        return findByProperty(CUSHINGOID, cushingoid);
    }

    @Override
    public List<DsdCahVisit> findByVirilisation(Object virilisation) {
        return findByProperty(VIRILISATION, virilisation);
    }

    @Override
    public List<DsdCahVisit> findByBpSystolic(Object bpSystolic) {
        return findByProperty(BP_SYSTOLIC, bpSystolic);
    }

    @Override
    public List<DsdCahVisit> findByBpDiastolic(Object bpDiastolic) {
        return findByProperty(BP_DIASTOLIC, bpDiastolic);
    }

    @Override
    public List<DsdCahVisit> findByHypertensive(Object hypertensive) {
        return findByProperty(HYPERTENSIVE, hypertensive);
    }

    @Override
    public List<DsdCahVisit> findBySickDayEpisodes(Object sickDayEpisodes) {
        return findByProperty(SICK_DAY_EPISODES, sickDayEpisodes);
    }

    @Override
    public List<DsdCahVisit> findBySickDayTotal(Object sickDayTotal) {
        return findByProperty(SICK_DAY_TOTAL, sickDayTotal);
    }

    @Override
    public List<DsdCahVisit> findByEpisodeNumber(Object episodeNumber) {
        return findByProperty(EPISODE_NUMBER, episodeNumber);
    }

    @Override
    public List<DsdCahVisit> findByEmergencyAdrenalCrisis(Object emergencyAdrenalCrisis) {
        return findByProperty(EMERGENCY_ADRENAL_CRISIS, emergencyAdrenalCrisis);
    }

    @Override
    public List<DsdCahVisit> findByTreatmentChange(Object treatmentChange) {
        return findByProperty(TREATMENT_CHANGE, treatmentChange);
    }

    @Override
    public List<DsdCahVisit> findByTreatmentChangeReason(Object treatmentChangeReason) {
        return findByProperty(TREATMENT_CHANGE_REASON, treatmentChangeReason);
    }

    @Override
    public List<DsdCahVisit> findByGlucocorticoid(Object glucocorticoid) {
        return findByProperty(GLUCOCORTICOID, glucocorticoid);
    }

    @Override
    public List<DsdCahVisit> findByGlucocorticoidDetail(Object glucocorticoidDetail) {
        return findByProperty(GLUCOCORTICOID_DETAIL, glucocorticoidDetail);
    }

    @Override
    public List<DsdCahVisit> findByGlucocorticoidNote(Object glucocorticoidNote) {
        return findByProperty(GLUCOCORTICOID_NOTE, glucocorticoidNote);
    }

    @Override
    public List<DsdCahVisit> findByFludrocortisone(Object fludrocortisone) {
        return findByProperty(FLUDROCORTISONE, fludrocortisone);
    }

    @Override
    public List<DsdCahVisit> findByFludrocortisoneDose(Object fludrocortisoneDose) {
        return findByProperty(FLUDROCORTISONE_DOSE, fludrocortisoneDose);
    }

    @Override
    public List<DsdCahVisit> findByFludrocortisoneDoseUnit(Object fludrocortisoneDoseUnit) {
        return findByProperty(FLUDROCORTISONE_DOSE_UNIT, fludrocortisoneDoseUnit);
    }

    @Override
    public List<DsdCahVisit> findByFludrocortisoneFrequency(Object fludrocortisoneFrequency) {
        return findByProperty(FLUDROCORTISONE_FREQUENCY, fludrocortisoneFrequency);
    }

    @Override
    public List<DsdCahVisit> findByFludrocortisoneNote(Object fludrocortisoneNote) {
        return findByProperty(FLUDROCORTISONE_NOTE, fludrocortisoneNote);
    }

    @Override
    public List<DsdCahVisit> findByCurrentGcReplacement(Object currentGcReplacement) {
        return findByProperty(CURRENT_GC_REPLACEMENT, currentGcReplacement);
    }

    @Override
    public List<DsdCahVisit> findByCurrentFcTreatment(Object currentFcTreatment) {
        return findByProperty(CURRENT_FC_TREATMENT, currentFcTreatment);
    }

    @Override
    public List<DsdCahVisit> findBySaltReplacement(Object saltReplacement) {
        return findByProperty(SALT_REPLACEMENT, saltReplacement);
    }

    @Override
    public List<DsdCahVisit> findBySaltReplacementDose(Object saltReplacementDose) {
        return findByProperty(SALT_REPLACEMENT_DOSE, saltReplacementDose);
    }

    @Override
    public List<DsdCahVisit> findBySaltReplacementDoseUnit(Object saltReplacementDoseUnit) {
        return findByProperty(SALT_REPLACEMENT_DOSE_UNIT,
                saltReplacementDoseUnit);
    }

    @Override
    public List<DsdCahVisit> findBySaltReplacementFrequency(Object saltReplacementFrequency) {
        return findByProperty(SALT_REPLACEMENT_FREQUENCY, saltReplacementFrequency);
    }

    @Override
    public List<DsdCahVisit> findBySaltReplacementNote(Object saltReplacementNote) {
        return findByProperty(SALT_REPLACEMENT_NOTE, saltReplacementNote);
    }

    @Override
    public List<DsdCahVisit> findByOestrogen(Object oestrogen) {
        return findByProperty(OESTROGEN, oestrogen);
    }

    @Override
    public List<DsdCahVisit> findByOestrogenNote(Object oestrogenNote) {
        return findByProperty(OESTROGEN_NOTE, oestrogenNote);
    }

    @Override
    public List<DsdCahVisit> findByTestosterone(Object testosterone) {
        return findByProperty(TESTOSTERONE, testosterone);
    }

    @Override
    public List<DsdCahVisit> findByTestosteroneNote(Object testosteroneNote) {
        return findByProperty(TESTOSTERONE_NOTE, testosteroneNote);
    }

    @Override
    public List<DsdCahVisit> findByGnrhAnalogues(Object gnrhAnalogues) {
        return findByProperty(GNRH_ANALOGUES, gnrhAnalogues);
    }

    @Override
    public List<DsdCahVisit> findByGnrhAnaloguesNote(Object gnrhAnaloguesNote) {
        return findByProperty(GNRH_ANALOGUES_NOTE, gnrhAnaloguesNote);
    }

    @Override
    public List<DsdCahVisit> findByAntihypertensives(Object antihypertensives) {
        return findByProperty(ANTIHYPERTENSIVES, antihypertensives);
    }

    @Override
    public List<DsdCahVisit> findByAntihypertensivesNote(Object antihypertensivesNote) {
        return findByProperty(ANTIHYPERTENSIVES_NOTE, antihypertensivesNote);
    }

    @Override
    public List<DsdCahVisit> findByAntidiabetic(Object antidiabetic) {
        return findByProperty(ANTIDIABETIC, antidiabetic);
    }

    @Override
    public List<DsdCahVisit> findByAntidiabeticNote(Object antidiabeticNote) {
        return findByProperty(ANTIDIABETIC_NOTE, antidiabeticNote);
    }

    @Override
    public List<DsdCahVisit> findByAntidepressants(Object antidepressants) {
        return findByProperty(ANTIDEPRESSANTS, antidepressants);
    }

    @Override
    public List<DsdCahVisit> findByAntidepressantsNote(Object antidepressantsNote) {
        return findByProperty(ANTIDEPRESSANTS_NOTE, antidepressantsNote);
    }

    @Override
    public List<DsdCahVisit> findByOtherDrugs(Object otherDrugs) {
        return findByProperty(OTHER_DRUGS, otherDrugs);
    }

    @Override
    public List<DsdCahVisit> findByOtherDrugsNote(Object otherDrugsNote) {
        return findByProperty(OTHER_DRUGS_NOTE, otherDrugsNote);
    }

    @Override
    public List<DsdCahVisit> findByGenitaliaSurgery(Object genitaliaSurgery) {
        return findByProperty(GENITALIA_SURGERY, genitaliaSurgery);
    }

    @Override
    public List<DsdCahVisit> findByGenitaliaSurgeryNote(Object genitaliaSurgeryNote) {
        return findByProperty(GENITALIA_SURGERY_NOTE, genitaliaSurgeryNote);
    }

    @Override
    public List<DsdCahVisit> findByOtherSurgery(Object otherSurgery) {
        return findByProperty(OTHER_SURGERY, otherSurgery);
    }

    @Override
    public List<DsdCahVisit> findByOtherSurgeryNote(Object otherSurgeryNote) {
        return findByProperty(OTHER_SURGERY_NOTE, otherSurgeryNote);
    }

    @Override
    public List<DsdCahVisit> findByOtherCongenitalAbnormalities(Object otherCongenitalAbnormalities) {
        return findByProperty(OTHER_CONGENITAL_ABNORMALITIES, otherCongenitalAbnormalities);
    }

    @Override
    public List<DsdCahVisit> findByOtherCongenitalAbnormalitiesNote(Object otherCongenitalAbnormalitiesNote) {
        return findByProperty(OTHER_CONGENITAL_ABNORMALITIES_NOTE, otherCongenitalAbnormalitiesNote);
    }

    @Override
    public List<DsdCahVisit> findByDiabetesMellitusType1(Object diabetesMellitusType1) {
        return findByProperty(DIABETES_MELLITUS_TYPE1, diabetesMellitusType1);
    }

    @Override
    public List<DsdCahVisit> findByDiabetesMellitusType1Note(Object diabetesMellitusType1Note) {
        return findByProperty(DIABETES_MELLITUS_TYPE1_NOTE, diabetesMellitusType1Note);
    }

    @Override
    public List<DsdCahVisit> findByDiabetesMellitusType2(Object diabetesMellitusType2) {
        return findByProperty(DIABETES_MELLITUS_TYPE2, diabetesMellitusType2);
    }

    @Override
    public List<DsdCahVisit> findByDiabetesMellitusType2Note(Object diabetesMellitusType2Note) {
        return findByProperty(DIABETES_MELLITUS_TYPE2_NOTE, diabetesMellitusType2Note);
    }

    @Override
    public List<DsdCahVisit> findByHypertension(Object hypertension) {
        return findByProperty(HYPERTENSION, hypertension);
    }

    @Override
    public List<DsdCahVisit> findByHypertensionNote(Object hypertensionNote) {
        return findByProperty(HYPERTENSION_NOTE, hypertensionNote);
    }

    @Override
    public List<DsdCahVisit> findByThyroidDisease(Object thyroidDisease) {
        return findByProperty(THYROID_DISEASE, thyroidDisease);
    }

    @Override
    public List<DsdCahVisit> findByThyroidDiseaseNote(Object thyroidDiseaseNote) {
        return findByProperty(THYROID_DISEASE_NOTE, thyroidDiseaseNote);
    }

    @Override
    public List<DsdCahVisit> findByOsteoporosis(Object osteoporosis) {
        return findByProperty(OSTEOPOROSIS, osteoporosis);
    }

    @Override
    public List<DsdCahVisit> findByOsteoporosisNote(Object osteoporosisNote) {
        return findByProperty(OSTEOPOROSIS_NOTE, osteoporosisNote);
    }

    @Override
    public List<DsdCahVisit> findByStroke(Object stroke) {
        return findByProperty(STROKE, stroke);
    }

    @Override
    public List<DsdCahVisit> findByStrokeNote(Object strokeNote) {
        return findByProperty(STROKE_NOTE, strokeNote);
    }

    @Override
    public List<DsdCahVisit> findByCardiovascularDisease(Object cardiovascularDisease) {
        return findByProperty(CARDIOVASCULAR_DISEASE, cardiovascularDisease);
    }

    @Override
    public List<DsdCahVisit> findByCardiovascularDiseaseNote(Object cardiovascularDiseaseNote) {
        return findByProperty(CARDIOVASCULAR_DISEASE_NOTE, cardiovascularDiseaseNote);
    }

    @Override
    public List<DsdCahVisit> findBySmoking(Object smoking) {
        return findByProperty(SMOKING, smoking);
    }

    @Override
    public List<DsdCahVisit> findBySmokingNote(Object smokingNote) {
        return findByProperty(SMOKING_NOTE, smokingNote);
    }

    @Override
    public List<DsdCahVisit> findByAnaemia(Object anaemia) {
        return findByProperty(ANAEMIA, anaemia);
    }

    @Override
    public List<DsdCahVisit> findByAnaemiaNote(Object anaemiaNote) {
        return findByProperty(ANAEMIA_NOTE, anaemiaNote);
    }

    @Override
    public List<DsdCahVisit> findByDepression(Object depression) {
        return findByProperty(DEPRESSION, depression);
    }

    @Override
    public List<DsdCahVisit> findByDepressionNote(Object depressionNote) {
        return findByProperty(DEPRESSION_NOTE, depressionNote);
    }

    @Override
    public List<DsdCahVisit> findByAnxiety(Object anxiety) {
        return findByProperty(ANXIETY, anxiety);
    }

    @Override
    public List<DsdCahVisit> findByAnxietyNote(Object anxietyNote) {
        return findByProperty(ANXIETY_NOTE, anxietyNote);
    }

    @Override
    public List<DsdCahVisit> findByPsychosis(Object psychosis) {
        return findByProperty(PSYCHOSIS, psychosis);
    }

    @Override
    public List<DsdCahVisit> findByPsychosisNote(Object psychosisNote) {
        return findByProperty(PSYCHOSIS_NOTE, psychosisNote);
    }

    @Override
    public List<DsdCahVisit> findByOtherMentalHealthProblem(Object otherMentalHealthProblem) {
        return findByProperty(OTHER_MENTAL_HEALTH_PROBLEM,
                otherMentalHealthProblem);
    }

    @Override
    public List<DsdCahVisit> findByOtherMentalHealthProblemNote(Object otherMentalHealthProblemNote) {
        return findByProperty(OTHER_MENTAL_HEALTH_PROBLEM_NOTE, otherMentalHealthProblemNote);
    }

    @Override
    public List<DsdCahVisit> findByJointHypermobility(Object jointHypermobility) {
        return findByProperty(JOINT_HYPERMOBILITY, jointHypermobility);
    }

    @Override
    public List<DsdCahVisit> findByJointHypermobilityNote(Object jointHypermobilityNote) {
        return findByProperty(JOINT_HYPERMOBILITY_NOTE, jointHypermobilityNote);
    }

    @Override
    public List<DsdCahVisit> findByOtherComorbidCondition(Object otherComorbidCondition) {
        return findByProperty(OTHER_COMORBID_CONDITION, otherComorbidCondition);
    }

    @Override
    public List<DsdCahVisit> findByOtherComorbidConditionNote(Object otherComorbidConditionNote) {
        return findByProperty(OTHER_COMORBID_CONDITION_NOTE, otherComorbidConditionNote);
    }

    @Override
    public List<DsdCahVisit> findBySodium(Object sodium) {
        return findByProperty(SODIUM, sodium);
    }

    @Override
    public List<DsdCahVisit> findBySodiumNote(Object sodiumNote) {
        return findByProperty(SODIUM_NOTE, sodiumNote);
    }

    @Override
    public List<DsdCahVisit> findByPotassium(Object potassium) {
        return findByProperty(POTASSIUM, potassium);
    }

    @Override
    public List<DsdCahVisit> findByPotassiumNote(Object potassiumNote) {
        return findByProperty(POTASSIUM_NOTE, potassiumNote);
    }

    @Override
    public List<DsdCahVisit> findByDeoxycortisol11(Object deoxycortisol11) {
        return findByProperty(DEOXYCORTISOL11, deoxycortisol11);
    }

    @Override
    public List<DsdCahVisit> findByDeoxycortisol11Note(Object deoxycortisol11Note) {
        return findByProperty(DEOXYCORTISOL11_NOTE, deoxycortisol11Note);
    }

    @Override
    public List<DsdCahVisit> findByRenin(Object renin) {
        return findByProperty(RENIN, renin);
    }

    @Override
    public List<DsdCahVisit> findByReninNote(Object reninNote) {
        return findByProperty(RENIN_NOTE, reninNote);
    }

    @Override
    public List<DsdCahVisit> findByPlasmaReninActivity(Object plasmaReninActivity) {
        return findByProperty(PLASMA_RENIN_ACTIVITY, plasmaReninActivity);
    }

    @Override
    public List<DsdCahVisit> findByPlasmaReninActivityNote(Object plasmaReninActivityNote) {
        return findByProperty(PLASMA_RENIN_ACTIVITY_NOTE,
                plasmaReninActivityNote);
    }

    @Override
    public List<DsdCahVisit> findByOhp17(Object ohp17) {
        return findByProperty(OHP17, ohp17);
    }

    @Override
    public List<DsdCahVisit> findByOhp17Note(Object ohp17Note) {
        return findByProperty(OHP17_NOTE, ohp17Note);
    }

    @Override
    public List<DsdCahVisit> findByAndostenedione(Object andostenedione) {
        return findByProperty(ANDOSTENEDIONE, andostenedione);
    }

    @Override
    public List<DsdCahVisit> findByAndostenedioneNote(Object andostenedioneNote) {
        return findByProperty(ANDOSTENEDIONE_NOTE, andostenedioneNote);
    }

    @Override
    public List<DsdCahVisit> findByTotalTestosterone(Object totalTestosterone) {
        return findByProperty(TOTAL_TESTOSTERONE, totalTestosterone);
    }

    @Override
    public List<DsdCahVisit> findByTotalTestosteroneNote(Object totalTestosteroneNote) {
        return findByProperty(TOTAL_TESTOSTERONE_NOTE, totalTestosteroneNote);
    }

    @Override
    public List<DsdCahVisit> findByDihydrotestosterone(Object dihydrotestosterone) {
        return findByProperty(DIHYDROTESTOSTERONE, dihydrotestosterone);
    }

    @Override
    public List<DsdCahVisit> findByDihydrotestosteroneNote(Object dihydrotestosteroneNote) {
        return findByProperty(DIHYDROTESTOSTERONE_NOTE, dihydrotestosteroneNote);
    }

    @Override
    public List<DsdCahVisit> findByLh(Object lh) {
        return findByProperty(LH, lh);
    }

    @Override
    public List<DsdCahVisit> findByLhNote(Object lhNote) {
        return findByProperty(LH_NOTE, lhNote);
    }

    @Override
    public List<DsdCahVisit> findByFsh(Object fsh) {
        return findByProperty(FSH, fsh);
    }

    @Override
    public List<DsdCahVisit> findByFshNote(Object fshNote) {
        return findByProperty(FSH_NOTE, fshNote);
    }

    @Override
    public List<DsdCahVisit> findByOestradiol(Object oestradiol) {
        return findByProperty(OESTRADIOL, oestradiol);
    }

    @Override
    public List<DsdCahVisit> findByOestradiolNote(Object oestradiolNote) {
        return findByProperty(OESTRADIOL_NOTE, oestradiolNote);
    }

    @Override
    public List<DsdCahVisit> findByProgesterone(Object progesterone) {
        return findByProperty(PROGESTERONE, progesterone);
    }

    @Override
    public List<DsdCahVisit> findByProgesteroneNote(Object progesteroneNote) {
        return findByProperty(PROGESTERONE_NOTE, progesteroneNote);
    }

    @Override
    public List<DsdCahVisit> findByInhibinB(Object inhibinB) {
        return findByProperty(INHIBIN_B, inhibinB);
    }

    @Override
    public List<DsdCahVisit> findByInhibinBNote(Object inhibinBNote) {
        return findByProperty(INHIBIN_BNOTE, inhibinBNote);
    }

    @Override
    public List<DsdCahVisit> findByHaemoglobin(Object haemoglobin) {
        return findByProperty(HAEMOGLOBIN, haemoglobin);
    }

    @Override
    public List<DsdCahVisit> findByHaemoglobinNote(Object haemoglobinNote) {
        return findByProperty(HAEMOGLOBIN_NOTE, haemoglobinNote);
    }

    @Override
    public List<DsdCahVisit> findByHaematocrit(Object haematocrit) {
        return findByProperty(HAEMATOCRIT, haematocrit);
    }

    @Override
    public List<DsdCahVisit> findByHaematocritNote(Object haematocritNote) {
        return findByProperty(HAEMATOCRIT_NOTE, haematocritNote);
    }

    @Override
    public List<DsdCahVisit> findByShbg(Object shbg) {
        return findByProperty(SHBG, shbg);
    }

    @Override
    public List<DsdCahVisit> findByShbgNote(Object shbgNote) {
        return findByProperty(SHBG_NOTE, shbgNote);
    }

    @Override
    public List<DsdCahVisit> findByBoneAgeResult(Object boneAgeResult) {
        return findByProperty(BONE_AGE_RESULT, boneAgeResult);
    }

    @Override
    public List<DsdCahVisit> findByBoneAgeTestMethod(Object boneAgeTestMethod) {
        return findByProperty(BONE_AGE_TEST_METHOD, boneAgeTestMethod);
    }

    @Override
    public List<DsdCahVisit> findByBoneMineralDensityDone(Object boneMineralDensityDone) {
        return findByProperty(BONE_MINERAL_DENSITY_DONE, boneMineralDensityDone);
    }

    @Override
    public List<DsdCahVisit> findByBoneMineralDensityTestResult(Object boneMineralDensityTestResult) {
        return findByProperty(BONE_MINERAL_DENSITY_TEST_RESULT,
                boneMineralDensityTestResult);
    }

    @Override
    public List<DsdCahVisit> findByPremenarcheal(Object premenarcheal) {
        return findByProperty(PREMENARCHEAL, premenarcheal);
    }

    @Override
    public List<DsdCahVisit> findByFemaleBreastStage(Object femaleBreastStage) {
        return findByProperty(FEMALE_BREAST_STAGE, femaleBreastStage);
    }

    @Override
    public List<DsdCahVisit> findByFemalePubicHairStage(Object femalePubicHairStage) {
        return findByProperty(FEMALE_PUBIC_HAIR_STAGE, femalePubicHairStage);
    }

    @Override
    public List<DsdCahVisit> findByFemaleAxillaryHairStage(Object femaleAxillaryHairStage) {
        return findByProperty(FEMALE_AXILLARY_HAIR_STAGE,
                femaleAxillaryHairStage);
    }

    @Override
    public List<DsdCahVisit> findByAgeAtMenarche(Object ageAtMenarche) {
        return findByProperty(AGE_AT_MENARCHE, ageAtMenarche);
    }

    @Override
    public List<DsdCahVisit> findByRegularMenstrualCycle(Object regularMenstrualCycle) {
        return findByProperty(REGULAR_MENSTRUAL_CYCLE, regularMenstrualCycle);
    }

    @Override
    public List<DsdCahVisit> findByFemaleFertilityDesired(Object femaleFertilityDesired) {
        return findByProperty(FEMALE_FERTILITY_DESIRED, femaleFertilityDesired);
    }

    @Override
    public List<DsdCahVisit> findByPastPregnancyNumber(Object pastPregnancyNumber) {
        return findByProperty(PAST_PREGNANCY_NUMBER, pastPregnancyNumber);
    }

    @Override
    public List<DsdCahVisit> findByLiveBirthNumber(Object liveBirthNumber) {
        return findByProperty(LIVE_BIRTH_NUMBER, liveBirthNumber);
    }

    @Override
    public List<DsdCahVisit> findByFemaleAssistedConceptionHistory(Object femaleAssistedConceptionHistory) {
        return findByProperty(FEMALE_ASSISTED_CONCEPTION_HISTORY,
                femaleAssistedConceptionHistory);
    }

    @Override
    public List<DsdCahVisit> findByFemaleAssistedConceptionHistoryNote(Object femaleAssistedConceptionHistoryNote) {
        return findByProperty(FEMALE_ASSISTED_CONCEPTION_HISTORY_NOTE,
                femaleAssistedConceptionHistoryNote);
    }

    @Override
    public List<DsdCahVisit> findByMaleGenitalStage(Object maleGenitalStage) {
        return findByProperty(MALE_GENITAL_STAGE, maleGenitalStage);
    }

    @Override
    public List<DsdCahVisit> findByMalePubicHairStage(Object malePubicHairStage) {
        return findByProperty(MALE_PUBIC_HAIR_STAGE, malePubicHairStage);
    }

    @Override
    public List<DsdCahVisit> findByMaleAxillaryHairStage(Object maleAxillaryHairStage) {
        return findByProperty(MALE_AXILLARY_HAIR_STAGE, maleAxillaryHairStage);
    }

    @Override
    public List<DsdCahVisit> findByTesticularVolumeLeft(Object testicularVolumeLeft) {
        return findByProperty(TESTICULAR_VOLUME_LEFT, testicularVolumeLeft);
    }

    @Override
    public List<DsdCahVisit> findByTesticularVolumeRight(Object testicularVolumeRight) {
        return findByProperty(TESTICULAR_VOLUME_RIGHT, testicularVolumeRight);
    }

    @Override
    public List<DsdCahVisit> findByTesticularTexture(Object testicularTexture) {
        return findByProperty(TESTICULAR_TEXTURE, testicularTexture);
    }

    @Override
    public List<DsdCahVisit> findByEvidenceOfTart(Object evidenceOfTart) {
        return findByProperty(EVIDENCE_OF_TART, evidenceOfTart);
    }

    @Override
    public List<DsdCahVisit> findByEvidenceOfTartNote(Object evidenceOfTartNote) {
        return findByProperty(EVIDENCE_OF_TART_NOTE, evidenceOfTartNote);
    }

    @Override
    public List<DsdCahVisit> findByMaleFertilityDesired(Object maleFertilityDesired) {
        return findByProperty(MALE_FERTILITY_DESIRED, maleFertilityDesired);
    }

    @Override
    public List<DsdCahVisit> findByMaleFertilityAttempted(Object maleFertilityAttempted) {
        return findByProperty(MALE_FERTILITY_ATTEMPTED, maleFertilityAttempted);
    }

    @Override
    public List<DsdCahVisit> findBySpermStorage(Object spermStorage) {
        return findByProperty(SPERM_STORAGE, spermStorage);
    }

    @Override
    public List<DsdCahVisit> findByPartnerPastPregnancyNumber(Object partnerPastPregnancyNumber) {
        return findByProperty(PARTNER_PAST_PREGNANCY_NUMBER,
                partnerPastPregnancyNumber);
    }

    @Override
    public List<DsdCahVisit> findByMaleAssistedConceptionHistory(Object maleAssistedConceptionHistory) {
        return findByProperty(MALE_ASSISTED_CONCEPTION_HISTORY,
                maleAssistedConceptionHistory);
    }

    @Override
    public List<DsdCahVisit> findByMaleAssistedConceptionHistoryNote(Object maleAssistedConceptionHistoryNote) {
        return findByProperty(MALE_ASSISTED_CONCEPTION_HISTORY_NOTE,
                maleAssistedConceptionHistoryNote);
    }

    @Override
    public DsdCahVisit findById(Long dsdCahVisitId) {
        return super.findById(dsdCahVisitId);
    }

	


}