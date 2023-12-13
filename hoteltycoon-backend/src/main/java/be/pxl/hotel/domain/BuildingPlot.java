package be.pxl.hotel.domain;

import jakarta.persistence.*;

@Entity
public class BuildingPlot {
	@Id
	private Long id;
	private double price;
	private int maxBuildings;
	private boolean sold;

	public BuildingPlot() {
		//JPA only
	}

	public BuildingPlot(Long id, double price, int maxBuildings, boolean sold) {
		this.id = id;
		this.price = price;
		this.maxBuildings = maxBuildings;
		this.sold = sold;
	}

	public Long getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public int getMaxBuildings() {
		return maxBuildings;
	}

	public boolean isSold() {
		return sold;
	}
}
