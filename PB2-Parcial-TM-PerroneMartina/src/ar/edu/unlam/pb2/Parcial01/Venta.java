package ar.edu.unlam.pb2.Parcial01;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Venta {

	private String codigo;
	private Cliente cliente;
	private Vendedor vendedor;
	private Map<Vendible, Integer> renglones;

	public Venta(String codigo, Cliente cliente, Vendedor vendedor) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.vendedor = vendedor;
		renglones = new HashMap<>();
	
	}
	

	public void agregarRenglon(Vendible vendible, Integer cantidad) {
		renglones.put(vendible, cantidad);
		// TODO: Agregar el vendible a la venta 
	}
	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Vendedor getVendedor() {
		return vendedor;
	}


	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}


	public Map<Vendible, Integer> getRenglones() {
		return renglones;
	}


	public void setRenglones(Map<Vendible, Integer> renglones) {
		this.renglones = renglones;
	}


	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Venta other = (Venta) obj;
		return Objects.equals(codigo, other.codigo);
	}


	@Override
	public String toString() {

		return "Venta: " + codigo + "\n" + "Cliente" + cliente + "\n" + "Vendedor" + vendedor + "\n";
	}

}
