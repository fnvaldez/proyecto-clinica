package ar.edu.unju.fi.poo.clinica.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class Mapper {

private ModelMapper mapper = new ModelMapper();
	
	public <D, T> D map(final T entity, Class<D> targetClass) {
		return mapper.map(entity, targetClass);
	}

	public <D, T> List<D> mapAll(final Collection<T> sourceList, Class<D> targetClass) {
		return sourceList.stream().map(entity -> map(entity, targetClass)).collect(Collectors.toList());
	}
}
