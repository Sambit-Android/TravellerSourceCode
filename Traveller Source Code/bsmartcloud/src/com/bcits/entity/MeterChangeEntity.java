package com.bcits.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="meterchangeoutput",schema="photobilling")
@NamedQueries
({
	@NamedQuery(name="MeterChangeEntity.findallbylist", query="SELECT ct FROM MeterChangeEntity ct where ct.lcno =:lcno and ct.updated ='0'"),
})
public class MeterChangeEntity {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="RRNO")
	private String rrno;
	
	@Column(name="LCNO")
	private String lcno;
	
	@Column(name="FOLIONO")
	private int foliono;
	
	
	@Column(name="FINALREADING")
	private int finalreading;
	
	@Column(name="INITIALREADING")
	private int initialreading;
	
	@Column(name="MCDATE")
	private Date mcdate;
	
	@Column(name="RELEASEDATE")
	private Date releasedate;
	
	@Column(name="GIVENDATE")
	private Date givendate;
	
	
	@Column(name="ENTERDATE")
	private Date enterdate;
	
	
	@Column(name="OLDERSERIALNO")
	private String olderserialno;
	
	
	@Column(name="NEWSERIALNO")
	private String newserialno;
	
	@Column(name="REASON")
	private String reason;
	
	@Column(name="OLDMAKE")
	private String oldmake;
	
	@Column(name="NEWMAKE")
	private String newmake;
	
	@Column(name="OLDCAPACITY")
	private String oldcapacity;
	
	@Column(name="NEWCAPACITY")
	private String newcapacity;
	
	@Column(name="TARIFF")
	private String rariff;

	@Column(name="SOCODE")
	private String socode;
	
	
	@Column(name="MCUNITSCONSUMED")
	private int mcunitsconsumed;
	
	@Column(name="UPDATED")
	private long updated;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="DATESTAMP")
	private Date datestamp;
	
	@Column(name="TYPE")
	private int type;
	
	@Column(name="COVER")
	private int cover;
	
	@Column(name="POSITION")
	private int position;
	
	@Column(name="OLDTYPE")
	private int oldtype;
	
	@Column(name="OLDCOVER")
	private int oldcover;
	
	@Column(name="OLDPOSITION")
	private int oldposition;
	
	@Column(name="OLDRRNO")
	private String oldrrno;
	
	
	@Column(name="METERFLAG")
	private String meterflag;
	
	
	@Column(name="OMLATTITUDE")
	private String omlattitude;
	
	@Column(name="OMLONGITUDE")
	private String omlongitude;
	
	
	@Column(name="NMLATTITUDE")
	private String nmlattitude;
	
	
	@Column(name="NMLONGITUDE")
	private String nmlongitude;
	
	
	@Column(name="OMIMAGE")
	private byte[] omimage;
	
	
	@Column(name="NMIMAGE")
	private byte[] nmimage;
	
	
	
	@Column(name="FDRCODE")
	private String fdrcode;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="TCCODE")
	private String tccode;
	
	@Column(name="POLENO")
	private String poleno;
	
	@Column(name="SDOCODE")
	private String sdocode;
	
	public MeterChangeEntity() 
	{
		
	}


	
	
	
	
	public String getSdocode() {
		return sdocode;
	}






	public void setSdocode(String sdocode) {
		this.sdocode = sdocode;
	}






	public String getFdrcode() {
		return fdrcode;
	}






	public void setFdrcode(String fdrcode) {
		this.fdrcode = fdrcode;
	}






	public String getAddress() {
		return address;
	}






	public void setAddress(String address) {
		this.address = address;
	}






	public String getTccode() {
		return tccode;
	}






	public void setTccode(String tccode) {
		this.tccode = tccode;
	}






	public String getPoleno() {
		return poleno;
	}






	public void setPoleno(String poleno) {
		this.poleno = poleno;
	}






	public void setMcdate(Date mcdate) {
		this.mcdate = mcdate;
	}






	public byte[] getOmimage() {
		return omimage;
	}




	public void setOmimage(byte[] omimage) {
		this.omimage = omimage;
	}




	public byte[] getNmimage() {
		return nmimage;
	}




	public void setNmimage(byte[] nmimage) {
		this.nmimage = nmimage;
	}




	public int getFinalreading() {
		return finalreading;
	}




	public void setFinalreading(int finalreading) {
		this.finalreading = finalreading;
	}




	public String getOmlattitude() {
		return omlattitude;
	}


	public void setOmlattitude(String omlattitude) {
		this.omlattitude = omlattitude;
	}


	public String getOmlongitude() {
		return omlongitude;
	}


	public void setOmlongitude(String omlongitude) {
		this.omlongitude = omlongitude;
	}


	public String getNmlattitude() {
		return nmlattitude;
	}


	public void setNmlattitude(String nmlattitude) {
		this.nmlattitude = nmlattitude;
	}


	public String getNmlongitude() {
		return nmlongitude;
	}


	public void setNmlongitude(String nmlongitude) {
		this.nmlongitude = nmlongitude;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRrno() {
		return rrno;
	}


	public void setRrno(String rrno) {
		this.rrno = rrno;
	}


	public String getLcno() {
		return lcno;
	}


	public void setLcno(String lcno) {
		this.lcno = lcno;
	}


	public int getFoliono() {
		return foliono;
	}


	public void setFoliono(int foliono) {
		this.foliono = foliono;
	}


	public int getInitialreading() {
		return initialreading;
	}


	public void setInitialreading(int initialreading) {
		this.initialreading = initialreading;
	}


	public Date getMcdate() {
		return mcdate;
	}


	public void setMcdate(String Date) {
		this.mcdate = mcdate;
	}


	public Date getReleasedate() {
		return releasedate;
	}


	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}


	public Date getGivendate() {
		return givendate;
	}


	public void setGivendate(Date givendate) {
		this.givendate = givendate;
	}


	public Date getEnterdate() {
		return enterdate;
	}


	public void setEnterdate(Date enterdate) {
		this.enterdate = enterdate;
	}


	public String getOldserialno() {
		return olderserialno;
	}


	public void setOldserialno(String oldserialno) {
		this.olderserialno = oldserialno;
	}


	public String getNewserialno() {
		return newserialno;
	}


	public void setNewserialno(String newserialno) {
		this.newserialno = newserialno;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getOldmake() {
		return oldmake;
	}


	public void setOldmake(String oldmake) {
		this.oldmake = oldmake;
	}


	public String getNewmake() {
		return newmake;
	}


	public void setNewmake(String newmake) {
		this.newmake = newmake;
	}


	public String getOldcapacity() {
		return oldcapacity;
	}


	public void setOldcapacity(String oldcapacity) {
		this.oldcapacity = oldcapacity;
	}


	public String getNewcapacity() {
		return newcapacity;
	}


	public void setNewcapacity(String newcapacity) {
		this.newcapacity = newcapacity;
	}


	public String getRariff() {
		return rariff;
	}


	public void setRariff(String rariff) {
		this.rariff = rariff;
	}


	public String getSocode() {
		return socode;
	}


	public void setSocode(String socode) {
		this.socode = socode;
	}


	public int getMcunitsconsumed() {
		return mcunitsconsumed;
	}


	public void setMcunitsconsumed(int mcunitsconsumed) {
		this.mcunitsconsumed = mcunitsconsumed;
	}


	public long getUpdated() {
		return updated;
	}


	public void setUpdated(long updated) {
		this.updated = updated;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Date getDatestamp() {
		return datestamp;
	}


	public void setDatestamp(Date datestamp) {
		this.datestamp = datestamp;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}





	


	public int getOldtype() {
		return oldtype;
	}


	public void setOldtype(int oldtype) {
		this.oldtype = oldtype;
	}


	
	public String getOldrrno() {
		return oldrrno;
	}


	public void setOldrrno(String oldrrno) {
		this.oldrrno = oldrrno;
	}
	

	public String getOlderserialno() {
		return olderserialno;
	}


	public void setOlderserialno(String olderserialno) {
		this.olderserialno = olderserialno;
	}


	public String getMeterflag() {
		return meterflag;
	}


	public void setMeterflag(String meterflag) {
		this.meterflag = meterflag;
	}




	public int getCover() {
		return cover;
	}




	public void setCover(int cover) {
		this.cover = cover;
	}




	public int getPosition() {
		return position;
	}




	public void setPosition(int position) {
		this.position = position;
	}




	public int getOldcover() {
		return oldcover;
	}




	public void setOldcover(int oldcover) {
		this.oldcover = oldcover;
	}




	public int getOldposition() {
		return oldposition;
	}




	public void setOldposition(int oldposition) {
		this.oldposition = oldposition;
	}
	
	
	
	
	
	
	
}
