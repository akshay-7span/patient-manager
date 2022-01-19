package com.sevenspan.patient.entitydtomapper;

import org.modelmapper.ModelMapper;

public class EntityDTOMapper {

    public static <T> T convert(Object source,Class<T> destination){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(source,destination);
    }

//    public static <T> T convertWithSkip(Object source,Class<T> destination){
//        ModelMapper modelMapper=new ModelMapper();
//        return modelMapper.typeMap(source.getClass(),destination).addMappings(mapping -> mapping.skip());
//    }
}
