package id.co.yraydhitya.springkafkadatajdbcmysqltransactionaltest;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SpringKafkaDataJdbcMysqlTransactionalTestApplicationTests {

    @ClassRule
    public static EmbeddedKafkaRule embeddedKafkaRule = new EmbeddedKafkaRule(1, true, 1, "topic");

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Autowired
    private EntityRepository entityRepository;

    @BeforeClass
    public static void setup() {
        System.setProperty("spring.kafka.bootstrap-servers",
                embeddedKafkaRule.getEmbeddedKafka().getBrokersAsString());
    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        kafkaConsumer.setCountDownLatch(countDownLatch);

        String id = UUID.randomUUID().toString();

        kafkaTemplate.send("topic", id);

        assertThat(countDownLatch.await(5, TimeUnit.SECONDS)).isTrue();

        assertThat(entityRepository.findById(id)).isNotEmpty();
    }

}

