package one.digitalinovation.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinovation.domain.Cliente;
import one.digitalinovation.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@RestController
	@RequestMapping("/clientes")
	public class ClientesController {
		@Autowired
		private ClienteService cs;

		@GetMapping()
		public ResponseEntity<Iterable<Cliente>> get() {
			return ResponseEntity.ok(cs.getClientes());
		}

		@GetMapping("/{id}")
		public ResponseEntity get(@PathVariable("id") Long id) {
			Optional<Cliente> cliente = cs.getClientesById(id);

			if (cliente.isPresent()) {
				return ResponseEntity.ok(cs.getClientesById(id));
			} else {
				return ResponseEntity.notFound().build();
			}

		}

		@PostMapping()
		@ResponseStatus(HttpStatus.CREATED)
		public void post(@RequestBody Cliente cliente) {
			Cliente c = cs.insert(cliente);
		}

		@PutMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public String put(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
			Cliente c = cs.update(cliente, id);
			return "Alterado: " + c.getId();
		}

		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public String delete(@PathVariable("id") Long id) {
			cs.delete(id);
			return "Deletado";
		}
	}
}
