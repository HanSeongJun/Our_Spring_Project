package backend.service.map;

import backend.repository.map.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

    //api를 활용해 grade를 보여주는 장소추천 서비스
    @Transactional
    public void cityGrade()



}
