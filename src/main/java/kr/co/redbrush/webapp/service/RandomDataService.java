package kr.co.redbrush.webapp.service;

import kr.co.redbrush.webapp.repository.RandomDataRepository;
import kr.co.redbrush.webapp.domain.RandomData;
import kr.co.redbrush.webapp.domain.RandomValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

//@Service
@Slf4j
public class RandomDataService {
    @Value("${api.server.host}")
    private String serverHost;

    @Value("${api.create.random.url}")
    private String createRandomUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RandomDataRepository repository;

    private String requestUrl;

    private boolean collectEnabled = true;

    @PostConstruct
    private void init() {
        requestUrl = serverHost + createRandomUrl;

        LOGGER.info("serverHost : {}, createRandomUrl : {}, requestUrl : {}", serverHost, createRandomUrl, requestUrl);
    }

    @Scheduled(fixedRate = 1000)
    public void collect() {
        if (collectEnabled) {
            RandomData lastData = repository.findFirst1ByOrderByCreatedDateDesc();
            RandomValue randomValue = restTemplate.getForObject(requestUrl, RandomValue.class);

            RandomData randomData = new RandomData();
            randomData.setValue(randomValue.getValue());
            randomData.setCreatedDate(new Date());

            if (lastData != null && lastData.getSum() != null) {
                randomData.setSum(lastData.getSum() + randomValue.getValue());
            } else {
                randomData.setSum(new Long(randomValue.getValue()));
            }

            LOGGER.info("New randomData to save : {}", randomData);

            repository.save(randomData);
        }
    }

    public List<RandomData> getListBetween(Date startDate, Date endDate) {
        return repository.findAllByCreatedDateBetween(startDate, endDate);
    }

    public RandomData getLastOne() {
        return repository.findFirst1ByOrderByCreatedDateDesc();
    }

    public RandomValue generateRandom(int startInclusive, int endInclusive) {
        int nextRandom = RandomUtils.nextInt(startInclusive, endInclusive) * (RandomUtils.nextBoolean() ? 1 : -1);

        RandomValue randomValue = new RandomValue();
        randomValue.setValue(nextRandom);

        return randomValue;
    }
}