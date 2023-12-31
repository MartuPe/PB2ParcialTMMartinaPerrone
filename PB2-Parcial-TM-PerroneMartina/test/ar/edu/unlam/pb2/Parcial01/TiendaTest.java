package ar.edu.unlam.pb2.Parcial01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

	@Test (expected = VendibleInexistenteException.class)
	public void queAlIntentarAgregarUnVendibleInexistenteAUnaVentaSeLanceUnaVendibleInexistenteException() throws VendedorDeLicenciaExceptionException, VendibleInexistenteException {
		Tienda tienda = new Tienda("5156165", "Maxikiosko");
		Vendedor vendedor = new Vendedor("46119380", "Martu");
		Cliente cliente = new Cliente("27615846", "Monotributista");
		Venta venta = new Venta("1", cliente, vendedor);
		Producto producto = new Producto(1, "Manzana", 20.0, 6);

		tienda.agregarStock(producto, 1);
		tienda.agregarCliente(cliente);
		tienda.agregarVendedor(vendedor);
		tienda.agregarVenta(venta);
		tienda.agregarProductoAVenta(venta.getCodigo(), producto);
	
	}

	@Test
	public void queSePuedaObtenerUnaListaDeProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() throws VendibleInexistenteException {
		Tienda tienda = new Tienda("5156165", "Maxikiosko");
		Producto producto = new Producto(1, "Manzana", 20.0, 6);
		Producto producto2 = new Producto(2, "Pera", 20.0, 6);
		Producto producto3 = new Producto(3, "Naranja", 20.0, 6);
		Producto producto4 = new Producto(4, "Sandia", 20.0, 6);
		
		tienda.agregarProducto(producto);
		tienda.agregarProducto(producto2);
		tienda.agregarProducto(producto3);
		tienda.agregarProducto(producto4);
		
		tienda.agregarStock(producto, 9);
		tienda.agregarStock(producto2, 4);
		tienda.agregarStock(producto3, 2);
		tienda.agregarStock(producto4, 15);
		
		ArrayList<Producto> listaDeProductos = (ArrayList<Producto>) tienda.obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion();
		
		assertTrue(listaDeProductos.contains(producto2));
		assertTrue(listaDeProductos.contains(producto3));
		
	}

	@Test
	public void queSePuedaObtenerUnListDeClientesOrdenadosPorRazonSocialDescendente() {
		Tienda tienda = new Tienda("5156165", "Maxikiosko");
		Cliente cliente = new Cliente("27615846", "m");
		Cliente cliente2 = new Cliente("27615846", "a");
		Cliente cliente3 = new Cliente("27615846", "b");
		Cliente cliente4 = new Cliente("27615846", "e");
		Cliente cliente5 = new Cliente("27615846", "c");
		
		tienda.agregarCliente(cliente);
		tienda.agregarCliente(cliente2);
		tienda.agregarCliente(cliente3);
		tienda.agregarCliente(cliente4);
		tienda.agregarCliente(cliente5);
		
		ArrayList<Cliente> clientesOrdenados =  (ArrayList<Cliente>) tienda.obtenerClientesOrdenadosPorRazonSocialDescendente();
		
		assertEquals(clientesOrdenados.get(0), cliente);
		assertEquals(clientesOrdenados.get(1), cliente4);
		assertEquals(clientesOrdenados.get(2), cliente5);
		assertEquals(clientesOrdenados.get(3), cliente3);
		assertEquals(clientesOrdenados.get(4), cliente2);
		
		
	}

	@Test
	public void queSePuedaObtenerUnMapaDeVentasRealizadasPorCadaVendedor() throws VendedorDeLicenciaExceptionException {
		
		Tienda tienda = new Tienda("5156165", "Maxikiosko");
		Vendedor vendedor = new Vendedor("46119380", "Martu");
		Vendedor vendedor2 = new Vendedor("566545", "Martu");
		Cliente cliente = new Cliente("27615846", "Monotributista");
		Venta venta = new Venta("1", cliente, vendedor);
		Venta venta2 = new Venta("2", cliente, vendedor);
		Venta venta3 = new Venta("3", cliente, vendedor2);
		Venta venta4 = new Venta("4", cliente, vendedor2);

		tienda.agregarCliente(cliente);
		tienda.agregarVendedor(vendedor);
		tienda.agregarVendedor(vendedor2);
		tienda.agregarVenta(venta);
		tienda.agregarVenta(venta2);
		tienda.agregarVenta(venta3);
		tienda.agregarVenta(venta4);

		
	    Map<Vendedor, Set<Venta>> reporte = tienda.obtenerVentasPorVendedor();

	    assertEquals(2, reporte.get(vendedor2).size());
	
		// TODO: usar como key el vendedor y Set<Venta> para las ventas
	}

	@Test
	public void queSePuedaObtenerElTotalDeVentasDeServicios() {
		Tienda tienda = new Tienda("5156165", "Maxikiosko");
		Servicio servicio = new Servicio(1, "Manzana", 20.0, "22/03/2023", "24/08/2004");
		Servicio servicio2 = new Servicio(1, "Kiwi", 80.0, "22/03/2023", "24/08/2004");
		Servicio servicio3 = new Servicio(1, "Pera", 40.0, "22/03/2023", "24/08/2004");
		Producto producto3 = new Producto(3, "Naranja", 20.0, 6);
		Producto producto4 = new Producto(4, "Sandia", 20.0, 6);
		
		tienda.agregarProducto(producto3);
		tienda.agregarProducto(producto4);
		tienda.agregarServicio(servicio);
		tienda.agregarServicio(servicio2);
		tienda.agregarServicio(servicio3);
		
		Double ve = 140.0;
		Double vo = tienda.obtenerTotalDeVentasDeServicios();
		
		assertEquals(ve, vo);
		
		
	}

//	@Test
//	public void queAlRealizarLaVentaDeUnProductoElStockSeActualiceCorrectamente() {
//		
//		
//	}
}
