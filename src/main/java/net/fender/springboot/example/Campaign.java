package net.fender.springboot.example;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "campaigns")
@JsonInclude(NON_EMPTY)
/*
 * CREATE TABLE campaigns ( id bigserial NOT NULL, description text, created
 * timestamp without time zone, updated timestamp without time zone, version
 * integer, CONSTRAINT campaigns_pkey PRIMARY KEY (id)); ALTER TABLE campaigns
 * OWNER TO pwruser; GRANT ALL ON TABLE campaigns TO pwruser; GRANT SELECT,
 * UPDATE, INSERT, DELETE ON TABLE campaigns TO appuser;
 */
public class Campaign {

	@Id
	// serialize as String because of JavaScript's 52-bit mantissa
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	private String description;
	@CreatedDate
	private ZonedDateTime created;
	@LastModifiedDate
	private ZonedDateTime updated;
	@Version
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getCreated() {
		return created;
	}

	public void setCreated(ZonedDateTime created) {
		this.created = created;
	}

	public ZonedDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(ZonedDateTime updated) {
		this.updated = updated;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Campaign other = (Campaign) obj;
		return Objects.equals(id, other.id);
	}
}
