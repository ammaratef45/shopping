package edu.miu.groupx.product.productservice.models;


import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.repository.SequenceNumberRepository;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="SEQUENCE_NUMBER",uniqueConstraints = {
		@UniqueConstraint(columnNames = { "TYPE" }) })
@NamedQueries({
		@NamedQuery(name = SequenceNumberRepository.QUERY_NAME.findBySequenceType, query = SequenceNumberRepository.QUERY.findBySequenceType) })
public class SequenceNumber implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	@Column(name="VERSION")
	private int  version;
	@Column(name = "SEQUENCE", nullable = false)
	private Long sequence;
 	@Column(name="TYPE",nullable = false)
 	@Enumerated(EnumType.STRING)
	private ESequenceType sequenceType;
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(final Long sequence) {
		this.sequence = sequence;
	}
	public ESequenceType getSequenceType() {
		return sequenceType;
	}
	public void setSequenceType(final ESequenceType type) {
		this.sequenceType = type;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
