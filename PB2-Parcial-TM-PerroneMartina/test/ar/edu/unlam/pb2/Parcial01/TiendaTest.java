package ar.edu.unlam.pb2.Parcial01;

import org.junit.Test;

public class TiendaTest {

	

	@Test(expected = VendedorDeLicenciaExceptionException.class)
	public void queAlIntentarAgregarUnaVentaParaUnVendedorDeLicenciaSeLanceUnaVendedorDeLicenciaException() throws VendedorDeLicenciaExceptionException {
		Vendedor vendedor = new Vendedor("46119380", "Martu");
		Cliente cliente = new Cliente("27615846", "Monotributista");
		Venta venta = new Venta("1", cliente, vendedor);
		Tienda tienda = new Tienda("5156165", "Maxikiosko");
		
		vendedor.setDeLicencia(true);
		tienda.agregarCliente(cliente);
		tienda.agregarVendedor(vendedor);
		tienda.agregarVenta(venta);
		
		
	}
//
//	@Test
//	public void queAlIntentarAgregarUnVendibleInexistenteAUnaVentaSeLanceUnaVendibleInexistenteException() {
//	}
//
//	@Test
//	public void queSePuedaObtenerUnaListaDeProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
//	}
//
//	@Test
//	public void queSePuedaObtenerUnSetDeClientesOrdenadosPorRazonSocialDescendente() {
//	}
//
//	@Test
//	public void queSePuedaObtenerUnMapaDeVentasRealizadasPorCadaVendedor() {
//		// TODO: usar como key el vendedor y Set<Venta> para las ventas
//	}
//
//	@Test
//	public void queSePuedaObtenerElTotalDeVentasDeServicios() {
//	}
//
//	@Test
//	public void queAlRealizarLaVentaDeUnProductoElStockSeActualiceCorrectamente() {
//	}
}
