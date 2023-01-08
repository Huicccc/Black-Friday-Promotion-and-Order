package org.ecommerce.infrastructure.jooqrepo;

public interface RecordDomainMapping<Record, Domain> {

  Domain toDomain(Record record);

  Record toRecord(Domain domain);
}
