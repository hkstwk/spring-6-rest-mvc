package nl.hkstwk.spring6restmvc.mappers;

import nl.hkstwk.spring6restmvc.entities.Customer;
import nl.hkstwk.spring6restmvcapi.model.CustomerDTO;
import org.mapstruct.Mapper;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);

}
