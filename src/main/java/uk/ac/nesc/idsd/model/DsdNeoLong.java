package uk.ac.nesc.idsd.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;



@Entity
@Table(name="dsd_neonatal_visit")
public class DsdNeoLong implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private Long dsdneolongId;
	private DsdIdentifier dsdIdentifier;
	Date dateOfAssessment; 
	private int ageAtAssessment;
	private String originalSexAssigned;
	private String currentGender;
	private String isTheChildBeingRaisedAs;
	private  double Weight;
	private   double Height;
	private   double Bmi;
	private   double mothersHeight;
	private double fathersHeight;
	private double midParentalHeight;
	private String associatedFeatrues;
	private String knownSyndrome;
	private double birthWeight;
	private double birthLength;
	private double birthHeadCircumfrence;
	private double gestationalAge;

	//External Phontype
	private String Meatus;
	private String leftGonadLocation; 
	private  String rightGonadLocation;   
	private String genitalTubercleLength;
	private String phallusSize;  
	//private String phallusSize;  
	private String  labioscrotalFusion;  
	private  double anogenitalDistance;  
	private  double  anoscrotalDistance;  
	private   double externalMasculinisationScore; 
	//autopopulate
	private      double  externalGenitaliaScore ;
	//Internal Phenotyp

	private String  imagingModalityLeftGonad;
	private String    imagingModalityRightGonad;
	private String   leftGonadMorphology;
	private String   rightGonadMorphology;
	private String  imagingModalityUterus;
	private String  uterusMorphology;
	private String  imagingModalityLeftFallopianTube;
	private String    imagingModalityRightFallopianTube;
	private String    leftFallopianTubeMorphology;
	private String   rightFallopianTubeMorphology;
	private String    imagingModalityLeftVasDeferens;
	private String   imagingModalityRightVasDeferens;
	private String   leftVasDeferensMorphology;
	private String  rightVasDeferensMorphology;
	private double    distanceVaginalConfluenceToBladderNeck;
	private double    distanceVaginalConfluenceToIntroitus;

	//Psychosocial

	private String changeInegalSex;
	private String psychosocialSupportForParents;
	private String Surgery;
	private String leftGonadBiospy;
	private String rightGonadBiopsy;
	private String Genitoplasty;
	//private String Genitoplasty;
	//private String Genitoplasty;
	private String leftGonadectomy;
	private String rightGonadectomy;
	private String complicationsFollowingSurgery;
	//Medication
	private String Testosterone;
	private String DHT;
	private String AromataseInhibitor;
	private String 	GnRHanalogues;
	private String Glucocorticoids;
	private String Fludrocortisone;
	private String Oestrogen;
	private String otherDrugs;
	// Lab Tests
	Date	Baseline ;
	private String LH;
	private String FSH;
	private String AMH;
	private String InhibinB;
	private String baselineAndrostenedione;
	private String baselineTotalTestosterone;
	private String freeTestosterone;
	private String baselineDihydrotestosterone;
	private String Oestradiol;
	private String baseline17OHP;
	private String urineSteroids;
	private String hcgStimulationTest;
	private String hcgAndrostenedione;
	private String hcgtotalTestosterone;
	private String hcgDihydrotestosterone;
	private String adrenalStimulationTest;
	private String adrenal17OHP;
	private String adrenal11deoxycortisol;
	private String adrenalPregnenolone;
	private String DHEA;
	private String freeText;
	
	
	public DsdNeoLong(Long dsdneolongId, DsdIdentifier dsdIdentifier, Date dateOfAssessment, int ageAtAssessment,
			String originalSexAssigned, String currentGender, String isTheChildBeingRaisedAs, double weight,
			double height, double bmi, double mothersHeight, double fathersHeight, double midParentalHeight,
			String associatedFeatrues, String knownSyndrome, double birthWeight, double birthLength,
			double birthHeadCircumfrence, double gestationalAge, String meatus, String leftGonadLocation,
			String rightGonadLocation, String genitalTubercleLength, String phallusSize, String labioscrotalFusion,
			double anogenitalDistance, double anoscrotalDistance, double externalMasculinisationScore,
			double externalGenitaliaScore, String imagingModalityLeftGonad, String imagingModalityRightGonad,
			String leftGonadMorphology, String rightGonadMorphology, String imagingModalityUterus,
			String uterusMorphology, String imagingModalityLeftFallopianTube, String imagingModalityRightFallopianTube,
			String leftFallopianTubeMorphology, String rightFallopianTubeMorphology,
			String imagingModalityLeftVasDeferens, String imagingModalityRightVasDeferens,
			String leftVasDeferensMorphology, String rightVasDeferensMorphology,
			double distanceVaginalConfluenceToBladderNeck, double distanceVaginalConfluenceToIntroitus,
			String changeInegalSex, String psychosocialSupportForParents, String surgery, String leftGonadBiospy,
			String rightGonadBiopsy, String genitoplasty, String leftGonadectomy, String rightGonadectomy,
			String complicationsFollowingSurgery, String testosterone, String dHT, String aromataseInhibitor,
			String gnRHanalogues, String glucocorticoids, String fludrocortisone, String oestrogen, String otherDrugs,
			Date baseline, String lH, String fSH, String aMH, String inhibinB, String baselineAndrostenedione,
			String baselineTotalTestosterone, String freeTestosterone, String baselineDihydrotestosterone,
			String oestradiol, String baseline17ohp, String urineSteroids, String hcgStimulationTest,
			String hcgAndrostenedione, String hcgtotalTestosterone, String hcgDihydrotestosterone,
			String adrenalStimulationTest, String adrenal17ohp, String adrenal11deoxycortisol,
			String adrenalPregnenolone, String dHEA, String freeText) {
		super();
		this.dsdneolongId = dsdneolongId;
		this.dsdIdentifier = dsdIdentifier;
		this.dateOfAssessment = dateOfAssessment;
		this.ageAtAssessment = ageAtAssessment;
		this.originalSexAssigned = originalSexAssigned;
		this.currentGender = currentGender;
		this.isTheChildBeingRaisedAs = isTheChildBeingRaisedAs;
		Weight = weight;
		Height = height;
		Bmi = bmi;
		this.mothersHeight = mothersHeight;
		this.fathersHeight = fathersHeight;
		this.midParentalHeight = midParentalHeight;
		this.associatedFeatrues = associatedFeatrues;
		this.knownSyndrome = knownSyndrome;
		this.birthWeight = birthWeight;
		this.birthLength = birthLength;
		this.birthHeadCircumfrence = birthHeadCircumfrence;
		this.gestationalAge = gestationalAge;
		Meatus = meatus;
		this.leftGonadLocation = leftGonadLocation;
		this.rightGonadLocation = rightGonadLocation;
		this.genitalTubercleLength = genitalTubercleLength;
		this.phallusSize = phallusSize;
		this.labioscrotalFusion = labioscrotalFusion;
		this.anogenitalDistance = anogenitalDistance;
		this.anoscrotalDistance = anoscrotalDistance;
		this.externalMasculinisationScore = externalMasculinisationScore;
		this.externalGenitaliaScore = externalGenitaliaScore;
		this.imagingModalityLeftGonad = imagingModalityLeftGonad;
		this.imagingModalityRightGonad = imagingModalityRightGonad;
		this.leftGonadMorphology = leftGonadMorphology;
		this.rightGonadMorphology = rightGonadMorphology;
		this.imagingModalityUterus = imagingModalityUterus;
		this.uterusMorphology = uterusMorphology;
		this.imagingModalityLeftFallopianTube = imagingModalityLeftFallopianTube;
		this.imagingModalityRightFallopianTube = imagingModalityRightFallopianTube;
		this.leftFallopianTubeMorphology = leftFallopianTubeMorphology;
		this.rightFallopianTubeMorphology = rightFallopianTubeMorphology;
		this.imagingModalityLeftVasDeferens = imagingModalityLeftVasDeferens;
		this.imagingModalityRightVasDeferens = imagingModalityRightVasDeferens;
		this.leftVasDeferensMorphology = leftVasDeferensMorphology;
		this.rightVasDeferensMorphology = rightVasDeferensMorphology;
		this.distanceVaginalConfluenceToBladderNeck = distanceVaginalConfluenceToBladderNeck;
		this.distanceVaginalConfluenceToIntroitus = distanceVaginalConfluenceToIntroitus;
		this.changeInegalSex = changeInegalSex;
		this.psychosocialSupportForParents = psychosocialSupportForParents;
		Surgery = surgery;
		this.leftGonadBiospy = leftGonadBiospy;
		this.rightGonadBiopsy = rightGonadBiopsy;
		Genitoplasty = genitoplasty;
		this.leftGonadectomy = leftGonadectomy;
		this.rightGonadectomy = rightGonadectomy;
		this.complicationsFollowingSurgery = complicationsFollowingSurgery;
		Testosterone = testosterone;
		DHT = dHT;
		AromataseInhibitor = aromataseInhibitor;
		GnRHanalogues = gnRHanalogues;
		Glucocorticoids = glucocorticoids;
		Fludrocortisone = fludrocortisone;
		Oestrogen = oestrogen;
		this.otherDrugs = otherDrugs;
		Baseline = baseline;
		LH = lH;
		FSH = fSH;
		AMH = aMH;
		InhibinB = inhibinB;
		this.baselineAndrostenedione = baselineAndrostenedione;
		this.baselineTotalTestosterone = baselineTotalTestosterone;
		this.freeTestosterone = freeTestosterone;
		this.baselineDihydrotestosterone = baselineDihydrotestosterone;
		Oestradiol = oestradiol;
		baseline17OHP = baseline17ohp;
		this.urineSteroids = urineSteroids;
		this.hcgStimulationTest = hcgStimulationTest;
		this.hcgAndrostenedione = hcgAndrostenedione;
		this.hcgtotalTestosterone = hcgtotalTestosterone;
		this.hcgDihydrotestosterone = hcgDihydrotestosterone;
		this.adrenalStimulationTest = adrenalStimulationTest;
		adrenal17OHP = adrenal17ohp;
		this.adrenal11deoxycortisol = adrenal11deoxycortisol;
		this.adrenalPregnenolone = adrenalPregnenolone;
		DHEA = dHEA;
		this.freeText = freeText;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dsd_neonatal_visit_id")
	public Long getDsdneolongId() {
		return dsdneolongId;
	}


	public void setDsdneolongId(Long dsdneolongId) {
		this.dsdneolongId = dsdneolongId;
	}


	public DsdIdentifier getDsdIdentifier() {
		return dsdIdentifier;
	}


	public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
		this.dsdIdentifier = dsdIdentifier;
	}

    @Column(name="date_of_assessment")
	public Date getDateOfAssessment() {
		return dateOfAssessment;
	}


	public void setDateOfAssessment(Date dateOfAssessment) {
		this.dateOfAssessment = dateOfAssessment;
	}


	@Column(name="age_of_assessment")
	public int getAgeAtAssessment() {
		return ageAtAssessment;
	}


	public void setAgeAtAssessment(int ageAtAssessment) {
		ageAtAssessment = ageAtAssessment;
	}

	@Column(name="original_sex_assigned ")
	public String getOriginalSexAssigned() {
		return originalSexAssigned;
	}


	public void setOriginalSexAssigned(String originalSexAssigned) {
		this.originalSexAssigned = originalSexAssigned;
	}

	@Column(name="current_gender")
	public String getCurrentGender() {
		return currentGender;
	}


	public void setCurrentGender(String currentGender) {
		this.currentGender = currentGender;
	}
      // rename this column in Database.
	@Column(name=" is_child_being_rraised_ad")
	public String getIsTheChildBeingRaisedAs() {
		return isTheChildBeingRaisedAs;
	}


	public void setIsTheChildBeingRaisedAs(String isTheChildBeingRaisedAs) {
		this.isTheChildBeingRaisedAs = isTheChildBeingRaisedAs;
	}

	@Column(name=" weight")
	public double getWeight() {
		return Weight;
	}


	public void setWeight(double weight) {
		Weight = weight;
	}

	@Column(name="height ")
	public double getHeight() {
		return Height;
	}


	public void setHeight(double height) {
		Height = height;
	}

	@Column(name="bmi ")
	public double getBmi() {
		return Bmi;
	}
	
	
	public void setBmi(double bmi) {
		Bmi = bmi;
	}

	@Column(name="mothers_height ")
	public double getMothersHeight() {
		return mothersHeight;
	}


	public void setMothersHeight(double mothersHeight) {
		this.mothersHeight = mothersHeight;
	}

	@Column(name="fathers_height ")
	public double getFathersHeight() {
		return fathersHeight;
	}


	public void setFathersHeight(double fathersHeight) {
		this.fathersHeight = fathersHeight;
	}

	@Column(name="mid_parental_height")
	public double getMidParentalHeight() {
		return midParentalHeight;
	}


	public void setMidParentalHeight(double midParentalHeight) {
		this.midParentalHeight = midParentalHeight;
	}

	@Column(name="associated_features")
	public String getAssociatedFeatrues() {
		return associatedFeatrues;
	}


	public void setAssociatedFeatrues(String associatedFeatrues) {
		this.associatedFeatrues = associatedFeatrues;
	}

	@Column(name="known_syndrome")
	public String getKnownSyndrome() {
		return knownSyndrome;
	}


	public void setKnownSyndrome(String knownSyndrome) {
		this.knownSyndrome = knownSyndrome;
	}

	@Column(name="birth_weight ")
	public double getBirthWeight() {
		return birthWeight;
	}


	public void setBirthWeight(double birthWeight) {
		this.birthWeight = birthWeight;
	}

	@Column(name="birth_length")
	public double getBirthLength() {
		return birthLength;
	}


	public void setBirthLength(double birthLength) {
		this.birthLength = birthLength;
	}

	@Column(name=" birth_head_circumference")
	public double getBirthHeadCircumfrence() {
		return birthHeadCircumfrence;
	}


	public void setBirthHeadCircumfrence(double birthHeadCircumfrence) {
		this.birthHeadCircumfrence = birthHeadCircumfrence;
	}

	@Column(name=" gestation_age ")
	public double getGestationalAge() {
		return gestationalAge;
	}


	public void setGestationalAge(double gestationalAge) {
		this.gestationalAge = gestationalAge;
	}

	@Column(name="meatus")
	public String getMeatus() {
		return Meatus;
	}


	public void setMeatus(String meatus) {
		Meatus = meatus;
	}
   
	@Column(name=" left_gonad_location")
	public String getLeftGonadLocation() {
		return leftGonadLocation;
	}


	public void setLeftGonadLocation(String leftGonadLocation) {
		this.leftGonadLocation = leftGonadLocation;
	}

	@Column(name="right_gonad_location")
	public String getRightGonadLocation() {
		return rightGonadLocation;
	}


	public void setRightGonadLocation(String rightGonadLocation) {
		this.rightGonadLocation = rightGonadLocation;
	}

	@Column(name=" genital_tubercle_length")
	public String getGenitalTubercleLength() {
		return genitalTubercleLength;
	}


	public void setGenitalTubercleLength(String genitalTubercleLength) {
		this.genitalTubercleLength = genitalTubercleLength;
	}

	@Column(name=" phallus_size_cm")
	public String getPhallusSize() {
		return phallusSize;
	}


	public void setPhallusSize(String phallusSize) {
		this.phallusSize = phallusSize;
	}
