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

@Entity
@Table(name = "PGRS_DOCUMENTS",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries({	
	@NamedQuery(name="TicketDocumentsEntity.findDocumentByDocketNo",query="SELECT td FROM TicketDocumentsEntity td WHERE td.docketNo=:docketNo"),
})
public class TicketDocumentsEntity extends BaseEntity {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	 private int id;
	
	 @Column(name = "DOCKET_NUMBER")
	 private int docketNo;
	 
	 @Column(name = "DOC_NAME")
	 private String docname;
	 
	 @Column(name = "DOCKET_DOC")
	 private byte[] document;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDocketNo() {
		return docketNo;
	}

	public void setDocketNo(int docketNo) {
		this.docketNo = docketNo;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}
	 
}

