package be.pxl.hotel.service;

import be.pxl.hotel.domain.Hotel;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HotelService {

	public List<Hotel> findAllOpenHotels() {
		// TODO implement this method
		return Collections.emptyList();
	}

	public void book(Hotel hotel, int persons, int nights) {
		// TODO: book the given hotel for the given number of persons for the given number of nights
		// make sure you receive the money on your wallet!
	}
}
