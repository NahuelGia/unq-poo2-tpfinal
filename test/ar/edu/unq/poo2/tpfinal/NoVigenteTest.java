package ar.edu.unq.poo2.tpfinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NoVigenteTest {

	NoVigente estadoNoVigente;
	AppSEM mockApp;

	@BeforeEach
	void setUp() throws Exception {

		estadoNoVigente = new NoVigente();
		mockApp = Mockito.mock(AppSEM.class);
	}

	@Test
	void test1_CuandoEsEstadoNoVigenteIniciarInteractuaConLaApp() {
		
		
	}

}
