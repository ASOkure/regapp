package uk.ac.nesc.idsd.model;

import java.util.List;

/**
 * Created by jiangj on 10/03/2015.
 */
public interface DsdCahVisitDao {
    void save(DsdCahVisit transientInstance);

    void delete(DsdCahVisit persistentInstance);

    List<DsdCahVisit> findByExample(DsdCahVisit instance);

    List findByProperty(String propertyName, Object value);

    List<DsdCahVisit> findByAge(Object age);

    List<DsdCahVisit> findByWeight(Object weight);

    List<DsdCahVisit> findByHeight(Object height);

    List<DsdCahVisit> findByWaistCircumference(Object waistCircumference);

    List<DsdCahVisit> findByHipCircumference(Object hipCircumference);

    List<DsdCahVisit> findByBmi(Object bmi);

    List<DsdCahVisit> findByBsa(Object bsa);

    List<DsdCahVisit> findByCushingoid(Object cushingoid);

    List<DsdCahVisit> findByVirilisation(Object virilisation);

    List<DsdCahVisit> findByBpSystolic(Object bpSystolic);

    List<DsdCahVisit> findByBpDiastolic(Object bpDiastolic);

    List<DsdCahVisit> findByHypertensive(Object hypertensive);

    List<DsdCahVisit> findBySickDayEpisodes(Object sickDayEpisodes);

    List<DsdCahVisit> findBySickDayTotal(Object sickDayTotal);

    List<DsdCahVisit> findByEpisodeNumber(Object episodeNumber);

    List<DsdCahVisit> findByEmergencyAdrenalCrisis(Object emergencyAdrenalCrisis);

    List<DsdCahVisit> findByTreatmentChange(Object treatmentChange);

    List<DsdCahVisit> findByTreatmentChangeReason(Object treatmentChangeReason);

    List<DsdCahVisit> findByGlucocorticoid(Object glucocorticoid);

    List<DsdCahVisit> findByGlucocorticoidDetail(Object glucocorticoidDetail);

    List<DsdCahVisit> findByGlucocorticoidNote(Object glucocorticoidNote);

    List<DsdCahVisit> findByFludrocortisone(Object fludrocortisone);

    List<DsdCahVisit> findByFludrocortisoneDose(Object fludrocortisoneDose);

    List<DsdCahVisit> findByFludrocortisoneDoseUnit(Object fludrocortisoneDoseUnit);

    List<DsdCahVisit> findByFludrocortisoneFrequency(Object fludrocortisoneFrequency);

    List<DsdCahVisit> findByFludrocortisoneNote(Object fludrocortisoneNote);

    List<DsdCahVisit> findByCurrentGcReplacement(Object currentGcReplacement);

    List<DsdCahVisit> findByCurrentFcTreatment(Object currentFcTreatment);

    List<DsdCahVisit> findBySaltReplacement(Object saltReplacement);

    List<DsdCahVisit> findBySaltReplacementDose(Object saltReplacementDose);

    List<DsdCahVisit> findBySaltReplacementDoseUnit(Object saltReplacementDoseUnit);

    List<DsdCahVisit> findBySaltReplacementFrequency(Object saltReplacementFrequency);

    List<DsdCahVisit> findBySaltReplacementNote(Object saltReplacementNote);

    List<DsdCahVisit> findByOestrogen(Object oestrogen);

    List<DsdCahVisit> findByOestrogenNote(Object oestrogenNote);

    List<DsdCahVisit> findByTestosterone(Object testosterone);

    List<DsdCahVisit> findByTestosteroneNote(Object testosteroneNote);

    List<DsdCahVisit> findByGnrhAnalogues(Object gnrhAnalogues);

    List<DsdCahVisit> findByGnrhAnaloguesNote(Object gnrhAnaloguesNote);

    List<DsdCahVisit> findByAntihypertensives(Object antihypertensives);

    List<DsdCahVisit> findByAntihypertensivesNote(Object antihypertensivesNote);

    List<DsdCahVisit> findByAntidiabetic(Object antidiabetic);

    List<DsdCahVisit> findByAntidiabeticNote(Object antidiabeticNote);

    List<DsdCahVisit> findByAntidepressants(Object antidepressants);

    List<DsdCahVisit> findByAntidepressantsNote(Object antidepressantsNote);

    List<DsdCahVisit> findByOtherDrugs(Object otherDrugs);

    List<DsdCahVisit> findByOtherDrugsNote(Object otherDrugsNote);

    List<DsdCahVisit> findByGenitaliaSurgery(Object genitaliaSurgery);

