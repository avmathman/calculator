package com.globalsavings.calculator.domain;

import java.util.List;

/**
 * Common mapper for converting model object(s) to entity object(s) and vice versa.
 */
public interface ModelMapper<ModelType, EntityType> {
    /**
     * Converts model object to entity object.
     *
     * @param model - The model object.
     * @return The entity object.
     */
    EntityType modelToEntity(ModelType model);

    /**
     * Converts entity object to model object.
     *
     * @param entity - The entity object.
     * @return The model object.
     */
    ModelType entityToModel(EntityType entity);

    /**
     * Converts a list of entities objects to a list of model objects.
     *
     * @param entities - The list of entity objects.
     * @return The list of model objects.
     */
    List<ModelType> entitiesToModels(List<EntityType> entities);

    /**
     * Converts a list of models objects to a list of entity objects.
     *
     * @param models - The list of models objects.
     * @return The list of entity objects.
     */
    List<EntityType> modelsToEntities(List<ModelType> models);
}
