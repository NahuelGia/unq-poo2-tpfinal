package ar.edu.unq.poo2.tpfinal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppInspectorTest {
	private ZonaEstacionamiento estacionamiento;
	private SEM sem;
	private AppInspector inspector;
	
	@BeforeEach
	public void setUp() {
		this.sem = mock(SEM.class);
		this.estacionamiento= mock(ZonaEstacionamiento.class);
		this.inspector = new AppInspector(sem,estacionamiento);//SUT
		
		
	}
	 
	@Test
	void test000UnInspectorSabeQuienEsSuSem() {

		assertEquals((this.sem),((this.inspector).getSem()));
	}
	
	@Test
	void test005UnInspectorSabeAqueZonaestaAsignado() {
		assertEquals(this.estacionamiento,this.inspector.getZona());
	}
	@Test
	void test010elInspectorConsultaAlSemPorUnaPatenteYEsteDiceEstaVigente() {
		when(this.inspector.getSem().consultarVigencia("patente")).thenReturn(true);
		assertTrue(this.inspector.getSem().consultarVigencia("patente"));
	}
	@Test
	void test015elInspectorConsultaAlSemPorUnaPatenteYEsteDiceNOEstaVigente() {
		when(this.inspector.getSem().consultarVigencia("patente")).thenReturn(false);
		assertFalse((this.inspector).consultarVigencia("patente"));
	}
	@Test
	
	void test020elsistemaDiceQueLaPatenteDadaNoEstaVigenteYElInspectorMandaUnaInfraccion() {
		when(this.inspector.getSem().consultarVigencia("patente")).thenReturn(false);
		this.inspector.consultarVigencia("patente");
		verify(this.inspector.getSem(),atLeast(1)).consultarVigencia("patente");
	}
	@Test 
	void test025elsistemaDiceQueLaPatenteDadaEstaVigenteYElInspectorNoMandaUnaInfraccion() {
		when(this.inspector.getSem().consultarVigencia("patente")).thenReturn(true);
		this.inspector.consultarVigencia("patente");
		verify(this.inspector.getSem(),atLeast(0)).consultarVigencia("patente");
	}
	@Test
	void test030laAppInspectorPuedeCambiarDeSem() {
		SEM sem2=mock(SEM.class);
		this.inspector.setSem(sem2);
		assertEquals(sem2,((this.inspector).getSem()));
	}
}
