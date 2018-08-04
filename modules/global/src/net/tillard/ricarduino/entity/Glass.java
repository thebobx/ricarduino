package net.tillard.ricarduino.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@NamePattern("%s %s|name,name")
@Table(name = "RICARDUINO_GLASS")
@Entity(name = "ricarduino$Glass")
public class Glass extends StandardEntity {
    private static final long serialVersionUID = -789768623795081601L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PICTURE_ID")
    protected FileDescriptor picture;

    @NotNull
    @Column(name = "SIZE_", nullable = false)
    protected Double size;

    public void setPicture(FileDescriptor picture) {
        this.picture = picture;
    }

    public FileDescriptor getPicture() {
        return picture;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getSize() {
        return size;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}