// need to implement another phallusSize here as it is in the database.
	
	
	@Column(name=" labioscrotal_fussion")
	public String getLabioscrotalFusion() {
		return labioscrotalFusion;
	}

     
	public void setLabioscrotalFusion(String labioscrotalFusion) {
		this.labioscrotalFusion = labioscrotalFusion;
	}

	@Column(name=" anogenital_distance_agn ")
	public double getAnogenitalDistance() {
		return anogenitalDistance;
	}


	public void setAnogenitalDistance(double anogenitalDistance) {
		this.anogenitalDistance = anogenitalDistance;
	}

	@Column(name=" anoscrotal_distance_asd ")
	public double getAnoscrotalDistance() {
		return anoscrotalDistance;
	}


	public void setAnoscrotalDistance(double anoscrotalDistance) {
		this.anoscrotalDistance = anoscrotalDistance;
	}

	@Column(name="external_masculinisation_score_ems")
	public double getExternalMasculinisationScore() {
		return externalMasculinisationScore;
	}


	public void setExternalMasculinisationScore(double externalMasculinisationScore) {
		this.externalMasculinisationScore = externalMasculinisationScore;
	}

	@Column(name=" external_genitalia_score_egs")
	public double getExternalGenitaliaScore() {
		return externalGenitaliaScore;
	}


	public void setExternalGenitaliaScore(double externalGenitaliaScore) {
		this.externalGenitaliaScore = externalGenitaliaScore;
	}

	@Column(name=" imaging_modality_left_gonad")
	public String getImagingModalityLeftGonad() {
		return imagingModalityLeftGonad;
	}

   
	public void setImagingModalityLeftGonad(String imagingModalityLeftGonad) {
		this.imagingModalityLeftGonad = imagingModalityLeftGonad;
	}
 
	@Column(name=" imaging_modality_right_gonad ")
	public String getImagingModalityRightGonad() {
		return imagingModalityRightGonad;
	}


	public void setImagingModalityRightGonad(String imagingModalityRightGonad) {
		this.imagingModalityRightGonad = imagingModalityRightGonad;
	}

	@Column(name=" left_gonad_morphology ")
	public String getLeftGonadMorphology() {
		return leftGonadMorphology;
	}


	public void setLeftGonadMorphology(String leftGonadMorphology) {
		this.leftGonadMorphology = leftGonadMorphology;
	}

	@Column(name=" right_gonad_morphology")
	public String getRightGonadMorphology() {
		return rightGonadMorphology;
	}


	public void setRightGonadMorphology(String rightGonadMorphology) {
		this.rightGonadMorphology = rightGonadMorphology;
	}

	@Column(name=" imaging_modality_uterus ")
	public String getImagingModalityUterus() {
		return imagingModalityUterus;
	}


	public void setImagingModalityUterus(String imagingModalityUterus) {
		this.imagingModalityUterus = imagingModalityUterus;
	}

	@Column(name=" uterus_morphology ")
	public String getUterusMorphology() {
		return uterusMorphology;
	}


	public void setUterusMorphology(String uterusMorphology) {
		this.uterusMorphology = uterusMorphology;
	}

	@Column(name=" imaging_modality_left_fallopian_tube ")
	public String getImagingModalityLeftFallopianTube() {
		return imagingModalityLeftFallopianTube;
	}


	public void setImagingModalityLeftFallopianTube(String imagingModalityLeftFallopianTube) {
		this.imagingModalityLeftFallopianTube = imagingModalityLeftFallopianTube;
	}

	@Column(name=" imaging_modality_right_fallopian_tube ")
	public String getImagingModalityRightFallopianTube() {
		return imagingModalityRightFallopianTube;
	}


	public void setImagingModalityRightFallopianTube(String imagingModalityRightFallopianTube) {
		this.imagingModalityRightFallopianTube = imagingModalityRightFallopianTube;
	}

	@Column(name="left_fallopian_tube_morphology")
	public String getLeftFallopianTubeMorphology() {
		return leftFallopianTubeMorphology;
	}


	public void setLeftFallopianTubeMorphology(String leftFallopianTubeMorphology) {
		this.leftFallopianTubeMorphology = leftFallopianTubeMorphology;
	}

	@Column(name=" right_fallopian_tube_morphology")
	public String getRightFallopianTubeMorphology() {
		return rightFallopianTubeMorphology;
	}


	public void setRightFallopianTubeMorphology(String rightFallopianTubeMorphology) {
		this.rightFallopianTubeMorphology = rightFallopianTubeMorphology;
	}

	@Column(name=" imaging_modality_left_vas_deferens ")
	public String getImagingModalityLeftVasDeferens() {
		return imagingModalityLeftVasDeferens;
	}


	public void setImagingModalityLeftVasDeferens(String imagingModalityLeftVasDeferens) {
		this.imagingModalityLeftVasDeferens = imagingModalityLeftVasDeferens;
	}

	@Column(name="imaging_modality_right_vas_deferens")
	public String getImagingModalityRightVasDeferens() {
		return imagingModalityRightVasDeferens;
	}


	public void setImagingModalityRightVasDeferens(String imagingModalityRightVasDeferens) {
		this.imagingModalityRightVasDeferens = imagingModalityRightVasDeferens;
	}

	@Column(name="left_vas_deferens_morphology ")
	public String getLeftVasDeferensMorphology() {
		return leftVasDeferensMorphology;
	}


	public void setLeftVasDeferensMorphology(String leftVasDeferensMorphology) {
		this.leftVasDeferensMorphology = leftVasDeferensMorphology;
	}

	@Column(name="  right_vas_deferens_morphology ")
	public String getRightVasDeferensMorphology() {
		return rightVasDeferensMorphology;
	}


	public void setRightVasDeferensMorphology(String rightVasDeferensMorphology) {
		this.rightVasDeferensMorphology = rightVasDeferensMorphology;
	}

	@Column(name=" distance_vaginal_confluence_to_bladder_neck ")
	public double getDistanceVaginalConfluenceToBladderNeck() {
		return distanceVaginalConfluenceToBladderNeck;
	}


	public void setDistanceVaginalConfluenceToBladderNeck(double distanceVaginalConfluenceToBladderNeck) {
		this.distanceVaginalConfluenceToBladderNeck = distanceVaginalConfluenceToBladderNeck;
	}

	@Column(name=" distance_vaginal_confluence_to_introitus ")
	public double getDistanceVaginalConfluenceToIntroitus() {
		return distanceVaginalConfluenceToIntroitus;
	}


	public void setDistanceVaginalConfluenceToIntroitus(double distanceVaginalConfluenceToIntroitus) {
		this.distanceVaginalConfluenceToIntroitus = distanceVaginalConfluenceToIntroitus;
	}

	@Column(name=" change_in_legal_sex ")
	public String getChangeInegalSex() {
		return changeInegalSex;
	}


	public void setChangeInegalSex(String changeInegalSex) {
		this.changeInegalSex = changeInegalSex;
	}

	@Column(name=" psychosocial_support_for_parents ")
	public String getPsychosocialSupportForParents() {
		return psychosocialSupportForParents;
	}


	public void setPsychosocialSupportForParents(String psychosocialSupportForParents) {
		this.psychosocialSupportForParents = psychosocialSupportForParents;
	}

	//not in database table
	public String getSurgery() {
		return Surgery;
	}


	public void setSurgery(String surgery) {
		Surgery = surgery;
	}

	@Column(name=" left_gonad_biospy ")
	public String getLeftGonadBiospy() {
		return leftGonadBiospy;
	}


	public void setLeftGonadBiospy(String leftGonadBiospy) {
		this.leftGonadBiospy = leftGonadBiospy;
	}

	@Column(name=" right_gonad_biopsy ")
	public String getRightGonadBiopsy() {
		return rightGonadBiopsy;
	}


	public void setRightGonadBiopsy(String rightGonadBiopsy) {
		this.rightGonadBiopsy = rightGonadBiopsy;
	}

	@Column(name=" genitoplasty_one ")
	public String getGenitoplasty() {
		return Genitoplasty;
	}


	public void setGenitoplasty(String genitoplasty) {
		Genitoplasty = genitoplasty;
	}
	
	// need to implement genitoplasty_one, two, three as they exist in DB


	@Column(name=" left_gonadectomy ")
	public String getLeftGonadectomy() {
		return leftGonadectomy;
	}

	
	public void setLeftGonadectomy(String leftGonadectomy) {
		this.leftGonadectomy = leftGonadectomy;
	}

	@Column(name=" right_gonadectomy")
	public String getRightGonadectomy() {
		return rightGonadectomy;
	}


	public void setRightGonadectomy(String rightGonadectomy) {
		this.rightGonadectomy = rightGonadectomy;
	}