    List<DsdCahVisit> findByGenitaliaSurgeryNote(Object genitaliaSurgeryNote);

    List<DsdCahVisit> findByOtherSurgery(Object otherSurgery);

    List<DsdCahVisit> findByOtherSurgeryNote(Object otherSurgeryNote);

    List<DsdCahVisit> findByOtherCongenitalAbnormalities(Object otherCongenitalAbnormalities);

    List<DsdCahVisit> findByOtherCongenitalAbnormalitiesNote(Object otherCongenitalAbnormalitiesNote);

    List<DsdCahVisit> findByDiabetesMellitusType1(Object diabetesMellitusType1);

    List<DsdCahVisit> findByDiabetesMellitusType1Note(Object diabetesMellitusType1Note);

    List<DsdCahVisit> findByDiabetesMellitusType2(Object diabetesMellitusType2);

    List<DsdCahVisit> findByDiabetesMellitusType2Note(Object diabetesMellitusType2Note);

    List<DsdCahVisit> findByHypertension(Object hypertension);

    List<DsdCahVisit> findByHypertensionNote(Object hypertensionNote);

    List<DsdCahVisit> findByThyroidDisease(Object thyroidDisease);

    List<DsdCahVisit> findByThyroidDiseaseNote(Object thyroidDiseaseNote);

    List<DsdCahVisit> findByOsteoporosis(Object osteoporosis);

    List<DsdCahVisit> findByOsteoporosisNote(Object osteoporosisNote);

    List<DsdCahVisit> findByStroke(Object stroke);

    List<DsdCahVisit> findByStrokeNote(Object strokeNote);

    List<DsdCahVisit> findByCardiovascularDisease(Object cardiovascularDisease);

    List<DsdCahVisit> findByCardiovascularDiseaseNote(Object cardiovascularDiseaseNote);

    List<DsdCahVisit> findBySmoking(Object smoking);

    List<DsdCahVisit> findBySmokingNote(Object smokingNote);

    List<DsdCahVisit> findByAnaemia(Object anaemia);

    List<DsdCahVisit> findByAnaemiaNote(Object anaemiaNote);

    List<DsdCahVisit> findByDepression(Object depression);

    List<DsdCahVisit> findByDepressionNote(Object depressionNote);

    List<DsdCahVisit> findByAnxiety(Object anxiety);

    List<DsdCahVisit> findByAnxietyNote(Object anxietyNote);

    List<DsdCahVisit> findByPsychosis(Object psychosis);

    List<DsdCahVisit> findByPsychosisNote(Object psychosisNote);

    List<DsdCahVisit> findByOtherMentalHealthProblem(Object otherMentalHealthProblem);

    List<DsdCahVisit> findByOtherMentalHealthProblemNote(Object otherMentalHealthProblemNote);

    List<DsdCahVisit> findByJointHypermobility(Object jointHypermobility);

    List<DsdCahVisit> findByJointHypermobilityNote(Object jointHypermobilityNote);

    List<DsdCahVisit> findByOtherComorbidCondition(Object otherComorbidCondition);

    List<DsdCahVisit> findByOtherComorbidConditionNote(Object otherComorbidConditionNote);

    List<DsdCahVisit> findBySodium(Object sodium);

    List<DsdCahVisit> findBySodiumNote(Object sodiumNote);

    List<DsdCahVisit> findByPotassium(Object potassium);

    List<DsdCahVisit> findByPotassiumNote(Object potassiumNote);

    List<DsdCahVisit> findByDeoxycortisol11(Object deoxycortisol11);

    List<DsdCahVisit> findByDeoxycortisol11Note(Object deoxycortisol11Note);

    List<DsdCahVisit> findByRenin(Object renin);

    List<DsdCahVisit> findByReninNote(Object reninNote);

    List<DsdCahVisit> findByPlasmaReninActivity(Object plasmaReninActivity);

    List<DsdCahVisit> findByPlasmaReninActivityNote(Object plasmaReninActivityNote);

    List<DsdCahVisit> findByOhp17(Object ohp17);

    List<DsdCahVisit> findByOhp17Note(Object ohp17Note);

    List<DsdCahVisit> findByAndostenedione(Object andostenedione);

    List<DsdCahVisit> findByAndostenedioneNote(Object andostenedioneNote);

    List<DsdCahVisit> findByTotalTestosterone(Object totalTestosterone);

    List<DsdCahVisit> findByTotalTestosteroneNote(Object totalTestosteroneNote);

