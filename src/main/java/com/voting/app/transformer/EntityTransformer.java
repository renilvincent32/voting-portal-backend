package com.voting.app.transformer;

public interface EntityTransformer<TEntity, TDto> {
    TDto from(TEntity entity);
}
