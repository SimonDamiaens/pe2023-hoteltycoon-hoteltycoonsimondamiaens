package be.pxl.hotel.controller;

import be.pxl.hotel.api.response.BuildingPlotDTO;
import be.pxl.hotel.domain.BuildingPlot;
import be.pxl.hotel.service.BuildingPlotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buildingplots")
public class BuildingPlotController {

    private final BuildingPlotService buildingPlotService;

    public BuildingPlotController(BuildingPlotService buildingPlotService) {
        this.buildingPlotService = buildingPlotService;
    }

    @GetMapping()
    public List<BuildingPlotDTO> getBuildingPlots() {
        return buildingPlotService.getAllUnsoldBuildingPlots();
    }
}
