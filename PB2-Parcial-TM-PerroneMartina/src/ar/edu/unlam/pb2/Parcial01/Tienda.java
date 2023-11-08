package ar.edu.unlam.pb2.Parcial01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tienda {

	/**
	 * En esta ocasion deberemos resolver un producto software que nos permita
	 * administrar la venta de productos o servicios de nuestra tienda. Venderemos
	 * entonces, productos como mouse o teclados y servicios como el soporte tecnico
	 * a domicilio. Sabemos que la tienda cuenta con items Vendibles que pueden ser
	 * del tipo Producto o Servicio. Ademas, podemos registrar el stock de los
	 * productos, los clientes a quienes les vendemos algun producto o servicio, las
	 * ventas y los vendedores de la tienda. Antes de realizar alguna operacion, se
	 * debera obtener el elemento correspondiente de las colecciones. Ejemplo: Si
	 * quisiera realizar alguna operacion con un cliente, el mismo debe obtenerse de
	 * la coleccion de clientes.
	 * 
	 * Cada Venta contiene renglones los cuales representa a los productos o
	 * servicios que se incluyen en la misma. Tambien cuenta con el Cliente y
	 * Vendedor que participan en la Venta. Cuando agregamos un vendible a una
	 * venta, lo haremos con 1 unidad. En una version posterior, admitiremos
	 * cantidades variables.
	 * 
	 * Cada Item debe compararse por nombre y precio, en caso de ser necesario.
	 * Recordar que los items deben ser Vendibles.
	 * 
	 */

	private String cuit;
	private String nombre;
	private Set<Vendible> vendibles;
	private Map<Producto, Integer> stock;
	private List<Cliente> clientes;
	private Set<Venta> ventas;
	private Set<Vendedor> vendedores;

	public Tienda(String cuit, String nombre) {
		this.cuit = cuit;
		this.nombre = nombre;
		clientes = new ArrayList<>();
		vendedores = new HashSet<>();
		ventas = new HashSet<>();
		vendibles = new HashSet<>();
		stock = new HashMap<>();
	}

	// TODO: Completar con los getters y setters necesarios

	public Vendible getVendible(Integer codigo) {

		for (Vendible vendible : vendibles) {
			if (vendible.getCodigo().equals(codigo)) {
				return vendible;
			}
		}
		// TODO: Obtiene un producto o servicio de la coleccion de vendibles utilizando
		// el codigo. En caso de no existir devuelve null.
		return null;
	}

	public void agregarProducto(Producto producto) {
		this.agregarProducto(producto, 0);
	}

	public void agregarProducto(Producto producto, Integer stockInicial) {
		vendibles.add(producto);
		// TODO: Agrega un producto a la coleccion de vendibles y pone en la coleccion
		// de stocks al producto con su stock inicial
	}

	public void agregarServicio(Servicio servicio) {
		vendibles.add(servicio);
	}

	public Integer getStock(Producto producto) {
		return stock.get(producto);
	}

	public void agregarStock(Producto producto, Integer incremento) throws VendibleInexistenteException {
		Producto productoExistente = (Producto) this.getVendible(producto.getCodigo());
		if (productoExistente != null) {
			stock.put(producto, incremento);
		}

		// TODO: se debe agregar stock a un producto existente
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void agregarVendedor(Vendedor vendedor) {
		vendedores.add(vendedor);
	}

	public void agregarVenta(Venta venta) throws VendedorDeLicenciaExceptionException {
		Vendedor vendedor = buscarVendedorPordni(venta.getVendedor().getDni());
		Cliente cliente = buscarClientePorCuit(venta.getCliente().getCuit());

		if (vendedor != null && cliente != null) {
			if (!vendedor.isDeLicencia()) {
				ventas.add(venta);
			} else {
				throw new VendedorDeLicenciaExceptionException();
			}
		}

		// TODO: Agrega una venta a la coleccion correspondiente. En caso de que el
		// vendedor este de licencia, arroja una
		// VendedorDeLicenciaException
	}

	private Cliente buscarClientePorCuit(String cuit) {
		for (Cliente cliente : clientes) {
			if (cliente.getCuit().equals(cuit)) {
				return cliente;
			}
		}
		return null;
	}

	private Vendedor buscarVendedorPordni(String dni) {
		for (Vendedor vendedor : vendedores) {
			if (vendedor.getDni().equals(dni)) {
				return vendedor;
			}
		}
		return null;
	}

	public Producto obtenerProductoPorCodigo(Integer codigo) {
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();

			if (key.getCodigo().equals(codigo)) {
				return key;
			}

		}
		// TODO: Obtiene un producto de los posibles por su codigo. En caso de no
		// encontrarlo se debera devolver null
		return null;
	}

	public void agregarProductoAVenta(String codigoVenta, Producto producto) throws VendibleInexistenteException {
		Producto encontrado = this.obtenerProductoPorCodigo(producto.getCodigo());
		Venta ventaEncontrada = buscarVenta(codigoVenta);
		Producto copia = new Producto(producto.getCodigo(), producto.getNombre(), producto.getPrecio(),
				producto.getPuntoDeReposicion());

		if (encontrado == null) {
			throw new VendibleInexistenteException();
		} else {
			ventaEncontrada.agregarRenglon(copia, 1);
		}

		// TODO: Agrega un producto a una venta. Si el vendible no existe (utilizando su
		// codigo), se debe lanzar una VendibleInexistenteException
		// Se debe actualizar el stock en la tienda del producto que se agrega a la
		// venta
	}

	private Venta buscarVenta(String codigoVenta) {
		for (Venta venta : ventas) {
			if (venta.getCodigo().equals(codigoVenta)) {
				return venta;
			}
		}
		return null;
	}

	public void agregarServicioAVenta(String codigoVenta, Servicio servicio) throws VendibleInexistenteException {
		Servicio encontrado = (Servicio) this.getVendible(servicio.getCodigo());
		Venta ventaEncontrada = buscarVenta(codigoVenta);
		Servicio copia = new Servicio(servicio.getCodigo(), servicio.getNombre(), servicio.getPrecio(),
				servicio.getFechaDeInicio(), servicio.getFechaDeFinalizacion());

		if (encontrado == null) {
			throw new VendibleInexistenteException();
		} else {
			ventaEncontrada.agregarRenglon(copia, 1);
		}
		// TODO: Agrega un servicio a la venta. Recordar que los productos y servicios
		// se traducen en renglones
	}

	public List<Producto> obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {

		ArrayList<Producto> listaDeProductos = new ArrayList<>();

		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();

			if (val <= key.getPuntoDeReposicion()) {
				listaDeProductos.add(key);
			}

		}

		// TODO: Obtiene una lista de productos cuyo stock es menor o igual al punto de
		// reposicion. El punto de reposicion, es un valor que
		// definimos de manera estrategica para que nos indique cuando debemos reponer
		// stock para no quedarnos sin productos
		return listaDeProductos;
	}

	public List<Cliente> obtenerClientesOrdenadosPorRazonSocialDescendente() {
		// TODO: obtiene una lista de clientes ordenados por su razon social de manera
		// descendente

		Collections.sort(clientes, new ClientesOrdenadoPorRazonSocial());

		return clientes;
	}

	public Map<Vendedor, Set<Venta>> obtenerVentasPorVendedor() {

		Map<Vendedor, Set<Venta>> reporte = new HashMap<>();

		for (Venta venta : ventas) {
			Vendedor vendedor = venta.getVendedor();

			if (!reporte.containsKey(vendedor)) {
				reporte.put(vendedor, new HashSet<Venta>());

			} else {
				reporte.get(vendedor).add(venta);

			}
		}
		return reporte;
		// TODO: Obtiene un mapa que contiene las ventas realizadas por cada vendedor.

	}

	public Double obtenerTotalDeVentasDeServicios() {
		Double valorTotal = 0.0;
		for (Vendible vendible : vendibles) {
			if (vendible instanceof Servicio) {
				valorTotal += vendible.getPrecio();
			}
		}
		// TODO: obtiene el total acumulado de los vendibles que son servicios incluidos
		// en todas las ventas.
		// Si una venta incluye productos y servicios, solo nos interesa saber el total
		// de los servicios
		return valorTotal;
	}
}
