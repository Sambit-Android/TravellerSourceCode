package com.pgrs.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import com.pgrs.util.PgrsSchemaName;


/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CORE_USERS",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries({
	@NamedQuery(name = "Users.readAllUsers",query="SELECT u.urId,u.urName,u.urLoginName,u.urContactNo,u.urEmailId,u.urPermanent,u.status,u.deptId,dept.deptName,u.dnId,desg.dnName,c.companyName,p.name,ph.text,ph.id FROM Users u INNER JOIN u.departmentEntity dept INNER JOIN u.designationEntity desg INNER JOIN u.project p INNER JOIN u.company c INNER JOIN u.projectHeirarchyEntity ph ORDER BY u.urId DESC",hints={@QueryHint(name="org.hibernate.cacheable",value="true"),@QueryHint(name="eclipselink.refresh",value="true")}),
	/*@NamedQuery(name = "Users.getImage", query = "SELECT u.personImages FROM Users u WHERE u.urId= :urId" ),*/
	@NamedQuery(name = "Users.readUsers", query = "SELECT u.urId,u.urName,u.urEmailId,u.urContactNo,u.dnId FROM  Users u " ),
	//@NamedQuery(name = "Users.uploadImageOnId", query = "UPDATE Users u SET u.personImages= :personImage WHERE u.urId= :urId" ),
	@NamedQuery(name = "Users.UpdateUserStatus",query="UPDATE Users u SET  u.status = :userStatus WHERE u.urId = :urId"),
	@NamedQuery(name = "Users.getUserBasedOnId",query="SELECT u FROM Users u WHERE u.urId = :urId"),
	@NamedQuery(name = "Users.getActiveUsers",query="SELECT u FROM Users u WHERE u.status = :userStatus"),
	@NamedQuery(name = "Users.readLoginNames",query="SELECT u.urLoginName FROM Users u"),
	@NamedQuery(name = "Users.userid",query="SELECT u.urId FROM Users u WHERE u.urLoginName = :username"),
	@NamedQuery(name = "Users.getLoggeduser",query="SELECT u.urName FROM Users u WHERE u.urId = :user_id"),
	@NamedQuery(name = "Users.getUserDetailsforprofile",query="SELECT model FROM Users model WHERE model.urId = :user_id"),
	@NamedQuery(name = "Users.getUserDetailsByLoginName",query="SELECT u.urId,u.urName,u.urLoginName,u.urContactNo,u.urEmailId,u.urPermanent,dept.deptName,desg.dnName,ph.text FROM Users u INNER JOIN u.departmentEntity dept INNER JOIN u.designationEntity desg INNER JOIN u.projectHeirarchyEntity ph WHERE u.urLoginName = :loginName",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "Users.updateuserDetailsFromProfile",query="UPDATE Users u SET u.urName=:userName,u.urContactNo=:contactNo,u.urEmailId=:emailId WHERE u.urId=:userId"),
	@NamedQuery(name = "Users.getThemeNameByLoginName",query="SELECT u.themeName FROM Users u WHERE u.urLoginName = :userLoginName",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "Users.updateTheme",query="UPDATE Users u SET  u.themeName = :themeName WHERE u.urLoginName = :urLoginName"),
	@NamedQuery(name = "Users.userNameValidation",query="SELECT u.urLoginName FROM Users u WHERE u.urLoginName = :urLoginName AND u.urEmailId=:urEmail AND u.urContactNo=:mobileNo",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "Users.readAllContactNumbers",query="SELECT u.urContactNo FROM Users u"),
	@NamedQuery(name = "Users.readAllEmailAddress",query="SELECT u.urEmailId FROM Users u"),
	@NamedQuery(name = "Users.getprojects",query="SELECT usr.project FROM Users usr WHERE usr.urLoginName = :urLoginName"),
	@NamedQuery(name = "Users.getcompanies",query="SELECT usr.company FROM Users usr WHERE usr.urLoginName = :urLoginName"),
	@NamedQuery(name = "Users.getUserRoles",query="SELECT r.rlName FROM Role r WHERE r.dnId = :dnId AND r.roleStatus='Active'"),
	@NamedQuery(name = "Users.UserDesignationOnLoginName",query="SELECT usr.designationEntity.dnName FROM Users usr WHERE usr.urLoginName = :urLoginName"),
	@NamedQuery(name = "Users.UserSiteCodeOnLoginName",query="SELECT usr.projectHeirarchyEntity.siteCode FROM Users usr WHERE usr.urLoginName = :urLoginName"),
	@NamedQuery(name = "Users.getAllInActiveUsres",query="SELECT u.urId FROM Users u WHERE u.status=:status"),
    @NamedQuery(name="Users.getEmployeeListBasedOnSiteCode",query="SELECT u.urName,u.urLoginName,u.urContactNo,u.urEmailId,u.status,phe.text,desg.dnName FROM Users u INNER JOIN u.departmentEntity dept INNER JOIN u.designationEntity desg INNER JOIN u.projectHeirarchyEntity phe WHERE  u.dnId IN (SELECT r.dnId FROM Role r WHERE r.rlName IN ('Field Verification','Estimation','Power Senction','Work Order','Meter Purchase Order','Wcr','Inst Service','Ae(tech)','Aee(tech)','See(tech)','CE(Elec)','Cee','Ee(tech)','SE(Elec)','Pc Test','Taqc Ei'))",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
    //@NamedQuery(name="Users.getUserOnSiteCodeAndRole",query="SELECT u.urId,u.urName,u.urLoginName,u.urContactNo,u.urEmailId FROM Users u Where u.projectHeirarchyEntity.siteCode=:sitecode AND u.designationEntity.dnId IN (SELECT r.dnId FROM Role r WHERE r.rlName=:pahseCompletedCode) ")
	@NamedQuery(name="Users.getUserOnSiteCodeAndRole",query="SELECT ur.urId,ur.urName,ur.urLoginName,ur.urContactNo,ur.urEmailId FROM Users ur INNER JOIN ur.projectHeirarchyEntity ph WHERE ph.siteCode LIKE :sitecode AND ur.dnId IN (SELECT r.dnId FROM Role r WHERE r.rlName=:pahseCompletedCode)")      
})

