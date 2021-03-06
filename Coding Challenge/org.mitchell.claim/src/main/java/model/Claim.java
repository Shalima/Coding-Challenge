package model;
// Generated Nov 15, 2015 3:54:50 PM by Hibernate Tools 4.3.1.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Claim generated by hbm2java Represents the model class Claim that is mapped
 * to the CLAIM table in Database
 */
@Entity
@Table(name = "CLAIM", schema = "PUBLIC", catalog = "PUBLIC")
public class Claim implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2176180310599929551L;
	private String claimnumber;
	private String claimfirstname;
	private String claimlastname;
	private String status;
	private Date lossdate;
	private String assignedadjusterid;
	private List<Vehicleinfotype> vehicleinfotypes = new ArrayList<Vehicleinfotype>();
	private Lossinfotype lossinfotype;

	public Claim() {
	}

	public Claim(String claimnumber) {
		this.claimnumber = claimnumber;
	}

	public Claim(String claimnumber, String claimfirstname, String claimlastname, String status, Date lossdate,
			String assignedadjusterid, List<Vehicleinfotype> vehicleinfotypes, Lossinfotype lossinfotype) {
		this.claimnumber = claimnumber;
		this.claimfirstname = claimfirstname;
		this.claimlastname = claimlastname;
		this.status = status;
		this.lossdate = lossdate;
		this.assignedadjusterid = assignedadjusterid;
		this.vehicleinfotypes = vehicleinfotypes;
		this.lossinfotype = lossinfotype;
	}

	@Id
	@Column(name = "CLAIMNUMBER", unique = true, nullable = false, length = 200)
	public String getClaimnumber() {
		return this.claimnumber;
	}

	public void setClaimnumber(String claimnumber) {
		this.claimnumber = claimnumber;
	}

	@Column(name = "CLAIMFIRSTNAME", length = 200)
	public String getClaimfirstname() {
		return this.claimfirstname;
	}

	public void setClaimfirstname(String claimfirstname) {
		this.claimfirstname = claimfirstname;
	}

	@Column(name = "CLAIMLASTNAME", length = 200)
	public String getClaimlastname() {
		return this.claimlastname;
	}

	public void setClaimlastname(String claimlastname) {
		this.claimlastname = claimlastname;
	}

	@Column(name = "STATUS", length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LOSSDATE", length = 10)
	public Date getLossdate() {
		return this.lossdate;
	}

	public void setLossdate(Date lossdate) {
		this.lossdate = lossdate;
	}

	@Column(name = "ASSIGNEDADJUSTERID", length = 10)
	public String getAssignedadjusterid() {
		return this.assignedadjusterid;
	}

	public void setAssignedadjusterid(String assignedadjusterid) {
		this.assignedadjusterid = assignedadjusterid;
	}

	@OneToMany(targetEntity = Vehicleinfotype.class, fetch = FetchType.LAZY, mappedBy = "claim", cascade = CascadeType.ALL)
	public List<Vehicleinfotype> getVehicleinfotypes() {
		return this.vehicleinfotypes;
	}

	public void setVehicleinfotypes(List<Vehicleinfotype> vehicleList) {
		this.vehicleinfotypes = vehicleList;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "claim", cascade = CascadeType.ALL)
	public Lossinfotype getLossinfotype() {
		return this.lossinfotype;
	}

	public void setLossinfotype(Lossinfotype lossinfotype) {
		this.lossinfotype = lossinfotype;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Claim \n[claimnumber=" + claimnumber + ", claimfirstname=" + claimfirstname + ", claimlastname="
				+ claimlastname + ", status=" + status + ", lossdate=" + lossdate + ", assignedadjusterid="
				+ assignedadjusterid + ",\n vehicleinfotypes=" + vehicleinfotypes + ",\n lossinfotype=" + lossinfotype
				+ "]";
	}
}
