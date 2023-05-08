package backend.map.service;

import backend.map.entity.City;
import backend.map.entity.dto.ParticulatePredictInfoDto;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface ApiMapService {

    void updateApiDataParti() throws IOException, ParseException;


}
