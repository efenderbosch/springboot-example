package net.fender.springboot.example;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class CampaignController {

	private static final String JSON = "application/json";

	@Autowired
	private CampaignRepository repo;
	@Autowired
	private PagedResourcesAssembler<Campaign> assembler;

	@RequestMapping(path = "/campaigns", method = GET, produces = JSON)
	public ResponseEntity<?> findAll(Pageable pageable) {
		Page<Campaign> campaigns = repo.findAll(pageable);
		return ResponseEntity.ok(assembler.toResource(campaigns));
	}

	@RequestMapping(path = "/campaigns/{id}", method = POST, produces = JSON, consumes = JSON)
	public ResponseEntity<?> create(@PathVariable Long id, @RequestBody Campaign campaign) {
		Campaign saved = repo.save(campaign);
		return ok(saved);
	}

	@RequestMapping(path = "/campaigns/{id}", method = GET, produces = JSON)
	public ResponseEntity<?> read(@PathVariable Long id) {
		Campaign campaign = repo.findOne(id);
		return campaign == null ? notFound().build() : ok(campaign);
	}

	@RequestMapping(path = "/campaigns/{id}", method = PUT, produces = JSON, consumes = JSON)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Campaign campaign) {
		if (!Objects.equals(id, campaign.getId())) {
			return badRequest().build();
		}
		if (!repo.exists(id)) {
			return notFound().build();
		}
		// TODO optimistic concurrency
		Campaign updated = repo.save(campaign);
		return ok(updated);
	}

	@RequestMapping(path = "/campaigns/{id}", method = DELETE, produces = JSON)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!repo.exists(id)) {
			return notFound().build();
		}
		repo.delete(id);
		return noContent().build();
	}
}
