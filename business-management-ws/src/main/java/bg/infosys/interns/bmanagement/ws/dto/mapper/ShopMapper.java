package bg.infosys.interns.bmanagement.ws.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Shop;
import bg.infosys.interns.bmanagement.ws.dto.ShopDTO;

@Service
public class ShopMapper implements IModelMapper<ShopDTO, Shop>{
	@Autowired
	private AddressMapper addressMapper;

	@Override
	public ShopDTO toDto(Shop entity) {
		if (entity == null) return null;
		return new ShopDTO(entity.getId(),entity.getName()
				,addressMapper.toDto(entity.getAddress())
				,entity.getPhoneNumber()
				,entity.getStatus()
				,entity.getIsDeleted());
	}

	@Override
	public Shop toEntity(ShopDTO dto) {
		if (dto == null) return null;
		return new Shop(dto.getId(),dto.getName()
				,addressMapper.toEntity(dto.getAddress())
				,dto.getPhoneNumber()
				,dto.getStatus()
				,dto.getIsDeleted());
	}

}
