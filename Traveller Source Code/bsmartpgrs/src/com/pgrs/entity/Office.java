package com.pgrs.entity;
// default package

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pgrs.util.PgrsSchemaName;

/**
 * Office entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CORE_OFFICE",schema=PgrsSchemaName.cescPgrsSchema)

@NamedQueries
({
	@NamedQuery(name="office.executeGetOfficeNameBasedOnId",query="SELECT o.officeName FROM Office o WHERE o.id =:officeId"),
})


public class Office extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 6, scale = 0)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	private Company company;
	
	@Column(name = "OFFICE_TYPE", nullable = false, length = 20)
	private String officeType;
	
	@Column(name = "RATE_CONTRACT_NUMBER", nullable = false, length = 45)
	private String rateContractNumber;
	
	@Column(name = "RATE_CONTRACT_DT", nullable = false, length = 11)
	private Timestamp rateContractDt;
	
	@Column(name = "BUYER_ORDER_NUMBER", nullable = false, length = 20)
	private String buyerOrderNumber;
	
	@Column(name = "BUYER_ORDER_DT", nullable = false, length = 11)
	private Timestamp buyerOrderDt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_INVOICE_MONTH", nullable = false, length = 7)
	private Date lastInvoiceMonth;
	
	@Column(name = "INVOICE_NUMBER_FORMAT", nullable = false, length = 20)
	private String invoiceNumberFormat;
	
	@Column(name = "LAST_INVOICE_NUMBER", nullable = false, length = 20)
	private String lastInvoiceNumber;
	
	@Column(name = "CONTRACT_RATE", nullable = false, precision = 13)
	private Double contractRate;
	
	@Column(name = "SERVICE_TAX_APPLICABLE", nullable = false, length = 45)
	private String serviceTaxApplicable;
	
	@Column(name = "SERVICE_TAX_RATE", nullable = false, precision = 13)
	private Double serviceTaxRate;
	
	@Column(name = "EDUCATIONAL_CESS", nullable = false, precision = 13)
	private Double educationalCess;
	
	@Column(name = "SH_EDUCATIONAL_CESS_ON_ST", nullable = false, precision = 13)
	private Double shEducationalCessOnSt;
	
	@Column(name = "OFFICE_NAME", nullable = false, precision = 13)
	private String officeName;

	/*private Set<Users> userses = new HashSet<Users>(0);*/

	// Constructors

	/** default constructor */
	public Office() {
	}

	/** minimal constructor */
	public Office(Integer id, Company company, String officeType,
			String rateContractNumber, Timestamp rateContractDt,
			String buyerOrderNumber, Timestamp buyerOrderDt,
			Date lastInvoiceMonth, String invoiceNumberFormat,
			String lastInvoiceNumber, Double contractRate,
			String serviceTaxApplicable, Double serviceTaxRate,
			Double educationalCess, Double shEducationalCessOnSt,
			String createdBy, String lastUpdatedBy, Timestamp lastUpdatedDt,
			String status, Timestamp createdDt) {
		this.id = id;
		this.company = company;
		this.officeType = officeType;
		this.rateContractNumber = rateContractNumber;
		this.rateContractDt = rateContractDt;
		this.buyerOrderNumber = buyerOrderNumber;
		this.buyerOrderDt = buyerOrderDt;
		this.lastInvoiceMonth = lastInvoiceMonth;
		this.invoiceNumberFormat = invoiceNumberFormat;
		this.lastInvoiceNumber = lastInvoiceNumber;
		this.contractRate = contractRate;
		this.serviceTaxApplicable = serviceTaxApplicable;
		this.serviceTaxRate = serviceTaxRate;
		this.educationalCess = educationalCess;
		this.shEducationalCessOnSt = shEducationalCessOnSt;
	}

	/** full constructor */
	public Office(Integer id, Company company, String officeType,
			String rateContractNumber, Timestamp rateContractDt,
			String buyerOrderNumber, Timestamp buyerOrderDt,
			Date lastInvoiceMonth, String invoiceNumberFormat,
			String lastInvoiceNumber, Double contractRate,
			String serviceTaxApplicable, Double serviceTaxRate,
			Double educationalCess, Double shEducationalCessOnSt,
			String createdBy, String lastUpdatedBy, Timestamp lastUpdatedDt,
			String status, Timestamp createdDt, Set<Users> userses) {
		this.id = id;
		this.company = company;
		this.officeType = officeType;
		this.rateContractNumber = rateContractNumber;
		this.rateContractDt = rateContractDt;
		this.buyerOrderNumber = buyerOrderNumber;
		this.buyerOrderDt = buyerOrderDt;
		this.lastInvoiceMonth = lastInvoiceMonth;
		this.invoiceNumberFormat = invoiceNumberFormat;
		this.lastInvoiceNumber = lastInvoiceNumber;
		this.contractRate = contractRate;
		this.serviceTaxApplicable = serviceTaxApplicable;
		this.serviceTaxRate = serviceTaxRate;
		this.educationalCess = educationalCess;
		this.shEducationalCessOnSt = shEducationalCessOnSt;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	
	public String getOfficeType() {
		return this.officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public String getRateContractNumber() {
		return this.rateContractNumber;
	}

	public void setRateContractNumber(String rateContractNumber) {
		this.rateContractNumber = rateContractNumber;
	}

	
	public Timestamp getRateContractDt() {
		return this.rateContractDt;
	}

	public void setRateContractDt(Timestamp rateContractDt) {
		this.rateContractDt = rateContractDt;
	}

	
	public String getBuyerOrderNumber() {
		return this.buyerOrderNumber;
	}

	public void setBuyerOrderNumber(String buyerOrderNumber) {
		this.buyerOrderNumber = buyerOrderNumber;
	}

	
	public Timestamp getBuyerOrderDt() {
		return this.buyerOrderDt;
	}

	public void setBuyerOrderDt(Timestamp buyerOrderDt) {
		this.buyerOrderDt = buyerOrderDt;
	}

	
	public Date getLastInvoiceMonth() {
		return this.lastInvoiceMonth;
	}

	public void setLastInvoiceMonth(Date lastInvoiceMonth) {
		this.lastInvoiceMonth = lastInvoiceMonth;
	}

	
	public String getInvoiceNumberFormat() {
		return this.invoiceNumberFormat;
	}

	public void setInvoiceNumberFormat(String invoiceNumberFormat) {
		this.invoiceNumberFormat = invoiceNumberFormat;
	}

	
	public String getLastInvoiceNumber() {
		return this.lastInvoiceNumber;
	}

	public void setLastInvoiceNumber(String lastInvoiceNumber) {
		this.lastInvoiceNumber = lastInvoiceNumber;
	}

	
	public Double getContractRate() {
		return this.contractRate;
	}

	public void setContractRate(Double contractRate) {
		this.contractRate = contractRate;
	}

	
	public String getServiceTaxApplicable() {
		return this.serviceTaxApplicable;
	}

	public void setServiceTaxApplicable(String serviceTaxApplicable) {
		this.serviceTaxApplicable = serviceTaxApplicable;
	}

	
	public Double getServiceTaxRate() {
		return this.serviceTaxRate;
	}

	public void setServiceTaxRate(Double serviceTaxRate) {
		this.serviceTaxRate = serviceTaxRate;
	}

	
	public Double getEducationalCess() {
		return this.educationalCess;
	}

	public void setEducationalCess(Double educationalCess) {
		this.educationalCess = educationalCess;
	}

	
	public Double getShEducationalCessOnSt() {
		return this.shEducationalCessOnSt;
	}

	public void setShEducationalCessOnSt(Double shEducationalCessOnSt) {
		this.shEducationalCessOnSt = shEducationalCessOnSt;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "office")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}*/
}
