package br.com.ferramentas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ferramentas.dao.FornecedorDao;
import br.com.ferramentas.domain.Fornecedor;

@FacesConverter("fornecedorConverter")
public class FornecedorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componente, String valor) {
		System.out.println("getAsObject: " + valor);

		try {
			Long codigo = Long.parseLong(valor);
			FornecedorDao fornecedorDao = new FornecedorDao();
			Fornecedor fornecedor = fornecedorDao.bucarPorCodigo(codigo);
			return fornecedor;

		} catch (RuntimeException ex) {
			return null;
		}

	
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, Object objeto) {
		System.out.println("getAsString: " + objeto);
		
		try {
			Fornecedor fornecedor = (Fornecedor) objeto;
			String codigo = fornecedor.getCodigo().toString();
			return codigo;
			
		} catch (RuntimeException ex) {
			return null;
		}
		
		
	}

}
