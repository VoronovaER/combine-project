package ru.plants.care.back.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.plants.care.back.dto.planttype.PlantTypeDTO;
import ru.plants.care.back.dto.planttype.PlantTypeListRecordDTO;
import ru.plants.care.back.repository.model.PlantTypeEntity;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring"
)
public interface PlantTypeMapper {
    List<PlantTypeListRecordDTO> plantTypeEntityToPlantTypeListRecordDTO(List<PlantTypeEntity> plantTypeList);
    PlantTypeEntity PlantTypeDTOToPlantTypeEntity(PlantTypeDTO value);
    PlantTypeDTO plantTypeEntityToPlantTypeDTO(PlantTypeEntity value);
}
