package hellojpa;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
@MappedSuperclass
public class BaseEntity {
	private String createdBy;
	private LocalDateTime createdDate;
	private String lastModifiedBy;
	private LocalDateTime lastModifiedDate;
}
