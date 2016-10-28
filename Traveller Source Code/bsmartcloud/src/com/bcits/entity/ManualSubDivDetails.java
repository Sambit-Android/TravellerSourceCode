package com.bcits.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MANUAL_SUBDIV_DETAILS",schema="VCLOUDENGINE")
@NamedQueries({
	
	@NamedQuery(name="ManualSubDivDetails.GetAll",query="SELECT model FROM ManualSubDivDetails model"),
	@NamedQuery(name="ManualSubDivDetails.GetSelectedDetails",query="SELECT model FROM ManualSubDivDetails model WHERE model.slno=:slno")
})
public class ManualSubDivDetails 

{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer slno;
  private String sdocode;
  private String subdivision;
  private String company;
  private Integer no_bcits_mr;
  private Integer no_jvvnl_mr;
  private Integer no_of_binders;
  private Integer no_cons_to_billed;
  private String input_data_provided_date;
  private String sc_date_for_input;
  private String ec_given_date;
  private String ec_v_r_date;
  private String pr_bill_repo_given_date;
  private String pr_bill_repo_v_r_date;
  private String billing_start_date;
  private Integer no_con_billed;
  private Integer no_cons_pending;
  
	public ManualSubDivDetails()
	{
		
	}

	public Integer getSlno() {
		return slno;
	}

	public void setSlno(Integer slno) {
		this.slno = slno;
	}

	public String getSdocode() {
		return sdocode;
	}

	public void setSdocode(String sdocode) {
		this.sdocode = sdocode;
	}

	public String getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getNo_bcits_mr() {
		return no_bcits_mr;
	}

	public void setNo_bcits_mr(Integer no_bcits_mr) {
		this.no_bcits_mr = no_bcits_mr;
	}

	public Integer getNo_jvvnl_mr() {
		return no_jvvnl_mr;
	}

	public void setNo_jvvnl_mr(Integer no_jvvnl_mr) {
		this.no_jvvnl_mr = no_jvvnl_mr;
	}

	public Integer getNo_of_binders() {
		return no_of_binders;
	}

	public void setNo_of_binders(Integer no_of_binders) {
		this.no_of_binders = no_of_binders;
	}

	public Integer getNo_cons_to_billed() {
		return no_cons_to_billed;
	}

	public void setNo_cons_to_billed(Integer no_cons_to_billed) {
		this.no_cons_to_billed = no_cons_to_billed;
	}

	public String getInput_data_provided_date() {
		return input_data_provided_date;
	}

	public void setInput_data_provided_date(String input_data_provided_date) {
		this.input_data_provided_date = input_data_provided_date;
	}

	public String getSc_date_for_input() {
		return sc_date_for_input;
	}

	public void setSc_date_for_input(String sc_date_for_input) {
		this.sc_date_for_input = sc_date_for_input;
	}

	public String getEc_given_date() {
		return ec_given_date;
	}

	public void setEc_given_date(String ec_given_date) {
		this.ec_given_date = ec_given_date;
	}

	public String getEc_v_r_date() {
		return ec_v_r_date;
	}

	public void setEc_v_r_date(String ec_v_r_date) {
		this.ec_v_r_date = ec_v_r_date;
	}

	public String getPr_bill_repo_given_date() {
		return pr_bill_repo_given_date;
	}

	public void setPr_bill_repo_given_date(String pr_bill_repo_given_date) {
		this.pr_bill_repo_given_date = pr_bill_repo_given_date;
	}

	public String getPr_bill_repo_v_r_date() {
		return pr_bill_repo_v_r_date;
	}

	public void setPr_bill_repo_v_r_date(String pr_bill_repo_v_r_date) {
		this.pr_bill_repo_v_r_date = pr_bill_repo_v_r_date;
	}

	public String getBilling_start_date() {
		return billing_start_date;
	}

	public void setBilling_start_date(String billing_start_date) {
		this.billing_start_date = billing_start_date;
	}

	public Integer getNo_con_billed() {
		return no_con_billed;
	}

	public void setNo_con_billed(Integer no_con_billed) {
		this.no_con_billed = no_con_billed;
	}

	public Integer getNo_cons_pending() {
		return no_cons_pending;
	}

	public void setNo_cons_pending(Integer no_cons_pending) {
		this.no_cons_pending = no_cons_pending;
	}
	
	
}
