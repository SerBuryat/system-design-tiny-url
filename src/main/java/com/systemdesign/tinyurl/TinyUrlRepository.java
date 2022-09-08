package com.systemdesign.tinyurl;

import org.springframework.data.repository.CrudRepository;

public interface TinyUrlRepository extends CrudRepository<TinyUrl,String> {}
