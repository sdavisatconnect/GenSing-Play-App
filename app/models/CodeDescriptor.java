package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class CodeDescriptor extends Model {
	
	@Required
	public String desc;
	
	@Required
	@ManyToOne
	public CodeCategory ccategory;
	
	
	public CodeDescriptor( CodeCategory ca, String d)
	{
		this.desc = d;
		this.ccategory = ca;
	}

	public static CodeDescriptor findByCategoryAndName(CodeCategory cc,
			String desc) {
		String qu = "select c from CodeDescriptor c WHERE c.category = "+cc+" and c.desc = "+desc;
		CodeDescriptor cd = CodeDescriptor.find(qu).first();
		return cd;
		//return find("byCategoryAndDescriptor", cc, desc).first();
	}
	
	public String toString() {
		return ( ccategory.category + " " + desc );
	}
	


}