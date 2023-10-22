package one.digitalinovation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import one.digitalinovation.domain.Cliente;
import one.digitalinovation.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository rep;
	
	public Iterable<Cliente> getClientes() {
		return rep.findAll();
	}
	
	public Optional<Cliente> getClientesById(Long id) {
		return rep.findById(id);
	}
	
	public Cliente insert(Cliente cliente) {
		Assert.notNull(cliente.getId(), "id null");
		return rep.save(cliente);
	}
	
	public Cliente update(Cliente cliente, Long id) {
		Assert.notNull(cliente.getId(), "id null");
		Optional<Cliente> optional = getClientesById(id);
		if(optional.isPresent()) {
			Cliente db = optional.get();
			db.setNome(cliente.getNome());
			db.setEndereco(cliente.getEndereco());
			
			return rep.save(db);
		}else {
			throw new RuntimeException("Falha ao atualizar");
		}
	}
	
	public void delete(Long id) {
		Optional<Cliente> cliente = getClientesById(id); 
		if(cliente.isPresent()) {
			rep.deleteById(id);
		}else {
			throw new RuntimeException("Falha ao excluir");
		}
	}

}
