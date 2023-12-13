package be.pxl.hotel.repository;

import be.pxl.hotel.domain.BuildingPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingPlotRepository extends JpaRepository<BuildingPlot, Long> {
}
