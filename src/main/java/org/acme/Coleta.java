package org.acme;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Coleta extends PanacheEntity{
    
	public String codigoColeta;

	public LocalTime horaInicio;

	public String diasSemana;

	public String tipoColeta;

	public int rota;

	@CreationTimestamp
	public Date dataCriacao;

	@UpdateTimestamp
	public Date dataAtualizacao;
}
