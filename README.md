This project shows that saved entities in a Kafka listener is not rollbacked in tests.

By default, test transactions will be automatically rolled back after completion of the test.

This is true if the transactions happen in the test thread. Kafka listener is running in a container thread so transactions happen there are not rolled back.

Asked this question in Stackoverflow and already answered by Spring Kafka's contributor.

See https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/transaction/TransactionalTestExecutionListener.html
See https://stackoverflow.com/questions/53995445/saved-entity-in-a-spring-kafka-listener-is-not-rollbacked-on-a-transactional-tes
