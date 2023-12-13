package be.pxl.hotel.service;

import be.pxl.hotel.api.response.BuildingPlotDTO;
import be.pxl.hotel.domain.BuildingPlot;
import be.pxl.hotel.repository.BuildingPlotRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingPlotService {

    private final BuildingPlotRepository buildingPlotRepository;

    @Autowired
    BuildingPlotService(BuildingPlotRepository buildingPlotRepository) {
        this.buildingPlotRepository = buildingPlotRepository;
    }

    @PostConstruct
    public void initializeData() {
        BuildingPlot plot1 = new BuildingPlot(1L, 75000, 4, false);
        BuildingPlot plot2 = new BuildingPlot(2L, 100000, 3, false);
        BuildingPlot plot3 = new BuildingPlot(3L, 105000, 1, false);
        BuildingPlot plot4 = new BuildingPlot(4L, 135000, 4, false);

        buildingPlotRepository.saveAll(List.of(plot1, plot2, plot3, plot4));
    }

    public List<BuildingPlotDTO> getAllUnsoldBuildingPlots() {
        return buildingPlotRepository.findAll()
                .stream()
                .filter(t -> !t.isSold())
                .map(BuildingPlotDTO::new)
                .collect(Collectors.toList());
    }

    public BuildingPlotDTO getUnsoldBuildingPlotById(Long id) {
        return new BuildingPlotDTO(buildingPlotRepository.findById(id).get());
    }
}
