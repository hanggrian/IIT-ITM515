package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

/**
 * Enable builder pattern support to create schema document in a single-line statement.
 */
public interface SchemaBuilder<T> {
    T build();
}
