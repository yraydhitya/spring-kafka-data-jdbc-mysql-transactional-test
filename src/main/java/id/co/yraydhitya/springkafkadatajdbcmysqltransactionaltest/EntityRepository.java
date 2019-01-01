package id.co.yraydhitya.springkafkadatajdbcmysqltransactionaltest;

import org.springframework.data.repository.CrudRepository;

public interface EntityRepository extends CrudRepository<Entity, String> {
}
