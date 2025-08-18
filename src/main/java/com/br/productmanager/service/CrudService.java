package com.br.productmanager.service;

import java.util.List;

/**
 * <p>
 * Generic interface for a basic CRUD (Create, Read, Update, Delete) service.
 * </p>
 * <p>
 * This interface defines the standard operations for managing entities in a
 * service layer, promoting reusability and consistency across different
 * types of data.
 * </p>
 *
 * @param <T> The type of the entity (e.g., Product, User).
 * @param <ID> The type of the entity's unique identifier (e.g., Long, String, UUID).
 */
public interface CrudService<T, ID> {

    /**
     * Retrieves all entities.
     *
     * @return A list of all entities.
     */
    List<T> findAll();

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity to retrieve.
     * @return The entity with the specified ID.
     */
    T findById(ID id);

    /**
     * Creates a new entity.
     *
     * @param entity The entity to be created.
     * @return The newly created entity.
     */
    T create(T entity);

    /**
     * Updates an existing entity.
     *
     * @param id The unique identifier of the entity to update.
     * @param newEntity The updated entity data.
     * @return The updated entity.
     */
    T update(ID id, T newEntity);

    /**
     * Deletes an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity to delete.
     */
    void delete(ID id);
}
