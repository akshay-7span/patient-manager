package com.sevenspan.patient.mapper;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class Mapper {

    static ModelMapper modelMapper=new ModelMapper();

    public static <T> T convert(Object source,Class<T> destination){
        return modelMapper.map(source,destination);
    }

    public static <T> T convertWithCondition(Object source, Class<T> destination, PropertyMap propertyMap){
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.addMappings(propertyMap);
        return modelMapper.map(source,destination);
    }

    //For convert list of source into list of destination
    /*----------------------------------------------------*/
    public static <D, T> D map(final T source, Class<D> destination) {
        return modelMapper.map(source, destination);
    }

    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }
    /*----------------------------------------------------*/

}
