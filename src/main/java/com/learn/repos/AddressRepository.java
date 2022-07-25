package com.learn.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learn.data.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
