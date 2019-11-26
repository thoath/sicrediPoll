package br.com.sicredi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.sicredi.controller.AssociadoController;
import br.com.sicredi.dto.AssociadoDto;
import br.com.sicredi.models.Associado;
import br.com.sicredi.models.Response;
import br.com.sicredi.repository.AssociadoRepository;
import br.com.sicredi.service.AssociadoService;
import br.com.sicredi.service.AssociadoServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AssociadoControllerTest {

	@InjectMocks
	private AssociadoController associadoController;
	
	@Mock
	private BindingResult mockBindingResult;
	
    @TestConfiguration
    static class AssociadoControllerTestContextConfiguration {
  
        @Bean
        public AssociadoService associadoService() {
            return new AssociadoServiceImpl();
        }
    }
	
    @Mock	
	private AssociadoServiceImpl associadoService;
	
	@Mock
	private AssociadoRepository associadoRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Before
	public void setupTest() {
	    MockitoAnnotations.initMocks(this);
	    Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);
	}
	
	@Test
	public void testStoreAssociado() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        Associado associado = new Associado();
        associado.setId(new Long(999));
        associado.setCpf("36653064000");
        associado.setName("Lucas Silva");
        
        AssociadoDto associadoDto = new AssociadoDto();
        associado.setId(new Long(999));
        associado.setCpf("36653064000");
        associado.setName("Lucas Silva");
        
        
        when(associadoService.store(associado)).thenReturn(associadoDto);
        
        ResponseEntity<Response<AssociadoDto>> response = associadoController.store(associado, mockBindingResult);
        
        assertThat(response.getStatusCodeValue()).isEqualTo(200);	
        assertThat(response.getBody().getError()).isNull();
		
	}
	
	@Test
	public void testStoreAssociadoCPFError() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        Associado associado = new Associado();
        associado.setId(new Long(1));
        associado.setCpf("03387621088");
        associado.setName("Lucas Silva");
        
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);
        
        ResponseEntity<Response<AssociadoDto>> response = associadoController.store(associado, mockBindingResult);
	    
        assertThat(response.getStatusCodeValue()).isEqualTo(200);	
        assertThat(response.getBody().getError()).isNotNull();
		
	}
	
}
