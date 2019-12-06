package br.com.sicredi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.dto.AssociadoDto;
import br.com.sicredi.models.Associado;
import br.com.sicredi.repository.AssociadoRepository;

@Service
public class AssociadoServiceImpl implements AssociadoService{

	@Autowired
	private AssociadoRepository associadoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AssociadoDto store(Associado associado) {
		return modelMapper.map(associadoRepository.save(associado), AssociadoDto.class);
	}

}