public class Users extends BaseEntity{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "UR_ID")
		private int urId;
		
		@Column(name = "NAME")
		private String urName;
		
		@Column(name = "UR_LOGIN_NAME")
		private String urLoginName;
		
		@Column(name = "UR_CONTACT_NO")
		private String urContactNo;
		
		@Column(name = "UR_EMAIL_ID")
		private String urEmailId;
		
		@Column(name = "UR_PERMANENT")
		private String urPermanent;
		
		/*@Column(name = "RL_ID")
		private String rlId;
		
		@Column(name = "PRIMARY_OFFICE_ID")
		private int primaryOfficeId;*/
		
		@Column(name = "DEPT_ID")
		private int deptId;
		
		@OneToOne
		@JoinColumn(name="DEPT_ID",insertable=false,updatable=false)
		private Department departmentEntity;
		
		@Column(name = "DN_ID")
		private int dnId;
		
		@OneToOne
		@JoinColumn(name="DN_ID",insertable=false,updatable=false)
		private Designation designationEntity;
	    
	    @Column(name="THEME")
	    private String themeName;
	    
	    @Column(name = "OFFICE_ID")
		private int officeId;
		
		@Column(name = "COMPANY_ID")
		private int companyId;
		
		@Column(name = "PROJECT_ID")
		private int projectId;
		
		@OneToOne
		@JoinColumn(name = "PROJECT_ID", insertable = false, updatable = false, nullable = false)
		private Project project;
		
		@OneToOne
		@JoinColumn(name = "COMPANY_ID", insertable = false, updatable = false, nullable = false)
		private Company company;
		
		@OneToOne
		@JoinColumn(name = "OFFICE_ID", insertable = false, updatable = false, nullable = false)
		private ProjectHeirarchyEntity projectHeirarchyEntity;
		
		public int getUrId() {
			return this.urId;
		}

		public void setUrId(int urId) {
			this.urId = urId;
		}
		
		public String getUrName() {
			return this.urName;
		}

		public void setUrName(String urName) {
			this.urName = urName;
		}
	
		public String getUrLoginName() {
			return this.urLoginName;
		}

		public void setUrLoginName(String urLoginName) {
			this.urLoginName = urLoginName;
		}
		
		public String getUrContactNo() {
			return this.urContactNo;
		}

		public void setUrContactNo(String urContactNo) {
			this.urContactNo = urContactNo;
		}

		public String getUrEmailId() {
			return urEmailId;
		}

		public void setUrEmailId(String urEmailId) {
			this.urEmailId = urEmailId;
		}

		public String getUrPermanent() {
			return urPermanent;
		}

		public void setUrPermanent(String urPermanent) {
			this.urPermanent = urPermanent;
		}

		public int getDeptId() {
			return deptId;
		}

		public void setDeptId(int deptId) {
			this.deptId = deptId;
		}

		public int getDnId() {
			return dnId;
		}

		public void setDnId(int dnId) {
			this.dnId = dnId;
		}

		public Designation getDesignationEntity() {
			return designationEntity;
		}

		public void setDesignationEntity(Designation designationEntity) {
			this.designationEntity = designationEntity;
		}

		public Department getDepartmentEntity() {
			return departmentEntity;
		}

		public void setDepartmentEntity(Department departmentEntity) {
			this.departmentEntity = departmentEntity;
		}

		public String getThemeName() {
			return themeName;
		}

		public void setThemeName(String themeName) {
			this.themeName = themeName;
		}

		public int getOfficeId() {
			return officeId;
		}

		public void setOfficeId(int officeId) {
			this.officeId = officeId;
		}

		public int getCompanyId() {
			return companyId;
		}

		public void setCompanyId(int companyId) {
			this.companyId = companyId;
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}

		public int getProjectId() {
			return projectId;
		}

		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}

		public ProjectHeirarchyEntity getProjectHeirarchyEntity() {
			return projectHeirarchyEntity;
		}

		public void setProjectHeirarchyEntity(ProjectHeirarchyEntity projectHeirarchyEntity) {
			this.projectHeirarchyEntity = projectHeirarchyEntity;
		}
		
}