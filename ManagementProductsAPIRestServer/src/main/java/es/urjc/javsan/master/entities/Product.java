package es.urjc.javsan.master.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {

	@Id
    @Min(0)
	private int code;

    @Size(min=2, max=30)
	private String name;
    
    @Size(min=0, max=120)
    private String desc;

    @Min(0)
    private float price;
	
	protected Product() {
		;
	}
	
	public Product(int code, String name, String desc, float price) {
		this.code = code;
		this.name = name;
		this.desc = desc;
		this.price = price;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product p = (Product) obj;
		return p.code == this.code && p.name == this.name && 
				p.desc == this.desc && p.price == this.price;
	}
	
	@Override 
	public String toString() {
		return String.format("Code : %d Name : %s, Desc : %s, Price : %f", code, name, desc, price);
	}
}
