package uk.ac.nesc.idsd.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="dsd_neonatal_visits")
public class DsdNeonatalVisits  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private  int dsd_neonatal_visit_id ;
	private  double  date_of_assessment;
	private  double age_of_assessment;  
	private  String original_sex_assigned;
	private  String current_gender; 
	private  String is_child_being_raised_as ;
	private double weight; 
	private double  height ;
	private double  bmi ;
	private double  mothers_height ;
	private double  fathers_height ;
	private double  mid_parental_height ;
	private String  associated_features ;
	private String   known_syndrome ;
	private double    birth_weight ;
	private double  birth_length ;
	private double   birth_head_circumference ;
	private String   gestation_age ;
	private String   meatus ;
	private String   left_gonad_location ;
	private String   right_gonad_location ;
	private String   genital_tubercle_length ;
	private String   phallus_size ;
	private String   labioscrotal_fussion ;
	private String   anogenital_distance_agn ;
	private String   anoscrotal_distance_asd ;
	private double   external_masculinisation_score_ems;
	private double   external_genitalia_score_egs ;
	private String  imaging_modality_left_gonad;
	private String   imaging_modality_right_gonad ;
	private String   left_gonad_morphology ;
	private String  right_gonad_morphology ; 
	private String  imaging_modality_uterus ;
	private String  uterus_morphology ;
	private String  imaging_modality_left_fallopian_tube ;
	private String  imaging_modality_right_fallopian_tube ;
	private String  left_fallopian_tube_morphology ;
	private String  right_fallopian_tube_morphology ;
	private String  imaging_modality_left_vas_deferens ;
	private String  imaging_modality_right_vas_deferens ;
	private String  left_vas_deferens_morphology ;
	private String  right_vas_deferens_morphology ;
	private String  distance_vaginal_confluence_to_bladder_neck ;
	private String  distance_vaginal_confluence_to_introitus ;
	private String  change_in_legal_sex ;
	private String  psychosocial_support_for_parents ;
	private String  left_gonad_biospy ;
	private String  right_gonad_biopsy ;
	private String  genitoplasty_one ;
	private String  genitoplasty_two ;
	private String  genitoplasty_three ;
	private String  left_gonadectomy ;
	private String  right_gonadectomy ;
	private String  Complications_following_surgery ;
	private String  testosterone ;
	private String  dht ;
	private String arromatase_inhibitor ;
	private String  glucocorticoids ;
	private String  fludrocortisone ;
	private String  oestrogen ;
	private String  other_drugs ;

	private String  lh ;
	private Date lh_date ;
	private String  fsh ;
	private Date fsd_date ;
	private String  amh ;
	private Date amh_date;
	private String  inhibin_b ;
	private Date Inhibin_b_date ;
	private String  androstenedione_one ;
	private Date androstenedione_one_date ;
	private String  total_testosterone_one ;
	private Date total_testosterone_one_date;
	private String  free_testosterone ;
	private Date free_testosterone_date;
	private String  dihydrotestosterone_one ;
	private Date dihydrotestosterone_one_date ;
	private String oestradiol ;
	private Date oestradiol_date ;
	private String ohp17 ;
	
	private Date ohp17Date ;
	private String urine_steroids ;
	private Date Urine_steroids_date ;

	private String hcg_stimulation_test ;
	private String androstenedione_two ;
	private String  total_testosterone_two ;
	private String dihydrotestosterone_two ;
	private Date dihydroterstosterone_date ;
	private String adrenal_stimulation_test ;
	private String ohp17Ast ;
	private String Deoxycortisol11 ;
	private String pregnenolone;
	private String oh17Pregnenolone ;
	private String dhea ;
	private String free_text ;
	
	
	public int getDsd_neonatal_visit_id() {
		return dsd_neonatal_visit_id;
	}
	public void setDsd_neonatal_visit_id(int dsd_neonatal_visit_id) {
		this.dsd_neonatal_visit_id = dsd_neonatal_visit_id;
	}
	public double getDate_of_assessment() {
		return date_of_assessment;
	}
	public void setDate_of_assessment(double date_of_assessment) {
		this.date_of_assessment = date_of_assessment;
	}
	public double getAge_of_assessment() {
		return age_of_assessment;
	}
	public void setAge_of_assessment(double age_of_assessment) {
		this.age_of_assessment = age_of_assessment;
	}
	public String getOriginal_sex_assigned() {
		return original_sex_assigned;
	}
	public void setOriginal_sex_assigned(String original_sex_assigned) {
		this.original_sex_assigned = original_sex_assigned;
	}
	public String getCurrent_gender() {
		return current_gender;
	}
	public void setCurrent_gender(String current_gender) {
		this.current_gender = current_gender;
	}
	public String getIs_child_being_raised_as() {
		return is_child_being_raised_as;
	}
	public void setIs_child_being_raised_as(String is_child_being_raised_as) {
		this.is_child_being_raised_as = is_child_being_raised_as;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	public double getMothers_height() {
		return mothers_height;
	}
	public void setMothers_height(double mothers_height) {
		this.mothers_height = mothers_height;
	}
	public double getFathers_height() {
		return fathers_height;
	}
	public void setFathers_height(double fathers_height) {
		this.fathers_height = fathers_height;
	}
	public double getMid_parental_height() {
		return mid_parental_height;
	}
	public void setMid_parental_height(double mid_parental_height) {
		this.mid_parental_height = mid_parental_height;
	}
	public String getAssociated_features() {
		return associated_features;
	}
	public void setAssociated_features(String associated_features) {
		this.associated_features = associated_features;
	}
	public String getKnown_syndrome() {
		return known_syndrome;
	}
	public void setKnown_syndrome(String known_syndrome) {
		this.known_syndrome = known_syndrome;
	}
	public double getBirth_weight() {
		return birth_weight;
	}
	public void setBirth_weight(double birth_weight) {
		this.birth_weight = birth_weight;
	}
	public double getBirth_length() {
		return birth_length;
	}
	public void setBirth_length(double birth_length) {
		this.birth_length = birth_length;
	}
	public double getBirth_head_circumference() {
		return birth_head_circumference;
	}
	public void setBirth_head_circumference(double birth_head_circumference) {
		this.birth_head_circumference = birth_head_circumference;
	}
	public String getGestation_age() {
		return gestation_age;
	}
	public void setGestation_age(String gestation_age) {
		this.gestation_age = gestation_age;
	}
	public String getMeatus() {
		return meatus;
	}
	public void setMeatus(String meatus) {
		this.meatus = meatus;
	}
	public String getLeft_gonad_location() {
		return left_gonad_location;
	}
	public void setLeft_gonad_location(String left_gonad_location) {
		this.left_gonad_location = left_gonad_location;
	}
	public String getRight_gonad_location() {
		return right_gonad_location;
	}
	public void setRight_gonad_location(String right_gonad_location) {
		this.right_gonad_location = right_gonad_location;
	}
	public String getGenital_tubercle_length() {
		return genital_tubercle_length;
	}
	public void setGenital_tubercle_length(String genital_tubercle_length) {
		this.genital_tubercle_length = genital_tubercle_length;
	}
	public String getPhallus_size() {
		return phallus_size;
	}
	public void setPhallus_size(String phallus_size) {
		this.phallus_size = phallus_size;
	}
	public String getLabioscrotal_fussion() {
		return labioscrotal_fussion;
	}
	public void setLabioscrotal_fussion(String labioscrotal_fussion) {
		this.labioscrotal_fussion = labioscrotal_fussion;
	}
	public String getAnogenital_distance_agn() {
		return anogenital_distance_agn;
	}
	public void setAnogenital_distance_agn(String anogenital_distance_agn) {
		this.anogenital_distance_agn = anogenital_distance_agn;
	}
	public String getAnoscrotal_distance_asd() {
		return anoscrotal_distance_asd;
	}
	public void setAnoscrotal_distance_asd(String anoscrotal_distance_asd) {
		this.anoscrotal_distance_asd = anoscrotal_distance_asd;
	}
	public double getExternal_masculinisation_score_ems() {
		return external_masculinisation_score_ems;
	}
	public void setExternal_masculinisation_score_ems(double external_masculinisation_score_ems) {
		this.external_masculinisation_score_ems = external_masculinisation_score_ems;
	}
	public double getExternal_genitalia_score_egs() {
		return external_genitalia_score_egs;
	}
	public void setExternal_genitalia_score_egs(double external_genitalia_score_egs) {
		this.external_genitalia_score_egs = external_genitalia_score_egs;
	}
	public String getImaging_modality_left_gonad() {
		return imaging_modality_left_gonad;
	}
	public void setImaging_modality_left_gonad(String imaging_modality_left_gonad) {
		this.imaging_modality_left_gonad = imaging_modality_left_gonad;
	}
	public String getImaging_modality_right_gonad() {
		return imaging_modality_right_gonad;
	}
	public void setImaging_modality_right_gonad(String imaging_modality_right_gonad) {
		this.imaging_modality_right_gonad = imaging_modality_right_gonad;
	}
	public String getLeft_gonad_morphology() {
		return left_gonad_morphology;
	}
	public void setLeft_gonad_morphology(String left_gonad_morphology) {
		this.left_gonad_morphology = left_gonad_morphology;
	}
	public String getRight_gonad_morphology() {
		return right_gonad_morphology;
	}
	public void setRight_gonad_morphology(String right_gonad_morphology) {
		this.right_gonad_morphology = right_gonad_morphology;
	}
	public String getImaging_modality_uterus() {
		return imaging_modality_uterus;
	}
	public void setImaging_modality_uterus(String imaging_modality_uterus) {
		this.imaging_modality_uterus = imaging_modality_uterus;
	}
	public String getUterus_morphology() {
		return uterus_morphology;
	}
	public void setUterus_morphology(String uterus_morphology) {
		this.uterus_morphology = uterus_morphology;
	}
	public String getImaging_modality_left_fallopian_tube() {
		return imaging_modality_left_fallopian_tube;
	}
	public void setImaging_modality_left_fallopian_tube(String imaging_modality_left_fallopian_tube) {
		this.imaging_modality_left_fallopian_tube = imaging_modality_left_fallopian_tube;
	}
	public String getImaging_modality_right_fallopian_tube() {
		return imaging_modality_right_fallopian_tube;
	}
	public void setImaging_modality_right_fallopian_tube(String imaging_modality_right_fallopian_tube) {
		this.imaging_modality_right_fallopian_tube = imaging_modality_right_fallopian_tube;
	}
	public String getLeft_fallopian_tube_morphology() {
		return left_fallopian_tube_morphology;
	}
	public void setLeft_fallopian_tube_morphology(String left_fallopian_tube_morphology) {
		this.left_fallopian_tube_morphology = left_fallopian_tube_morphology;
	}
	public String getRight_fallopian_tube_morphology() {
		return right_fallopian_tube_morphology;
	}
	public void setRight_fallopian_tube_morphology(String right_fallopian_tube_morphology) {
		this.right_fallopian_tube_morphology = right_fallopian_tube_morphology;
	}
	public String getImaging_modality_left_vas_deferens() {
		return imaging_modality_left_vas_deferens;
	}
	public void setImaging_modality_left_vas_deferens(String imaging_modality_left_vas_deferens) {
		this.imaging_modality_left_vas_deferens = imaging_modality_left_vas_deferens;
	}
	public String getImaging_modality_right_vas_deferens() {
		return imaging_modality_right_vas_deferens;
	}
	public void setImaging_modality_right_vas_deferens(String imaging_modality_right_vas_deferens) {
		this.imaging_modality_right_vas_deferens = imaging_modality_right_vas_deferens;
	}
	public String getLeft_vas_deferens_morphology() {
		return left_vas_deferens_morphology;
	}
	public void setLeft_vas_deferens_morphology(String left_vas_deferens_morphology) {
		this.left_vas_deferens_morphology = left_vas_deferens_morphology;
	}
	public String getRight_vas_deferens_morphology() {
		return right_vas_deferens_morphology;
	}
	public void setRight_vas_deferens_morphology(String right_vas_deferens_morphology) {
		this.right_vas_deferens_morphology = right_vas_deferens_morphology;
	}
	public String getDistance_vaginal_confluence_to_bladder_neck() {
		return distance_vaginal_confluence_to_bladder_neck;
	}
	public void setDistance_vaginal_confluence_to_bladder_neck(String distance_vaginal_confluence_to_bladder_neck) {
		this.distance_vaginal_confluence_to_bladder_neck = distance_vaginal_confluence_to_bladder_neck;
	}
	public String getDistance_vaginal_confluence_to_introitus() {
		return distance_vaginal_confluence_to_introitus;
	}
	public void setDistance_vaginal_confluence_to_introitus(String distance_vaginal_confluence_to_introitus) {
		this.distance_vaginal_confluence_to_introitus = distance_vaginal_confluence_to_introitus;
	}
	public String getChange_in_legal_sex() {
		return change_in_legal_sex;
	}
	public void setChange_in_legal_sex(String change_in_legal_sex) {
		this.change_in_legal_sex = change_in_legal_sex;
	}
	public String getPsychosocial_support_for_parents() {
		return psychosocial_support_for_parents;
	}
	public void setPsychosocial_support_for_parents(String psychosocial_support_for_parents) {
		this.psychosocial_support_for_parents = psychosocial_support_for_parents;
	}
	public String getLeft_gonad_biospy() {
		return left_gonad_biospy;
	}
	public void setLeft_gonad_biospy(String left_gonad_biospy) {
		this.left_gonad_biospy = left_gonad_biospy;
	}
	public String getRight_gonad_biopsy() {
		return right_gonad_biopsy;
	}
	public void setRight_gonad_biopsy(String right_gonad_biopsy) {
		this.right_gonad_biopsy = right_gonad_biopsy;
	}
	public String getGenitoplasty_one() {
		return genitoplasty_one;
	}
	public void setGenitoplasty_one(String genitoplasty_one) {
		this.genitoplasty_one = genitoplasty_one;
	}
	public String getGenitoplasty_two() {
		return genitoplasty_two;
	}
	public void setGenitoplasty_two(String genitoplasty_two) {
		this.genitoplasty_two = genitoplasty_two;
	}
	public String getGenitoplasty_three() {
		return genitoplasty_three;
	}
	public void setGenitoplasty_three(String genitoplasty_three) {
		this.genitoplasty_three = genitoplasty_three;
	}
	public String getLeft_gonadectomy() {
		return left_gonadectomy;
	}
	public void setLeft_gonadectomy(String left_gonadectomy) {
		this.left_gonadectomy = left_gonadectomy;
	}
	public String getRight_gonadectomy() {
		return right_gonadectomy;
	}
	public void setRight_gonadectomy(String right_gonadectomy) {
		this.right_gonadectomy = right_gonadectomy;
	}
	public String getComplications_following_surgery() {
		return Complications_following_surgery;
	}
	public void setComplications_following_surgery(String complications_following_surgery) {
		Complications_following_surgery = complications_following_surgery;
	}
	public String getTestosterone() {
		return testosterone;
	}
	public void setTestosterone(String testosterone) {
		this.testosterone = testosterone;
	}
	public String getDht() {
		return dht;
	}
	public void setDht(String dht) {
		this.dht = dht;
	}
	public String getArromatase_inhibitor() {
		return arromatase_inhibitor;
	}
	public void setArromatase_inhibitor(String arromatase_inhibitor) {
		this.arromatase_inhibitor = arromatase_inhibitor;
	}
	public String getGlucocorticoids() {
		return glucocorticoids;
	}
	public void setGlucocorticoids(String glucocorticoids) {
		this.glucocorticoids = glucocorticoids;
	}
	public String getFludrocortisone() {
		return fludrocortisone;
	}
	public void setFludrocortisone(String fludrocortisone) {
		this.fludrocortisone = fludrocortisone;
	}
	public String getOestrogen() {
		return oestrogen;
	}
	public void setOestrogen(String oestrogen) {
		this.oestrogen = oestrogen;
	}
	public String getOther_drugs() {
		return other_drugs;
	}
	public void setOther_drugs(String other_drugs) {
		this.other_drugs = other_drugs;
	}
	public String getLh() {
		return lh;
	}
	public void setLh(String lh) {
		this.lh = lh;
	}
	public Date getLh_date() {
		return lh_date;
	}
	public void setLh_date(Date lh_date) {
		this.lh_date = lh_date;
	}
	public String getFsh() {
		return fsh;
	}
	public void setFsh(String fsh) {
		this.fsh = fsh;
	}
	public Date getFsd_date() {
		return fsd_date;
	}
	public void setFsd_date(Date fsd_date) {
		this.fsd_date = fsd_date;
	}
	public String getAmh() {
		return amh;
	}
	public void setAmh(String amh) {
		this.amh = amh;
	}
	public Date getAmh_date() {
		return amh_date;
	}
	public void setAmh_date(Date amh_date) {
		this.amh_date = amh_date;
	}
	public String getInhibin_b() {
		return inhibin_b;
	}
	public void setInhibin_b(String inhibin_b) {
		this.inhibin_b = inhibin_b;
	}
	public Date getInhibin_b_date() {
		return Inhibin_b_date;
	}
	public void setInhibin_b_date(Date inhibin_b_date) {
		Inhibin_b_date = inhibin_b_date;
	}
	public String getAndrostenedione_one() {
		return androstenedione_one;
	}
	public void setAndrostenedione_one(String androstenedione_one) {
		this.androstenedione_one = androstenedione_one;
	}
	public Date getAndrostenedione_one_date() {
		return androstenedione_one_date;
	}
	public void setAndrostenedione_one_date(Date androstenedione_one_date) {
		this.androstenedione_one_date = androstenedione_one_date;
	}
	public String getTotal_testosterone_one() {
		return total_testosterone_one;
	}
	public void setTotal_testosterone_one(String total_testosterone_one) {
		this.total_testosterone_one = total_testosterone_one;
	}
	public Date getTotal_testosterone_one_date() {
		return total_testosterone_one_date;
	}
	public void setTotal_testosterone_one_date(Date total_testosterone_one_date) {
		this.total_testosterone_one_date = total_testosterone_one_date;
	}
	public String getFree_testosterone() {
		return free_testosterone;
	}
	public void setFree_testosterone(String free_testosterone) {
		this.free_testosterone = free_testosterone;
	}
	public Date getFree_testosterone_date() {
		return free_testosterone_date;
	}
	public void setFree_testosterone_date(Date free_testosterone_date) {
		this.free_testosterone_date = free_testosterone_date;
	}
	public String getDihydrotestosterone_one() {
		return dihydrotestosterone_one;
	}
	public void setDihydrotestosterone_one(String dihydrotestosterone_one) {
		this.dihydrotestosterone_one = dihydrotestosterone_one;
	}
	public Date getDihydrotestosterone_one_date() {
		return dihydrotestosterone_one_date;
	}
	public void setDihydrotestosterone_one_date(Date dihydrotestosterone_one_date) {
		this.dihydrotestosterone_one_date = dihydrotestosterone_one_date;
	}
	public String getOestradiol() {
		return oestradiol;
	}
	public void setOestradiol(String oestradiol) {
		this.oestradiol = oestradiol;
	}
	public Date getOestradiol_date() {
		return oestradiol_date;
	}
	public void setOestradiol_date(Date oestradiol_date) {
		this.oestradiol_date = oestradiol_date;
	}
	public String getOhp17() {
		return ohp17;
	}
	public void setOhp17(String ohp17) {
		this.ohp17 = ohp17;
	}
	public Date getOhp17Date() {
		return ohp17Date;
	}
	public void setOhp17Date(Date ohp17Date) {
		this.ohp17Date = ohp17Date;
	}
	public String getUrine_steroids() {
		return urine_steroids;
	}
	public void setUrine_steroids(String urine_steroids) {
		this.urine_steroids = urine_steroids;
	}
	public Date getUrine_steroids_date() {
		return Urine_steroids_date;
	}
	public void setUrine_steroids_date(Date urine_steroids_date) {
		Urine_steroids_date = urine_steroids_date;
	}
	public String getHcg_stimulation_test() {
		return hcg_stimulation_test;
	}
	public void setHcg_stimulation_test(String hcg_stimulation_test) {
		this.hcg_stimulation_test = hcg_stimulation_test;
	}
	public String getAndrostenedione_two() {
		return androstenedione_two;
	}
	public void setAndrostenedione_two(String androstenedione_two) {
		this.androstenedione_two = androstenedione_two;
	}
	public String getTotal_testosterone_two() {
		return total_testosterone_two;
	}
	public void setTotal_testosterone_two(String total_testosterone_two) {
		this.total_testosterone_two = total_testosterone_two;
	}
	public String getDihydrotestosterone_two() {
		return dihydrotestosterone_two;
	}
	public void setDihydrotestosterone_two(String dihydrotestosterone_two) {
		this.dihydrotestosterone_two = dihydrotestosterone_two;
	}
	public Date getDihydroterstosterone_date() {
		return dihydroterstosterone_date;
	}
	public void setDihydroterstosterone_date(Date dihydroterstosterone_date) {
		this.dihydroterstosterone_date = dihydroterstosterone_date;
	}
	public String getAdrenal_stimulation_test() {
		return adrenal_stimulation_test;
	}
	public void setAdrenal_stimulation_test(String adrenal_stimulation_test) {
		this.adrenal_stimulation_test = adrenal_stimulation_test;
	}
	public String getOhp17Ast() {
		return ohp17Ast;
	}
	public void setOhp17Ast(String ohp17Ast) {
		this.ohp17Ast = ohp17Ast;
	}
	public String getDeoxycortisol11() {
		return Deoxycortisol11;
	}
	public void setDeoxycortisol11(String deoxycortisol11) {
		Deoxycortisol11 = deoxycortisol11;
	}
	public String getPregnenolone() {
		return pregnenolone;
	}
	public void setPregnenolone(String pregnenolone) {
		this.pregnenolone = pregnenolone;
	}
	public String getOh17Pregnenolone() {
		return oh17Pregnenolone;
	}
	public void setOh17Pregnenolone(String oh17Pregnenolone) {
		this.oh17Pregnenolone = oh17Pregnenolone;
	}
	public String getDhea() {
		return dhea;
	}
	public void setDhea(String dhea) {
		this.dhea = dhea;
	}
	public String getFree_text() {
		return free_text;
	}
	public void setFree_text(String free_text) {
		this.free_text = free_text;
	}
	
	@Override
	public String toString() {
		return "DsdNeonatalVisits [dsd_neonatal_visit_id=" + dsd_neonatal_visit_id + ", date_of_assessment="
				+ date_of_assessment + ", age_of_assessment=" + age_of_assessment + ", original_sex_assigned="
				+ original_sex_assigned + ", current_gender=" + current_gender + ", is_child_being_raised_as="
				+ is_child_being_raised_as + ", weight=" + weight + ", height=" + height + ", bmi=" + bmi
				+ ", mothers_height=" + mothers_height + ", fathers_height=" + fathers_height + ", mid_parental_height="
				+ mid_parental_height + ", associated_features=" + associated_features + ", known_syndrome="
				+ known_syndrome + ", birth_weight=" + birth_weight + ", birth_length=" + birth_length
				+ ", birth_head_circumference=" + birth_head_circumference + ", gestation_age=" + gestation_age
				+ ", meatus=" + meatus + ", left_gonad_location=" + left_gonad_location + ", right_gonad_location="
				+ right_gonad_location + ", genital_tubercle_length=" + genital_tubercle_length + ", phallus_size="
				+ phallus_size + ", labioscrotal_fussion=" + labioscrotal_fussion + ", anogenital_distance_agn="
				+ anogenital_distance_agn + ", anoscrotal_distance_asd=" + anoscrotal_distance_asd
				+ ", external_masculinisation_score_ems=" + external_masculinisation_score_ems
				+ ", external_genitalia_score_egs=" + external_genitalia_score_egs + ", imaging_modality_left_gonad="
				+ imaging_modality_left_gonad + ", imaging_modality_right_gonad=" + imaging_modality_right_gonad
				+ ", left_gonad_morphology=" + left_gonad_morphology + ", right_gonad_morphology="
				+ right_gonad_morphology + ", imaging_modality_uterus=" + imaging_modality_uterus
				+ ", uterus_morphology=" + uterus_morphology + ", imaging_modality_left_fallopian_tube="
				+ imaging_modality_left_fallopian_tube + ", imaging_modality_right_fallopian_tube="
				+ imaging_modality_right_fallopian_tube + ", left_fallopian_tube_morphology="
				+ left_fallopian_tube_morphology + ", right_fallopian_tube_morphology="
				+ right_fallopian_tube_morphology + ", imaging_modality_left_vas_deferens="
				+ imaging_modality_left_vas_deferens + ", imaging_modality_right_vas_deferens="
				+ imaging_modality_right_vas_deferens + ", left_vas_deferens_morphology=" + left_vas_deferens_morphology
				+ ", right_vas_deferens_morphology=" + right_vas_deferens_morphology
				+ ", distance_vaginal_confluence_to_bladder_neck=" + distance_vaginal_confluence_to_bladder_neck
				+ ", distance_vaginal_confluence_to_introitus=" + distance_vaginal_confluence_to_introitus
				+ ", change_in_legal_sex=" + change_in_legal_sex + ", psychosocial_support_for_parents="
				+ psychosocial_support_for_parents + ", left_gonad_biospy=" + left_gonad_biospy
				+ ", right_gonad_biopsy=" + right_gonad_biopsy + ", genitoplasty_one=" + genitoplasty_one
				+ ", genitoplasty_two=" + genitoplasty_two + ", genitoplasty_three=" + genitoplasty_three
				+ ", left_gonadectomy=" + left_gonadectomy + ", right_gonadectomy=" + right_gonadectomy
				+ ", Complications_following_surgery=" + Complications_following_surgery + ", testosterone="
				+ testosterone + ", dht=" + dht + ", arromatase_inhibitor=" + arromatase_inhibitor
				+ ", glucocorticoids=" + glucocorticoids + ", fludrocortisone=" + fludrocortisone + ", oestrogen="
				+ oestrogen + ", other_drugs=" + other_drugs + ", lh=" + lh + ", lh_date=" + lh_date + ", fsh=" + fsh
				+ ", fsd_date=" + fsd_date + ", amh=" + amh + ", amh_date=" + amh_date + ", inhibin_b=" + inhibin_b
				+ ", Inhibin_b_date=" + Inhibin_b_date + ", androstenedione_one=" + androstenedione_one
				+ ", androstenedione_one_date=" + androstenedione_one_date + ", total_testosterone_one="
				+ total_testosterone_one + ", total_testosterone_one_date=" + total_testosterone_one_date
				+ ", free_testosterone=" + free_testosterone + ", free_testosterone_date=" + free_testosterone_date
				+ ", dihydrotestosterone_one=" + dihydrotestosterone_one + ", dihydrotestosterone_one_date="
				+ dihydrotestosterone_one_date + ", oestradiol=" + oestradiol + ", oestradiol_date=" + oestradiol_date
				+ ", ohp17=" + ohp17 + ", ohp17Date=" + ohp17Date + ", urine_steroids=" + urine_steroids
				+ ", Urine_steroids_date=" + Urine_steroids_date + ", hcg_stimulation_test=" + hcg_stimulation_test
				+ ", androstenedione_two=" + androstenedione_two + ", total_testosterone_two=" + total_testosterone_two
				+ ", dihydrotestosterone_two=" + dihydrotestosterone_two + ", dihydroterstosterone_date="
				+ dihydroterstosterone_date + ", adrenal_stimulation_test=" + adrenal_stimulation_test + ", ohp17Ast="
				+ ohp17Ast + ", Deoxycortisol11=" + Deoxycortisol11 + ", pregnenolone=" + pregnenolone
				+ ", oh17Pregnenolone=" + oh17Pregnenolone + ", dhea=" + dhea + ", free_text=" + free_text + "]";
	}























}