    List<DsdCahVisit> findByDihydrotestosterone(Object dihydrotestosterone);

    List<DsdCahVisit> findByDihydrotestosteroneNote(Object dihydrotestosteroneNote);

    List<DsdCahVisit> findByLh(Object lh);

    List<DsdCahVisit> findByLhNote(Object lhNote);

    List<DsdCahVisit> findByFsh(Object fsh);

    List<DsdCahVisit> findByFshNote(Object fshNote);

    List<DsdCahVisit> findByOestradiol(Object oestradiol);

    List<DsdCahVisit> findByOestradiolNote(Object oestradiolNote);

    List<DsdCahVisit> findByProgesterone(Object progesterone);

    List<DsdCahVisit> findByProgesteroneNote(Object progesteroneNote);

    List<DsdCahVisit> findByInhibinB(Object inhibinB);

    List<DsdCahVisit> findByInhibinBNote(Object inhibinBNote);

    List<DsdCahVisit> findByHaemoglobin(Object haemoglobin);

    List<DsdCahVisit> findByHaemoglobinNote(Object haemoglobinNote);

    List<DsdCahVisit> findByHaematocrit(Object haematocrit);

    List<DsdCahVisit> findByHaematocritNote(Object haematocritNote);

    List<DsdCahVisit> findByShbg(Object shbg);

    List<DsdCahVisit> findByShbgNote(Object shbgNote);

    List<DsdCahVisit> findByBoneAgeResult(Object boneAgeResult);

    List<DsdCahVisit> findByBoneAgeTestMethod(Object boneAgeTestMethod);

    List<DsdCahVisit> findByBoneMineralDensityDone(Object boneMineralDensityDone);

    List<DsdCahVisit> findByBoneMineralDensityTestResult(Object boneMineralDensityTestResult);

    List<DsdCahVisit> findByPremenarcheal(Object premenarcheal);

    List<DsdCahVisit> findByFemaleBreastStage(Object femaleBreastStage);

    List<DsdCahVisit> findByFemalePubicHairStage(Object femalePubicHairStage);

    List<DsdCahVisit> findByFemaleAxillaryHairStage(Object femaleAxillaryHairStage);

    List<DsdCahVisit> findByAgeAtMenarche(Object ageAtMenarche);

    List<DsdCahVisit> findByRegularMenstrualCycle(Object regularMenstrualCycle);

    List<DsdCahVisit> findByFemaleFertilityDesired(Object femaleFertilityDesired);

    List<DsdCahVisit> findByPastPregnancyNumber(Object pastPregnancyNumber);

    List<DsdCahVisit> findByLiveBirthNumber(Object liveBirthNumber);

    List<DsdCahVisit> findByFemaleAssistedConceptionHistory(Object femaleAssistedConceptionHistory);

    List<DsdCahVisit> findByFemaleAssistedConceptionHistoryNote(Object femaleAssistedConceptionHistoryNote);

    List<DsdCahVisit> findByMaleGenitalStage(Object maleGenitalStage);

    List<DsdCahVisit> findByMalePubicHairStage(Object malePubicHairStage);

    List<DsdCahVisit> findByMaleAxillaryHairStage(Object maleAxillaryHairStage);

    List<DsdCahVisit> findByTesticularVolumeLeft(Object testicularVolumeLeft);

    List<DsdCahVisit> findByTesticularVolumeRight(Object testicularVolumeRight);

    List<DsdCahVisit> findByTesticularTexture(Object testicularTexture);

    List<DsdCahVisit> findByEvidenceOfTart(Object evidenceOfTart);

    List<DsdCahVisit> findByEvidenceOfTartNote(Object evidenceOfTartNote);

    List<DsdCahVisit> findByMaleFertilityDesired(Object maleFertilityDesired);

    List<DsdCahVisit> findByMaleFertilityAttempted(Object maleFertilityAttempted);

    List<DsdCahVisit> findBySpermStorage(Object spermStorage);

    List<DsdCahVisit> findByPartnerPastPregnancyNumber(Object partnerPastPregnancyNumber);

    List<DsdCahVisit> findByMaleAssistedConceptionHistory(Object maleAssistedConceptionHistory);

    List<DsdCahVisit> findByMaleAssistedConceptionHistoryNote(Object maleAssistedConceptionHistoryNote);

    List findAll();

    DsdCahVisit merge(DsdCahVisit detachedInstance);

    void attachDirty(DsdCahVisit instance);

    void attachClean(DsdCahVisit instance);

    DsdCahVisit findById(Long dsdCahVisitId);
}
