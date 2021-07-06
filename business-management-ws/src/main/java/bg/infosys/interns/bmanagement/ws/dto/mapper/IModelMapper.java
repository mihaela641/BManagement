package bg.infosys.interns.bmanagement.ws.dto.mapper;

public interface IModelMapper<T,U>{
	T toDto(U entity);
	U toEntity(T dto);
}
