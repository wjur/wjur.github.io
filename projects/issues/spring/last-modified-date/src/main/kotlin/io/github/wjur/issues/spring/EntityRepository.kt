package io.github.wjur.issues.spring;

import org.springframework.data.repository.CrudRepository;

interface EntityRepository : CrudRepository<Entity, Long>
interface EntityVarRepository : CrudRepository<EntityVar, Long>
interface EntityWitherRepository : CrudRepository<EntityWither, Long>
