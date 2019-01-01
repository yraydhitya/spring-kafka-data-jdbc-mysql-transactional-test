package id.co.yraydhitya.springkafkadatajdbcmysqltransactionaltest;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class KafkaConsumer {

    private final EntityRepository entityRepository;

    private CountDownLatch countDownLatch;

    public KafkaConsumer(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @KafkaListener(topics = "topic", groupId = "group-id")
    public void consume(String id) {
        Entity entity = new Entity();
        entity.setId(id);
        entity.setNew(true);

        entityRepository.save(entity);

        if (countDownLatch != null) countDownLatch.countDown();
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
