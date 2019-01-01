package id.co.yraydhitya.springkafkadatajdbcmysqltransactionaltest;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

public class Entity implements Persistable {

    @Id
    private String id;

    @Transient
    private boolean isNew;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
