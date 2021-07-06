package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.AddressDAO;
import bg.infosys.interns.bmanagement.core.dto.AddressFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.AddressDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.AddressMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class AddressService {
	private AddressDAO addressDAO;
	private AddressMapper addressMapper;

	public AddressService(AddressDAO addressDAO, AddressMapper addressMapper) {
		this.addressDAO = addressDAO;
		this.addressMapper = addressMapper;
	}

	public List<AddressDTO> findAll() {
		return addressMapper.toDtoList(addressDAO.getAll());
	}

	public AddressDTO findById(Integer id) {
		return addressMapper.toDto(addressDAO.getById(id));
	}

	public Page<AddressDTO> findAllByFilter(AddressFilterDTO filter, PagingSorting pagingSorting) {
		List<Address> results = addressDAO.findAllByFilter(filter, pagingSorting);

		return new Page<AddressDTO>(results.stream().map(p -> addressMapper.toDto(p)).collect(Collectors.toList()),
				addressDAO.countAllByFilter(filter), pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	}

	@Transactional
	public AddressDTO save(AddressDTO addressDTO) {
		Address newAddress = addressMapper.toEntity(addressDTO);
		addressDAO.save(newAddress);

		return addressMapper.toDto(newAddress);
	} 

	@Transactional
	public AddressDTO update(Integer id,AddressDTO addressDTO) {
		Address updatedAddress = addressDAO.findById(id);

		addressDTO.setId(id);
		updatedAddress = addressDAO.merge(addressMapper.toEntity(addressDTO));
		return addressMapper.toDto(updatedAddress);
	}

	@Transactional
	public boolean deleteById(Integer id) {
		return addressDAO.delete(id);
	}
}
