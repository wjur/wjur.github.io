package io.github.wjur.issues.spring;

import org.springframework.data.repository.PagingAndSortingRepository

interface EntityRepository : PagingAndSortingRepository<StoredEvent<*, *>, Long>

