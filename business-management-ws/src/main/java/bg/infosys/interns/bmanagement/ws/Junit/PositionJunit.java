package bg.infosys.interns.bmanagement.ws.Junit;

import java.util.Random;

import bg.infosys.interns.bmanagement.ws.dto.PositionDTO;

public class PositionJunit {
	
	private static Random testPosition = new Random(); 

	private PositionJunit() {
		throw new IllegalStateException("Junit");
	}
	
	public static PositionDTO generatePosition() {
		PositionDTO position = new PositionDTO();
		//position.setCode(generateCode());
		//position.setName(generateName());
		
		return position;
	}
	
	private static String generateCode() {
		return PositionCodeNames.positionCode[testPosition.nextInt(PositionCodeNames.positionCode.length)];
		
	} 
	
	private static String generateName() {
		return PositionCodeNames.positionName[testPosition.nextInt(PositionCodeNames.positionName.length)];
	}
	
}