// need to add this column in database
	public String getComplicationsFollowingSurgery() {
		return complicationsFollowingSurgery;
	}


	public void setComplicationsFollowingSurgery(String complicationsFollowingSurgery) {
		this.complicationsFollowingSurgery = complicationsFollowingSurgery;
	}

	@Column(name=" testosterone ")
	public String getTestosterone() {
		return Testosterone;
	}


	public void setTestosterone(String testosterone) {
		Testosterone = testosterone;
	}

	@Column(name=" dht ")
	public String getDHT() {
		return DHT;
	}


	public void setDHT(String dHT) {
		DHT = dHT;
	}

	@Column(name=" arromatase_inhibitor")
	public String getAromataseInhibitor() {
		return AromataseInhibitor;
	}


	public void setAromataseInhibitor(String aromataseInhibitor) {
		AromataseInhibitor = aromataseInhibitor;
	}

	@Column(name="gnrh_analogues")
	public String getGnRHanalogues() {
		return GnRHanalogues;
	}


	public void setGnRHanalogues(String gnRHanalogues) {
		GnRHanalogues = gnRHanalogues;
	}

	@Column(name="glucocorticoids")
	public String getGlucocorticoids() {
		return Glucocorticoids;
	}


	public void setGlucocorticoids(String glucocorticoids) {
		Glucocorticoids = glucocorticoids;
	}

	@Column(name=" fludrocortisone ")
	public String getFludrocortisone() {
		return Fludrocortisone;
	}


	public void setFludrocortisone(String fludrocortisone) {
		Fludrocortisone = fludrocortisone;
	}

	@Column(name=" oestrogen ")
	public String getOestrogen() {
		return Oestrogen;
	}


	public void setOestrogen(String oestrogen) {
		Oestrogen = oestrogen;
	}

	@Column(name=" other_drugs")
	public String getOtherDrugs() {
		return otherDrugs;
	}


	public void setOtherDrugs(String otherDrugs) {
		this.otherDrugs = otherDrugs;
	}

	@Column(name="baseline")
	public Date getBaseline() {
		return Baseline;
	}


	public void setBaseline(Date baseline) {
		Baseline = baseline;
	}

	@Column(name=" lh ")
	public String getLH() {
		return LH;
	}


	public void setLH(String lH) {
		LH = lH;
	}

	@Column(name="fsh")
	public String getFSH() {
		return FSH;
	}


	public void setFSH(String fSH) {
		FSH = fSH;
	}

	@Column(name=" amh ")
	public String getAMH() {
		return AMH;
	}


	public void setAMH(String aMH) {
		AMH = aMH;
	}

	@Column(name=" inhibin_b ")
	public String getInhibinB() {
		return InhibinB;
	}


	public void setInhibinB(String inhibinB) {
		InhibinB = inhibinB;
	}

	@Column(name=" androstenedione_one ")
	public String getBaselineAndrostenedione() {
		return baselineAndrostenedione;
	}


	public void setBaselineAndrostenedione(String baselineAndrostenedione) {
		this.baselineAndrostenedione = baselineAndrostenedione;
	}

	@Column(name=" total_testosterone_one ")
	public String getBaselineTotalTestosterone() {
		return baselineTotalTestosterone;
	}


	public void setBaselineTotalTestosterone(String baselineTotalTestosterone) {
		this.baselineTotalTestosterone = baselineTotalTestosterone;
	}

	@Column(name="free_testosterone")
	public String getFreeTestosterone() {
		return freeTestosterone;
	}


	public void setFreeTestosterone(String freeTestosterone) {
		this.freeTestosterone = freeTestosterone;
	}

	@Column(name="dihydrotestosterone_one")
	public String getBaselineDihydrotestosterone() {
		return baselineDihydrotestosterone;
	}


	public void setBaselineDihydrotestosterone(String baselineDihydrotestosterone) {
		this.baselineDihydrotestosterone = baselineDihydrotestosterone;
	}

	@Column(name="oestradiol ")
	public String getOestradiol() {
		return Oestradiol;
	}


	public void setOestradiol(String oestradiol) {
		Oestradiol = oestradiol;
	}

	@Column(name=" 17_ohp ")
	public String getBaseline17OHP() {
		return baseline17OHP;
	}


	public void setBaseline17OHP(String baseline17ohp) {
		baseline17OHP = baseline17ohp;
	}

	@Column(name=" urine_steroids ")
	public String getUrineSteroids() {
		return urineSteroids;
	}


	public void setUrineSteroids(String urineSteroids) {
		this.urineSteroids = urineSteroids;
	}

	@Column(name="hcg_stimulation_test")
	public String getHcgStimulationTest() {
		return hcgStimulationTest;
	}


	public void setHcgStimulationTest(String hcgStimulationTest) {
		this.hcgStimulationTest = hcgStimulationTest;
	}

	@Column(name=" androstenedione_two ")
	public String getHcgAndrostenedione() {
		return hcgAndrostenedione;
	}


	public void setHcgAndrostenedione(String hcgAndrostenedione) {
		this.hcgAndrostenedione = hcgAndrostenedione;
	}

	@Column(name=" total_testosterone_two")
	public String getHcgtotalTestosterone() {
		return hcgtotalTestosterone;
	}


	public void setHcgtotalTestosterone(String hcgtotalTestosterone) {
		this.hcgtotalTestosterone = hcgtotalTestosterone;
	}

	@Column(name=" dihydrotestosterone_two ")
	public String getHcgDihydrotestosterone() {
		return hcgDihydrotestosterone;
	}


	public void setHcgDihydrotestosterone(String hcgDihydrotestosterone) {
		this.hcgDihydrotestosterone = hcgDihydrotestosterone;
	}

	@Column(name=" adrenal_stimulation_test ")
	public String getAdrenalStimulationTest() {
		return adrenalStimulationTest;
	}


	public void setAdrenalStimulationTest(String adrenalStimulationTest) {
		this.adrenalStimulationTest = adrenalStimulationTest;
	}

	@Column(name="17_ohp_ast")
	public String getAdrenal17OHP() {
		return adrenal17OHP;
	}


	public void setAdrenal17OHP(String adrenal17ohp) {
		adrenal17OHP = adrenal17ohp;
	}

	@Column(name="  11_deoxycortisol")
	public String getAdrenal11deoxycortisol() {
		return adrenal11deoxycortisol;
	}


	public void setAdrenal11deoxycortisol(String adrenal11deoxycortisol) {
		this.adrenal11deoxycortisol = adrenal11deoxycortisol;
	}

	@Column(name=" pregnenolone")
	public String getAdrenalPregnenolone() {
		return adrenalPregnenolone;
	}


	public void setAdrenalPregnenolone(String adrenalPregnenolone) {
		this.adrenalPregnenolone = adrenalPregnenolone;
	}

	@Column(name=" dhea ")
	public String getDHEA() {
		return DHEA;
	}


	public void setDHEA(String dHEA) {
		DHEA = dHEA;
	}

	@Column(name=" free_text ")
	public String getFreeText() {
		return freeText;
	}


	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}


	@Override
	public String toString() {
		return "DsdNeoLong [dsdneolongId=" + dsdneolongId + ", dsdIdentifier=" + dsdIdentifier + ", dateOfAssessment="
				+ dateOfAssessment + ", ageAtAssessment=" + ageAtAssessment + ", originalSexAssigned="
				+ originalSexAssigned + ", currentGender=" + currentGender + ", isTheChildBeingRaisedAs="
				+ isTheChildBeingRaisedAs + ", Weight=" + Weight + ", Height=" + Height + ", Bmi=" + Bmi
				+ ", mothersHeight=" + mothersHeight + ", fathersHeight=" + fathersHeight + ", midParentalHeight="
				+ midParentalHeight + ", associatedFeatrues=" + associatedFeatrues + ", knownSyndrome=" + knownSyndrome
				+ ", birthWeight=" + birthWeight + ", birthLength=" + birthLength + ", birthHeadCircumfrence="
				+ birthHeadCircumfrence + ", gestationalAge=" + gestationalAge + ", Meatus=" + Meatus
				+ ", leftGonadLocation=" + leftGonadLocation + ", rightGonadLocation=" + rightGonadLocation
				+ ", genitalTubercleLength=" + genitalTubercleLength + ", phallusSize=" + phallusSize
				+ ", labioscrotalFusion=" + labioscrotalFusion + ", anogenitalDistance=" + anogenitalDistance
				+ ", anoscrotalDistance=" + anoscrotalDistance + ", externalMasculinisationScore="
				+ externalMasculinisationScore + ", externalGenitaliaScore=" + externalGenitaliaScore
				+ ", imagingModalityLeftGonad=" + imagingModalityLeftGonad + ", imagingModalityRightGonad="
				+ imagingModalityRightGonad + ", leftGonadMorphology=" + leftGonadMorphology + ", rightGonadMorphology="
				+ rightGonadMorphology + ", imagingModalityUterus=" + imagingModalityUterus + ", uterusMorphology="
				+ uterusMorphology + ", imagingModalityLeftFallopianTube=" + imagingModalityLeftFallopianTube
				+ ", imagingModalityRightFallopianTube=" + imagingModalityRightFallopianTube
				+ ", leftFallopianTubeMorphology=" + leftFallopianTubeMorphology + ", rightFallopianTubeMorphology="
				+ rightFallopianTubeMorphology + ", imagingModalityLeftVasDeferens=" + imagingModalityLeftVasDeferens
				+ ", imagingModalityRightVasDeferens=" + imagingModalityRightVasDeferens
				+ ", leftVasDeferensMorphology=" + leftVasDeferensMorphology + ", rightVasDeferensMorphology="
				+ rightVasDeferensMorphology + ", distanceVaginalConfluenceToBladderNeck="
				+ distanceVaginalConfluenceToBladderNeck + ", distanceVaginalConfluenceToIntroitus="
				+ distanceVaginalConfluenceToIntroitus + ", changeInegalSex=" + changeInegalSex
				+ ", psychosocialSupportForParents=" + psychosocialSupportForParents + ", Surgery=" + Surgery
				+ ", leftGonadBiospy=" + leftGonadBiospy + ", rightGonadBiopsy=" + rightGonadBiopsy + ", Genitoplasty="
				+ Genitoplasty + ", leftGonadectomy=" + leftGonadectomy + ", rightGonadectomy=" + rightGonadectomy
				+ ", complicationsFollowingSurgery=" + complicationsFollowingSurgery + ", Testosterone=" + Testosterone
				+ ", DHT=" + DHT + ", AromataseInhibitor=" + AromataseInhibitor + ", GnRHanalogues=" + GnRHanalogues
				+ ", Glucocorticoids=" + Glucocorticoids + ", Fludrocortisone=" + Fludrocortisone + ", Oestrogen="
				+ Oestrogen + ", otherDrugs=" + otherDrugs + ", Baseline=" + Baseline + ", LH=" + LH + ", FSH=" + FSH
				+ ", AMH=" + AMH + ", InhibinB=" + InhibinB + ", baselineAndrostenedione=" + baselineAndrostenedione
				+ ", baselineTotalTestosterone=" + baselineTotalTestosterone + ", freeTestosterone=" + freeTestosterone
				+ ", baselineDihydrotestosterone=" + baselineDihydrotestosterone + ", Oestradiol=" + Oestradiol
				+ ", baseline17OHP=" + baseline17OHP + ", urineSteroids=" + urineSteroids + ", hcgStimulationTest="
				+ hcgStimulationTest + ", hcgAndrostenedione=" + hcgAndrostenedione + ", hcgtotalTestosterone="
				+ hcgtotalTestosterone + ", hcgDihydrotestosterone=" + hcgDihydrotestosterone
				+ ", adrenalStimulationTest=" + adrenalStimulationTest + ", adrenal17OHP=" + adrenal17OHP
				+ ", adrenal11deoxycortisol=" + adrenal11deoxycortisol + ", adrenalPregnenolone=" + adrenalPregnenolone
				+ ", DHEA=" + DHEA + ", freeText=" + freeText + "]";
	}

	
}
