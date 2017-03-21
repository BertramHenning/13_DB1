package dto01917;

/**
 * Operatoer Data Access Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class OperatoerDTO
{
	/** Operatoer-identifikationsnummer (opr_id) i omraadet 1-99999999. Vaelges af brugerne */
	int oprId;                     
	/** Operatoernavn (opr_navn) min. 2 max. 20 karakterer */
	String oprFornavn; 
	/** Operatoernavn (opr_navn) min. 2 max. 20 karakterer */
	String oprEfternavn;
	/** Operatoer-initialer min. 2 max. 3 karakterer */
	String ini;                 
	/** Operatoer cpr-nr 10 karakterer */
	String cpr;                 
	/** Operatoer password min. 7 max. 8 karakterer */
	String password;            

	public OperatoerDTO(int oprId, String oprFornavn, String oprEfternavn, String ini, String cpr, String password)
	{
		this.oprId = oprId;
		this.oprFornavn = oprFornavn;
		this.oprEfternavn = oprEfternavn;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
	}
	
    public OperatoerDTO(OperatoerDTO opr)
    {
    	this.oprId = opr.getOprId();
    	this.oprFornavn = opr.getOprFornavn();
    	this.oprEfternavn = opr.getOprEfternavn();
    	this.ini = opr.getIni();
    	this.cpr = opr.getCpr();
    	this.password = opr.getPassword();
    }
    
    public int getOprId() { return oprId; }
	public void setOprId(int oprId) { this.oprId = oprId; }
	public String getOprFornavn() { return oprFornavn; }
	public void setOprFornavn(String oprFornavn) { this.oprFornavn = oprFornavn; }
	public String getOprEfternavn() { return oprEfternavn; }
	public void setOprEfternavn(String oprEfternavn) { this.oprEfternavn = oprEfternavn; }
	public String getIni() { return ini; }
	public void setIni(String ini) { this.ini = ini; }
	public String getCpr() { return cpr; }
	public void setCpr(String cpr) { this.cpr = cpr; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String toString() { return "\n" + oprId + "\t" + oprFornavn + "\t" + oprEfternavn + "\t" + ini + "\t" + cpr + "\t" + password; }
}
