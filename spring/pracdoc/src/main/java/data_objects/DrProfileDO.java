package data_objects;

public class DrProfileDO {

	private int id;
	private String drFullName;
	private int drSpecialization;
	private String drQualification;
	private int drExperience;
	private int drConsulationFee;
	private String drContactNum;
	private String drClinicName;
	private String drClinicAddress;
	private float drClinicRating;
	private String drVerifiedVia;

	private String[] drServices;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDrFullName() {
		return drFullName;
	}

	public void setDrFullName(String drFullName) {
		this.drFullName = drFullName;
	}

	public int getDrSpecialization() {
		return drSpecialization;
	}

	public void setDrSpecialization(int drSpecialization) {
		this.drSpecialization = drSpecialization;
	}

	public String getDrQualification() {
		return drQualification;
	}

	public void setDrQualification(String drQualification) {
		this.drQualification = drQualification;
	}

	public int getDrExperience() {
		return drExperience;
	}

	public void setDrExperience(int drExperience) {
		this.drExperience = drExperience;
	}

	public int getDrConsulationFee() {
		return drConsulationFee;
	}

	public void setDrConsulationFee(int drConsulationFee) {
		this.drConsulationFee = drConsulationFee;
	}

	public String getDrContactNum() {
		return drContactNum;
	}

	public void setDrContactNum(String drContactNum) {
		this.drContactNum = drContactNum;
	}

	public String getDrClinicName() {
		return drClinicName;
	}

	public void setDrClinicName(String drClinicName) {
		this.drClinicName = drClinicName;
	}

	public String getDrClinicAddress() {
		return drClinicAddress;
	}

	public void setDrClinicAddress(String drClinicAddress) {
		this.drClinicAddress = drClinicAddress;
	}

	public float getDrClinicRating() {
		return drClinicRating;
	}

	public void setDrClinicRating(float drClinicRating) {
		this.drClinicRating = drClinicRating;
	}

	public String getDrVerifiedVia() {
		return drVerifiedVia;
	}

	public void setDrVerifiedVia(String drVerifiedVia) {
		this.drVerifiedVia = drVerifiedVia;
	}

	public String[] getDrServices() {
		return drServices;
	}

	public void setDrServices(String[] drServices) {
		this.drServices = drServices;
	}

}
