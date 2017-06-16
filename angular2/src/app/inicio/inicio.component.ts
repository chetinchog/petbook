import { Component, OnInit } from '@angular/core';
import { InicioService } from './inicio.service';
import { Estado } from '../classes/estado.class';
import { UsuarioService } from '../usuario/usuario.service';
import { Usuario } from '../classes/usuario.class';
import { Router } from '@angular/router';
import { MascotaService } from '../mascota/mascota.service';
import { Mascota } from '../classes/mascota.class';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  logueado: Usuario;
  errorMessage: string;

  constructor(
    private inicioService: InicioService,
    private usuarioService: UsuarioService,
    private router: Router,
    private mascotasService: MascotaService
  ) { }

  estados: Estado[];
  mascota: string;
  text: string;
  mascotas: Mascota[];

  ngOnInit() {
    this.getEstados();
    this.text = undefined;
    this.usuarioService.getPrincipal()
      .then(usuario => this.logueado = usuario)
      .catch(error => this.errorMessage = <any>error);
    this.mascotasService.buscarMascotas()
        .then(mascotas => {
          this.mascotas = mascotas;
          this.mascota = mascotas[0].nombre;
        })
        .catch(error => this.errorMessage = <any>error );
  }

  post() {
    if (this.text != undefined) {
      let estado: Estado = {
        fecha: Date.now(),
        mascota: this.mascota,
        texto: this.text,
        usuario: this.logueado.nombre
      };
      this.newEstado(estado);
      setTimeout(() => this.getEstados(), 500);
      this.text = undefined;
    }
  }

  agregarMascota() {
    this.router.navigate(['/mascotas']);
  }

  getEstados() {
    this.inicioService.getEstados()
      .then(estados => this.estados = estados)
      .catch(error => this.errorMessage = error);
  }

  newEstado(estado: Estado) {
    this.inicioService.newEstado(estado);
  }
}