package com.pgrs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.pgrs.util.PgrsSchemaName;

@NamedQueries
({
	@NamedQuery(name="Company.findCompanyById",query="SELECT c FROM Company c WHERE c.id=:companyId"),
	@NamedQuery(name="Company.getCompanyNameBasedOnId",query="SELECT c.companyName FROM Company c WHERE c.id = :companyId"),
	@NamedQuery(name="Company.companyStatusUpdate",query="UPDATE Company c SET c.status=:status WHERE c.id=:companyId"),
})
@Entity
@Table(name = "CORE_COMPANY",schema=PgrsSchemaName.cescPgrsSchema)
public class Company extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="COMPANY_ADDRESS_1")
	private String companyAddress1;
	
	@Column(name="COMPANY_ADDRESS_2")
	private String companyAddress2;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name="FAX_NUMBER")
	private String faxNumber;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="BANK_NAME")
	private String bankName;
	
	@Column(name="IFSC_CODE")
	private String ifscCode;
	
	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;
	
	@Column(name="SERVICE_TAX_NUMBER")
	private String serviceTaxNumber;
	
	@Column(name="TIN_NUMBER")
	private String tinNumber;
	
	@Column(name="PAN_NUMBER")
	private String panNumber;
	
	@Column(name="CST_NUMBER")
	private String cstNumber;
	
	@Column(name="BRANCH_CODE")
	private String branchCode;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress1() {
		return companyAddress1;
	}
	public void setCompanyAddress1(String companyAddress1) {
		this.companyAddress1 = companyAddress1;
	}
	public String getCompanyAddress2() {
		return companyAddress2;
	}
	public void setCompanyAddress2(String companyAddress2) {
		this.companyAddress2 = companyAddress2;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getServiceTaxNumber() {
		return serviceTaxNumber;
	}
	public void setServiceTaxNumber(String serviceTaxNumber) {
		this.serviceTaxNumber = serviceTaxNumber;
	}
	public String getTinNumber() {
		return tinNumber;
	}
	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getCstNumber() {
		return cstNumber;
	}
	public void setCstNumber(String cstNumber) {
		this.cstNumber = cstNumber;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
}