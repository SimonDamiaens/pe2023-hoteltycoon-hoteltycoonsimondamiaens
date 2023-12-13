package be.pxl.hotel.api.request;

import jakarta.validation.constraints.NotBlank;

public record CreateHotelRequest (@NotBlank String name, long buildingplotId) {
 // TODO validation
}
