package org.ecommerce.infrastructure;

public interface RecordDomainMapping<Record, Domain> {

  Domain toDomain(Record record);

  Record toRecord(Domain domain);
}
